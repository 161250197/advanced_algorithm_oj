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
            this.canBeRing();
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
        ArrToString<Character> characterArrToString = new ArrToString<>();
        StringBuilder builder = new StringBuilder();
        builder
            .append("strCount: " + this.strCount + ";\n")
            .append("headChars: " + characterArrToString.exec(this.headChars) + ";\n")
            .append("tailChars: " + characterArrToString.exec(this.tailChars) + ";\n")
            .append("unusedArr: " + new ArrToString<Boolean>().exec(this.unusedArr) + ";\n")
            .append("isRing: " + this.isRing + ";\n")
            .append("usedCount: " + this.usedCount + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int strCount = -1;
    Character[] headChars = null;
    Character[] tailChars = null;
    Boolean[] unusedArr = null;
    boolean isRing = false;
    int usedCount = -1;

    private void canBeRing() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.strCount = Integer.parseInt(this.scanner.nextLine());
        String[] strArr = this.scanner.nextLine().split(" ");
        this.headChars = new Character[this.strCount];
        this.tailChars = new Character[this.strCount];
        this.unusedArr = new Boolean[this.strCount];
        for (int i = 0; i < this.strCount; i++) {
            String str = strArr[i];
            int len = str.length();
            this.headChars[i] = str.charAt(0);
            this.tailChars[i] = str.charAt(len - 1);
            this.unusedArr[i] = true;
        }
        this.isRing = false;
        this.usedCount = 0;
    }

    private void calResult() {
        if (this.strCount == 1) {
            this.isRing = this.headChars[0].equals(this.tailChars[0]);
            return;
        }
        this.unusedArr[0] = false;
        this.usedCount++;
        this.findRing(this.headChars[0], this.tailChars[0]);
    }

    private void findRing(Character head, Character tail) {
        for (int i = 1; i < this.strCount; i++) {
            if (this.unusedArr[i]) {
                if (this.headChars[i].equals(tail)) {
                    this.unusedArr[i] = false;
                    this.usedCount++;
                    if (this.usedCount == this.strCount && head.equals(this.tailChars[i])) {
                        this.isRing = true;
                        return;
                    }
                    this.findRing(head, this.tailChars[i]);
                    if (this.isRing) {
                        return;
                    }
                    this.unusedArr[i] = true;
                    this.usedCount--;
                }
                if (this.tailChars[i].equals(head)) {
                    this.unusedArr[i] = false;
                    this.usedCount++;
                    if (this.usedCount == this.strCount && this.headChars[i].equals(tail)) {
                        this.isRing = true;
                        return;
                    }
                    this.findRing(this.headChars[i], tail);
                    if (this.isRing) {
                        return;
                    }
                    this.unusedArr[i] = true;
                    this.usedCount--;
                }
            }
        }
    }

    private void printResult() {
        System.out.println(this.isRing ? 1 : 0);
    }
}
