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
            this.findStr();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("txt: " + new String(this.txt) + ";\n")
            .append("pat: " + new String(this.pat) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    char[] txt;
    char[] pat;
    boolean found;

    private void findStr() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] strs = this.scanner.nextLine().split(",");
        this.txt = strs[0].toCharArray();
        this.pat = strs[1].toCharArray();
        this.found = false;
    }

    private void calResult() {
        Integer[] offsets = this.createOffsets(this.pat);
        int start = 0;
        int patLen = this.pat.length;
        int lastStart = this.txt.length - patLen;
        int index = 0;
        while (start <= lastStart) {
            while (index < patLen) {
                if (this.txt[start + index] != this.pat[index]) {
                    break;
                }
                index++;
            }
            if (index == 0) {
                start++;
                continue;
            }
            if (index == patLen) {
                this.findAtIndex(start);
            }
            int offset = offsets[index - 1];
            start = start + index - offset;
            index = offset;
        }
    }

    private Integer[] createOffsets(char[] chars) {
        int charsLen = chars.length;
        Integer[] offsets = new Integer[charsLen];
        offsets[0] = 0;
        for (int i = 1; i < charsLen; i++) {
            offsets[i] = 0;
            for (int len = 1; len <= i; len++) {
                int j = 0;
                while (j < len) {
                    if (chars[j] != chars[i + 1 - len + j]) {
                        break;
                    }
                    j++;
                }
                if (j == len) {
                    offsets[i] = len;
                }
            }
        }
        return offsets;
    }

    private void findAtIndex(int index) {
        if (this.found) {
            System.out.print(" ");
        } else {
            this.found = true;
        }
        System.out.print(index);
    }

    private void printResult() {
        System.out.println();
    }
}
