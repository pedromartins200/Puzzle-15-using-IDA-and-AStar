import java.text.DecimalFormat;
import java.util.*;

class IDAStar {
    List<State> seq = new ArrayList<State>();
    private State actual;
    private Ilayout objective;
    public static int generated = 0;
    public static int expanded = 0;
    public int number_steps = 0;
    List<State> list_path = new ArrayList<State>();
    DecimalFormat df = new DecimalFormat("#,###");

    public class State {
        private Ilayout state;
        private State father;
        private int temp;
        private double g; // cost from the current to the initial node
        private int h;

        public State() {

        }

        public State getFather() {
            return this.father;
        }

        public State(Ilayout l, State n) {
            state = l;
            father = n;
            h = l.getH();
            if (father != null)
                g = father.g + 1;
            else g = 0;
        }

        public int getH() {
            return this.h;
        }

        public double getG() {
            return this.g;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        public int getTemp() {
            return this.temp;
        }

        public Ilayout getState() {
            return state;
        }


        public String toString() {
            return state.toString();
        }


        public double getDepth() {
            return g;
        }
    }


    final private List<State> sucessores(State n) {
        expanded++;
        List<State> sucs = new ArrayList<State>();
        List<Ilayout> children = n.state.children();
        for (Ilayout e : children) {
            if (n.father == null || !e.equals(n.father.state)) {
                State nn = new State(e, n);
                sucs.add(nn);
                generated++;
            }
        }
        return sucs;
    }


    public Iterator<State> solveIDAStar(Ilayout s, Ilayout goal) {
        objective = goal;
        State start = new State(s, null);
        int threshold = start.getH();
        while (true) {
            int temp = search(start, 0, threshold);
            if (temp == -1) {
                System.out.println("generated nodes = " + df.format(generated).replaceAll(",", " "));
                System.out.println("expanded nodes = " + df.format(expanded).replaceAll(",", " "));
                Collections.reverse(list_path);
                return list_path.iterator();
            }
            if (temp == Integer.MAX_VALUE) {
                break;
            }
            threshold = temp;
        }
        return null;
    }

    public int search(State node, int depth, int threshold) {
        int f = (int) node.g + node.getH();
        if (f > threshold) {
            return f;
        }

        if (node.state.equals(objective)) {
            //GOAL NODE FOUND
            number_steps = depth;
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (State tempnode : sucessores(node)) {

            int temp = search(tempnode, depth + 1, threshold);
            if (temp == -1) {
                list_path.add(tempnode);
                return -1;
            }
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

}
