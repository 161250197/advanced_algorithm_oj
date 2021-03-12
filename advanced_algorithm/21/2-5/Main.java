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
            this.findMagnetZeroPositions();
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
        ArrToString<Integer> intArrToString = new ArrToString<Integer>();

        StringBuilder builder = new StringBuilder();
        builder
            .append("magnetCount: " + this.magnetCount + ";\n")
            .append("magnetPositions: " + intArrToString.exec(this.magnetPositions) + ";\n")
            .append("zeroPositionList: " + this.zeroPositionList + ";\n")
            .append("fromRightDistanceArr: " + intArrToString.exec(this.fromRightDistanceArr) + ";\n")
            .append("fromLeftDistanceArr: " + intArrToString.exec(this.fromLeftDistanceArr) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int magnetCount = -1;
    Integer[] magnetPositions = null;
    List<Integer> zeroPositionList = null;
    Integer[] fromRightDistanceArr = null;
    Integer[] fromLeftDistanceArr = null;

    private void findMagnetZeroPositions() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.zeroPositionList = new ArrayList<>();

        this.loadMagnetCount();
        this.loadMagnets();

        if (this.magnetCount > 0) {
            this.loadFromRightDistanceArr();
            this.loadFromLeftDistanceArr();
        }
    }

    private void loadMagnetCount() {
        this.magnetCount = Integer.parseInt(this.scanner.nextLine());
    }

    private void loadMagnets() {
        String[] magnetPositionStrArr = this.scanner.nextLine().split(" ");
        this.magnetPositions = new Integer[this.magnetCount];
        for (int i = 0; i < this.magnetCount; i++) {
            this.magnetPositions[i] = Integer.parseInt(magnetPositionStrArr[i]) * 100;
        }
    }

    private void loadFromRightDistanceArr() {
        this.fromRightDistanceArr = new Integer[this.magnetCount];
        this.fromRightDistanceArr[this.magnetCount - 1] = 0;
        int lastIndex = this.magnetCount - 1;
        for (int i = lastIndex - 1; i >= 0; i--) {
            this.fromRightDistanceArr[i] = this.magnetPositions[lastIndex] - this.magnetPositions[i];
        }
    }

    private void loadFromLeftDistanceArr() {
        this.fromLeftDistanceArr = new Integer[this.magnetCount];
        this.fromLeftDistanceArr[0] = 0;
        for (int i = 0; i < this.magnetCount; i++) {
            this.fromLeftDistanceArr[i] = this.magnetPositions[i] - this.magnetPositions[0];
        }
    }

    private void calResult() {
        if (this.magnetCount == 0) {
            return;
        }

        if (this.magnetCount == 1) {
            this.zeroPositionList.add(this.magnetPositions[0]);
            return;
        }

        int lastIndex = this.magnetCount - 1;
        for (int i = 0; i < lastIndex; i++) {
            this.calZeroPosition(i);
        }
    }

    private void calZeroPosition(int i) {
        int distance = this.magnetPositions[i + 1] - this.magnetPositions[i];
        int left = 0;
        if (leftZero(i)) {
            this.addZeroPosition(i, 0);
        }
        int right = distance;
        while (left + 1 < right) {
            int half = left + (int)Math.floor((double)(right - left) / 2);
            double force = this.calForce(i, half);
            if (force < 0) {
                right = half;
            } else {
                left = half;
            }
        }
        if (this.calForce(i, left) > -this.calForce(i, right)) {
            this.addZeroPosition(i, right);
        } else {
            this.addZeroPosition(i, left);
        }
    }

    private boolean leftZero(int index) {
        double forceSum = 0;
        for (int i = 0; i < index; i++) {
            forceSum += (1.0 / (this.fromLeftDistanceArr[index] - this.fromLeftDistanceArr[i]));
        }
        for (int i = index + 1; i < this.magnetCount; i++) {
            forceSum -= (1.0 / (this.fromRightDistanceArr[index] - this.fromRightDistanceArr[i]));
        }
        return forceSum == 0;
    }

    private double calForce(int index, int distance) {
        double forceSum = 0;
        for (int i = 0; i <= index; i++) {
            forceSum += (1.0 / (this.fromLeftDistanceArr[index] - this.fromLeftDistanceArr[i] + distance));
        }
        for (int i = index + 1; i < this.magnetCount; i++) {
            forceSum -= (1.0 / (this.fromRightDistanceArr[index] - this.fromRightDistanceArr[i] - distance));
        }
        return forceSum;
    }

    private void addZeroPosition(int index, int distance) {
        int position = this.magnetPositions[index] + distance;
        this.zeroPositionList.add(position);
    }

    private void printResult() {
        int resultCount = this.zeroPositionList.size();
        Integer[] resultArr = new Integer[resultCount];
        this.zeroPositionList.toArray(resultArr);
        if (resultCount > 0) {
            System.out.format("%.2f", resultArr[0] / 100.0);
        }
        for (int i = 1; i < resultCount; i++) {
            System.out.print(" ");
            System.out.format("%.2f", resultArr[i] / 100.0);
        }
        System.out.println();
    }
}
