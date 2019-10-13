public class MaximumSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maximumSubArrayLength(arr, 0, arr.length-1));
    }

    private static int maximumSubArrayLength(int[] arr, int start, int end) {

        if (start == end) {
            return arr[start];
        }

        int mid = (start + end)/2;
        int L = maximumSubArrayLength(arr, start, mid);
        int R = maximumSubArrayLength(arr, mid+1, end);

        int lMax = Integer.MIN_VALUE;
        int rMax = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = mid; i >=start ; i--) {
            sum += arr[i];

            lMax = Math.max(lMax, sum);
        }

        sum = 0;

        for (int i = mid+1; i <=end ; i++) {
            sum += arr[i];

            rMax = Math.max(rMax, sum);
        }

        return Math.max(Math.max(L, R), lMax + rMax);
    }
}
