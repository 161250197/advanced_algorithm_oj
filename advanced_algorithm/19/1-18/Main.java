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
            this.bubbleSort();
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
    int[] indexArr;

    private void bubbleSort() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] numCountAndArrStringArr = this.scanner.nextLine().split(" ");
        this.numCount = Integer.parseInt(numCountAndArrStringArr[0]);
        this.arr = new int[this.numCount];
        this.sortedArr = new int[this.numCount];
        this.indexArr = new int[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.arr[i] = Integer.parseInt(numCountAndArrStringArr[i + 1]);
            this.indexArr[i] = 0;
        }
    }

    private void calResult() {
        if (this.numCount == 0) {
            return;
        }
        this.createIndexArr();
        this.createSortedArr();
    }

    private void createIndexArr() {
        for (int i = 0; i < this.numCount; i++) {
            for (int j = i + 1; j < this.numCount; j++) {
                if (this.arr[i] > this.arr[j]) {
                    this.indexArr[i]++;
                } else {
                    this.indexArr[j]++;
                }
            }
        }
    }

    private void createSortedArr() {
        for (int i = 0; i < this.numCount; i++) {
            int index = this.indexArr[i];
            this.sortedArr[index] = this.arr[i];
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
