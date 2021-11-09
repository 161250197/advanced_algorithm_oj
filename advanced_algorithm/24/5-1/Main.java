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
            this.numsInGrids();
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
        MatrixToString<Integer> intMatrixToString = new MatrixToString<>();
        StringBuilder builder = new StringBuilder();
        builder
            .append("size: " + this.size + ";\n")
            .append("numGrids: " + intMatrixToString.exec(this.numGrids) + ";\n")
            .append("minSumGrids: " + intMatrixToString.exec(this.numGrids) + ";\n")
            .append("minSum: " + this.minSum + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int size;
    Integer[][] numGrids;
    // 到达某个格子需要的最小数字和
    Integer[][] minSumGrids;
    Integer minSum;

    private void numsInGrids() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.size = Integer.parseInt(this.scanner.nextLine());
        this.numGrids = new Integer[this.size][];
        this.minSumGrids = new Integer[this.size][];
        String[] numStrs = this.scanner.nextLine().split(" ");
        int strIndex = 0;
        for (int row = 0; row < this.size; row++) {
            this.numGrids[row] = new Integer[this.size];
            this.minSumGrids[row] = new Integer[this.size];
            for (int col = 0; col < this.size; col++) {
                this.numGrids[row][col] = Integer.parseInt(numStrs[strIndex]);
                strIndex++;
            }
        }
        this.minSum = null;
    }

    class Grid {
        int row;
        int col;
        Grid(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public String toString() {
            return "row: " + this.row + ", col: " + this.col;
        }
    }

    private void calResult() {
        this.minSumGrids[0][0] = this.numGrids[0][0];
        Grid[] fromGrids = { new Grid(0,0) };
        int fromGridCount = 1;
        while (fromGridCount != 0) {
            Grid[] newFromGrids = new Grid[fromGridCount * 4];
            int newFromGridCount = 0;
            for (int i = 0; i < fromGridCount; i++) {
                Grid g = fromGrids[i];
                int row = g.row;
                int col = g.col;
                int fromMinSum = this.minSumGrids[row][col];
                int up = row - 1;
                int down = row + 1;
                int left = col - 1;
                int right = col + 1;
                // 向上
                if (up >= 0 && this.canEnterGrid(fromMinSum, up, col)) {
                    setMinSumGrid(fromMinSum, up, col);
                    newFromGrids[newFromGridCount] = new Grid(up, col);
                    newFromGridCount++;
                }
                // 向右
                if (right < this.size && this.canEnterGrid(fromMinSum, row, right)) {
                    setMinSumGrid(fromMinSum, row, right);
                    newFromGrids[newFromGridCount] = new Grid(row, right);
                    newFromGridCount++;
                }
                // 向上
                if (down < this.size && this.canEnterGrid(fromMinSum, down, col)) {
                    setMinSumGrid(fromMinSum, down, col);
                    newFromGrids[newFromGridCount] = new Grid(down, col);
                    newFromGridCount++;
                }
                // 向上
                if (left >= 0 && this.canEnterGrid(fromMinSum, row, left)) {
                    setMinSumGrid(fromMinSum, row, left);
                    newFromGrids[newFromGridCount] = new Grid(row, left);
                    newFromGridCount++;
                }
            }
            fromGrids = newFromGrids;
            fromGridCount = newFromGridCount;
        }
    }

    private void setMinSumGrid(int fromMinSum, int row, int col) {
        this.minSumGrids[row][col] = fromMinSum + this.numGrids[row][col];
        if (row == this.size - 1 && col == this.size - 1) {
            this.setMinSum(this.minSumGrids[row][col]);
        }
    }

    private boolean canEnterGrid(int fromMinSum, int row, int col) {
        int newMinSum = fromMinSum + this.numGrids[row][col];
        return this.integerIsNullOrLarger(this.minSum, newMinSum) && this.integerIsNullOrLarger(this.minSumGrids[row][col], newMinSum);
    }

    private void setMinSum(int newMinSum) {
        if (this.integerIsNullOrLarger(this.minSum, newMinSum)) {
            this.minSum = newMinSum;
        }
    }

    private boolean integerIsNullOrLarger(Integer left, int right) {
        return left == null || left > right;
    }

    private void printResult() {
        System.out.println(this.minSum);
    }
}
