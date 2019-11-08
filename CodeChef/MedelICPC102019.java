import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MedelICPC102019 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        while (testCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] inputLines = br.readLine().split("\\s");
            int[] inputNums = new int[n];
            for (int i = 0; i < inputLines.length; i++) {
                inputNums[i] = Integer.parseInt(inputLines[i]);
            }
            int[] output = getMedian(inputNums);
            System.out.println(output[0] + " " + output[1]);
        }
    }

    private static int[] getMedian(int[] inputArray) {

        for (int i = 0; i < inputArray.length - 2; i++) {
            int index = middleOfThree(inputArray[i], inputArray[i + 1], inputArray[i + 2], i, i + 1, i + 2);

            if (index == i + 1) {
                swap(inputArray, i, i + 1);
            } else if (index == i + 2) {
                swap(inputArray, i + 1, i + 2);
                swap(inputArray, i, i + 1);
            }
        }

        int[] returnArray = new int[2];
        returnArray[0] = inputArray[inputArray.length - 2];
        returnArray[1] = inputArray[inputArray.length - 1];

        return returnArray;
    }

    private static int middleOfThree(int num1, int num2, int num3, int i1, int i2, int i3) {
        int x = num1 - num2;

        int y = num2 - num3;
        int z = num1 - num3;

        if (x * y > 0)
            return i2;

        else if (x * z > 0)
            return i3;
        else
            return i1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
