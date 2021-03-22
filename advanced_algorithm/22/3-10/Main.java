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
            this.countDivisibileNum();
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
            .append("n: " + this.n + ";\n")
            .append("m: " + this.m + ";\n")
            .append("arrA: " + new ArrToString<Integer>().exec(this.arrA) + ";\n")
            .append("arrQ: " + new ArrToString<Integer>().exec(this.arrQ) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int n;
    int m;
    Integer[] arrA;
    Integer[] arrQ;
    boolean notFirst;

    private void countDivisibileNum() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.notFirst = false;

        String[] nmStr = this.scanner.nextLine().split(" ");
        this.n = Integer.parseInt(nmStr[0]);
        this.m = Integer.parseInt(nmStr[1]);
        
        String[] strArrA = this.scanner.nextLine().split(" ");
        this.arrA = new Integer[this.n];
        for (int i = 0; i < this.n; i++) {
            this.arrA[i] = Integer.parseInt(strArrA[i]);
        }
        
        String[] strArrQ = this.scanner.nextLine().split(" ");
        this.arrQ = new Integer[this.m];
        for (int i = 0; i < this.m; i++) {
            this.arrQ[i] = Integer.parseInt(strArrQ[i]);
        }
    }

    private void calResult() {
        for (int q : this.arrQ) {
            int count = 0;
            if (q != 0) {
                for (int a : this.arrA) {
                    if (a != 0 && a % q == 0) {
                        count++;
                    }
                }
            }
            this.printCount(count);
        }
    }

    private void printCount(int count) {
        if (this.notFirst) {
            System.out.print(" ");
        } else {
            this.notFirst = true;
        }
        System.out.print(count);
    }

    private void printResult() {
        System.out.println();
    }
}
