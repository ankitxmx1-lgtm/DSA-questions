class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);
        
        ListNode lessTail = lessHead;
        ListNode greaterTail = greaterHead;
        
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.val < x) {
                lessTail.next = curr;
                lessTail = lessTail.next;
            } else {
                greaterTail.next = curr;
                greaterTail = greaterTail.next;
            }
        }
        
        greaterTail.next = null;
        
        lessTail.next = greaterHead.next;
        
        return lessHead.next;
    }
}
