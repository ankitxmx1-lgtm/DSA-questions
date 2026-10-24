class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pointer = dummy;

        while (pointer != null) {
            ListNode tracker = pointer;
            for (int i = 0; i < k && tracker != null; i++) {
                tracker = tracker.next;
            }
            if (tracker == null) {
                break;
            }

            ListNode current = pointer.next;
            ListNode nxt = current.next;

            for (int i = 0; i < k - 1; i++) {
                current.next = nxt.next;
                nxt.next = pointer.next;
                pointer.next = nxt;
                nxt = current.next;
            }

            pointer = current;
        }

        return dummy.next;
    }
}
