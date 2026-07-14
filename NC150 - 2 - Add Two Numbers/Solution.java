import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;

    // Constructors
    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        if (this.next == null) {
            // base case
            return Integer.toString(this.val);
        } else {
            // recursive relation
            return (this.val + ", " + this.next.toString());
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        // Test Case 1
        // var l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        // var l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        // Test Case 2
        var l1 = new ListNode(8, new ListNode(3, new ListNode(2)));
        var l2 = new ListNode(9, new ListNode(2, new ListNode(1)));

        var sol = new Solution();
        ListNode result = sol.addTwoNumbers(l1, l2);
        System.out.println(result.toString());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
         * L1[0] -> L1[1] -> ...
         * L2[0] -> L2[1] -> ...
         * 
         * So if L1[i] + L2[i] > 9, L1[i+1] += 1
         */

        // don't have access to length beforehand
        // so continue only considering next step

        ListNode root = new ListNode();
        ListNode curNode = root;
        int carry = 0;
        boolean init = true;

        while (l1 != null || l2 != null || carry > 0) {
            if (!init) {
                curNode.next = new ListNode();
                curNode = curNode.next;
            }

            init = false;

            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int rawSum = x + y + carry;
            int remainder = rawSum % 10;
            carry = rawSum / 10; // integer division

            // move to next digit if not already at end
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;

            //if (carry > 0) {
            //    l1 = (l1 == null) ? new ListNode(carry) : new ListNode(l1.val + carry, l1.next);
            //}
            // now redundant

            curNode.val = remainder;
        }
        return root;
    }
}