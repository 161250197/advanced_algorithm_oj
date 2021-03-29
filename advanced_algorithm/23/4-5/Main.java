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
            this.purchaseUsingMinCost();
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
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("shopCount: " + this.shopCount + ";\n")
            .append("costMatrix: " + new MatrixToString<Integer>().exec(this.costMatrix) + ";\n")
            .append("minCosts: " + new ArrToString<Integer>().exec(this.minCosts) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int goodsCount = 3;
    int shopCount;
    Integer[][] costMatrix;
    Integer[] minCosts;

    private void purchaseUsingMinCost() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.shopCount = Integer.parseInt(this.scanner.nextLine());
        this.costMatrix = new Integer[this.shopCount][];
        this.minCosts = new Integer[this.goodsCount];
        for (int i = 0; i < this.shopCount; i++) {
            this.costMatrix[i] = new Integer[this.goodsCount];
            String[] costStrs = this.scanner.nextLine().split(" ");
            for (int j = 0; j < this.goodsCount; j++) {
                this.costMatrix[i][j] = Integer.parseInt(costStrs[j]);
            }
        }
    }

    private void calResult() {
        System.arraycopy(this.costMatrix[0], 0, this.minCosts, 0, this.goodsCount);

        Integer[] newMinCosts = new Integer[this.goodsCount];
        for (int i = 1; i < this.shopCount; i++) {
            for (int goodIndex = 0; goodIndex < this.goodsCount; goodIndex++) {
                newMinCosts[goodIndex] = this.costMatrix[i][goodIndex] + getMinCost(goodIndex);
            }
            System.arraycopy(newMinCosts, 0, this.minCosts, 0, this.goodsCount);
        }
    }

    private Integer getMinCost(int lastGoodIndex) {
        // 不买和上一家一样的
        if (lastGoodIndex == 0) {
            int minCost = this.minCosts[1];
            for (int i = 2; i < this.goodsCount; i++) {
                minCost = Math.min(minCost, this.minCosts[i]);
            }
            return minCost;
        }
        int minCost = this.minCosts[0];
        for (int i = 1; i < this.goodsCount; i++) {
            if (i != lastGoodIndex) {
                minCost = Math.min(minCost, this.minCosts[i]);
            }
        }
        return minCost;
    }

    private void printResult() {
        int minCost = this.minCosts[0];
        for (int i = 1; i < this.goodsCount; i++) {
            minCost = Math.min(minCost, this.minCosts[i]);
        }
        System.out.println(minCost);
    }
}
