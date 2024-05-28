package org.zzx.nk.nk04;

import java.util.*;

/**
 * LRU缓存机制
 * 问题： 设计并实现一个满足LRU（最近最少使用）缓存机制的数据结构。
 *
 * 为什么使用 LinkedHashSet？
 * 维护插入顺序：
 * LinkedHashSet 是 HashSet 的一个子类，它的特点是内部使用双向链表来维护元素的插入顺序。对于 LRU 缓存来说，最重要的是要知道最近最少使用的元素，而 LinkedHashSet 正好提供了这一特性。它保证了迭代顺序是插入元素的顺序，这样我们可以很容易地找到最早插入的元素（即最近最少使用的元素）。
 *
 * 快速访问和删除：
 * LinkedHashSet 提供了 O(1) 的时间复杂度来进行插入、删除和查找操作。这对于实现一个高效的 LRU 缓存是至关重要的。使用 LinkedHashSet 可以确保我们的缓存操作在常数时间内完成。
 *
 * 原理
 * 插入和更新元素：
 * 当我们插入一个新元素时，或者更新一个已有元素的值时，我们需要将这个元素移动到集合的尾部，以表示它是最近使用过的。LinkedHashSet 的 remove 和 add 操作可以保证元素被移动到集合的尾部。
 */
public class LRUCache {
    private final int capacity;
    private final Map<Integer, Integer> map;
    private final LinkedHashSet<Integer> order;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.order = new LinkedHashSet<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        order.remove(key);
        order.add(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            order.remove(key);
            order.add(key);
            return;
        }
        if (map.size() == capacity) {
            int oldest = order.iterator().next();
            order.remove(oldest);
            map.remove(oldest);
        }
        map.put(key, value);
        order.add(key);
    }

}