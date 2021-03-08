import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

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
            this.allocateTasks();
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

    class MatrixToString<T> {
        String exec(T[][] matrix) {
            if (matrix == null) {
                return "null";
            }
            int arrLen = matrix.length;
            if (arrLen == 0) {
                return "[]";
            }
            ArrToString arrToString = new ArrToString<T>();
            StringBuilder builder = new StringBuilder();
            builder.append("[\n" + arrToString.exec(matrix[0]));
            for (int i = 1; i < arrLen; i++) {
                builder.append(",\n" + arrToString.exec(matrix[i]));
            }
            builder.append("\n]");
            return builder.toString();
        }
    }

    private String intMatrixToString(int[][] matrix) {
        int arrLen = matrix.length;
        if (arrLen == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[\n" + this.intArrToString(matrix[0]));
        for (int i = 1; i < arrLen; i++) {
            builder.append(",\n" + this.intArrToString(matrix[i]));
        }
        builder.append("\n]");
        return builder.toString();
    }

    private String intArrToString(int[] ints) {
        int arrLen = ints.length;
        if (arrLen == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ints[0]);
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ints[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("peopleToTaskTimeMatrix: " + new MatrixToString<Integer>().exec(this.peopleToTaskTimeMatrix) + ";\n")
            .append("peopleTaskCount: " + this.peopleTaskCount + ";\n")
            .append("allocationList: " + this.allocationList + ";\n")
            .append("taskFinishedArr: " + new ArrToString<Boolean>().exec(this.taskFinishedArr) + ";\n")
            .append("minTimeSum: " + this.minTimeSum + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    Integer[][] peopleToTaskTimeMatrix = null;
    int peopleTaskCount = -1;
    List<String> allocationList = null;
    Boolean[] taskFinishedArr = null;
    int minTimeSum = Integer.MAX_VALUE;

    private void allocateTasks() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.allocationList = new ArrayList<>();
        this.minTimeSum = Integer.MAX_VALUE;

        this.loadPeopleTaskCount();
        this.loadPeopleToTaskTimeMatrix();
    }

    private void loadPeopleTaskCount() {
        this.peopleTaskCount = Integer.parseInt(this.scanner.nextLine());
        int countPlusOne = this.peopleTaskCount + 1;

        this.peopleToTaskTimeMatrix = new Integer[countPlusOne][];
        this.taskFinishedArr = new Boolean[countPlusOne];

        for (int i = 1; i <= this.peopleTaskCount; i++) {
            this.peopleToTaskTimeMatrix[i] = new Integer[countPlusOne];
            this.taskFinishedArr[i] = false;
            for (int j = 1; j <= this.peopleTaskCount; j++) {
                this.peopleToTaskTimeMatrix[i][j] = -1;
            }
        }

    }

    private void loadPeopleToTaskTimeMatrix() {
        String[] peopleToTaskTimeStrArr = this.scanner.nextLine().split(",");
        for (String peopleToTaskTimeStr : peopleToTaskTimeStrArr) {
            String[] peopleTaskTimeStrArr = peopleToTaskTimeStr.split(" ");
            int people = Integer.parseInt(peopleTaskTimeStrArr[0]);
            int task = Integer.parseInt(peopleTaskTimeStrArr[1]);
            int time = Integer.parseInt(peopleTaskTimeStrArr[2]);
            this.setPeopleTaskTime(people, task, time);
        }
    }

    private void setPeopleTaskTime(int people, int task, int time) {
        this.peopleToTaskTimeMatrix[people][task] = time;
    }

    private void calResult() {
        this.allocateFirst();
    }

    private void allocateFirst() {
        int people = 1;
        int newPeople = people + 1;
        for (int task = 1; task <= this.peopleTaskCount; task++) {
            int timeSum = this.peopleToTaskTimeMatrix[people][task];
            this.finishTask(task);
            this.allocateNext(timeSum, newPeople, "" + task);
            this.resetTask(task);
        }
    }

    private void finishTask(int task) {
        this.taskFinishedArr[task] = true;
    }

    private void resetTask(int task) {
        this.taskFinishedArr[task] = false;
    }

    private boolean taskFinished(int task) {
        return this.taskFinishedArr[task];
    }

    private void allocateNext(int timeSum, int people, String allocation) {
        if (timeSum >= this.minTimeSum) {
            return;
        }
        boolean allFinished = people == this.peopleTaskCount;
        int newPeople = people + 1;
        for (int task = 1; task <= this.peopleTaskCount; task++) {
            if (!taskFinished(task)) {
                int newTimeSum = timeSum + this.peopleToTaskTimeMatrix[people][task];
                String newAllocation = allocation + " " + task;
                this.finishTask(task);
                if (allFinished) {
                    this.addAllocation(newTimeSum, newAllocation);
                }
                this.allocateNext(newTimeSum, newPeople, newAllocation);
                this.resetTask(task);
            }
        }
    }

    private void addAllocation(int timeSum, String allocation) {
        if (timeSum > this.minTimeSum) {
            return;
        }
        if (timeSum < this.minTimeSum) {
            this.minTimeSum = timeSum;
            this.allocationList = new ArrayList<>();
        }
        this.allocationList.add(allocation);
    }

    private void printResult() {
        int allocationCount = this.allocationList.size();
        String[] allocationStrArr = new String[allocationCount];
        this.allocationList.toArray(allocationStrArr);
        Arrays.sort(allocationStrArr, (allocation1, allocation2) -> {
            return -allocation1.compareTo(allocation2);
        });

        if (allocationCount > 0) {
            System.out.print(allocationStrArr[0]);
            for (int i = 1; i < allocationCount; i++) {
                System.out.print(",");
                System.out.print(allocationStrArr[i]);
            }
        }

        System.out.println();
    }
}
