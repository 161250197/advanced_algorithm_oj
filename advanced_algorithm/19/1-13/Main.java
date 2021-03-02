import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

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
            this.findCertainSumSubArr();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("sum: " + this.sum + ";\n")
            .append("nums: " + this.intArrToString(this.nums) + ";\n")
        ;
        return builder.toString();
    }

    private String intArrToString(int[] ints) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ints[0]);
        int arrLen = ints.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ints[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount = -1;
    int sum = -1;
    int[] nums = null;
    ArrayList<int[]> resultNumArrList;
    Set<String> checkedSet;

    private void findCertainSumSubArr() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.resultNumArrList = new ArrayList<>();
        this.checkedSet = new HashSet<>();

        String[] numCountAndSumStr = this.scanner.nextLine().split(" ");
        this.numCount = Integer.parseInt(numCountAndSumStr[0]);
        this.sum = Integer.parseInt(numCountAndSumStr[1]);

        this.nums = new int[this.numCount];
        String[] numStrArr = this.scanner.nextLine().split(" ");
        for (int i = 0; i < this.numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrArr[i]);
        }

        Arrays.sort(this.nums);
    }

    private void calResult() {
        findOuterTwoNum(0, this.numCount - 1);
    }

    private void findOuterTwoNum(int left, int right) {
        String key = "left: " + left + "; right: " + right;
        if (right - left <= 2 || this.checkedSet.contains(key)) {
            return;
        }
        this.checkedSet.add(key);
        int leftNum = this.nums[left];
        int rightNum = this.nums[right];
        int minSum = leftNum + this.nums[left + 1] + this.nums[left + 2] + rightNum;
        if (minSum > this.sum) {
            this.findOuterTwoNum(left, right - 1);
            return;
        }
        int maxSum = leftNum + this.nums[right - 2] + this.nums[right - 1] + rightNum;
        if (maxSum < this.sum) {
            this.findOuterTwoNum(left + 1, right);
            return;
        }

        this.findInnerTwoNum(left, right);

        int newLeft = left + 1;
        while (right - newLeft > 2 && this.nums[newLeft] == leftNum) {
            newLeft++;
        }
        this.findOuterTwoNum(newLeft, right);

        int newRight = right - 1;
        while (newRight - left > 2 && this.nums[newRight] == rightNum) {
            newRight--;
        }
        this.findOuterTwoNum(left, newRight);

        this.findOuterTwoNum(newLeft, newRight);
    }

    private void findInnerTwoNum(int left, int right) {
        int leftNum = this.nums[left];
        int rightNum = this.nums[right];
        int target = this.sum - leftNum - rightNum;
        int l = left + 1;
        int r = right - 1;
        while (l < r) {
            int lNum = this.nums[l];
            int rNum = this.nums[r];
            int sum = lNum + rNum;
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                int[] resultNumArr = { leftNum, lNum, rNum, rightNum };
                this.resultNumArrList.add(resultNumArr);
                l++;
                while (l < r && this.nums[l] == lNum) {
                    l++;
                }
                r--;
                while (l < r && this.nums[r] == rNum) {
                    r--;
                }
            }
        }
    }

    private String createResultNumArrStr(int[] numArr) {
        StringBuilder builder = new StringBuilder();
        int arrLen = numArr.length;
        for (int i = 0; i < arrLen; i++) {
            builder.append(numArr[i] + " ");
        }
        builder.append("$");
        return builder.toString();
    }

    private int[][] createSortedNumArrArr() {
        int resultNumCount = this.resultNumArrList.size();
        int[][] sortedNumArrArr = new int[resultNumCount][];
        this.resultNumArrList.toArray(sortedNumArrArr);
        Arrays.sort(sortedNumArrArr, (arr1, arr2) -> {
            for (int i = 0; i < 4; i++) {
                if (arr1[i] != arr2[i]) {
                    return arr1[i] - arr2[i];
                }
            }
            return 0;
        });
        return sortedNumArrArr;
    }

    private void printResult() {
        int[][] sortedNumArrArr = this.createSortedNumArrArr();

        StringBuilder builder = new StringBuilder();
        for (int[] numArr : sortedNumArrArr) {
            builder.append(this.createResultNumArrStr(numArr));
        }
        System.out.println(builder.toString());
    }
}
