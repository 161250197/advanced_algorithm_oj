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
            this.breadthFirstSearch();
        }
    }

    private String stringArrToString(String[] strs) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + strs[0]);
        int arrLen = strs.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + strs[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("vertexCount: " + this.vertexCount + ";\n")
            .append("firstVertex: " + this.firstVertex + ";\n")
            .append("vertexArr: " + this.stringArrToString(this.vertexArr) + ";\n")
            .append("vertexToAdjacentVertexListMap: " + this.vertexToAdjacentVertexListMap.toString() + ";\n")
            .append("searchedVertexSet: " + this.searchedVertexSet + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int vertexCount = -1;
    String firstVertex = null;
    String[] vertexArr = null;
    Map<String, List<String>> vertexToAdjacentVertexListMap = null;
    Set<String> searchedVertexSet = null;

    private void breadthFirstSearch() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.searchedVertexSet = new HashSet<>();

        this.loadVertexCountAndFirstVertex();
        this.loadVertexArr();
        this.loadAdjacentMatrix();
    }

    private void loadVertexCountAndFirstVertex() {
        String[] vertexCountAndFirstVertexStrArr = this.scanner.nextLine().split(" ");
        this.vertexCount = Integer.parseInt(vertexCountAndFirstVertexStrArr[0]);
        this.firstVertex = vertexCountAndFirstVertexStrArr[1];
    }

    private void loadVertexArr() {
        this.vertexArr = this.scanner.nextLine().split(" ");
    }

    private void loadAdjacentMatrix() {
        this.vertexToAdjacentVertexListMap = new HashMap<>();
        for (int i = 0; i < this.vertexCount; i++) {
            this.loadAdjacentMatrixRow();
        }
    }

    private void loadAdjacentMatrixRow() {
        String[] vertexAndAdjacentVertexArr = this.scanner.nextLine().split(" ");
        String vertex = vertexAndAdjacentVertexArr[0];
        List<String> adjacentVertexList = new ArrayList<>();
        for (int i = 0; i < this.vertexCount; i++) {
            if (vertexAndAdjacentVertexArr[i + 1].equals("1")) {
                adjacentVertexList.add(this.vertexArr[i]);
            }
        }
        this.vertexToAdjacentVertexListMap.put(vertex, adjacentVertexList);
    }

    private void calResult() {
        this.printFirstVertex();
        this.findAdjacentVertex(this.firstVertex);
    }

    private void findAdjacentVertex(String vertex) {
        List<String> adjacentVertexList = this.vertexToAdjacentVertexListMap.get(vertex);
        List<String> unsearchedVertexList = new ArrayList<>();
        for (String adjacentVertex : adjacentVertexList) {
            if (!searched(adjacentVertex)) {
                this.printVertex(adjacentVertex);
                unsearchedVertexList.add(adjacentVertex);
            }
        }
        for (String adjacentVertex : unsearchedVertexList) {
            this.findAdjacentVertex(adjacentVertex);
        }
    }
    
    private boolean searched(String vertex) {
        return this.searchedVertexSet.contains(vertex);
    }

    private void printFirstVertex() {
        System.out.print(this.firstVertex);
        this.searchedVertexSet.add(this.firstVertex);
    }

    private void printVertex(String vertex) {
        System.out.print(" ");
        System.out.print(vertex);
        this.searchedVertexSet.add(vertex);
    }

    private void printResult() {
        System.out.println();
    }
}
