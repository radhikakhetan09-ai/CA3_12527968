import java.util.*;

class MyQueue {
    private Stack<Integer> input = new Stack<>();
    private Stack<Integer> output = new Stack<>();

    public MyQueue() {
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        moveItems();
        return output.pop();
    }

    public int peek() {
        moveItems();
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }

    private void moveItems() {
        if (output.empty()) {
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
    }
}
