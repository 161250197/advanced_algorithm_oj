import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;

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
            dealConvexHull();
        }
    }

    Point[] points;
    ArrayList<Point> convexHullPoints;

    String pointsToString(Point[] points, String separator) {
        StringBuilder builder = new StringBuilder();
        int pointCount = points.length;
        builder.append(points[0].toString());
        for (int i = 1; i < pointCount; i++) {
            builder.append(separator)
            .append(points[i].toString());
        }
        return builder.toString();
    }
    
    public String toString() {
        return 
            "points: [" + pointsToString(this.points, ", ") + "]" + 
            "convexHullPoints: " + this.convexHullPoints
            ; 
    }
    
    void printData() {
        System.out.println(this);
    }

    void addConvexHullPoint(Point p) {
        if (!this.convexHullPoints.contains(p)) {
            this.convexHullPoints.add(p);
        }
    }

    class Point implements Comparable<Point> {
        int x;
        int y;

        Point (String x, String y) {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
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

    int calCrossProduct(Point p1, Point p2, Point target) {
        int x1 = p2.x - p1.x;
        int x2 = target.x - p1.x;
        int y1 = p2.y - p1.y;
        int y2 = target.y - p1.y;
        int crossProduct = x1 * y2 - x2 * y1;
        return crossProduct;
    }

    void dealConvexHull() {
        loadData();
        calResult();
        printResult();
    }

    void loadData() {
        String pointCountStr = this.scanner.nextLine();
        int pointCount = Integer.parseInt(pointCountStr.trim());
        this.points = new Point[pointCount];
        this.convexHullPoints = new ArrayList<Point>();
        String[] pointXYs = this.scanner.nextLine().split(" ");
        for (int i = 0; i < pointCount; i++) {
            this.points[i] = new Point(pointXYs[i * 2], pointXYs[i * 2 + 1]);
        }
    }

    void calResult() {
        Arrays.sort(this.points);
        int pointCount = this.points.length;
        if (pointCount < 3) {
            return;
        }
        Point p1 = this.points[0];
        Point p2 = this.points[pointCount - 1];
        this.addConvexHullPoint(p1);
        this.addConvexHullPoint(p2);
        this.calAntiClockwiseSideResult(this.points, p1, p2);
        this.calAntiClockwiseSideResult(this.points, p2, p1);
    }

    void calAntiClockwiseSideResult(Point[] points, Point p1, Point p2) {
        int crossProductMax = 0;
        Point pointFarthest = null;
        ArrayList<Point> antiClockwisePointList = new ArrayList<Point>();
        ArrayList<Point> collinearPointList = new ArrayList<Point>();
        
        for (Point p : this.points) {
            int crossProduct = this.calCrossProduct(p1, p2, p);
            // 点位于逆时针方向
            if (crossProduct > 0) 
            {
                antiClockwisePointList.add(p);
                if (crossProduct > crossProductMax) {
                    crossProductMax = crossProduct;
                    pointFarthest = p;
                }
            }
            // 共线
            else if (crossProduct == 0) 
            {
                collinearPointList.add(p);
            }
        }

        if (pointFarthest == null) {
            collinearPointList.forEach(p -> {
                this.addConvexHullPoint(p);
            });
        } else {
            this.addConvexHullPoint(pointFarthest);
            Point[] antiClockwisePointArr = new Point[antiClockwisePointList.size()];
            antiClockwisePointList.toArray(antiClockwisePointArr);
            this.calAntiClockwiseSideResult(antiClockwisePointArr, p1, pointFarthest);
            this.calAntiClockwiseSideResult(antiClockwisePointArr, pointFarthest, p2);
        }
    }

    boolean pointsInOneLine(Point[] points) {
        int pointCount = points.length;
        for (int i = 0; i < pointCount; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < pointCount; j++) {
                Point p2 = points[j];
                for (int k = j + 1; k < pointCount; k++) {
                    Point p3 = points[k];
                    int crossProduct = this.calCrossProduct(p1, p2, p3);
                    if (crossProduct != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void printResult() {
        int convexHullPointCount = this.convexHullPoints.size();
        Point[] convexHullPointArr = new Point[convexHullPointCount];
        this.convexHullPoints.toArray(convexHullPointArr);
        if (convexHullPointCount < 3 || this.pointsInOneLine(convexHullPointArr)) {
            System.out.println("-1");
            return;
        }
        Arrays.sort(convexHullPointArr);
        String result = pointsToString(convexHullPointArr, ", ");
        System.out.println(result);
    }
}
