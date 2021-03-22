import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
            this.calMaxSumDownQueue();
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
            .append("showed: " + new ArrToString<Boolean>().exec(this.showed) + ";\n")
            .append("result: " + this.result + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    Boolean[] showed;
    String result;

    private void calMaxSumDownQueue() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        char[] charArr = this.scanner.nextLine().toCharArray();
        this.showed = new Boolean[26];
        for (char c : charArr) {
            this.showed['Z' - c] = true;
        }
        this.result = "";
    }

    private void calResult() {
        for (int diff = 1; diff < 26; diff++) {
            this.findArithmeticQueue(diff);
        }
    }

    private void findArithmeticQueue(int diff) {
        Boolean[] usedArr = new Boolean[26];
        for (int i = 0; i < 26; i++) {
            if (this.showed[i] != null && usedArr[i] == null) {
                usedArr[i] = true;
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                int nextIndex = i + diff;
                while (nextIndex < 26 && this.showed[nextIndex] != null && usedArr[nextIndex] == null) {
                    usedArr[nextIndex] = true;
                    indexList.add(nextIndex);
                    nextIndex += diff;
                }
                String result = this.createResult(indexList);
                this.setResult(result);
            }
        }
    }

    private String createResult(List<Integer> indexList) {
        int len = indexList.size();
        char[] charArr = new char[len];
        int index = 0;
        for (int i : indexList) {
            charArr[index] = (char)('Z' - i);
            index++;
        }
        return new String(charArr);
    }

    private void setResult(String newResult) {
        int newLen = newResult.length();
        int oldLen = this.result.length();
        if (
            newLen > oldLen || 
            (newLen == oldLen && newResult.compareTo(this.result) > 0)
        ) {
            this.result = newResult;
        }
    }

    private void printResult() {
        System.out.println(this.result);
    }
}
