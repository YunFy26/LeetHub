package org.example.linklist;

class LinkedList<T>{
    private ListNode<T> head;

    public LinkedList() {
        this.head = null;
    }
    // 在链表末尾添加一个节点
    public void add(T data) {
        ListNode<T> newNode = new ListNode<T>((Integer) data);
        // 如果链表为空
        if (head == null) {
            head = newNode;
        }else{
            ListNode<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    // 删除链表中的一个节点
    public void delete(T data) {
        ListNode<T> current = head;

    }

    public void display() {
        ListNode<T> current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
