package org.example.linklist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkListSolutionTest {
    private final LinkListSolution<Integer> linkListSolution = new LinkListSolution<>();

    @Test
    public void testDeleteDuplicatesRecursion() {
        // Test case 1: 1 -> 1 -> 2 -> 3 -> 3 should return 1 -> 2 -> 3
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(3)))));
//        ListNode<Integer> result1 = deleteDuplicates_1(list1);
//        assertEquals("1 -> 2 -> 3", result1.toString());
        ListNode<Integer> result2 = linkListSolution.deleteDuplicatesRecursion(list1);
        assertEquals("1 -> 2 -> 3", result2.toString());
    }

    @Test
    public void testDeleteDuplicatesPointer() {
        // Test case 1: 1 -> 1 -> 2 -> 3 -> 3 should return 1 -> 2 -> 3
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(3)))));
//        ListNode<Integer> result1 = deleteDuplicates_1(list1);
//        assertEquals("1 -> 2 -> 3", result1.toString());
        ListNode<Integer> result2 = linkListSolution.deleteDuplicatesPointer(list1);
        assertEquals("1 -> 2 -> 3", result2.toString());
    }

    @Test
    public void testRemoveNthFromEnd(){
        // 1 -> 1 -> 2 -> 3 -> 3
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(3)))));
        // 1 -> 2
        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2));
        ListNode<Integer> result = linkListSolution.removeNthFromEnd(list2, 2);
        assertEquals("2", result.toString());
    }

    @Test
    public void testRemoveNthFromEnd2(){
        // 1 -> 1 -> 2 -> 3 -> 3
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(3)))));
        // 1 -> 2
        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2));
        ListNode<Integer> result = linkListSolution.removeNthFromEnd2(list2, 2);
        assertEquals("2", result.toString());
    }

    @Test
    public void testSwapPairsIteration(){
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5)))));
        ListNode<Integer> result = linkListSolution.swapPairsIteration(list1);
        assertEquals("2 -> 1 -> 4 -> 3 -> 5", result.toString());
    }

    @Test
    public void testSwapPairsRecursion(){
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5)))));
        ListNode<Integer> result = linkListSolution.swapPairsRecursion(list1);
        assertEquals("2 -> 1 -> 4 -> 3 -> 5", result.toString());
    }

    @Test
    public void testAddTwoNumbers(){
        ListNode<Integer> list1 = new ListNode<>(3, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9))))))))));
//        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5)))));
        ListNode<Integer> list2 = new ListNode<>(7);
        ListNode<Integer> result = linkListSolution.addTwoNumbers(list1, list2);
        System.out.println(result);
    }

    @Test
    public void testIsPalindrome(){
//        ListNode<Integer> list1 = new ListNode<>(3, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9, new ListNode<>(9))))))))));
//        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5)))));

        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2));
        boolean result = linkListSolution.isPalindrome(list2);
        System.out.println(result);
    }

    @Test
    public void testSplitListToParts(){
        int k = 5;
        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3)));
        linkListSolution.splitListToParts(list2, k);
    }

    @Test
    public void testOddEvenList(){
        ListNode<Integer> list2 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3)));
        linkListSolution.oddEvenList(list2);
    }


}