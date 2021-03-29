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
            this.findMaxSubArrWithoutSameDigit();
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
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("nums: " + new ArrToString<Integer>().exec(this.nums) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount;
    String[] numStrs;
    Integer[] nums;
    int maxSum;
    boolean[] digitUsed;

    private void findMaxSubArrWithoutSameDigit() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.maxSum = 0;
        this.numCount = Integer.parseInt(this.scanner.nextLine());
        this.numStrs = this.scanner.nextLine().split(" ");
        this.nums = new Integer[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrs[i]);
        }
        this.digitUsed = new boolean[10];
        for (int i = 0; i < 10; i++) {
            this.digitUsed[i] = false;
        }
    }

    private void calResult() {
        for (int i = 0; i < this.numCount; i++) {
            this.setDigitUsed(i, true);
            this.findMaxSum(this.nums[i], i + 1);
            this.setDigitUsed(i, false);
        }
    }

    private void findMaxSum(int sum, int start) {
        boolean notFindNext = true;
        for (int i = start; i < this.numCount; i++) {
            if (this.digitAllUnused(i)) {
                notFindNext = false;
                this.setDigitUsed(i, true);
                this.findMaxSum(sum + this.nums[i], i + 1);
                this.setDigitUsed(i, false);
            }
        }
        if (notFindNext) {
            this.setMaxSum(sum);
        }
    }

    private void setDigitUsed(int index, boolean val) {
        String numStr = this.numStrs[index];
        int len = numStr.length();
        for (int i = 0; i < len; i++) {
            char c = numStr.charAt(i);
            this.digitUsed[c - '0'] = val;
        }
    }

    private boolean digitAllUnused(int index) {
        String numStr = this.numStrs[index];
        int len = numStr.length();
        for (int i = 0; i < len; i++) {
            char c = numStr.charAt(i);
            if (this.digitUsed[c - '0']) {
                return false;
            }
        }
        return true;
    }

    private void setMaxSum(int newMaxSum) {
        this.maxSum = Math.max(this.maxSum, newMaxSum);
    }

    private void printResult() {
        System.out.println(this.maxSum);
    }
}
