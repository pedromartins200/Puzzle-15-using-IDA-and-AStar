import java.util.*;

class Puzzle15 implements Ilayout, Cloneable {
    private int cost; // cost from the receiver to its father
    private String s;
    private int dim = 4;
    private int[][] goal = new int[4][4];
    private int[][] board = new int[4][4];
    private HashMap<Integer, Point> map_goal = new HashMap<Integer, Point>();

    public Puzzle15() {

    }

    public Puzzle15(String str) {
        this(str, 0);
    }

    public String getString() {
        return this.s;
    }

    public int[][] getGoal() {
        return this.goal;
    }

    public void setString(String str) {
        this.s = str;
    }

    public Puzzle15(String str, int cost) {
        int position = 0;
        this.s = str;
        this.cost = cost;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.board[i][j] = Character.getNumericValue(s.charAt(position++));
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        Puzzle15 puzzle = (Puzzle15) super.clone();
        puzzle.board = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                puzzle.board[i][j] = this.board[i][j];
            }
        }
        return puzzle;
    }

    @Override
    public int hashCode() {
        return 17 * Arrays.deepHashCode(this.board);
    }


    @Override
    public boolean equals(Object that) {
        Puzzle15 other = (Puzzle15) that;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(this.board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public boolean isGoal(Ilayout that) {
        return this.equals(that);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 0) {
                    result = result + "0";
                } else if (board[i][j] >= 10 && board[i][j] <= 15) {
                    result = result + "" + Character.toUpperCase(Character.forDigit(board[i][j], 16));
                } else {
                    result = result + board[i][j];
                }
            }
            //result= "";
        }
        //return result;
        return result.substring(0, 4) + "\n" + result.substring(4, 8) + "\n" + result.substring(8, 12) + "\n" + result.substring(12, 16) + "\n";
    }

    public HashMap<Integer, Point> getMap() {
        return this.map_goal;
    }


    public void setGoal(String str) {
        int position = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.map_goal.put(Character.getNumericValue(str.charAt(position)), new Point(i,j));
                this.goal[i][j] = Character.getNumericValue(str.charAt(position++));
            }
        }

    }

    @Override
    public double getG() {
        return this.cost + this.manhattan();
    }


    @Override
    public List<Ilayout> children() {
        List<Ilayout> children_nodes = new ArrayList<Ilayout>();

        Point point_zero = Point.locateZero_inBoard(this.board);
        int x = point_zero.getX();
        int y = point_zero.getY();

        //left
        if (y > 0) {
            try {
                Puzzle15 child_board1 = (Puzzle15) this.clone();
                int value = this.board[x][y - 1];
                child_board1.board[x][y] = value;
                child_board1.board[x][y - 1] = 0;
                children_nodes.add(child_board1);
            } catch(Exception e) {
                e.getStackTrace();
            }

        }

        //right
        if (y < 3) {
            try {
                Puzzle15 child_board2 = (Puzzle15) this.clone();
                int value = this.board[x][y + 1];
                child_board2.board[x][y] = value;
                child_board2.board[x][y + 1] = 0;
                children_nodes.add(child_board2);
            }  catch(Exception e) {
                e.getStackTrace();
            }

        }

        //down
        if (x < 3) {
            try {
                Puzzle15 child_board3 = (Puzzle15) this.clone();
                int value = this.board[x + 1][y];
                child_board3.board[x][y] = value;
                child_board3.board[x + 1][y] = 0;
                children_nodes.add(child_board3);
            } catch(Exception e) {
                e.getStackTrace();
            }
        }

        //up
        if (x > 0) {
            try {
                Puzzle15 child_board4 = (Puzzle15) this.clone();
                int value = this.board[x - 1][y];
                child_board4.board[x][y] = value;
                child_board4.board[x - 1][y] = 0;
                children_nodes.add(child_board4);
            } catch(Exception e) {
                e.getStackTrace();
            }

        }

        return children_nodes;
    }


    public int manhattan() {
        HashMap<Integer, Point> map_goal = this.getMap();
        int result = 0;
        int s = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                s = this.board[i][j];
                if (s != 0) {
                    Point piece = map_goal.get(s);
                    result += (Math.abs(j - piece.getY()) + Math.abs(i - piece.getX()));
                }
            }
        }
        return result;
    }


    public int BlockDifferent() {
        int result = 0;
        int s = 0;
        int g = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                s = this.board[i][j];
                g = this.goal[i][j];
                if (s != g) {
                    result++;
                }
            }
        }
        return result;

    }
    public int getMaxHeu()
    {
        int x = this.manhattan();
        int k = this.BlockDifferent();
        if (x >= k)
            return  x;
        else
            return k;
    }

    @Override
    public int getH() {
        return this.manhattan();
    }

}