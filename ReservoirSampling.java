//By using Reservoir Sampling algorithm, we can uniformly pick up samples in a very large data stream in fly
//leetcode 382. Linked List Random Node
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ReservoirSampling {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode head;
    Random rand;
    public Solution(ListNode head) {
        this.head = head;
        rand = new Random();        
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = head;
        int index = 1;
        int res = -1;
        while (node != null) {
            if (rand.nextInt(index) == 0) res = node.val;
            node = node.next;
            index++;
            
        }
        return res;
    }
}
