import java.util.Arrays;

public class CountParenthesis {

    private static int countParenthesisRecursively(int length, int[] result) {

        if (length < 2) {
            return 1;
        }

        if (result[length] != 0) {
            return result[length];
        }

        for (int i = 1; i <= length; i++) {
            result[length] += countParenthesisRecursively(i, result) *
                    countParenthesisRecursively(length-i, result);
        }

        return result[length];
    }

    private static int countParenthesisIteratively(String str, int[] result) {

        for (int i = 2; i <= str.length(); i++) {
            for (int j = 1; j <= i; j++) {
                result[i] += result[j]*result[i-j];
            }
        }

        return result[str.length()];
    }

    public static void main(String[] args) {
        String str = "yupi";
        int[] result = new int[str.length()+1];

        Arrays.fill(result, 0);
        System.out.println(countParenthesisRecursively(str.length(), result));

        Arrays.fill(result, 0);
        result[0] = 1;
        result[1] = 1;
        System.out.println(countParenthesisIteratively(str, result));
    }
}
