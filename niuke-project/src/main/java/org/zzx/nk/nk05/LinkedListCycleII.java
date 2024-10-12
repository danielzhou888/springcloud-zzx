package org.zzx.nk.nk05;

/**
 * 环形链表 II
 * 问题： 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class LinkedListCycleII {
    // 检测链表中的环并返回环的起始节点
    public ListNode detectCycle(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回 null
        if (head == null || head.next == null) {
            return null;
        }
        // 初始化快慢指针
        ListNode slow = head, fast = head;
        // 使用快慢指针遍历链表
        while (fast != null && fast.next != null) {
            slow = slow.next; // 慢指针每次移动一步
            fast = fast.next.next; // 快指针每次移动两步
            // 如果快慢指针相遇，说明链表中有环
            if (slow == fast) {
                // 初始化新的指针从链表头开始
                ListNode start = head;
                // 新指针和慢指针同步移动，找到环的起始节点
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                // 返回环的起始节点
                return start;
            }
        }
        // 如果没有环，返回 null
        return null;
    }

    public static void main(String[] args) {
        LinkedListCycleII solver = new LinkedListCycleII();
        // 创建测试链表 3 -> 2 -> 0 -> -4 并构成环
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // 构成环，-4 指向节点 2
        // 检测链表中的环
        ListNode cycleNode = solver.detectCycle(head);
        // 输出环的起始节点值
        System.out.println(cycleNode != null ? cycleNode.val : "No cycle"); // 输出结果：2
    }
}
