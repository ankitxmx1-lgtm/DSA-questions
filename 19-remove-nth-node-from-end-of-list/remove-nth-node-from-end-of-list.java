    class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
     
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode first = dummy;
        ListNode last  = dummy;
        
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
      
        while (first.next != null) {
            first = first.next;
            last= last.next;
        }
        last.next =last.next.next;
        
        return dummy.next;
    }
}
