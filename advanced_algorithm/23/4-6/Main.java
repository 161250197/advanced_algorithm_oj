import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
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
            this.getMaxSum();
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
            .append("numToCountMap: " + this.numToCountMap + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount;
    Integer[] nums;
    Map<Integer, Integer> numToCountMap;
    int sum;

    private void getMaxSum() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.sum = 0;
        this.numCount = Integer.parseInt(this.scanner.nextLine());
        String[] numStrs = this.scanner.nextLine().split(" ");
        this.nums = new Integer[this.numCount];
        this.numToCountMap = new HashMap<>();
        for (int i = 0; i < this.numCount; i++) {
            int num = Integer.parseInt(numStrs[i]);
            int count = 1;
            if (this.numToCountMap.containsKey(num)) {
                count = count + this.numToCountMap.get(num);
            }
            this.numToCountMap.put(num, count);
            this.nums[i] = num;
        }
    }

    private void calResult() {
        Arrays.sort(this.nums);
        for (int i = this.numCount - 1; i >= 0; i--) {
            int num = this.nums[i];
            if (this.numToCountMap.get(num) > 0) {
                this.addNum(num);
            }
        }
    }

    private void addNum(int num) {
        this.sum = this.sum + num;
        this.numToCountMap.put(num, this.numToCountMap.get(num) - 1);
        if (this.numToCountMap.containsKey(num - 1)) {
            this.numToCountMap.put(num - 1, this.numToCountMap.get(num - 1) - 1);
        }
    }

    private void printResult() {
        System.out.println(this.sum);
    }
}
