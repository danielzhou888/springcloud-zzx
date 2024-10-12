package org.zzx.nk.nk05;

/**
 * 旋转链表
 * 问题： 给定一个链表，旋转链表，使得每个节点向右移动 k 个位置，其中 k 是非负数。
 */
public class RotateList {
    // 定义旋转链表的方法，输入为链表头节点和旋转步数 k
    public ListNode rotateRight(ListNode head, int k) {
        // 如果链表为空，或只有一个节点，或 k 为 0，则不需要旋转，直接返回原链表
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 找到链表的尾节点，并计算链表的长度 n
        ListNode oldTail = head;
        int n = 1; // 初始化链表长度为 1
        while (oldTail.next != null) {
            oldTail = oldTail.next; // 迭代到尾节点
            n++; // 计算链表长度
        }

        // 将链表变成循环链表
        oldTail.next = head;

        // 找到新的尾节点 newTail，新尾节点在 (n - k % n - 1) 处
        ListNode newTail = head;
        for (int i = 0; i < n - k % n - 1; i++) {
            newTail = newTail.next; // 移动到新的尾节点位置
        }

        // 新的头节点为 newTail 的下一个节点
        ListNode newHead = newTail.next;

        // 断开循环链表
        newTail.next = null;

        // 返回新的头节点
        return newHead;
    }

    public static void main(String[] args) {
        RotateList solver = new RotateList();
        // 创建测试链表 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 旋转链表，旋转步数为 2
        ListNode result = solver.rotateRight(head, 2);

        // 打印旋转后的链表 4 -> 5 -> 1 -> 2 -> 3
        while (result != null) {
            System.out.print(result.val + " "); // 输出结果：4 5 1 2 3
            result = result.next;
        }
    }
}