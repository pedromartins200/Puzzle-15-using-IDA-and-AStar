import java.util.Iterator;
import java.util.Stack;

class IT implements Iterator<AStar.State> {
    private AStar.State last;
    private Stack<AStar.State> stack;

    public IT(AStar.State actual) {
        last = actual;
        stack = new Stack<AStar.State>();
        while (last != null) {
            stack.push(last);
            last = last.getFather();
        }
    }

    public boolean hasNext() {
        return !stack.empty();
    }

    public AStar.State next() {
        return stack.pop();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}