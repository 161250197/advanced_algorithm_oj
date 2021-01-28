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
            this.calMathFormula();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("a: " + this.a + ";\n")
            .append("b: " + this.b + ";\n")
            .append("c: " + this.c + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    private void calMathFormula() {
        loadData();
        calResult();
        printResult();
    }

    int a, b, c;
    long result;

    private void loadData() {
        String[] abcStrArr = this.scanner.nextLine().split(" ");
        this.a = Integer.parseInt(abcStrArr[0]);
        this.b = Integer.parseInt(abcStrArr[1]);
        this.c = Integer.parseInt(abcStrArr[2]);
    }

    private void calResult() {
        long sum = 1;
        for (int i = 0; i < this.b; i++) {
            sum = sum * this.a;
            sum = sum % this.c;
            if (sum == 0) {
                break;
            }
        }
        this.result = sum;
    }

    private void printResult() {
        System.out.println(this.result);
    }
}
