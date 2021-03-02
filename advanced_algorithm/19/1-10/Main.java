import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

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
            this.allocateBooks();
        }
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("bookCount: " + this.bookCount + ";\n")
            .append("bookArr: " + this.intArrToString(this.bookArr) + ";\n")
            .append("studentCount: " + this.studentCount + ";\n")
            ;
        builder.append("bookSumArr: [\n");
        builder.append("  " + this.intArrToString(this.bookSumArr[0]));
        for (int i = 1; i < this.bookCount; i++) {
            builder.append(",\n  " + this.intArrToString(this.bookSumArr[i]));
        }
        builder.append("\n];\n");
        builder.append("bookSumMap: " + bookSumMap.toString() + ";\n");
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int bookCount = -1;
    int[] bookArr = null;
    int studentCount = -1;
    int minMaxBookSum = Integer.MAX_VALUE;
    int[][] bookSumArr = null;
    Map<String, Integer> bookSumMap = null;

    private void allocateBooks() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.minMaxBookSum = Integer.MAX_VALUE;
        this.bookSumMap = new HashMap<>();

        this.bookCount = Integer.parseInt(this.scanner.nextLine());

        String[] bookStrArr = this.scanner.nextLine().split(" ");
        this.bookArr = new int[this.bookCount];
        this.bookSumArr = new int[this.bookCount][];
        for (int i = 0; i < this.bookCount; i++) {
            this.bookArr[i] = Integer.parseInt(bookStrArr[i]);
            this.bookSumArr[i] = new int[this.bookCount];
        }

        this.studentCount = Integer.parseInt(this.scanner.nextLine());
    }

    private void calResult() {
        this.calBookSum();
        this.minMaxBookSum =
            this.bookCount >= this.studentCount
                ? this.calMinMaxBookSum(0, this.bookCount - 1, this.studentCount)
                : -1
        ;
    }

    private void calBookSum() {
        // 使用二维数据存储书本页数和数据
        for (int start = 0; start < this.bookCount; start++) {
            this.bookSumArr[start][start] = this.bookArr[start];
            for (int end = start + 1; end < this.bookCount; end++) {
                this.bookSumArr[start][end] = this.bookSumArr[start][end - 1] + this.bookArr[end];
            }
        }
    }

    private int calMinMaxBookSum(int start, int end, int studentCount) {
        if (studentCount == 1) {
            return this.bookSumArr[start][end];
        }
        // 使用哈希表记录计算结果
        String mapKey = "start: " + start + ", end: " + end + ", studentCount: " + studentCount;
        if (this.bookSumMap.containsKey(mapKey)) {
            return this.bookSumMap.get(mapKey);
        }
        int minMaxBookSum = Integer.MAX_VALUE;
        int lastIndex;
        if ((1 & studentCount) == 1) {
            studentCount = studentCount - 1;
            lastIndex = end - studentCount;
            for (int i = start; i <= lastIndex; i++) {
                int oneBookSum = this.bookSumArr[start][i];
                if (oneBookSum >= minMaxBookSum) {
                    break;
                }
                int lastBookSum = this.calMinMaxBookSum(i + 1, end, studentCount);
                minMaxBookSum = Math.min(minMaxBookSum, Math.max(oneBookSum, lastBookSum));
            }
        } else {
            studentCount = studentCount / 2;
            lastIndex = end - studentCount;
            for (int i = start + studentCount - 1; i <= lastIndex; i++) {
                int leftBookSum = this.calMinMaxBookSum(start, i, studentCount);
                if (leftBookSum >= minMaxBookSum) {
                    break;
                }
                int rightBookSum = this.calMinMaxBookSum(i + 1, end, studentCount);
                minMaxBookSum = Math.min(minMaxBookSum, Math.max(leftBookSum, rightBookSum));
            }
        }
        this.bookSumMap.put(mapKey, minMaxBookSum);
        return minMaxBookSum;
    }

    private void printResult() {
        System.out.println(this.minMaxBookSum);
    }
}
