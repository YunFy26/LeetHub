package org.example.linklist;


import java.util.ArrayList;
import java.util.Stack;

/**
 * 链表工具
 * @param <T>
 */
public class LinkListSolution<T> {
    /**
     * 寻找两个链表交点-无环
     * @param headA
     * @param headB
     * @return
     */
    public ListNode<T> getIntersectionNode(ListNode<T> headA, ListNode<T> headB) {
        if(headA == null || headB == null) return null;
        ListNode<T> pA = headA;
        ListNode<T> pB = headB;
        while(pA != pB){
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 反转链表-栈
     * @param head
     * @return
     */
    public ListNode<T> reverseList1(ListNode<T> head) {
        Stack<ListNode<T>> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;
        }

        if(stack.isEmpty()){
            return null;
        }
        ListNode<T> newLink = stack.pop();
        ListNode<T> temp = newLink;
        while(!stack.isEmpty()){
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return newLink;
    }

    /**
     * 迭代法反转链表
     * @param head
     * @return
     */
    public ListNode<T> reverseList2(ListNode<T> head) {
        ListNode<T> prev = null;
        ListNode<T> curr = head;
        while(curr != null){
            ListNode<T> nextTemp = curr.next; // 保存当前节点的下一个节点，临时变量
            curr.next = prev;   // 将当前节点的next指针指向前一个节点<本来next是指向后一个节点>
            prev = curr;        // prev指针指向当前节点
            curr = nextTemp;    // curr指针指向下一个节点     重复以上过程，更改所有节点的next指针，使next指针都指向前一个节点
        }
        return prev;
    }

    /**
     * 反转指定位置的链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode<T> reverseBetween(ListNode<T> head, int left, int right) {
        // TODO
        return null;
    }


    /**
     * 合并两个有序链表 - 以空间换时间
     * @param list1
     * @param list2
     * @return
     */
    public ListNode<Integer> mergeTwoLists(ListNode<Integer> list1, ListNode<Integer> list2){
//        ListNode<T> cur = null;
        ListNode<Integer> ret = new ListNode<>(0);
        ListNode<Integer> dump = ret;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val) {
                ret.next = list1;
                ret = ret.next;
                list1 = list1.next;
            }else {
                ret.next = list2;
                ret = ret.next;
                list2 = list2.next;
            }
        }
        if (list1 == null) ret.next = list2;
        else ret.next = list1;

        return dump.next;
    }

    /**
     * 给定一个已排序的链表的头head，删除所有重复的元素，使每个元素只出现一次，返回已排序的链表
     * 递归
     * @param head head node
     * @return
     */
    public ListNode<Integer> deleteDuplicatesRecursion(ListNode<Integer> head){

        if (head == null || head.next == null) return head;

        if (head.val == head.next.val){
            return deleteDuplicatesRecursion(head.next);
        }else {
            head.next = deleteDuplicatesRecursion(head.next);
            return head;
        }

    }

    /**
     * 指针
     * @param head
     * @return
     */
    public ListNode<Integer> deleteDuplicatesPointer(ListNode<Integer> head){

        ListNode<Integer> cur = head;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return cur;

    }

    /**
     * 删除链表的倒数第 n 个结点，并且返回链表的头结点：获取长度 -> 删除
     * @param head
     * @param n
     * @return
     */
    public ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        ListNode<Integer> cur = new ListNode<>(0, head);
        ListNode<Integer> dump = cur;
        int length = getLength(head);
        for (int i = 0; i < length - n; i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dump.next;
    }

    /**
     * 双指针：删除倒数第n个，维护2个pointer，让第1个与第2个指针之间相隔n个，然后遍历，直到第1个pointer为null
     * @param head
     * @param n
     * @return
     */
    public ListNode<Integer> removeNthFromEnd2(ListNode<Integer> head, int n) {

        ListNode<Integer> cur = new ListNode<>(0, head);
        ListNode<Integer> first = head;
        ListNode<Integer> second = cur;
        for (int i = 0; i < n; i++){
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;


        return cur.next;
    }

    /**
     * 两两交换链表中相邻节点 : 递归
     * @param head
     * @return
     */
    public ListNode<Integer> swapPairsRecursion(ListNode<Integer> head) {
        if (head == null || head.next == null) return head;
        ListNode<Integer> dumpHead = head.next;
        // 只有两个元素时， head.next = dumpHead.next;  --> 在这里递归
        head.next = swapPairsRecursion(dumpHead.next);
        dumpHead.next = head;
        return dumpHead;

    }

    /**
     * 两两交换链表中相邻节点 : 迭代
     * @param head
     * @return
     */
    public ListNode<Integer> swapPairsIteration(ListNode<Integer> head) {
        // head: 1 2 3 4 5
        ListNode<Integer> dumpHead = new ListNode<>(0, head);
        ListNode<Integer> temp = dumpHead; // temp: 0 1 2 3 4 5
        while (temp.next != null && temp.next.next != null){
            ListNode<Integer> node1 = temp.next; // node1: 1 2 3 4 5
            ListNode<Integer> node2 = temp.next.next; // node2: 2 3 4 5
            temp.next = node2; // temp.next: 2 3 4 5  temp: 0 2 3 4 5
            node1.next = node2.next; // node1.next: 3 4 5  node1: 1 3 4 5
            node2.next = node1; // node2.next: 1 3 4 5   node2: 2 1 3 4 5
            temp = node1; // temp: 1 3 4 5
        }
        return dumpHead.next;

    }

    /**
     * 两数相加-链表实现
     * @param l1
     * @param l2
     * @return
     */
    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        Stack<ListNode<Integer>> stack1 = new Stack<>();
        Stack<ListNode<Integer>> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode<Integer> dump = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0){
            int sum = carry;
            sum += stack1.isEmpty()? 0 : stack1.pop().val;
            sum += stack2.isEmpty()? 0 : stack2.pop().val;
            ListNode<Integer> head = new ListNode<>(sum % 10); // head: 2 下一轮 head : 3
            head.next = dump; // head: 2 -> null   下一轮: head: 3 -> 2 -> null
            dump = head; // dump: 2 -> null;  下一轮 dump: 3 -> 2 -> null
            carry = sum / 10;
        }
        return dump;
    }

    /**
     * 判断是否是回文链表（前向后向输出是同样的
     * 前半段链表入栈，通过出栈的方式与链表后半段的值进行比较
     * 时间复杂度O(n), 空间复杂度O(n)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode<Integer> head) {
        int length = getLength(head);
        Stack<Integer> stack = new Stack<>();
        // 1 -> 2 -> 3 -> 2 -> 1
        for (int i=0;i < length/2;i++){
            stack.push(head.val);
            head = head.next;
        }
        if (length % 2 == 1){
            while (head.next != null){
                int cur = stack.pop();
                if (head.next.val == cur) {
                    head = head.next;
                }else return false;
            }
            return true;
        }
        while (head != null){
            int cur = stack.pop();
            if (head.val == cur){
                head = head.next;
            }else return false;
        }
        return true;
    }

    /**
     * 给一个头结点为 head 的单链表和一个整数 k ，将链表分隔为 k 个连续的部分
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode<Integer> head, int k) {
        int length = getLength(head);
        int average = length / k;
        int remainder = length % k;
        ListNode<Integer>[] listNodes = new ListNode[k];

        ListNode<Integer> temp = head;
        for (int i = 0; i < k && temp != null; i++){
            listNodes[i] = temp;
            int size = average + (i < remainder? 1 : 0);
            for (int j = 1; j < size; j++) {
                temp =  temp.next;
            }
            // 1 2 3 -> 1 截断temp.next
            ListNode<Integer> next = temp.next;
            temp.next = null;
            temp = next;

        }


        return listNodes;
    }

    /**
     * 给定单链表的头节点 head 将所有索引为奇数的节点和索引为偶数的节点分别组合在一起,然后返回重新排序的列表
     * 转换成数组，时间复杂度，空间复杂度均为O(n)
     * @param head 单链表的头节点
     * @return 重新排序的列表
     */
    public ListNode<Integer> oddEvenList(ListNode<Integer> head) {

        ArrayList<Integer> valArray = new ArrayList<>();
        ListNode<Integer> oddEven = new ListNode<>(0, head);
        ListNode<Integer> dump = oddEven;
        while (head != null){
            valArray.add(head.val);
            head = head.next;
        }

        for (int i = 0; i < valArray.size(); i+=2){
            oddEven.next.val = valArray.get(i);
            oddEven = oddEven.next;
        }

        for (int i = 1; i < valArray.size(); i+=2){
            oddEven.next.val = valArray.get(i);
            oddEven = oddEven.next;
        }
        return dump.next;
    }

    /**
     * 同上，不转换为数组
     * 时间复杂度O(n), 空间复杂度O(1)
     * @param head
     * @return
     */
    public ListNode<Integer> oddEvenList2(ListNode<Integer> head) {
        if (head == null) return head;
        ListNode<Integer> evenHead = head.next;
        ListNode<Integer> odd = head, even = evenHead;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;

    }


    /**
     * 获取ListNode长度
     * @param list
     * @return
     */
    public int getLength(ListNode<Integer> list){
        int count = 0;
        while (list != null){
            count ++;
            list = list.next;
        }
        return count;
    }


}
