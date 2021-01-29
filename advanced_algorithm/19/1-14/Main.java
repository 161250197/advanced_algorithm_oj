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
            this.mergeArr();
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
        builder.append("k: " + this.k + ";\n");
        builder.append("numMatrix: [\n");
        builder.append("  " + this.intArrToString(this.numMatrix[0]));
        for (int i = 1; i < this.k; i++) {
            builder.append(",\n  " + this.intArrToString(this.numMatrix[i]));
        }
        builder.append("\n];\n");
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    private void mergeArr() {
        loadData();
        calResult();
        printResult();
    }

    int k = -1;
    int[][] numMatrix = null;
    int[] numArr = null;

    private void loadData() {
        this.k = Integer.parseInt(this.scanner.nextLine());
        String[] numStrArr = this.scanner.nextLine().split(" ");
        this.numMatrix = new int[this.k][];
        for (int i = 0; i < this.k; i++) {
            this.numMatrix[i] = new int[this.k];
            int ik = i * k;
            for (int j = 0; j < this.k; j++) {
                this.numMatrix[i][j] = Integer.parseInt(numStrArr[ik + j]);
            }
        }
    }

    private void calResult() {
        this.numArr = this.mergeKArrays(this.numMatrix, this.k);
    }

    private int[] mergeKArrays(int[][] matrix, int k) {
        int[] numArr = new int[k * k];
        for (int i = 0; i < k; i++) {
            System.arraycopy(matrix[i], 0, numArr, i * k, k);
        }
        Arrays.sort(numArr);
        return numArr;
    }

    private String createResultNumArrStr(int[] numArr) {
        StringBuilder builder = new StringBuilder();
        builder.append(numArr[0]);
        int arrLen = numArr.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append(" " + numArr[i]);
        }
        return builder.toString();
    }

    private void printResult() {
        System.out.println(this.createResultNumArrStr(this.numArr));
    }
}
