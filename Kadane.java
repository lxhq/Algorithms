//Kadane's algorithm for finding the maxmium subarray in array
public int kadane(int[] arr) {
    int maxSoFar = 0;
    int[] maxEndHere = new int[arr.lenght];
    maxEndHere[0] = arr[0];
    for (int i = 1; i < n; i++) {
        maxEndHere[i] = Math.max(arr[i], maxEndHere[i - 1] + arr[i]);
        maxSoFar = Math.max(maxSoFar, maxEndHere[i]);
    }
    return maxSoFar;
}
