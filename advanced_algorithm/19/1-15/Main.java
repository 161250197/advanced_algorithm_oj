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
            this.calMinSwapCount();
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
            .append("t: " + this.t + ";\n")
            .append("arr: " + this.intArrToString(this.arr) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    private void calMinSwapCount() {
        loadData();
        calResult();
        printResult();
    }

    int t = -1;
    int[] arr = null;
    int result = -1;

    private void loadData() {
        this.result = 0;

        this.t = Integer.parseInt(this.scanner.nextLine());
        String[] strArr = this.scanner.nextLine().split(" ");
        this.arr = new int[this.t];
        for (int i = 0; i < this.t; i++) {
            this.arr[i] = Integer.parseInt(strArr[i]);
        }
    }

    private void calResult() {
        int[] arr = new int[this.t];
        int[] sortedArr = new int[this.t];
        System.arraycopy(this.arr, 0, arr, 0, this.t);
        System.arraycopy(this.arr, 0, sortedArr, 0, this.t);
        Arrays.sort(sortedArr);

        int index = 0;

        while (index < this.t) {
            if (arr[index] != sortedArr[index]) {
                while (arr[index] != sortedArr[index]) {
                    int num = arr[index];
                    for (int i = index + 1; i < this.t; i++) {
                        if (arr[i] != sortedArr[i] && sortedArr[i] == num) {
                            this.result++;
                            arr[index] = arr[i];
                            arr[i] = num;
                        }
                    }
                }
            }
            index++;
        }
    }

    private void printResult() {
        System.out.println(this.result);
    }
}
