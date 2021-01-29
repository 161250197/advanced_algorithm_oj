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
            this.insertSort();
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
            .append("numCount: " + this.numCount + ";\n")
            .append("arr: " + this.intArrToString(this.arr) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount;
    int[] arr;
    int[] sortedArr;

    private void insertSort() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] numCountAndArrStringArr = this.scanner.nextLine().split(" ");
        this.numCount = Integer.parseInt(numCountAndArrStringArr[0]);
        this.arr = new int[this.numCount];
        this.sortedArr = new int[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.arr[i] = Integer.parseInt(numCountAndArrStringArr[i + 1]);
        }
    }

    private void calResult() {
        if (this.numCount == 0) {
            return;
        }
        this.sortedArr[0] = this.arr[0];
        for (int i = 1; i < this.numCount; i++) {
            int num = this.arr[i];
            int j = 0;
            for (; j < i; j++) {
                if (this.sortedArr[j] >= num) {
                    break;
                }
            }
            if (j < i) {
                System.arraycopy(this.sortedArr, j, this.sortedArr, j + 1, i - j);
            }
            this.sortedArr[j] = num;
        }
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
        if (this.numCount == 0) {
            System.out.println("");
            return;
        }
        System.out.println(this.createResultNumArrStr(this.sortedArr));
    }
}
