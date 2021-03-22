import java.util.Scanner;

public class Main {
    private Scanner scanner;
    
    public static void main(String[] args) {
        new Main().exec();
    }
    
    private void exec() {
        this.scanner = new Scanner(System.in);
        this.certainExec();
        this.scanner.close();
    }
    
    private void certainExec() {
        String caseCountStr = this.scanner.nextLine();
        int caseCount = Integer.parseInt(caseCountStr);
        for (int i = 0; i < caseCount; i++) {
            this.findLongestSymmetricSubStr();
        }
    }
    
    class ArrToString<T> {
        String exec(T[] arr) {
            if (arr == null) {
                return "null";
            }
            int arrLen = arr.length;
            if (arrLen == 0) {
                return "[]";
            }
            StringBuilder builder = new StringBuilder();
            builder.append("[ " + arr[0]);
            for (int i = 1; i < arrLen; i++) {
                builder.append("," + arr[i]);
            }
            builder.append(" ]");
            return builder.toString();
        }
    }

    class MatrixToString<T> {
        String exec(T[][] matrix) {
            if (matrix == null) {
                return "null";
            }
            int arrLen = matrix.length;
            if (arrLen == 0) {
                return "[]";
            }
            ArrToString arrToString = new ArrToString<T>();
            StringBuilder builder = new StringBuilder();
            builder.append("[\n" + arrToString.exec(matrix[0]));
            for (int i = 1; i < arrLen; i++) {
                builder.append(",\n" + arrToString.exec(matrix[i]));
            }
            builder.append("\n]");
            return builder.toString();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("nums: " + new ArrToString<Integer>().exec(this.nums) + ";\n")
            .append("sumMatrix: " + new MatrixToString<Integer>().exec(this.sumMatrix) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount;
    Integer[] nums;
    Integer[][] sumMatrix;
    int longestLen;

    private void findLongestSymmetricSubStr() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.longestLen = 0;
        String[] numStrs = this.scanner.nextLine().split("");
        this.numCount = numStrs.length;
        this.nums = new Integer[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrs[i]);
        }
    }

    private void calResult() {
        /**
         * 方法 1
         * 使用累加和矩阵
         * 从头遍历，提前结束
         */
        // this.initSumMatrix();
        // this.findLongestLen();

        /**
         * 方法 2
         * 从中间向两边累加检查
         */
        this.findLongestLenFromCenter();
    }

    private void initSumMatrix() {
        this.sumMatrix = new Integer[this.numCount][];
        for (int i = 0; i < this.numCount; i++) {
            this.sumMatrix[i] = new Integer[this.numCount];
            this.sumMatrix[i][i] = this.nums[i];
            for (int j = i + 1; j < this.numCount; j++) {
                this.sumMatrix[i][j] = this.sumMatrix[i][j - 1] + this.nums[j];
            }
        }
    }

    private void findLongestLen() {
        int maxLeftStart = this.numCount - 2;
        for (int leftStart = 0; leftStart <= maxLeftStart; leftStart++) {
            int maxLen = (int)((this.numCount - leftStart) / 2);
            if (maxLen <= this.longestLen) {
                break;
            }
            for (int len = maxLen; len > 0; len--) {
                int leftEnd = leftStart + len - 1;
                int rightStart = leftEnd + 1;
                int rightEnd = rightStart + len - 1;
                if (this.sumMatrix[leftStart][leftEnd].equals(this.sumMatrix[rightStart][rightEnd])) {
                    this.setLongestLen(len);
                    break;
                }
            }
        }
    }

    private void findLongestLenFromCenter() {
        for (int leftEnd = 0; leftEnd < this.numCount; leftEnd++) {
            int rightStart = leftEnd + 1;
            if (rightStart == this.numCount) {
                break;
            }
            int leftSum = 0;
            int rightSum = 0;
            int len = 0;
            while (true) {
                int leftStart = leftEnd - len;
                int rightEnd = rightStart + len;
                if (leftStart < 0 || rightEnd == this.numCount) {
                    break;
                }
                leftSum += this.nums[leftStart];
                rightSum += this.nums[rightEnd];
                len++;
                if (leftSum == rightSum) {
                    this.setLongestLen(len);
                }
            }
        }
    }

    private void setLongestLen(int newLongestLen) {
        if (newLongestLen > this.longestLen) {
            this.longestLen = newLongestLen;
        }
    }

    private void printResult() {
        System.out.println(this.longestLen * 2);
    }
}
