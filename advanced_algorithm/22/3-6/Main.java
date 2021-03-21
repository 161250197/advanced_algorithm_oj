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
            this.calCowCount();
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("year: " + this.year + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    long year = -1;
    Long[][] matrix;

    private void calCowCount() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.year = Long.parseLong(this.scanner.nextLine());
    }

    private void calResult() {
        this.matrix = this.getYearMatrix(this.year);
    }

    private Long[][] getYearMatrix(long y) {
        if (y <= 1) {
            return new Long[][] {
                {1l, 1l},
                {1l, 0l}
            };
        }
        Long[][] matrix = new Long[][]{
            {1l, 0l},
            {0l, 1l}
        };
        if ((y & 1) == 1) {
            matrix = this.mulMatrix(matrix, new Long[][] {
                {1l, 1l},
                {1l, 0l}
            });
        }
        Long[][] subMatrix = this.getYearMatrix((long)Math.floor(y / 2));
        return this.mulMatrix(matrix, this.mulMatrix(subMatrix, subMatrix));
    }

    private Long[][] mulMatrix(Long[][] matrix1, Long[][] matrix2) {
        int modulo = 1000000007;
        return new Long[][] {
            {
                (matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0]) % modulo,
                (matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1]) % modulo
            },
            {
                (matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0]) % modulo,
                (matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1]) % modulo
            }
        };
    }

    private void printResult() {
        System.out.println(this.matrix[0][0]);
    }
}
