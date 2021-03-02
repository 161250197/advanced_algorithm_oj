import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

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
            this.numCountSort();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("nums: " + this.intArrToString(this.nums) + ";\n")
            .append("bookSumMap: " + this.numCountMap.toString() + ";\n")
            .append("numKeys: " + this.intArrToString(this.numKeys) + ";\n")
        ;
        return builder.toString();
    }

    private String intArrToString(int[] ints) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ints[0]);
        int arrLen = ints.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ints[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int numCount = -1;
    int[] nums = null;
    Map<Integer, Integer> numCountMap = null;
    int[] numKeys = null;

    private void numCountSort() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.numCount = Integer.parseInt(this.scanner.nextLine());

        String[] numStrArr = this.scanner.nextLine().split(" ");
        this.nums = new int[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrArr[i]);
        }
    }

    private void createNumCountMap() {
        this.numCountMap = new HashMap<>();
        for (int num : this.nums) {
            int count = 0;
            if (this.numCountMap.containsKey(num)) {
                count = this.numCountMap.get(num);
            }
            count++;
            this.numCountMap.put(num, count);
        }
    }

    private void createNumKeys() {
        Set<Integer> numSet = this.numCountMap.keySet();
        int size = 0;
        this.numKeys = new int[numSet.size()];
        for (int num : numSet) {
            int numCount = this.numCountMap.get(num);
            int index = 0;
            for (; index < size; index++) {
                int n = this.numKeys[index];
                int numC = this.numCountMap.get(n);
                if (
                    numC < numCount ||
                    (numC == numCount && n > num)
                ) {
                    break;
                }
            }
            for (int i = size; i > index; i--) {
                this.numKeys[i] = this.numKeys[i - 1];
            }
            this.numKeys[index] = num;
            size++;
        }
    }

    private void calResult() {
        this.createNumCountMap();
        this.createNumKeys();
    }

    private String createResultNumArrStr(int[] numArr) {
        StringBuilder builder = new StringBuilder();
        builder.append(numArr[0]);
        int arrLen = numArr.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append(" " + numArr[i]);
        }
        return builder.toString();
    }

    private void printResult() {
        int[] resultNums = new int[this.numCount];
        int index = 0;
        for (int num : this.numKeys) {
            int numCount = this.numCountMap.get(num);
            for (int i = 0; i < numCount; i++) {
                resultNums[index] = num;
                index++;
            }
        }
        System.out.println(this.createResultNumArrStr(resultNums));
    }
}
