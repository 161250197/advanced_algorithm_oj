import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
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
            this.calTopologicalSortingCount();
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("toToFromListMap: " + this.toToFromListMap.toString() + ";\n")
            .append("vertexToRemovedMap: " + this.vertexToRemovedMap.toString() + ";\n")
            .append("vertexSet: " + this.vertexSet.toString() + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    Map<String, List<String>> toToFromListMap;
    Map<String, Boolean> vertexToRemovedMap;
    Set<String> vertexSet;
    int methodCount;

    private void calTopologicalSortingCount() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.toToFromListMap = new HashMap<>();
        this.vertexToRemovedMap = new HashMap<>();
        this.vertexSet = new HashSet<>();
        this.methodCount = 0;

        String[] fromAndToStrArr = this.scanner.nextLine().split(",");
        for (String fromAndToStr : fromAndToStrArr) {
            String[] fromAndTo = fromAndToStr.split(" ");
            this.addEdge(fromAndTo[0], fromAndTo[1]);
        }

        initVertexToRemovedMap();
    }

    private void addEdge(String from, String to) {
        this.vertexSet.add(from);
        this.vertexSet.add(to);
        if (this.toToFromListMap.containsKey(to)) {
            List<String> fromList = toToFromListMap.get(to);
            fromList.add(from);
        } else {
            List<String> fromList = new ArrayList<>();
            fromList.add(from);
            this.toToFromListMap.put(to, fromList);
        }
    }

    private void initVertexToRemovedMap() {
        for (String vertex : this.vertexSet) {
            this.vertexToRemovedMap.put(vertex, false);
        }
    }

    private void calResult() {
        this.findNextVertex();
    }
    
    private void findNextVertex() {
        boolean allRemoved = true;
        for (String vertex : this.vertexSet) {
            if (!this.isRemoved(vertex) && this.hasNoFrom(vertex)) {
                allRemoved = false;
                this.removeVertex(vertex);
                this.findNextVertex();
                this.recoverVertex(vertex);
            }
        }
        if (allRemoved) {
            this.addMethodCount();
        }
    }

    private boolean isRemoved(String vertex) {
        return this.vertexToRemovedMap.get(vertex);
    }

    private boolean hasNoFrom(String vertex) {
        if (this.toToFromListMap.containsKey(vertex)) {
            List<String> fromList = this.toToFromListMap.get(vertex);
            for (String from : fromList) {
                if (!this.isRemoved(from)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void removeVertex(String vertex) {
        this.vertexToRemovedMap.put(vertex, true);
    }

    private void recoverVertex(String vertex) {
        this.vertexToRemovedMap.put(vertex, false);
    }

    private void addMethodCount() {
        this.methodCount++;
    }

    private void printResult() {
        System.out.println(this.methodCount);
    }
}
