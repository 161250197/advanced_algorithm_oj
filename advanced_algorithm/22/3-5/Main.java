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
            this.dealProblem();
        }
    }
    
    public String toString() {
        // TODO 修改属性信息
        StringBuilder builder = new StringBuilder();
        builder
            .append("test: " + this.test + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    // TODO 添加属性
    String test = "test";

    // TODO 修改方法名称
    private void dealProblem() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        // TODO
    }

    private void calResult() {
        // TODO
        this.printData();
    }

    private void printResult() {
        // TODO
    }
}
