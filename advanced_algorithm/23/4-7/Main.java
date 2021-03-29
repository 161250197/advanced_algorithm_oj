import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
            this.findMaxSumByRemovingOneNum();
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
        ArrToString<Integer> intArrToString = new ArrToString<>();
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("nums: " + intArrToString.exec(this.nums) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount;
    Integer[] nums;
    int maxSum;

    private void findMaxSumByRemovingOneNum() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.numCount = Integer.parseInt(this.scanner.nextLine());
        this.nums = new Integer[this.numCount];
        String[] numStrs = this.scanner.nextLine().split(" ");
        for (int i = 0; i < this.numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrs[i]);
        }
        this.maxSum = this.nums[0];
        for (int i = 1; i < this.numCount; i++) {
            this.maxSum = Math.min(this.maxSum, this.nums[i]);
        }
    }

    private void calResult() {
        int sum = 0;
        boolean noNegativeNum = true;
        List<Integer> negativeNumIndexList = new ArrayList<>();
        for (int i = 0; i < this.numCount; i++) {
            int num = this.nums[i];
            sum += num;
            if (num < 0) {
                negativeNumIndexList.add(i);
            }
        }
        if (negativeNumIndexList.isEmpty()) {
            this.setMaxSum(sum);
            return;
        }
        for (int i : negativeNumIndexList) {
            this.calMaxSubSum(i);
        }
    }

    private void calMaxSubSum(int negativeIndex) {
        int maxSubSum = this.maxSum;
        int sum = 0;
        for (int i = 0; i < this.numCount; i++) {
            if (i == negativeIndex) {
                continue;
            }
            int num = this.nums[i];
            sum += num;
            maxSubSum = Math.max(maxSubSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        this.setMaxSum(maxSubSum);
    }

    private void setMaxSum(int newMaxSum) {
        this.maxSum = Math.max(newMaxSum, maxSum);
    }

    private void printResult() {
        System.out.println(this.maxSum);
    }
}
