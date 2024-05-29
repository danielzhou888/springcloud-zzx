package org.zzx.nk.nk05;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

/**
 * 删除链表中的节点
 * 问题： 给定一个单链表中的某个节点，删除该节点。
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}