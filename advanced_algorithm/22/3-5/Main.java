import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

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
            this.findMax17Number();
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
            .append("numCount: " + this.numCount + ";\n")
            .append("numbers: " + new ArrToString<Integer>().exec(this.numbers) + ";\n")
            .append("unusedArr: " + new ArrToString<Boolean>().exec(this.unusedArr) + ";\n")
            .append("max17Number: " + this.max17Number + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount = -1;
    Integer[] numbers = null;
    Boolean[] unusedArr = null;
    Integer max17Number = null;

    private void findMax17Number() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.max17Number = null;
        String[] numberStrs = this.scanner.nextLine().split("");
        this.numCount = numberStrs.length;
        this.numbers = new Integer[this.numCount];
        this.unusedArr = new Boolean[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.numbers[i] = Integer.parseInt(numberStrs[i]);
            this.unusedArr[i] = true;
        }
    }

    private void calResult() {
        this.createNewDivisible17(0, 0);
    }

    private void createNewDivisible17(int sum, int usedNumCount) {
        Set<Integer> usedNumber = new HashSet<Integer>();
        int newUsedNumCount = usedNumCount + 1;
        for (int i = 0; i < this.numCount; i++) {
            int num = this.numbers[i];
            if (this.unusedArr[i] && !usedNumber.contains(num)) {
                this.unusedArr[i] = false;
                usedNumber.add(num);
                int newSum = sum * 10 + num;
                if (newUsedNumCount == this.numCount) {
                    this.setMaxDivisible17(newSum);
                } else {
                    this.createNewDivisible17(newSum, newUsedNumCount);
                }
                this.unusedArr[i] = true;
            }
        }
    }

    private void setMaxDivisible17(int number) {
        if (undivisble17(number)) {
            return;
        }
        if (this.max17Number == null || this.max17Number < number) {
            this.max17Number = number;
        }
    }

    private boolean undivisble17(int number) {
        return number % 17 != 0;
    }

    private void printResult() {
        if (this.max17Number == null || this.max17Number == 0) {
            System.out.println("Not Possible");
        } else {
            System.out.println(this.max17Number);
        }
    }
}
