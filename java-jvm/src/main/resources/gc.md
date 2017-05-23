上周有幸给部门的小伙伴分享了一些JVM相关的知识，在整个做PPT的过程中，也是对一个领域的碎片知识的整理，本文将针对虚拟机GC相关的一些内容进行整理，本文不会涉及到G1收集器。

在Hotspot VM实现中，主要有两大类GC

### Partial GC：并不会堆整个GC堆进行收集
- young gc：只收集 young gen 的GC
- old gc：只收集 old gen 的GC，只有CMS的 concurrent collection
- mixed GC：收集整个 young gen 以及部分 old gen 的GC，只有G1
### Full GC：收集整个堆，包括young gen、old gen、perm gen（如果存在的话）等
其实在各种文章或书上还可以看到Minor GC、Major GC的字眼，

其中minor GC和young gc对应，

而Major GC通常是和Full GC是等价的，

由于HotSpot VM发展了这么多年，外界对各种名词的解读已经完全混乱了，

所以Major GC有时也可能是指old gc，在下定论之前一定要先问清楚。

## 单线程、并行、并发

在GC收集器实现中，分为了单线程、并行和并发。

### 单线程收集器：如 Serial GC，这个比较好理解，即垃圾收集过程中只有单一线程在进行收集工作，实现也最简单。

### 并行收集器：如Parallel GC，每次运行时，不管是YGC，还是FGC，会 stop-the-world，暂停所有的用户线程，并采用多个线程同时进行垃圾收集。

### 并发收集器：如CMS GC，在新生代进行垃圾收集时和并行收集器类似，都是并行收集（当然具体算法中，你也可以设置成采用单线程进行收集），而且都会stop-the-world，主要的区别在于老年代的收集上，CMS在老年代进行垃圾收集时，大部分时间可以和用户线程并发执行的，只有小部分的时间stop-the-world，这就是它的优势，可以大大降低应用的暂停时间，当然也是有劣势的。

## 算法组合

Hotspot VM实现的几种GC算法组合中，其中CMS GC使用最广，因为现在都是大内存时代。

### 1、Serial GC

Serial Young GC ＋ Serial Old GC，是全局范围的Full GC，这种算法组合是最早出现的，当年的Java堆内存大小都还不大，使用Serial GC进行单线程收集，还感觉不出来GC耗时导致应用暂停的问题

### 2、Parallel GC

Parallel Young GC ＋ 非并行的PS MarkSweep GC 或 并行的Parallel Old GC，也是全局范围的Full GC，选PS MarkSweep GC 还是 Parallel Old GC 由参数-XX:+UseParallelOldGC进行控制。当Java堆慢慢变大时，发现已经无法忍受GC耗时带来的应用暂停了，出现了Parallel GC，采用多线程的方式进行垃圾收集，很明显可以提升垃圾收集效率。

### 2、CMS GC

ParNewGC + ConcMarkSweepGC + Mark-Sweep-Compact GC
当Java堆达到更大时，比如8G，使用Parallel GC带来的应用暂停已经很明显了，所有又出现了 CMS GC，这是目前我看到线上环境使用的比较多的GC策略，在参数中添加-XX:+UseConcMarkSweepGC，对于 young gen，会自动选用 ParNewGC，不需要额外添加 -XX:+UseParNewGC。

CMS虽然好，因为它的特殊算法，大部分的收集过程可以和用户线程并发执行，大大降低应用的暂停时间，不过也会带来负面影响，在收集完 old gen 之后，CMS并不会做整理过程，会产生空间碎片，如果这些碎片空间得不到利用，就会造成空间的浪费，整个过程中可能发生 concurrent mode failure，导致一次真正意义的 full gc，采用单线程对整个堆（young+old+perm） 使用MSC（Mark-Sweep-Compact）进行收集，这个过程意味着很慢很慢很慢，而且这个碎片问题是无法预测的

触发条件

### young gc

对于 young gc，触发条件似乎要简单很多，当 eden 区的内存不够时，就会触发young gc，我们看看在 eden 区给对象分配一块内存是怎样一个过程，画了一个简单的流程图，我一直觉得一个好的示意图可以让一个枯燥的过程变得更有意思。



在 eden 区分配空间内存不足时有两种情况，为对象分配内存、为TLAB分配内存，总之就是内存不够，需要进行一次 young gc 为eden区腾出空间为后续的内存申请做准备，然后由一个用户线程通知VM Thread，接下去要执行一次 young gc。

### full gc

#### 1、old gen 空间不足

当创建一个大对象、大数组时，eden 区不足以分配这么大的空间，会尝试在old gen 中分配，如果这时 old gen 空间也不足时，会触发 full gc，为了避免上述导致的 full gc，

调优时应尽量让对象在 young gc 时就能够被回收，还有不要创建过大的对象和数组。

#### 2、统计得到的 young gc 晋升到 old gen的对象平均总大小大于old gen 的剩余空间

当准备触发一次 young gc时，会判断这次 young gc 是否安全，这里所谓的安全是当前老年代的剩余空间可以容纳之前 young gc 晋升对象的平均大小，或者可以容纳 young gen 的全部对象，如果结果是不安全的，就不会执行这次 young gc，转而执行一次 full gc

#### 3、perm gen 空间不足

如果有perm gen的话，当系统中要加载的类、反射的类和调用的方法较多，而且perm gen没有足够空间时，也会触发一次 full gc

#### 4、ygc出现 promotion failure

promotion failure 发生在 young gc 阶段，即 cms 的 ParNewGC，当对象的gc年龄达到阈值时，或者 eden 的 to 区放不下时，会把该对象复制到 old gen，如果 old gen 空间不足时，会发生 promotion failure，并接下去触发full gc

在GC日志中，有时会看到 concurrent mode failure 关键字，这是因为什么原因导致的问题呢? 对这一块的理解，很多文章都是说因为 concurrent mode failure 导致触发full gc，其实应该反过来，是full gc 导致的 concurrent mode failure，在cms gc的算法实现中，通常说的cms是由一个后台线程定时触发的，默认每2秒检查一次old gen的内存使用率，当 old gen 的内存使用率达到-XX:CMSInitiatingOccupancyFraction设置的值时，会触发一次 cms gc，对 old gen 进行并发收集，而真正的 full gc 是通过 vm thread线程触发的，而且在判断当前ygc会失败的情况下触发full gc，如上一次ygc出现了promotion failure，如果执行 full gc 时，发现后台线程正在执行 cms gc，就会导致 concurrent mode failure。

对于以上这些情况，CMSInitiatingOccupancyFraction参数的设置就显得尤为重要，设置的太大的话，发生CMS时的剩余空间太小，在ygc的时候容易发生promotion failure，导致 concurrent mode failure 发生的概率就增大，如果设置太小的话，会导致 cms gc 的频率会增加，所以需要根据应用的需求对该参数进行调优。

#### 5、执行 System.gc()、jmap -histo:live <pid>、jmap -dump ...