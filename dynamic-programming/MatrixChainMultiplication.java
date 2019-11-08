public class MatrixChainMultiplication {

    public static void main(String[] args) {

        int[] matrices = new int[] {1, 2, 3, 4, 3};

        System.out.println(recursiveOptMultiplication(matrices, 1, matrices.length-1));
    }

    private static int recursiveOptMultiplication(int[] matrices, int left, int right) {

        if (left == right) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for (int i = left; i < right; i++) {
            minCost = Math.min(minCost, matrices[left-1] * matrices[i] * matrices[right] +
                    recursiveOptMultiplication(matrices, left, i) + recursiveOptMultiplication(matrices, i+1, right));
        }

        return minCost;
    }
}
