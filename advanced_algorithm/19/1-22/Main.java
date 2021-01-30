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
            this.shellSort();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("nums: " + this.intArrToString(this.nums) + ";\n")
            .append("seps: " + this.intArrToString(this.seps) + ";\n")
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

    int[] nums;
    int[] seps;

    private void shellSort() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] numStrArr = this.scanner.nextLine().split(" ");
        int numCount = numStrArr.length;
        this.nums = new int[numCount];
        for (int i = 0; i < numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrArr[i]);
        }

        String[] sepStrArr = this.scanner.nextLine().split(" ");
        int sepCount = sepStrArr.length;
        this.seps = new int[sepCount];
        for (int i = 0; i < sepCount; i++) {
            this.seps[i] = Integer.parseInt(sepStrArr[i]);
        }
    }

    private void calResult() {
        for (int sep : this.seps) {
            this.sort(sep);
        }
    }

    private void sort(int sep) {
        int numCount = this.nums.length;
        for (int start = 0; start < sep; start++) {
            int subArrCount = (int)Math.ceil((double)(numCount - start) / sep);
            int[] subArr = new int[subArrCount];
            for (int i = 0; i < subArrCount; i++) {
                subArr[i] = this.nums[i * sep + start];
            }
            Arrays.sort(subArr);
            for (int i = 0; i < subArrCount; i++) {
                this.nums[i * sep + start] = subArr[i];
            }
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
        System.out.println(this.createResultNumArrStr(this.nums));
    }
}
