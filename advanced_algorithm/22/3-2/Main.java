import java.util.Scanner;
import java.util.Arrays;

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
            this.findKMin();
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
        StringBuilder builder = new StringBuilder();
        builder
            .append("ints: " + new ArrToString<Integer>().exec(this.ints) + ";\n")
            .append("k:" + this.k + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    Integer[] ints = null;
    int k = -1;

    private void findKMin() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] intStrArr = this.scanner.nextLine().split(" ");
        String[] rangeStrArr = this.scanner.nextLine().split(" ");
        int start = Integer.parseInt(rangeStrArr[0]) - 1;
        int length = Integer.parseInt(rangeStrArr[1]) - start;
        this.ints = new Integer[length];
        for (int i = 0; i < length; i++) {
            this.ints[i] = Integer.parseInt(intStrArr[start + i]);
        }
        this.k = Integer.parseInt(this.scanner.nextLine());
    }

    private void calResult() {
        Arrays.sort(this.ints);
    }

    private void printResult() {
        System.out.println(this.ints[this.k - 1]);
    }
}
