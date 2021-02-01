import java.util.Scanner;
import java.util.NoSuchElementException;

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
        while (true) {
            try {
                this.quickSort();
            } catch (NoSuchElementException e) {
                break;
            }
        }
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
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("nums: " + this.intArrToString(this.nums) + ";\n")
            .append("rangeStack: " + this.rangeStack + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    class Range {
        int start;
        int end;

        Range (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder
                .append("{")
                .append("start: " + this.start + ",")
                .append("end: " + this.end + "")
                .append("}")
            ;
            return builder.toString();
        }
    }

    int numCount = -1;
    int[] nums = null;
    Stack<Range> rangeStack = null;

    private void quickSort() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        String[] numStrArr = this.scanner.nextLine().split(" ");
        this.numCount = Integer.parseInt(numStrArr[0]);
        this.nums = new int[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.nums[i] = Integer.parseInt(numStrArr[i + 1]);
        }
    }

    private void calResult() {
        this.rangeStack = new Stack<>();
        this.rangeStack.push(new Range(0, this.numCount));
        while (!this.rangeStack.empty()) {
            Range r = this.rangeStack.pop();
            this.quickSort(r.start, r.end);
        }
    }

    private void quickSort(int start, int end) {
        int count = end - start;
        if (count < 2) {
            return;
        }
        if (count == 2) {
            int lastIndex = end - 1;
            if (this.nums[start] > this.nums[lastIndex]) {
                int num = this.nums[start];
                this.nums[start] = this.nums[lastIndex];
                this.nums[lastIndex] = num;
            }
            return;
        }
        int num = this.nums[start];
        int index = start;
        int lastIndex = end - 1;
        while (index < lastIndex) {
            int n = this.nums[index + 1];
            if (n < num) {
                this.nums[index] = n;
                this.nums[index + 1] = num;
                index++;
            } else if (n == num) {
                index++;
            } else {
                int lastNum = this.nums[lastIndex];
                this.nums[lastIndex] = n;
                this.nums[index + 1] = lastNum;
                lastIndex--;
            }
        }
        this.rangeStack.push(new Range(start, index));
        this.rangeStack.push(new Range(index + 1, end));
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
