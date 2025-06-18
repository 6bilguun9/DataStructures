public class Stack<T> {
    private SinglyLinkedList<T> list;
    public Stack(){
        list = new SinglyLinkedList<>();
    }

    public void push(T data){
        list.addFirst(data);
    }
    public T pop(){
        return list.removeFirst();
    }
    public T peek(){
        return list.getFirst();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public int size(){
        return list.size();
    }
    public void clear(){
        list.clear();
    }
    @Override
    public String toString() {
        return "Top -> " + list.toString();
    }
    public T[] toArray(){
        return list.toArray();
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("Initial State:");
        System.out.println("Is empty? " + stack.isEmpty());
        System.out.println("Size: " + stack.size());
        System.out.println();

        System.out.println("Pushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack: " + stack);
        System.out.println("Size: " + stack.size());
        System.out.println();

        System.out.println("Peeking top element:");
        System.out.println("Top: " + stack.peek());
        System.out.println();

        System.out.println("Popping top element:");
        System.out.println("Popped: " + stack.pop());
        System.out.println("Stack after pop: " + stack);
        System.out.println("Size: " + stack.size());
        System.out.println();

        System.out.println("Pushing 40 & 50");
        stack.push(40);
        stack.push(50);
        System.out.println("Stack: " + stack);
        System.out.println();

        System.out.println("Stack as Array:");
        Object[] arr = stack.toArray();
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println();

        System.out.println("Clearing Stack...");
        stack.clear();
        System.out.println("Is empty? " + stack.isEmpty());
        System.out.println("Size: " + stack.size());
        System.out.println("Stack: " + stack);
    }

}
