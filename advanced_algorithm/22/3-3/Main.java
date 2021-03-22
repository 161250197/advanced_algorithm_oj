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
            this.findMax1Count();
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
        MatrixToString<Integer> integerMatrixToString = new MatrixToString<Integer>();
        StringBuilder builder = new StringBuilder();
        builder
            .append("row: " + this.row + ";\n")
            .append("col: " + this.col + ";\n")
            .append("matrix: " + integerMatrixToString.exec(this.matrix) + ";\n")
            .append("max: " + this.max + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int row = -1;
    int col = -1;
    Integer[][] matrix = null;
    int max = -1;

    private void findMax1Count() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] rowColStrArr = this.scanner.nextLine().split(" ");
        this.row = Integer.parseInt(rowColStrArr[0]);
        this.col = Integer.parseInt(rowColStrArr[1]);

        this.matrix = new Integer[this.row][];
        for (int r = 0; r < this.row; r++) {
            String[] rowStrArr = this.scanner.nextLine().split(" ");
            this.matrix[r] = new Integer[this.col];
            for (int c = 0; c < this.col; c++) {
                this.matrix[r][c] = Integer.parseInt(rowStrArr[c]);
            }
        }

        this.max = 0;
    }

    private void calResult() {
        for (int rStart = 0; rStart < this.row; rStart++) {
            for (int cStart = 0; cStart < this.col; cStart++) {
                if (this.matrix[rStart][cStart] == 1) {
                    this.calMax1Count(rStart, cStart);
                }
            }
        }
    }

    private void calMax1Count(int rStart, int cStart) {
        int maxCol = cStart + 1;
        while (maxCol < this.col && this.matrix[rStart][maxCol] == 1) {
            maxCol++;
        }
        this.updateMax(rStart, rStart + 1, cStart, maxCol);

        for (int rEnd = rStart + 1; rEnd < this.row; rEnd++) {
            int cEnd = cStart;
            for (; cEnd < maxCol; cEnd++) {
                if (this.matrix[rEnd][cEnd] == 0) {
                    maxCol = cEnd;
                    break;
                }
            }
            this.updateMax(rStart, rEnd + 1, cStart, cEnd);
        }
    }

    private void updateMax(int rStart, int rEnd, int cStart, int cEnd) {
        this.max = Math.max(this.max, (rEnd - rStart) * (cEnd - cStart));
    }

    private void printResult() {
        System.out.println(this.max);
    }
}
