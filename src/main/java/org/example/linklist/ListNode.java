package org.example.linklist;

public class ListNode<T> {
    int val;
    ListNode<T> next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode<T> next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return val + (next != null ? " -> " + next.toString() : "");
    }
}