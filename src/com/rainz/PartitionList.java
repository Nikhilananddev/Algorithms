package com.rainz;

public class PartitionList {
	public static void test(String args[]) {
	}
	
	 public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	 }
	 public static ListNode partition(ListNode head, int x) {
		 // Partition 1
         ListNode dummy1 = new ListNode(x-1);
         dummy1.next = head;
         // Partition 2
         ListNode dummy2 = new ListNode(x+1);
         //dummy2.next = null; // do this later
         
         ListNode tail2 = dummy2;
         ListNode tail = dummy1;
         // Look at the next node of the current node so we don't have to maintain prev
         while (tail.next != null) {
        	 // If next node is >= x, move it to partition 2
             if (tail.next.val >= x) {
                 tail2.next = tail.next;
                 tail2 = tail.next;
                 //tail2.next = null; // do this later
                 tail.next = tail.next.next;
                 // Note in this case we don't advance tail,
                 // since tail.next has been updated and we look at the next node anyway. 
             } else {
                 tail = tail.next;
             }
         }
         tail2.next = null;
         tail.next = dummy2.next; // connect the two partitions
         return dummy1.next;
	 }
}
