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
            this.paintPetHouse();
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
        // TODO 修改属性信息
        StringBuilder builder = new StringBuilder();
        builder
            .append("painterCount: " + this.painterCount + ";\n")
            .append("boardCount: " + this.boardCount + ";\n")
            .append("minTime: " + this.minTime + ";\n")
            .append("boardArr: " + new ArrToString<Integer>().exec(this.boardArr) + ";\n")
            .append("timeSumArr: " + new MatrixToString<Integer>().exec(this.timeSumArr) + ";\n")
            .append("timeMap: " + this.timeMap + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int painterCount = -1;
    int boardCount = -1;
    int minTime = Integer.MAX_VALUE;
    Integer[] boardArr = null;
    Integer[][] timeSumArr = null;
    Map<String, Integer> timeMap = null;

    private void paintPetHouse() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.minTime = Integer.MAX_VALUE;
        this.timeMap = new HashMap<>();

        String[] painterCountAndBoardCountStr = this.scanner.nextLine().split(" ");
        this.painterCount = Integer.parseInt(painterCountAndBoardCountStr[0]);
        this.boardCount = Integer.parseInt(painterCountAndBoardCountStr[1]);
        this.boardArr = new Integer[this.boardCount];

        String[] boardStrArr = this.scanner.nextLine().split(" ");
        this.timeSumArr = new Integer[this.boardCount][];
        for (int i = 0; i < this.boardCount; i++) {
            this.boardArr[i] = Integer.parseInt(boardStrArr[i]);
            this.timeSumArr[i] = new Integer[this.boardCount];
        }
    }

    private void calResult() {
        // 需要处理画匠比板子多的情况
        if (this.painterCount > this.boardCount) {
            this.minTime = this.findLongestBoard();
            return;
        }
        this.calTimeSum();
        this.minTime = this.calMinTime(0, this.boardCount - 1, this.painterCount);
    }

    private void calTimeSum() {
        // 使用二维数据存储时间开销数据
        for (int start = 0; start < this.boardCount; start++) {
            this.timeSumArr[start][start] = this.boardArr[start];
            for (int end = start + 1; end < this.boardCount; end++) {
                this.timeSumArr[start][end] = this.timeSumArr[start][end - 1] + this.boardArr[end];
            }
        }
    }

    private int findLongestBoard() {
        int longest = this.boardArr[0];
        for (int i = 1; i < this.boardCount; i++) {
            if (this.boardArr[i] > longest) {
                longest = this.boardArr[i];
            }
        }
        return longest;
    }

    private int calMinTime(int start, int end, int painterCount) {
        if (painterCount == 1) {
            return this.timeSumArr[start][end];
        }
        // 使用哈希表记录计算结果
        String mapKey = "start: " + start + ", end: " + end + ", painterCount: " + painterCount;
        if (this.timeMap.containsKey(mapKey)) {
            return this.timeMap.get(mapKey);
        }
        int minTime = Integer.MAX_VALUE;
        int lastIndex;
        if ((1 & painterCount) == 1) {
            painterCount = painterCount - 1;
            lastIndex = end - painterCount;
            for (int i = start; i <= lastIndex; i++) {
                int onePainterTime = this.timeSumArr[start][i];
                if (onePainterTime >= minTime) {
                    break;
                }
                int lastPaintersTime = this.calMinTime(i + 1, end, painterCount);
                minTime = Math.min(minTime, Math.max(onePainterTime, lastPaintersTime));
            }
        } else {
            painterCount = painterCount / 2;
            lastIndex = end - painterCount;
            for (int i = start + painterCount - 1; i <= lastIndex; i++) {
                int leftPaintersTime = this.calMinTime(start, i, painterCount);
                if (leftPaintersTime >= minTime) {
                    break;
                }
                int rightPaintersTime = this.calMinTime(i + 1, end, painterCount);
                minTime = Math.min(minTime, Math.max(leftPaintersTime, rightPaintersTime));
            }
        }
        this.timeMap.put(mapKey, minTime);
        return minTime;
    }

    private void printResult() {
        System.out.println(this.minTime);
    }
}
