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
            this.findClosestPair();
        }
    }
    
    private String pointArrToString(Point[] points) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + points[0]);
        int arrLen = points.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + points[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }

    private String pointPairArrToString(PointPair[] pps) {
        if (pps == null || pps.length == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + pps[0]);
        int arrLen = pps.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + pps[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    private String rangeArrToString(Range[] ranges) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ranges[0]);
        int arrLen = ranges.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ranges[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("pointCount: " + this.pointCount + ";\n")
            .append("points: " + this.pointArrToString(this.points) + ";\n")
            .append("minDistance: " + this.minDistance + ";\n")
            .append("closestPointPairs: " + this.pointPairArrToString(this.closestPointPairs) + ";\n")
            .append("ranges: " + this.rangeArrToString(this.ranges) + ";\n")
        ;
        return builder.toString();
    }
    
    void printData() {
        System.out.println(this);
    }

    class Point implements Comparable<Point> {
        double x;
        double y;

        Point (String x, String y) {
            this.x = Double.parseDouble(x);
            this.y = Double.parseDouble(y);
        }
        
        public String toString() {
            return this.x + " " + this.y;
        }

        public int compareTo(Point p) {
            if (this.x < p.x || (this.x == p.x && this.y < p.y)) {
                return -1;
            }
            if (this.x > p.x || (this.x == p.x && this.y > p.y)) {
                return 1;
            }
            return 0;
        }
    }

    class PointPair implements Comparable<PointPair> {
        Point p1;
        Point p2;

        PointPair (Point p1, Point p2) {
            if (p1.compareTo(p2) <= 0) {
                this.p1 = p1;
                this.p2 = p2;
            } else {
                this.p1 = p2;
                this.p2 = p1;
            }
        }

        double getDistance() {
            double dx = p1.x - p2.x;
            double dy = p1.y - p2.y;
            return dx * dx + dy * dy;
        }
        
        public String toString() {
            return this.p1 + "," + this.p2;
        }

        public int compareTo(PointPair pp) {
            if (
                this.p1.compareTo(pp.p1) < 0 ||
                (
                    this.p1.compareTo(pp.p1) == 0 &&
                    this.p2.compareTo(pp.p2) < 0
                )
            ) {
                return -1;
            }
            if (
                this.p1.compareTo(pp.p1) > 0 ||
                (
                    this.p1.compareTo(pp.p1) == 0 &&
                    this.p2.compareTo(pp.p2) > 0
                )
            ) {
                return 1;
            }
            return 0;
        }
    }

    class Range {
        int start;
        int end;

        Range (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder
                .append("{")
                .append("start: " + this.start + ",")
                .append("end: " + this.end + "")
                .append("}")
            ;
            return builder.toString();
        }
    }

    int pointCount = -1;
    Point[] points = null;
    double minDistance = -1;
    PointPair[] closestPointPairs = null;
    Range[] ranges = null;

    private void findClosestPair() {
        loadData();
        calResult();
        printResult();
    }

    private void loadData() {
        this.minDistance = Double.MAX_VALUE;
        this.closestPointPairs = new PointPair[0];

        String[] pointStrArr = this.scanner.nextLine().split(",");
        this.pointCount = pointStrArr.length;
        this.points = new Point[this.pointCount];
        for (int i = 0; i < this.pointCount; i++) {
            String[] xyStrArr = pointStrArr[i].split(" ");
            this.points[i] = new Point(xyStrArr[0], xyStrArr[1]);
        }
        Arrays.sort(this.points);
    }

    private void createRanges() {
        this.ranges = new Range[this.pointCount];
        for (int i = 0; i < this.pointCount; i++) {
            this.ranges[i] = new Range(i, i + 1);
        }
    }

    private void addClosestPointPair(PointPair pp) {
        int index = this.closestPointPairs.length;
        PointPair[] newPPs = new PointPair[index + 1];
        System.arraycopy(this.closestPointPairs, 0, newPPs, 0, index);
        newPPs[index] = pp;
        this.closestPointPairs = newPPs;
    }

    private void setClosestPointPair(PointPair pp) {
        this.closestPointPairs = new PointPair[1];
        this.closestPointPairs[0] = pp;
    }

    private void calClosestPair(Range r1, Range r2) {
        for (int i = r1.start; i < r1.end; i++) {
            Point p1 = this.points[i];
            for (int j = r2.start; j < r2.end; j++) {
                Point p2 = this.points[j];
                PointPair pp = new PointPair(p1, p2);
                double d = pp.getDistance();
                if (d < this.minDistance) {
                    this.minDistance = d;
                    this.setClosestPointPair(pp);
                } else if (d == this.minDistance) {
                    this.addClosestPointPair(pp);
                }
            }
        }
    }

    private void calResult() {
        this.createRanges();
        int rangeCount = this.ranges.length;
        while (rangeCount > 1) {
            int newRangeCount = (int)(Math.ceil(rangeCount / 2.0));
            Range[] newRanges = new Range[newRangeCount];
            for (int i = 0; i < newRangeCount; i++) {
                Range r1 = this.ranges[i * 2];
                if (i * 2 + 1 < rangeCount) {
                    Range r2 = this.ranges[i * 2 + 1];
                    this.calClosestPair(r1, r2);
                    newRanges[i] = new Range(r1.start, r2.end);
                } else {
                    newRanges[i] = r1;
                }
            }
            this.ranges = newRanges;
            rangeCount = newRangeCount;
        }
    }

    private String createResultString(PointPair[] pps) {
        if (pps == null || pps.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(pps[0]);
        int arrLen = pps.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + pps[i]);
        }
        return builder.toString().replaceAll("\\.0+(?=[ ,]|$)", "");
    }

    private void printResult() {
        System.out.println(this.createResultString(this.closestPointPairs));
    }
}
