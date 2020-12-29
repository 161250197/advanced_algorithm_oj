import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private int upsideDownCount;
    
    public static void main(String[] args) {
        new Main().exec();
    }
    
    private void exec() {
        this.scanner = new Scanner(System.in);
        String testCountStr = scanner.nextLine();
        int testCount = Integer.parseInt(testCountStr);
        for (int i = 0; i < testCount; i++) {
            this.execSingleTest();
        }
        this.scanner.close();
    }
    
    private void execSingleTest() {
        this.resetData();
        int[] nums = this.readNums();
        // System.out.println(Arrays.toString(nums));
        // int[] sortedArr = 
            this.mergeSort(nums);
        // System.out.println(Arrays.toString(sortedArr));
        this.printResult();
    }
    
    private void resetData() {
        this.upsideDownCount = 0;
    }
    
    private int[] readNums() {
        String numCountStr = scanner.nextLine();
        int numCount = Integer.parseInt(numCountStr);
        String[] numStrs = scanner.nextLine().split(" ");
        int[] nums = new int[numCount];
        for (int i = 0; i < numCount; i++) {
            nums[i] = Integer.parseInt(numStrs[i]);
        }
        return nums;
    }
    
    private int[] mergeSort(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return nums;
        }
        int mid = (int)(length / 2);
        int leftLength = mid;
        int rightLength = length - mid;
        int[] leftNums = this.mergeSort(this.getSubArr(nums, 0, leftLength));
        int[] rightNums = this.mergeSort(this.getSubArr(nums, mid, rightLength));
        int[] result = new int[length];
        int index = 0;
        int rightIndex = 0;
        for (int i = 0; i < leftLength; i++) {
            int leftNum = leftNums[i];
            while (rightIndex < rightLength && rightNums[rightIndex] < leftNum) {
                int rightNum = rightNums[rightIndex];
                if (rightNum < leftNum) {
                    this.upsideDownCount = this.upsideDownCount + leftLength - i;
                    
                    result[index] = rightNums[rightIndex];
                    rightIndex++;
                    index++;
                } else {
                    break;
                }
            }
            result[index] = leftNum;
            index++;
        }
        while (rightIndex < rightLength) {
            result[index] = rightNums[rightIndex];
            rightIndex++;
            index++;
        }
        return result;
    }
    
    private int[] getSubArr(int[] arr, int left, int length) {
        int[] result = new int[length];
        System.arraycopy(arr, left, result, 0, length);
        return result;
    }
    
    private void printResult() {
        System.out.println(this.upsideDownCount);
    }
}
