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
            this.findMinOverallPoint();
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
            .append("row: " + this.row + ";\n")
            .append("col: " + this.col + ";\n")
            .append("matrix: " + new MatrixToString<Integer>().exec(this.matrix) + ";\n")
            .append("minMatrix: " + new MatrixToString<Integer>().exec(this.minMatrix) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int row;
    int col;
    Integer[][] matrix;
    Integer[][] minMatrix;

    private void findMinOverallPoint() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] rowColStrs = this.scanner.nextLine().split(" ");
        this.row = Integer.parseInt(rowColStrs[0]);
        this.col = Integer.parseInt(rowColStrs[1]);

        this.matrix = new Integer[this.row][];
        this.minMatrix = new Integer[this.row][];
        String[] valStrs = this.scanner.nextLine().split(" ");
        int i = 0;
        for (int r = 0; r < this.row; r++) {
            this.matrix[r] = new Integer[this.col];
            this.minMatrix[r] = new Integer[this.col];
            for (int c = 0; c < this.col; c++) {
                this.matrix[r][c] = Integer.parseInt(valStrs[i]);
                i++;
            }
        }
    }

    private void calResult() {
        int lastRow = this.row - 1;
        int lastCol = this.col - 1;
        this.minMatrix[lastRow][lastCol] = 1 - this.matrix[lastRow][lastCol];
        // 初始化最后一列
        for (int c = lastCol - 1; c >= 0; c--) {
            this.minMatrix[lastRow][c] = Math.max(1, this.minMatrix[lastRow][c + 1]) - this.matrix[lastRow][c];
        }
        // 初始化最后一行
        for (int r = lastRow - 1; r >= 0; r--) {
            this.minMatrix[r][lastCol] = Math.max(1, this.minMatrix[r + 1][lastCol]) - this.matrix[r][lastCol];
        }
        for (int r = lastRow - 1; r >= 0; r--) {
            for (int c = lastCol - 1; c >= 0; c--) {
                this.minMatrix[r][c] = Math.max(
                    1,
                    Math.min(
                        this.minMatrix[r + 1][c],
                        this.minMatrix[r][c + 1]
                    )
                ) - this.matrix[r][c];
            }
        }
    }

    private void printResult() {
        System.out.println(this.minMatrix[0][0]);
    }
}
