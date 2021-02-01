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
                this.mergeSort();
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
    
    private String intsArrToString(int[][] numsArr) {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n  " + this.intArrToString(numsArr[0]));
        int arrLen = numsArr.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append(",\n  " + this.intArrToString(numsArr[i]));
        }
        builder.append("\n]");
        return builder.toString();
    }
    
    private String intArrToString(int[] nums) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + nums[0]);
        int arrLen = nums.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + nums[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    private String rangeArrToString(Range[] ranges) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ranges[0]);
        int arrLen = ranges.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ranges[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("numCount: " + this.numCount + ";\n")
            .append("nums: " + this.intArrToString(this.nums) + ";\n")
            .append("ranges: " + this.rangeArrToString(this.ranges) + ";\n")
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
    Range[] ranges = null;

    private void mergeSort() {
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
        splitRanges();
        joinRanges();
    }

    private void splitRanges() {
        this.ranges = new Range[this.numCount];
        for (int i = 0; i < this.numCount; i++) {
            this.ranges[i] = new Range(i, i + 1);
        }
    }

    private void joinRanges() {
        int rangeCount = this.ranges.length;
        int[][] numsArr = new int[rangeCount][];
        for (int i = 0; i < rangeCount; i++) {
            numsArr[i] = createNums(this.ranges[i]);
        }
        while (rangeCount > 1) {
            int newRangeCount = (int)Math.ceil(rangeCount / 2.0);
            int[][] newNumsArr = new int[newRangeCount][];
            Range[] newRanges = new Range[newRangeCount];
            int index = 0;
            for (int i = 0; i < rangeCount; i = i + 2) {
                if (i + 1 < rangeCount) {
                    newNumsArr[index] = this.mergeSort(numsArr[i], numsArr[i + 1]);
                    int start = this.ranges[i].start;
                    int end = this.ranges[i + 1].end;
                    newRanges[index] = new Range(start, end);
                } else {
                    newNumsArr[index] = numsArr[i];
                    newRanges[index] = this.ranges[i];
                }
                index++;
            }
            this.ranges = newRanges;
            numsArr = newNumsArr;
            rangeCount = newRangeCount;
        }
        this.nums = numsArr[0];
    }

    private int[] createNums(Range r) {
        int start = r.start;
        int end = r.end;
        int count = end - start;
        int[] nums = new int[end - start];
        System.arraycopy(this.nums, start, nums, 0, count);
        return nums;
    }

    private int[] mergeSort(int[] nums1, int[] nums2) {
        int numCount1 = nums1.length;
        int numCount2 = nums2.length;
        int[] nums = new int[numCount1 + numCount2];
        int i1 = 0;
        int i2 = 0;
        int i = 0;
        while (i1 < numCount1 && i2 < numCount2) {
            if (nums1[i1] <= nums2[i2]) {
                nums[i] = nums1[i1];
                i1++;
            } else {
                nums[i] = nums2[i2];
                i2++;
            }
            i++;
        }
        System.arraycopy(nums1, i1, nums, i, numCount1 - i1);
        i = i + numCount1 - i1;
        System.arraycopy(nums2, i2, nums, i, numCount2 - i2);
        return nums;
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
