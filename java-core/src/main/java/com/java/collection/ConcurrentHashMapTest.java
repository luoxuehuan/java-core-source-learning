package com.java.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;




public class ConcurrentHashMapTest {

	public static Map map = new ConcurrentHashMap();//和hashtable类似
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		new Thread("Thread1"){  
            @Override  
            public void run() {  
                map.put(3, 33);  
            }  
        };  
          
        new Thread("Thread2"){  
            @Override  
            public void run() {  
                map.put(4, 44);  
            }  
        };  
          
        new Thread("Thread3"){  
            @Override  
            public void run() {  
                map.put(7, 77);  
            }  
        };  
        System.out.println(map);  
	}

}

/**
 * 底层实现：
 *  transient volatile Node<K,V>[] table;
 *  
 *  static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        volatile V val;
        volatile Node<K,V> next;
 */

/*

	【^】:按位异或。比如二进制     1001 ^ 1100 = 0101
	0^0=0，1^1=0 ，1^0 = 1，0^1=1。
	
	【&】：a&b=2 ,按位与,算术运算..1010&0010 = 0010

	【>>】 右移,高位补符号位” 这里右移一位表示除2
	【>>>】 无符号右移,高位补0”； 与>>类似
	【<<】 左移” 左移一位表示乘2，二位就表示4，就是2的n次方
	
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    
    static final int spread(int h) {
    
    	//无符号右移后与自己做异或。再与hash_bits做与运算。
        return (h ^ (h >>> 16)) & HASH_BITS;
    }
    
*/

/*

1.putVal方法
onlyIfAbsent:

//Implementation for put and putIfAbsent 
final V putVal(K key, V value, boolean onlyIfAbsent) {
	//如果key是null或者value是null。则抛出空指针异常||注：hashmap 运行一个null的key。value可以为null。
    if (key == null || value == null) throw new NullPointerException();
    int hash = spread(key.hashCode());
    int binCount = 0;
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        
        //tab为null或者为空，则初始化。
        if (tab == null || (n = tab.length) == 0)
            tab = initTable();
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
            if (casTabAt(tab, i, null,
                         new Node<K,V>(hash, key, value, null)))
                break;                   // no lock when adding to empty bin
        }
        else if ((fh = f.hash) == MOVED)
            tab = helpTransfer(tab, f);
        else {
            V oldVal = null;
            synchronized (f) {
                if (tabAt(tab, i) == f) {
                    if (fh >= 0) {
                        binCount = 1;
                        for (Node<K,V> e = f;; ++binCount) {
                            K ek;
                            if (e.hash == hash &&
                                ((ek = e.key) == key ||
                                 (ek != null && key.equals(ek)))) {
                                oldVal = e.val;
                                if (!onlyIfAbsent)
                                    e.val = value;
                                break;
                            }
                            Node<K,V> pred = e;
                            if ((e = e.next) == null) {
                                pred.next = new Node<K,V>(hash, key,
                                                          value, null);
                                break;
                            }
                        }
                    }
                    else if (f instanceof TreeBin) {
                        Node<K,V> p;
                        binCount = 2;
                        if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                       value)) != null) {
                            oldVal = p.val;
                            if (!onlyIfAbsent)
                                p.val = value;
                        }
                    }
                }
            }
            if (binCount != 0) {
                if (binCount >= TREEIFY_THRESHOLD)
                    treeifyBin(tab, i);
                if (oldVal != null)
                    return oldVal;
                break;
            }
        }
    }
    addCount(1L, binCount);
    return null;
}


*/


/*

2.replaceNode方法：

final V replaceNode(Object key, V value, Object cv) {
    int hash = spread(key.hashCode());
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        if (tab == null || (n = tab.length) == 0 ||
            (f = tabAt(tab, i = (n - 1) & hash)) == null)
            break;
        else if ((fh = f.hash) == MOVED)
            tab = helpTransfer(tab, f);
        else {
            V oldVal = null;
            boolean validated = false;
            synchronized (f) {
                if (tabAt(tab, i) == f) {
                    if (fh >= 0) {
                        validated = true;
                        for (Node<K,V> e = f, pred = null;;) {
                            K ek;
                            if (e.hash == hash &&
                                ((ek = e.key) == key ||
                                 (ek != null && key.equals(ek)))) {
                                V ev = e.val;
                                if (cv == null || cv == ev ||
                                    (ev != null && cv.equals(ev))) {
                                    oldVal = ev;
                                    if (value != null)
                                        e.val = value;
                                    else if (pred != null)
                                        pred.next = e.next;
                                    else
                                        setTabAt(tab, i, e.next);
                                }
                                break;
                            }
                            pred = e;
                            if ((e = e.next) == null)
                                break;
                        }
                    }
                    else if (f instanceof TreeBin) {
                        validated = true;
                        TreeBin<K,V> t = (TreeBin<K,V>)f;
                        TreeNode<K,V> r, p;
                        if ((r = t.root) != null &&
                            (p = r.findTreeNode(hash, key, null)) != null) {
                            V pv = p.val;
                            if (cv == null || cv == pv ||
                                (pv != null && cv.equals(pv))) {
                                oldVal = pv;
                                if (value != null)
                                    p.val = value;
                                else if (t.removeTreeNode(p))
                                    setTabAt(tab, i, untreeify(t.first));
                            }
                        }
                    }
                }
            }
            if (validated) {
                if (oldVal != null) {
                    if (value == null)
                        addCount(-1L, -1);
                    return oldVal;
                }
                break;
            }
        }
    }
    return null;
}
*/