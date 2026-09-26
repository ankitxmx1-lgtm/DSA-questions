class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        for (ListNode current = dummy; current.next != null && current.next.next != null; ) {
            ListNode i = current.next;
            ListNode j = current.next.next;
     
            i.next = j.next;
            j.next = i;
            current.next = j;
            
            current = i;
        }
        
        return dummy.next;
    }
}
