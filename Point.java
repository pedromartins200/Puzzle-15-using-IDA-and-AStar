public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public static Point locateZero_inBoard(int[][] board) {
        Point point = new Point(0,0);
        int xCoordinate = 0;
        int yCoordinate = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    xCoordinate = i;
                    yCoordinate = j;
                }
            }
        }
        point.setX(xCoordinate);
        point.setY(yCoordinate);
        return point;
    }

    public static Point locatePiece_inBoard(int number, int[][] board) {
        Point point = new Point(-1,-1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == number) {
                    // X and Y coordinates
                    point.setX(i);
                    point.setY(j);
                    return point;
                }
            }
        }
        return null;
    }
}
