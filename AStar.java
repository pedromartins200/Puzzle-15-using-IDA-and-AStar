import java.text.DecimalFormat;
import java.util.*;

class AStar {
    public static int generated = 0;
    public static int expanded = 0;
    DecimalFormat df = new DecimalFormat("#,###");


    public class State {
        private Ilayout state;
        private State father;
        private double g; // cost from the current to the initial node


        public State getFather() {
            return this.father;
        }

        public State(Ilayout l, State n) {
            state = l;
            father = n;
            if (father != null)
                g = father.g + l.getG();
            else
                g = 0.0;
        }

        public Ilayout getState() {
            return state;
        }

        public String toString() {//feito aqui
            //return "(" + state.toString() + ", " + g + ") ";
            return state.toString();
        }


        public double getG() {
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

    final public Iterator<State> solveAStar(Ilayout s, Ilayout goal) {
        //LOCAL VARIABLES OCCUPY LESS MEMORY THAN GLOBAL VARIABLES
        Queue<State> abertos;
        List<State> fechados;
        State actual;
        Ilayout objective;
        Hashtable<Ilayout, AStar.State> fechados_table = new Hashtable<Ilayout, AStar.State>();
        abertos = new PriorityQueue<State>(10, (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));
        abertos.add(new State(s, null));
        List<State> sucs;
        for (; ; ) {
            if (abertos.isEmpty()) {
                return null;
            }
            actual = abertos.poll();
            if (goal.isGoal(actual.state)) {
                abertos.clear();
                fechados_table.clear();
                System.out.println("generated nodes = " + df.format(generated).replaceAll(",", " "));
                System.out.println("expanded nodes = " + df.format(expanded).replaceAll(",", " "));
                return new IT(actual);
            } else {
                sucs = sucessores(actual);
                fechados_table.put(actual.state, actual);
                boolean contains;
                for (State e : sucs) {
                    contains = false;
                    if (fechados_table.containsKey(e.state)) {
                        contains = true;
                        if (fechados_table.get(e.state).getG() > actual.getG()) {
                            fechados_table.get(e.state).father = actual;
                        }
                        //abertos.remove(e);
                    }

                    if (!contains)
                        abertos.add(e);
                }
            }
        }
    }
}
