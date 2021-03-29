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
            this.dealOrderProblem();
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
            .append("orderCount: " + this.orderCount + ";\n")
            .append("xMaxCount: " + this.xMaxCount + ";\n")
            .append("yMaxCount: " + this.yMaxCount + ";\n")
            .append("xArr: " + intArrToString.exec(this.xArr) + ";\n")
            .append("yArr: " + intArrToString.exec(this.yArr) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int orderCount;
    int xMaxCount;
    int yMaxCount;
    Integer[] xArr;
    Integer[] yArr;
    int maxSum;

    private void dealOrderProblem() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.maxSum = 0;
        String[] nxyStrs = this.scanner.nextLine().split(" ");
        this.orderCount = Integer.parseInt(nxyStrs[0]);
        this.xMaxCount = Integer.parseInt(nxyStrs[1]);
        this.yMaxCount = Integer.parseInt(nxyStrs[2]);
        this.xArr = new Integer[this.orderCount];
        this.yArr = new Integer[this.orderCount];
        String[] xStrArr = this.scanner.nextLine().split(" ");
        String[] yStrArr = this.scanner.nextLine().split(" ");
        for (int i = 0; i < this.orderCount; i++) {
            this.xArr[i] = Integer.parseInt(xStrArr[i]);
            this.yArr[i] = Integer.parseInt(yStrArr[i]);
        }
    }

    private void calResult() {
        int sum = 0;
        int xCount = 0;
        int yCount = 0;
        int sameCount = 0;
        for (int i = 0; i < this.orderCount; i++) {
            if (this.xArr[i] > this.yArr[i]) {
                sum += this.xArr[i];
                xCount++;
            } else if (this.xArr[i] == this.yArr[i]) {
                sum += this.xArr[i];
                sameCount++;
            } else {
                sum += this.yArr[i];
                yCount++;
            }
        }
        if (xCount <= this.xMaxCount && yCount <= this.yMaxCount) {
            this.maxSum = sum;
            return;
        }
        if (xCount > this.xMaxCount) {
            this.maxSum = sum - this.reduceXSum(xCount - this.xMaxCount);
            return;
        }
        this.maxSum = sum - this.reduceYSum(yCount - this.yMaxCount);
    }

    private int reduceXSum(int xCount) {
        Integer[] nums = new Integer[xCount];
        for (int i = 0; i < this.orderCount; i++) {
            if (this.xArr[i] > this.yArr[i]) {
                this.insertInt(this.xArr[i] - this.yArr[i], nums, xCount);
            }
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    private int reduceYSum(int yCount) {
        Integer[] nums = new Integer[yCount];
        for (int i = 0; i < this.orderCount; i++) {
            if (this.yArr[i] > this.xArr[i]) {
                this.insertInt(this.yArr[i] - this.xArr[i], nums, yCount);
            }
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    private void insertInt(int num, Integer[] nums, int len) {
        int index = 0;
        while (index < len) {
            if (nums[index] == null || nums[index] >= num) {
                break;
            }
            index++;
        }
        if (index == len) {
            return;
        }
        System.arraycopy(nums, index, nums, index + 1, len - index - 1);
        nums[index] = num;
    }

    private void printResult() {
        System.out.println(this.maxSum);
    }
}
