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
            this.tellMark();
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
        ArrToString<Long> longArrToString = new ArrToString<>();

        StringBuilder builder = new StringBuilder();
        builder
            .append("n: " + this.n + ";\n")
            .append("q: " + this.q + ";\n")
            .append("startArr: " + longArrToString.exec(this.startArr) + ";\n")
            .append("countArr: " + longArrToString.exec(this.countArr) + ";\n")
            .append("qArr: " + longArrToString.exec(this.qArr) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int n = -1;
    int q = -1;
    Long[] startArr = null;
    Long[] countArr = null;
    Long[] qArr = null;

    private void tellMark() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.loadNAndQ();
        this.loadGroups();
        this.loadQArr();
    }

    private void loadNAndQ() {
        String[] nAndQStrArr = this.scanner.nextLine().split(" ");
        this.n = Integer.parseInt(nAndQStrArr[0]);
        this.q = Integer.parseInt(nAndQStrArr[1]);
    }

    private void loadGroups() {
        this.startArr = new Long[this.n];
        this.countArr = new Long[this.n];
        String[] scoreStrArr = this.scanner.nextLine().split(" ");
        for (int i = 0; i < this.n; i++) {
            int startI = i * 2;
            int lastI = startI + 1;
            this.startArr[i] = Long.parseLong(scoreStrArr[startI]);
            this.countArr[i] = Long.parseLong(scoreStrArr[lastI]) - this.startArr[i] + 1;
        }
    }
    
    private void loadQArr() {
        this.qArr = new Long[this.q];
        String[] rankStrArr = this.scanner.nextLine().split(" ");
        for (int i = 0; i < this.q; i++) {
            this.qArr[i] = Long.parseLong(rankStrArr[i]);
        }
    }

    private void calResult() {
        long firstQ = this.qArr[0];
        long score = this.calScore(firstQ);
        this.printFirstScore(score);
        for (int i = 1; i < this.q; i++) {
            long q = this.qArr[i];
            score = this.calScore(q);
            this.printScore(score);
        }
    }

    private long calScore(long q) {
        for (int i = 0; i < this.n; i++) {
            long count = this.countArr[i];
            if (q <= count) {
                return this.startArr[i] + q - 1;
            }
            q = q - count; 
        }
        return -1;
    }

    private void printFirstScore(long score) {
        System.out.print(score);
    }

    private void printScore(long score) {
        System.out.print(" ");
        System.out.print(score);
    }

    private void printResult() {
        System.out.println();
    }
}
