import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
            this.find9DivisorNum();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("n: " + this.n + ";\n")
            .append("count: " + this.count + ";\n")
            .append("primeNumberList: " + this.primeNumberList + ";\n")
            .append("primeNumberSquareList: " + this.primeNumberSquareList + ";\n")
            .append("lastNum: " + this.lastNum + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    long n = -1;
    int count = -1;
    List<Integer> primeNumberList = null;
    List<Integer> primeNumberSquareList = null;
    int lastNum = -1;

    private void find9DivisorNum() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.n = Long.parseLong(this.scanner.nextLine());
        this.count = 0;
        this.primeNumberList = new ArrayList<>();
        this.primeNumberSquareList = new ArrayList<>();
        this.lastNum = 1;
    }

    private void calResult() {
        boolean found = true;
        while (found) {
            found = false;
            int newPrimeNumber = this.getPrimeNumber();
            int newPrimeNumberSquare = newPrimeNumber * newPrimeNumber;

            if (Math.pow(newPrimeNumberSquare, 4) < this.n) {
                this.addCount();
                found = true;
            }

            for (int primeNumberSquare : this.primeNumberSquareList) {
                if (newPrimeNumberSquare * primeNumberSquare < this.n) {
                    this.addCount();
                    found = true;
                } else {
                    break;
                }
            }

            this.primeNumberList.add(newPrimeNumber);
            this.primeNumberSquareList.add(newPrimeNumberSquare);

            if (newPrimeNumber == 2) {
                found = true;
            }
        }
    }

    private int getPrimeNumber() {
        while (true) {
            this.lastNum++;
            boolean isPrime = true;
            for (int primeNumber : primeNumberList) {
                if (this.lastNum % primeNumber == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                return this.lastNum;
            }
        }
    }

    private void addCount() {
        this.count++;
    }

    private void printResult() {
        System.out.println(this.count);
    }
}
