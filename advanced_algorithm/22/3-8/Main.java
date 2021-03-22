import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

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
            this.distanceProblem();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("pairCount: " + this.pairCount + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int pairCount;

    private void distanceProblem() {
        loadData();
        printResult();
    }

    private void loadData() {
        int pairCount = 0;
        int pointCount = Integer.parseInt(this.scanner.nextLine());

        Set<String> pointStrSet = new HashSet<>();
        Map<String, Integer> xToCountMap = new HashMap<>();
        Map<String, Integer> yToCountMap = new HashMap<>();
        for (int i = 0; i < pointCount; i++) {
            String xy = this.scanner.nextLine();
            if (!pointStrSet.contains(xy)) {
                String[] xyArr = xy.split(" ");
                String x = xyArr[0];
                String y = xyArr[1];
                
                int xCount = 1;
                if (xToCountMap.containsKey(x)) {
                    xCount = xToCountMap.get(x);
                    pairCount += xCount;
                    xCount++;
                }
                xToCountMap.put(x, xCount);
                
                int yCount = 1;
                if (yToCountMap.containsKey(y)) {
                    yCount = yToCountMap.get(y);
                    pairCount += yCount;
                    yCount++;
                }
                yToCountMap.put(y, yCount);

                pointStrSet.add(xy);
            }
        }

        this.pairCount = pairCount;
    }

    private void printResult() {
        System.out.println(this.pairCount);
    }
}
