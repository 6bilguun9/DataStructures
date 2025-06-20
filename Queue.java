import java.util.Iterator;

public class Queue<T> implements Iterable<T>{
    private DoublyLinkedList<T> list;

    @Override 
    public Iterator<T> iterator(){
        return list.iterator();
    }
    public Queue(){
        list = new DoublyLinkedList<>();
    }

    public void enqueue(T item){
        list.addLast(item);
    }
    public T dequeue(){
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
    public T[] toArray(){
        return list.toArray();
    }
    public boolean contains(T item){
        return list.contains(item);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder("Top\n");
        Iterator<T> t = list.iterator();
        while(t.hasNext()){
            sb.append(t.next());
            if(t.hasNext()){
                sb.append("\n");
            }
        }
        sb.append("\nBottom");
        return sb.toString();
    }
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
    
        System.out.println("Empty? " + queue.isEmpty());  // expected: true
      
        System.out.println("\nEnqueueing 10, 20, 30...");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("\nCurrent queue:");
        System.out.println(queue);


        System.out.println("\nSize: " + queue.size());    // expected: 3
        System.out.println("Peek: " + queue.peek());      // expected: 10

 
        System.out.println("\nContains 20? " + queue.contains(20));  // expected: true
        System.out.println("Contains 40? " + queue.contains(40));    // expected: false

 
        System.out.println("\nDequeuing two items...");
        System.out.println("Removed: " + queue.dequeue()); // expected: 10
        System.out.println("Removed: " + queue.dequeue()); // expected: 20

        System.out.println("\nQueue now:");
        System.out.println(queue);
        System.out.println("Size now: " + queue.size());  // expected: 1
        System.out.println("Peek now: " + queue.peek());  // expected: 30

        System.out.println("\nClearing queue...");
        queue.clear();
        System.out.println("Empty after clear? " + queue.isEmpty());  // expected: true

        System.out.println("\nEnqueue 100, 200...");
        queue.enqueue(100);
        queue.enqueue(200);
        Integer[] arr = queue.toArray();
        System.out.print("toArray contents: ");
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
