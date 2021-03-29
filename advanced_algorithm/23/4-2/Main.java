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
            this.countOneInMatrix();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("n: " + this.n + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    boolean[] isOdd = { false, false, true, true, true, false, true};
    int n;
    int count;

    private void countOneInMatrix() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.n = Integer.parseInt(this.scanner.nextLine());
        this.count = 0;
    }

    private void calResult() {
        for (int i = 1; i <= this.n; i++) {
            if (this.isOdd[getIsOddIndex(i, i)]) {
                this.count = this.count + 1;
            }
            for (int j = i + 1; j <= this.n; j++) {
                if (this.isOdd[getIsOddIndex(i, j)]) {
                    // 上半和下半都加 1
                    this.count = this.count + 2;
                }
            }
        }
    }

    private int getIsOddIndex(int i, int j) {
        int ij = (i * j) % 7;
        return (ij * ij * ij) % 7;
    }

    private void printResult() {
        System.out.println(this.count);
    }
}
