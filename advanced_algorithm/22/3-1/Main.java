import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
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
            this.findLongestSubSequence();
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
        ArrToString<String> stringArrToString = new ArrToString<>();
        StringBuilder builder = new StringBuilder();
        builder
            .append("str1: " + stringArrToString.exec(this.str1) + ";\n")
            .append("str1Length: " + this.str1Length + ";\n")
            .append("str2: " + stringArrToString.exec(this.str2) + ";\n")
            .append("str2Length: " + this.str2Length + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    String[] str1 = null;
    int str1Length = -1;
    String[] str2 = null;
    int str2Length = -1;
    String[][][] longestSubSequencesMatrix = null;

    private void findLongestSubSequence() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.loadString();
        this.initLongestSubSequencesMatrix();
    }
    
    private void loadString() {
        this.str1 = this.scanner.nextLine().split("");
        this.str1Length = this.str1.length;
        this.str2 = this.scanner.nextLine().split("");
        this.str2Length = this.str2.length;
    }

    private void initLongestSubSequencesMatrix() {
        this.longestSubSequencesMatrix = new String[this.str1Length + 1][][];
        int str2LengthPlus1 = this.str2Length + 1;
        this.longestSubSequencesMatrix[0] = new String[str2LengthPlus1][];
        for (int i = 0; i <= this.str2Length; i++) {
            this.longestSubSequencesMatrix[0][i] = new String[1];
            this.longestSubSequencesMatrix[0][i][0] = "";
        }
        for (int i = 1; i <= this.str1Length; i++) {
            this.longestSubSequencesMatrix[i] = new String[str2LengthPlus1][];
            this.longestSubSequencesMatrix[i][0] = new String[1];
            this.longestSubSequencesMatrix[i][0][0] = "";
        }
    }

    private void calResult() {
        // this.printData();
        for (int i1 = 1; i1 <= this.str1Length; i1++) {
            for (int i2 = 1; i2 <= this.str2Length; i2++) {
                this.longestSubSequencesMatrix[i1][i2] = 
                    this.str1[this.str1Length - i1].equals(this.str2[this.str2Length - i2])
                    ? this.createNewStringArr(this.str1[this.str1Length - i1], this.longestSubSequencesMatrix[i1 -1][i2 - 1])
                    : this.createNewStringArr(i1, i2)
                ;
            }
        }
    }

    private String[] createNewStringArr(String prefix, String[] source) {
        String[] result = this.copyArr(source);
        int length = result.length;
        for (int i = 0; i < length; i++) {
            result[i] = prefix + result[i];
        }
        return result;
    }

    private String[] createNewStringArr(int i1, int i2) {
        String[] arr1 = this.longestSubSequencesMatrix[i1 - 1][i2];
        String[] arr2 = this.longestSubSequencesMatrix[i1][i2 - 1];
        int length1 = arr1[0].length();
        int length2 = arr2[0].length();
        if (length1 > length2) {
            return this.copyArr(arr1);
        }
        if (length1 < length2) {
            return this.copyArr(arr2);
        }
        return joinArr(arr1, arr2);
    }

    private String[] copyArr(String[] arr) {
        int length = arr.length;
        String[] result = new String[length];
        System.arraycopy(arr, 0, result, 0, length);
        return result;
    }

    private String[] joinArr(String[] arr1, String[] arr2) {
        Set<String> set = new HashSet<>();
        for (String str : arr1) {
            set.add(str);
        }
        for (String str : arr2) {
            set.add(str);
        }
        String[] result = new String[set.size()];
        int i = 0;
        for (String str : set) {
            result[i] = str;
            i++;
        }
        return result;
    }

    private void printResult() {
        Arrays.sort(this.longestSubSequencesMatrix[this.str1Length][this.str2Length]);
        for (String str : this.longestSubSequencesMatrix[this.str1Length][this.str2Length]) {
            System.out.println(str);
        }
    }
}
