public class MergeKSortedList {

    public static void main(String[] args) {
        int[][] arr= {{2, 6, 12, 34},
                {1, 9, 20, 1000},
                {23, 34, 90, 2000}};

        int[] sortedArr = mergeKArrays(arr, 0, arr.length-1);

        for (int value : sortedArr) {
            System.out.println(value);
        }
    }

    private static int[] mergeKArrays(int[][] arr, int start, int end) {

        if (start == end) {
            return arr[start];
        }

        int mid = (start + end)/2;

        int[] leftArr = mergeKArrays(arr, start, mid);
        int[] rightArr = mergeKArrays(arr, mid+1, end);

        return mergeArrays(leftArr, rightArr);
    }


    private static int[] mergeArrays(int[] array1, int[] array2) {

        int i = 0;
        int j = 0;

        int[] newArr = new int[array1.length + array2.length];
        int k = 0;

        while(i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                newArr[k] = array1[i];
                i++;
            } else {
                newArr[k] = array2[j];
                j++;
            }
            k++;
        }

        while (i < array1.length) {
            newArr[k] = array1[i];
            i++;
            k++;
        }

        while (j < array2.length) {
            newArr[k] = array2[j];
            j++;
            k++;
        }
        return newArr;
    }
}
