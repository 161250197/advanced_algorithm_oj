import java.util.Scanner;
import java.util.Set;
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
            this.printBinaryTreeOrderedLevelTraversal();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("numArr: " + this.intArrToString(this.numArr) + ";\n")
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

    private void printBinaryTreeOrderedLevelTraversal() {
        loadData();
        calResult();
        printResult();
    }

    int numCount;
    int[] numArr;
    ArrayList<int[]> resultNumArrList;

    private void loadData() {
        this.resultNumArrList = new ArrayList<>();

        String numCountStr = this.scanner.nextLine();
        this.numCount = Integer.parseInt(numCountStr);

        String[] numStrArr = this.scanner.nextLine().split(" ");
        this.numArr = new int[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.numArr[i] = Integer.parseInt(numStrArr[i]);
        }
    }

    private void calResult() {
        int maxCount = 1;
        int index = 0;

        while (index < this.numCount) {
            Set<Integer> set = new HashSet<>();
            int[] numArr = new int[maxCount];
            int numCount = 0;
            int lastIndex = Math.min(index + maxCount, this.numCount) - 1;
            int newIndex = lastIndex;

            while (newIndex >= index) {
                int num = this.numArr[newIndex];
                if (!set.contains(num)) {
                    set.add(num);
                    numArr[numCount] = num;
                    numCount++;
                }
                newIndex--;
            }

            int[] resultNumArr = new int[numCount];
            System.arraycopy(numArr, 0, resultNumArr, 0, numCount);
            Arrays.sort(resultNumArr);
            resultNumArrList.add(resultNumArr);

            index = index + maxCount;
            maxCount = maxCount * 2;
        }
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
        for (int[] numArr : this.resultNumArrList) {
            String resultNumArrStr = createResultNumArrStr(numArr);
            System.out.println(resultNumArrStr);
        }
    }
}
