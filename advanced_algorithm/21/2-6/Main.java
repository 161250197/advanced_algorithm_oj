import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
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
            this.sortByOtherArr();
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
            .append("arrLen1: " + this.arrLen1 + ";\n")
            .append("arrLen2: " + this.arrLen2 + ";\n")
            .append("intArr1: " + new ArrToString<Integer>().exec(this.intArr1) + ";\n")
            .append("intArr2: " + new ArrToString<Integer>().exec(this.intArr2) + ";\n")
            .append("intToIndexMap: " + this.intToIndexMap + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int arrLen1 = -1;
    int arrLen2 = -2;
    Integer[] intArr1 = null;
    Integer[] intArr2 = null;
    Map<Integer, Integer> intToIndexMap = null;

    private void sortByOtherArr() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.loadArrLength();
        this.loadIntArrs();
        this.createIntToIndexMap();
    }

    private void loadArrLength() {
        String[] arrLenStrArr = this.scanner.nextLine().split(" ");
        this.arrLen1 = Integer.parseInt(arrLenStrArr[0]);
        this.arrLen2 = Integer.parseInt(arrLenStrArr[1]);
    }

    private void loadIntArrs() {
        String[] intStrArr1 = this.scanner.nextLine().split(" ");
        this.intArr1 = new Integer[this.arrLen1];
        for (int i = 0; i < this.arrLen1; i++) {
            this.intArr1[i] = Integer.parseInt(intStrArr1[i]);
        }

        String[] intStrArr2 = this.scanner.nextLine().split(" ");
        this.intArr2 = new Integer[this.arrLen2];
        for (int i = 0; i < this.arrLen2; i++) {
            this.intArr2[i] = Integer.parseInt(intStrArr2[i]);
        }
    }

    private void createIntToIndexMap() {
        this.intToIndexMap = new HashMap<>();
        for (int i = 0; i < this.arrLen2; i++) {
            this.intToIndexMap.put(this.intArr2[i], i);
        }
    }

    private void calResult() {
        Arrays.sort(intArr1, (n1, n2) -> {
            if (this.intToIndexMap.containsKey(n1)) {
                if (this.intToIndexMap.containsKey(n2)) {
                    return this.intToIndexMap.get(n1) - this.intToIndexMap.get(n2);
                }
                return -1;
            }
            if (this.intToIndexMap.containsKey(n2)) {
                return 1;
            }
            return n1 - n2;
        });
    }

    private void printResult() {
        if (this.arrLen1 == 0) {
            System.out.println();
            return;
        }
        System.out.print(this.intArr1[0]);
        for (int i = 1; i < this.arrLen1; i++) {
            System.out.print(" ");
            System.out.print(this.intArr1[i]);
        }
        System.out.println();
    }
}
