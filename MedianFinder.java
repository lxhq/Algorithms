class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    boolean even;
    public MedianFinder() {
        left = new PriorityQueue<>();
        right = new PriorityQueue<>((a, b) -> (Integer.compare(b, a)));
        even = true;
    }
    
    public void addNum(int num) {
        right.offer(num);
        left.offer(right.poll());
        if (left.size() > right.size()) {
            right.offer(left.poll());
        }
        
        even = !even;
    }
    
    public double findMedian() {
        if (even) {
            return (double)(left.peek() + right.peek()) / 2;
        } else {
            return (double)right.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
