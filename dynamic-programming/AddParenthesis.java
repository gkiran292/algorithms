import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Input : expr = “3-2-1”
Output : {0, 2}
((3-2)-1) = 0
(3-(2-1)) = 2

Input : expr = "5*4-3*2"
Output : {-10, 10, 14, 10, 34}
(5*(4-(3*2))) = -10
(5*((4-3)*2)) = 10
((5*4)-(3*2)) = 14
((5*(4-3))*2) = 10
(((5*4)-3)*2) = 34
 */

//Works for only single digit positive integer

public class AddParenthesis {

    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String  MULTIPLY = "*";

    private static int performOperation(int a, String sign, int b) {
        if (sign.equals(ADDITION)) {
            return a + b;
        }

        if (sign.equals(SUBTRACTION)) {
            return a - b;
        }
        return a * b;
    }

    private static boolean isOperator(String sign) {
        return sign.equals(ADDITION) || sign.equals(SUBTRACTION) || sign.equals(MULTIPLY);
    }

    private static List<Integer> recursiveApproach(String str, Map<String, List<Integer>> resultMap) {

        List<Integer> result = new ArrayList<>();

        if (str.length() == 1) {
            result.add(Integer.parseInt(str));
        }

        if (resultMap.containsKey(str)) {
            return resultMap.get(str);
        }

        for (int i = 0; i < str.length(); i++) {
            if (isOperator(str.charAt(i)+"")) {

                List<Integer> leftList = recursiveApproach(str.substring(0, i), resultMap);
                List<Integer> rightList = recursiveApproach(str.substring(i+1), resultMap);

                for (Integer left : leftList) {
                    for (Integer right : rightList) {
                        result.add(performOperation(left, str.charAt(i) + "", right));
                    }
                }
            }
        }
        resultMap.put(str, result);

        return result;
    }

    public static void main(String[] args) {
        String str = "5*4-3*2";
        Map<String, List<Integer>> resultMap = new HashMap<>();
        List<Integer> computedList = recursiveApproach(str, resultMap);

        computedList.forEach(System.out::println);
    }
}
