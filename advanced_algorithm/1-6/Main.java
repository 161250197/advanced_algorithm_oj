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
            int tCount = this.calTCount();
            System.out.println(tCount);
        }
    }
    
    private int calTCount() {
        String powerStr = this.scanner.nextLine();
        long power = Long.parseLong(powerStr);
        int count = 0;
        long t = 1;
        long sum = 1;
        while (sum <= power) {
            count++;
            t++;
            sum += t * t;
        }
        return count;
    }
}
