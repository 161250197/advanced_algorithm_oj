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
    
    int n;
    Point removedPoint;
    Point targetPoint;
    OneFourthRegion removedPointRegion;
    int halfRow, halfCol;
    int left, right, top, bottom;
    LType lType;
    int oneFourthRow, threeFourthRow, oneFourthCol, threeFourthCol;
    
    public String toString() {
        return "n: " + n + "\n" +
            "removedPoint: " + removedPoint + "\n" +
            "targetPoint: " + targetPoint + "\n" +
            "left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom + "\n" +
            "lType: " + lType + "\n"
            ;
    }
    
    void printData() {
        System.out.println(this);
    }
    
    enum OneFourthRegion {
        RIGHT_TOP, LEFT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM
    }
    
    enum LType {
        RIGHT_TOP_EMPTY, LEFT_TOP_EMPTY, LEFT_BOTTOM_EMPTY, RIGHT_BOTTOM_EMPTY
    }
    
    class Point {
        int row;
        int col;
        
        Point(String row, String col) {
            this.row = Integer.parseInt(row);
            this.col = Integer.parseInt(col);
        }
        
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        OneFourthRegion getOneFourthRegion() {
            if (this.row < halfRow) {
                if (this.col < halfCol) {
                    return OneFourthRegion.LEFT_TOP;
                } else {
                    return OneFourthRegion.RIGHT_TOP;
                }
            } else {
                if (this.col < halfCol) {
                    return OneFourthRegion.LEFT_BOTTOM;
                } else {
                    return OneFourthRegion.RIGHT_BOTTOM;
                }
            }
        }
        
        public String toString() {
            return this.row + " " + this.col;
        }
    }
    
    void certainExec() {
        String caseCountStr = this.scanner.nextLine();
        int caseCount = Integer.parseInt(caseCountStr);
        for (int i = 0; i < caseCount; i++) {
            dealChess();
        }
    }
    
    void dealChess() {
        loadData();
        calResult();
    }
    
    void loadData() {
        String[] firstLineItems = this.scanner.nextLine().split(" ");
        this.n = Integer.parseInt(firstLineItems[0]);
        this.removedPoint = new Point(firstLineItems[1], firstLineItems[2]);
        String[] secondLineItems = this.scanner.nextLine().split(" ");
        this.targetPoint = new Point(secondLineItems[0], secondLineItems[1]);
    }
    
    void calResult() {
        splitRegion();
        initLType();
        while (right - left > 2) {
            reduceL();
        }
        printResult();
    }
    
    void splitRegion() {
        left = 0;
        top = 0;
        right = 1;
        bottom = 1;
        for (int i = 0; i < n; i++) {
            right = right << 1;
            bottom = bottom << 1;
        }
        
        halfRow = (int)((top + bottom) / 2);
        halfCol = (int)((left + right) / 2);
        
        removedPointRegion = removedPoint.getOneFourthRegion();
        OneFourthRegion targetPointRegion = targetPoint.getOneFourthRegion();
        
        while (removedPointRegion == targetPointRegion) {
            switch (removedPointRegion) {
                case RIGHT_TOP:
                    bottom = halfRow;
                    left = halfCol;
                    break;
                case LEFT_TOP:
                    bottom = halfRow;
                    right = halfCol;
                    break;
                case LEFT_BOTTOM:
                    top = halfRow;
                    right = halfCol;
                    break;
                case RIGHT_BOTTOM:
                    top = halfRow;
                    left = halfCol;
                    break;
            }
            halfRow = (int)((top + bottom) / 2);
            halfCol = (int)((left + right) / 2);
            removedPointRegion = removedPoint.getOneFourthRegion();
            targetPointRegion = targetPoint.getOneFourthRegion();
        }
    }
    
    void initLType() {
        switch (removedPointRegion) {
            case RIGHT_TOP:
                lType = LType.RIGHT_TOP_EMPTY;
                break;
            case LEFT_TOP:
                lType = LType.LEFT_TOP_EMPTY;
                break;
            case LEFT_BOTTOM:
                lType = LType.LEFT_BOTTOM_EMPTY;
                break;
            case RIGHT_BOTTOM:
                lType = LType.RIGHT_BOTTOM_EMPTY;
                break;
        }
    }
    
    void reduceL() {
        int row = targetPoint.row;
        int col = targetPoint.col;
        int oneFourthRange = (int)((right - left) / 4);

        oneFourthRow = top + oneFourthRange;
        halfRow = oneFourthRow + oneFourthRange;
        threeFourthRow = halfRow + oneFourthRange;
        oneFourthCol = left + oneFourthRange;
        halfCol = oneFourthCol + oneFourthRange;
        threeFourthCol = halfCol + oneFourthRange;

        switch (lType) {
            case RIGHT_TOP_EMPTY:
                reduceRightTopEmptyL(row, col);
                break;
            case LEFT_TOP_EMPTY:
                reduceLeftTopEmptyL(row, col);
                break;
            case LEFT_BOTTOM_EMPTY:
                reduceLeftBottomEmptyL(row, col);
                break;
            case RIGHT_BOTTOM_EMPTY:
                reduceRightBottomEmptyL(row, col);
                break;
        }
    }
    
    void reduceRightTopEmptyL(int row, int col) {
        if (row < oneFourthRow || (row < halfRow && col < oneFourthCol)) {
            bottom = halfRow;
            right = halfCol;
            lType = LType.RIGHT_BOTTOM_EMPTY;
            return;
        }
        if (col < oneFourthCol || (col < halfCol && row >= threeFourthRow)) {
            top = halfRow;
            right = halfCol;
            lType = LType.RIGHT_TOP_EMPTY;
            return;
        }
        if (row >= threeFourthRow || col >= threeFourthCol) {
            top = halfRow;
            left = halfCol;
            lType = LType.LEFT_TOP_EMPTY;
            return;
        }
        left = oneFourthCol;
        right = threeFourthCol;
        top = oneFourthRow;
        bottom = threeFourthRow;
    }
    
    void reduceLeftTopEmptyL(int row, int col) {
        if (row < oneFourthRow || (row < halfRow && col >= threeFourthCol)) {
            bottom = halfRow;
            left = halfCol;
            lType = LType.LEFT_BOTTOM_EMPTY;
            return;
        }
        if (col < oneFourthCol || (col < halfCol && row >= threeFourthRow)) {
            top = halfRow;
            right = halfCol;
            lType = LType.RIGHT_TOP_EMPTY;
            return;
        }
        if (row >= threeFourthRow || col >= threeFourthCol) {
            top = halfRow;
            left = halfCol;
            lType = LType.LEFT_TOP_EMPTY;
            return;
        }
        left = oneFourthCol;
        right = threeFourthCol;
        top = oneFourthRow;
        bottom = threeFourthRow;
    }
    
    void reduceLeftBottomEmptyL(int row, int col) {
        if (row >= threeFourthRow || (row >= halfRow && col >= threeFourthCol)) {
            top = halfRow;
            left = halfCol;
            lType = LType.LEFT_TOP_EMPTY;
            return;
        }
        if (col >= threeFourthCol || (col >= halfCol && row < oneFourthRow)) {
            bottom = halfRow;
            left = halfCol;
            lType = LType.LEFT_BOTTOM_EMPTY;
            return;
        }
        if (row < oneFourthRow || col < oneFourthCol) {
            bottom = halfRow;
            right = halfCol;
            lType = LType.RIGHT_BOTTOM_EMPTY;
            return;
        }
        left = oneFourthCol;
        right = threeFourthCol;
        top = oneFourthRow;
        bottom = threeFourthRow;
    }
    
    void reduceRightBottomEmptyL(int row, int col) {
        if (row >= threeFourthRow || (row >= halfRow && col < oneFourthCol)) {
            top = halfRow;
            right = halfCol;
            lType = LType.RIGHT_TOP_EMPTY;
            return;
        }
        if (col >= threeFourthCol || (col >= halfCol && row < oneFourthRow)) {
            bottom = halfRow;
            left = halfCol;
            lType = LType.LEFT_BOTTOM_EMPTY;
            return;
        }
        if (row < oneFourthRow || col < oneFourthCol) {
            bottom = halfRow;
            right = halfCol;
            lType = LType.RIGHT_BOTTOM_EMPTY;
            return;
        }
        left = oneFourthCol;
        right = threeFourthCol;
        top = oneFourthRow;
        bottom = threeFourthRow;
    }
    
    void printResult() {
        int row = targetPoint.row;
        int col = targetPoint.col;
        switch (lType) {
            case RIGHT_TOP_EMPTY:
                if (row == top) {
                    printResult(top + 1, left, top + 1, left + 1);
                } else {
                    if (col == left) {
                        printResult(top, left, top + 1, left + 1);
                    } else {
                        printResult(top, left, top + 1, left);
                    }
                }
                break;
            case LEFT_TOP_EMPTY:
                if (row == top) {
                    printResult(top + 1, left, top + 1, left + 1);
                } else {
                    if (col == left) {
                        printResult(top, left + 1, top + 1, left + 1);
                    } else {
                        printResult(top, left + 1, top + 1, left);
                    }
                }
                break;
            case LEFT_BOTTOM_EMPTY:
                if (col == left) {
                    printResult(top, left + 1, top + 1, left + 1);
                } else {
                    if (row == top) {
                        printResult(top, left, top + 1, left + 1);
                    } else {
                        printResult(top, left, top, left + 1);
                    }
                }
                break;
            case RIGHT_BOTTOM_EMPTY:
                if (col == left) {
                    if (row == top) {
                        printResult(top, left + 1, top + 1, left);
                    } else {
                        printResult(top, left, top, left + 1);
                    }
                } else {
                    printResult(top, left, top + 1, left);
                }
                break;
        }
    }
    
    void printResult(int row1, int col1, int row2, int col2) {
        System.out.println(new Point(row1, col1) + "," + new Point(row2, col2));
    }
}
