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
            this.checkCanPassExam();
        }
    }

    class Question implements Comparable<Question> {
        int time;
        int score;

        Question (String timeScoreStr) {
            String[] timeScoreStrs = timeScoreStr.split(" ");
            this.time = Integer.parseInt(timeScoreStrs[0]);
            this.score = Integer.parseInt(timeScoreStrs[1]);
        }
        
        public String toString() {
            return "time: " + this.time + "; score: " + this.score;
        }

        public int compareTo(Question q) {
            int result = q.score * this.time - this.score * q.time;
            if (result == 0) {
                return this.time - q.time;
            }
            return result;
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
        StringBuilder builder = new StringBuilder();
        builder
            .append("questionCount: " + this.questionCount + ";\n")
            .append("examTime: " + this.examTime + ";\n")
            .append("passScore: " + this.passScore + ";\n")
            .append("questionArr: " + new ArrToString<Question>().exec(this.questionArr) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    int questionCount;
    int examTime;
    int passScore;
    Question[] questionArr;
    int minTime;

    private void checkCanPassExam() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.minTime = -1;
        String[] infoStrs = this.scanner.nextLine().split(" ");
        this.questionCount = Integer.parseInt(infoStrs[0]);
        this.examTime = Integer.parseInt(infoStrs[1]);
        this.passScore = Integer.parseInt(infoStrs[2]);
        this.questionArr = new Question[this.questionCount];
        for (int i = 0; i < this.questionCount; i++) {
            this.questionArr[i] = new Question(this.scanner.nextLine());
        }
    }

    private void calResult() {
        this.findSolution(0, 0, 0);
    }

    private void findSolution(int index, int usedTime, int scoreSum) {
        if (scoreSum >= this.passScore) {
            this.setMinTime(usedTime);
            return;
        }
        int restTime = this.examTime - usedTime;
        for (int i = index; i < this.questionCount; i++) {
            Question q = this.questionArr[i];
            if (q.time <= restTime) {
                this.findSolution(i + 1, usedTime + q.time, scoreSum + q.score);
            }
        }
    }

    private void setMinTime(int newMinTime) {
        if (this.minTime < 0) {
            this.minTime = newMinTime;
        } else {
            this.minTime = Math.min(this.minTime, newMinTime);
        }
    }

    private void printResult() {
        if (this.minTime < 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES " + this.minTime);
        }
    }
}
