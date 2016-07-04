package com.java.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap.Node;
import java.util.HashMap.TreeNode;

/**
 * Hashmap思考
 * @author lxh
 * 
 * 1.数据结构
 * Hashmap是一个数组和链表的结合体（在数据结构称“链表散列“）
 * 
 * 当我们往hashmap中put元素的时候，先根据key的hash值得到这个元素在数组中的位置（即下标）
 * 然后就可以把这个元素放到对应的位置中了。
 * 如果这个元素所在的位子上已经存放有其他元素了，
 * 那么在同一个位子上的元素将以链表的形式存放，（Java7）新加入的放在链头，最先加入的放在链尾。(Java8)新加入的放在链尾。
 *
 */
public class HashMapTest {

	public static void main(String[] args) {
		Map mp = new HashMap();
		mp.put("h", "dd");
		mp.put("h","cc");
		mp.get("h");
		
		System.out.println(mp.get("h"));
		// TODO Auto-generated method stub
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("Spark", 98);
		map.put("Hadoop", 60);
		map.put("Flink", 90);
		map.put("Flume", 69);
		map.put(null, 0);
		map.put("Strom", 91);
		map.put("Spark Streaming", 88);
		map.put("Hive", 70);
		map.put("Mahout", 78);
		map.put("Mahout", 100);
		System.out.println(map);
		map.put("Hive1", 70);
		map.put("Mahout2", 78);
		map.put("Hive3", 70);
		map.put("Mahout4", 78);
		map.put("Hive5", 70);
		map.put("Mahout6", 78);
		map.put("Hive7", 70);
		map.put("Mahout8", 78);
		
		
		
		int hash = 9;
		int index = 9;
		System.out.println(index % hash);
		System.out.println((index - 1) & hash);

	}
	static final int TREEIFY_THRESHOLD = 8;
	
	/**
	 * 线程不安全
	 * 
	 * 如果需要同步，可以用 Collections的synchronizedMap方法使HashMap具有同步的能力
	 * 或者使用ConcurrentHashMap。 
	 */

	
	
	  /**
     * Implements Map.put and related methods
     *
     * @param hash hash for key 
     * 			key的hash值。
     * 
     * @param key the key 
     * 			key值
     * 
     * @param value the value to put 
     * 			value值
     * 
     * @param onlyIfAbsent if true, don't change existing value 如果设为true 
     * 			不改变已存在的值 。默认使用fasle。进行覆盖更新
     * 
     * @param evict if false, the table is in creation mode. 
     * 			true 驱逐模式。
     * 			false 创建模式。？
     * 			怎么理解？
     * @return previous value, or null if none
     * 
     * putVal(hash(key), key, value, false, true);
     * 
     * transient Node<K,V>[] table;
     * 
     * 碰撞只是hash碰撞。不是key相同。
     * 之前碰撞用链表解决。java8开始用红黑树解决。时间复杂度由O(1)+O(n)编程O(1)+O(logn)
     */
	final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
            boolean evict) {
		 
		Node<K,V>[] tab; //用于缓存 此HashMap的tab,以便后续判断。
		Node<K,V> p; //用于缓存此HashMap里面已存在的一个Node。
		int n, i;
		 
		 /*
		  * 如果hashmap底层的table为空。或者长度为0。
		  * 进行resize：初始化或者加倍长度。然后讲table长度赋值给n。(Initializes or doubles table size)
		  */
		 if ((tab = table) == null || (n = tab.length) == 0)
		     n = (tab = resize()).length;
		 /*
		  * 如果下标为i（i是和n-1和hash相与）的tab[i]为空。
		  * 则在此下标创建一个 新的Node。并保存在tab[i]里。
		  */
		 if ((p = tab[i = (n - 1) & hash]) == null)
		     tab[i] = newNode(hash, key, value, null);
		 else {
			 /*
			  * 若table不为空。且将要放置的结点不为空。
			  * 即 发生碰撞！ 
			  */
		     Node<K,V> e; //用于 缓存 发生碰撞的原Node p
		     K k;//用于缓存发生碰撞的原Node p 的 key
		     /*
		      * p不为空。而且：
		      * 如果Node p [p = tab[i = (n - 1) & hash]的hash值等于要放置的node的key的hash值。
		      * 	而且 这个p的key 和要放置的node的key相同。
		      * 	或者,要放置的node的key不等于null而且 key的值 等于 已存在的Node p的key值。
		      * 则,把原来存在的Node p 存储在临时node e中。
		      */
		     if (p.hash == hash &&
		         ((k = p.key) == key || (key != null && key.equals(k))))
		         e = p;//将碰撞p缓存到e
		     
		     /*
		      * 如果p是一个TreeNode类型。
		      * 则 在这个树 中添加结点 NewNode:（key,value）
		      */
		     else if (p instanceof TreeNode)
		    	 
		    	 /*
		    	  * 用Tree version of putVal.树版本的putVal。
		    	  */
		         e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);//-------------------------------------------------------------------放置
		     
		     /*
		      * 不满足key相同,也不满足 原结点是树类型。
		      * 则。
		      * 
		      * 
		      */
		     else {
		    	 /*
		    	  * binCount用于 ： 计算链表长度
		    	  * 链表长度加1的时候binCount会再加一次。
		    	  * 直到 binCount 等于8的时候,会把链表模式变成红黑树模式。
		    	  */
		         for (int binCount = 0; ; ++binCount) {
		        	 /*
		        	  * 如果此结点p 的next结点为空。（此时仍然为链表结构）
		        	  * 则在p的next上创建新的Node。
		        	  */
		             if ((e = p.next) == null) {
		                 p.next = newNode(hash, key, value, null);
		                
		                 
		                 /**
		                  * The bin count threshold for using a tree rather than list for a
		                  * bin.  Bins are converted to trees when adding an element to a
		                  * bin with at least this many nodes. The value must be greater
		                  * than 2 and should be at least 8 to mesh with assumptions in
		                  * tree removal about conversion back to plain bins upon
		                  * shrinkage.
		                  * 
		                  * 在Java 8之前的实现中是用链表解决冲突的，
		                  * 在产生碰撞的情况下，进行get时，两步的时间复杂度是O(1)+O(n)。
		                  * 因此，当碰撞很厉害的时候n很大，O(n)的速度显然是影响速度的。
		                  * 因此在Java 8中，利用红黑树替换链表，这样复杂度就变成了O(1)+O(logn)了，这样在n很大的时候，能够比较理想的解决这个问题，
		                  * 在Java 8：HashMap的性能提升一文中有性能测试的结果。
		                  * 
		                  * static final int TREEIFY_THRESHOLD = 8;
		                  */
		                 
		                 /*
		                  * 如果binCount大于等于7
		                  * 链表——>红黑树
		                  */
		                 if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
	                	   
		                	 /**
	                	     * 重置所有的连接node将链表变成红黑树。
	                	     * Replaces all linked nodes in bin at index for given hash unless
	                	     * table is too small, in which case resizes instead.
	                	     */
		                     treeifyBin(tab, hash);
		                 break;
		             }
		             
		             /*
		              * 如果Node e(用于临时存储的Node)的hash 和 要放置结点的hash相同。且 key相同。
		              * 	或者key的值相等。 跳出循环。
		              * 
		              * 此时这个e 缓存的是什么？e = p.next。是链表结构里面的下一个节点。
		              * 
		              * 注意： 此处是为了线程安全设计的判断。
		              * 
		              * 若不满足。 将e(用于临时存储的Node)的赋给p。即p = tab[i = (n - 1) & hash
		              */
		             if (e.hash == hash &&
		                 ((k = e.key) == key || (key != null && key.equals(k))))
		                 break;//为什么要break?   因为.hash 再次碰撞了！ 所以：binCount +1.  如果碰撞次数多,binCount= 7 的时候就要把链表结构转成红黑树了。
		             
		             p = e;//  若没有发生碰撞,则把e赋值给p.在链表的前面。 e是什么e = p.next？ p是什么？p是e 前面一个节点。     链表： A1-A2-p-e-A5-A6  实际意义就是, Node指针 后移动一个节点！
		         }
		     }
		     
		     /*
		      * e不为空。
		      * key不相同的碰撞结束。
		      * 得到了最后一次碰撞的 Node e。。此时这个e的key和将要放置的key相同。
		      * 把e的值 放到oldValue中。
		      */
		     if (e != null) { // existing mapping for key
		         V oldValue = e.value;
		         /*
		          * 如果设置覆盖更新的话。
		          * 则进行覆盖！
		          */
		         if (!onlyIfAbsent || oldValue == null)
		             e.value = value;//-------------------------------------------------------------------放置
		         
		      
		         afterNodeAccess(e);// Callbacks to allow LinkedHashMap post-actions
		         
		         /*
		          * 返回这个key相同的oldValue。
		          */
		         return oldValue;
		     }
		 }
		 ++modCount;//操作次数加1
		 if (++size > threshold)//size+1 再判断长度
		     resize();			//重置size。
		 afterNodeInsertion(evict);// Callbacks to allow LinkedHashMap post-actions
		 return null;
	}
	
	
	
    /**
     * Tree version of putVal.
     * 
     * e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
     */
	
	/*
    final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                   int h, K k, V v) {
        Class<?> kc = null;
        boolean searched = false;
        TreeNode<K,V> root = (parent != null) ? root() : this;
        for (TreeNode<K,V> p = root;;) {
            int dir, ph; K pk;
            if ((ph = p.hash) > h)
                dir = -1;
            else if (ph < h)
                dir = 1;
            else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                return p;
            else if ((kc == null &&
                      (kc = comparableClassFor(k)) == null) ||
                     (dir = compareComparables(kc, k, pk)) == 0) {
                if (!searched) {
                    TreeNode<K,V> q, ch;
                    searched = true;
                    if (((ch = p.left) != null &&
                         (q = ch.find(h, k, kc)) != null) ||
                        ((ch = p.right) != null &&
                         (q = ch.find(h, k, kc)) != null))
                        return q;
                }
                dir = tieBreakOrder(k, pk);
            }

            TreeNode<K,V> xp = p;
            if ((p = (dir <= 0) ? p.left : p.right) == null) {
                Node<K,V> xpn = xp.next;
                TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
                if (dir <= 0)
                    xp.left = x;
                else
                    xp.right = x;
                xp.next = x;
                x.parent = x.prev = xp;
                if (xpn != null)
                    ((TreeNode<K,V>)xpn).prev = x;
                moveRootToFront(tab, balanceInsertion(root, x));
                return null;
            }
        }
    }
}

*/