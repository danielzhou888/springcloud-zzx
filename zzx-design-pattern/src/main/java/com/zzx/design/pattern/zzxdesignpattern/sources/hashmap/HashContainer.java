//package com.zzx.design.pattern.zzxdesignpattern.sources.hashmap;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
///**
// * @author zhouzhixiang
// * @Date 2020-09-20
// */
//public class HashContainer<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
//    private static final long serialVersionUID = -2030703890822426163L;
//
//    /**
//     * The default initial capacity - Must be a power of two
//     */
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
//
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    static final  float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    static final int TREEIFY_THRESHOLD = 8;
//
//    int threshold;
//
//    final float loadFactor;
//
//    transient int modCount;
//
//    transient int size;
//
//    public HashContainer(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY) {
//            initialCapacity = MAXIMUM_CAPACITY;
//        }
//        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
//            throw new IllegalArgumentException("Illegal load Factor: " + loadFactor);
//        }
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//    static final int tableSizeFor(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    transient Node<K,V>[] table;
//
//
//    static class Node<K, V> implements Map.Entry<K, V> {
//
//        final int hash;
//
//        final K key;
//
//        V value;
//
//        Node<K, V> next;
//
//        public Node(final int hash, final K key, final V value, final Node<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @Override
//        public final K getKey() {
//            return key;
//        }
//
//        @Override
//        public final V getValue() {
//            return value;
//        }
//
//        @Override
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        @Override
//        public final String toString() {
//            return key + "=" + value;
//        }
//
//        @Override
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        @Override
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                if (Objects.equals(key, ((Entry<?, ?>) o).getKey()) && Objects.equals(value, ((Entry<?, ?>) o).getValue())) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//
//    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
//        TreeNode<K,V> parent;
//        TreeNode<K,V> left;
//        TreeNode<K,V> right;
//        TreeNode<K,V> prev;
//        boolean red;
//        TreeNode(int hash, K key, V val, Node<K,V> next) {
//            super(hash, key, val, next);
//        }
//        final TreeNode<K,V> root() {
//            for (TreeNode<K,V> r = this, p;;) {
//                if ((p = r.parent) == null) {
//                    return r;
//                }
//                p = r;
//            }
//        }
//    }
//
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    static Class<?> comparableClassFor(Object x) {
//        if (x instanceof Comparable) {
//            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
//            if ((c = x.getClass()) == String.class) {
//                return c;
//            }
//            if ((ts = c.getGenericInterfaces()) != null) {
//                for (int i = 0; i < ts.length; i++) {
//                    if ((ts = c.getGenericInterfaces()) != null) {
//                        for (int i = 0; i < ts.length; ++i) {
//                            if (((t = ts[i]) instanceof ParameterizedType) &&
//                                    ((p = (ParameterizedType)t).getRawType() ==
//                                            Comparable.class) &&
//                                    (as = p.getActualTypeArguments()) != null &&
//                                    as.length == 1 && as[0] == c) // type arg is c
//                                return c;
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    static int compareComparables(Class<?> kc, Object k, Object x) {
//        return (x == null || x.getClass() != kc ? 0 : ((Comparable)k).compareTo(x));
//    }
//
//
//    @Override
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsend, boolean evict) {
//        // 先判断数组是否为空
//        // 为空 resize
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        // 不为空 判断table数组索引处是否存在Node
//        // 不存在Node -》创建新node放入table中
//        if ((p = tab[i = (n - 1) & hash]) == null) {
//            tab[i] = newNode(hash, key, value, null);
//        } else {
//            // 存在Node -》放入Node链表合适处
//            Node<K,V> e; K k;
//            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))) {
//                e = p;
//            } else if (p instanceof  TreeNode) {
//                e = ((TreeNode<K,V>) p).putTreeVal(this, tab, hash, key, value);
//            } else {
//                for (int bitCount = 0; ; bitCount++) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (bitCount >= TREEIFY_THRESHOLD - 1)
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash && ((k = e.key) == key || (key != null) && key.equals(k)))
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) {
//                V oldValue = e.value;
//                if (!onlyIfAbsend || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//    final Node<K,V>[] resize() {
//        Node<K,V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
//                newThr = oldThr << 1;
//            }
//        } else if (oldThr > 0) {
//            newCap = oldThr;
//        } else {
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_INITIAL_CAPACITY);
//        }
//        if (newThr == 0) {
//            float ft = newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        Node<K,V>[] newTab = new Node[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            for (int i = 0; i < oldCap; i++) {
//                Node<K,V> e;
//                if ((e = oldTab[i]) != null) {
//                    oldTab[i] = null;
//                    if (e.next == null) {
//                        newTab[e.hash & (newCap - 1)] = e;
//                    } else if (e instanceof TreeNode) {
//                        //
//                        ((TreeNode<K,V>) e).split(this, newTab, i, oldCap);
//                    } else {
//                        Node<K,V> loHead = null, loTail = null;
//                        Node<K,V> hiHead = null, hiTail = null;
//                        Node<K,V> next;
//
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            } else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e.next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[i] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[i + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
//        return new Node<>(hash, key, value, next);
//    }
//
//    public V get(Object key) {
//        Node<K,V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    final Node<K,V> getNode(int hash, Object key) {
//        Node<K,V>[] tab; int n; Node<K,V> first, e; K k;
//        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
//            if (first.hash == hash && (k = first.key) == key || key.equals(k)) {
//                return first;
//            }
//            if ((e = first.next) != null) {
//                if (first instanceof TreeNode) {
//                    return ((TreeNode<K,V>) first).getTreeNode(hash, key);
//                }
//                do {
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        return e;
//                    }
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    public V getOrDefault(Object key, V defaultValue) {
//        return null;
//    }
//
//    @Override
//    public void forEach(BiConsumer<? super K, ? super V> action) {
//
//    }
//
//    @Override
//    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
//
//    }
//
//    @Override
//    public V putIfAbsent(K key, V value) {
//        return null;
//    }
//
//    @Override
//    public boolean remove(Object key, Object value) {
//        return false;
//    }
//
//    @Override
//    public boolean replace(K key, V oldValue, V newValue) {
//        return false;
//    }
//
//    @Override
//    public V replace(K key, V value) {
//        return null;
//    }
//
//    @Override
//    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
//        return null;
//    }
//
//    @Override
//    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        return null;
//    }
//
//    @Override
//    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        return null;
//    }
//
//    @Override
//    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
//        return null;
//    }
//
//    @Override
//    public Set<Entry<K, V>> entrySet() {
//        return null;
//    }
//}
