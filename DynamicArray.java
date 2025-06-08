public class DynamicArray<T> {
    private T array[];
    private int size;
    private int capacity;

    public DynamicArray(){
        capacity = 4;
        size = 0;
        array = (T[]) new Object [capacity];
    }

    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index out of bounds");
        }
    }
    public void resize(){
        if (capacity == size){
            capacity *= 2;
            T[] newArray = (T[]) new Object [capacity];
            for (int i = 0; i < size; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }
    public void append(T value){
        resize();
        array[size] = value;
        size++;
    }
    public void prepend(T value){
        insert(value, 0);
    }
    public void insert(T value, int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        resize();
        for (int i = size; i > index; i--){
            array[i] = array[i-1];
        }
        array[index] = value;
        size++;
    }
    public void remove(int index){
        checkIndex(index);
        for (int i = index; i < size-1; i++){
            array[i] = array[i+1];
        }
        size--;
    }
    public T get(int index){
        checkIndex(index);
        return array[index];
    }
    public void set(T value, int index){
        checkIndex(index);
        array[index] = value;
    }
    public int size(){return size;}
    public int capacity(){return capacity;}
    public boolean isEmpty(){
        return size == 0;
    }
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        DynamicArray<Integer> da = new DynamicArray<>();
        da.append(10);
        da.append(20);
        da.append(30);
        da.prepend(5);
        da.insert(25, 3);
        da.print(); // Expected: 5 10 20 25 30
        da.remove(2);
        da.print(); // Expected: 5 10 25 30
        System.out.println("Element at index 2: " + da.get(2)); // 25
        da.set(99, 2);
        da.print(); // 5 10 99 30
        System.out.println("Size: " + da.size() + ", Capacity: " + da.capacity());
        System.out.println("Is Empty? " + da.isEmpty());
        DynamicArray<String> names = new DynamicArray<>();
        names.append("the");
        names.append("bilguun");
        names.append(".com");
        names.print(); // Bilguun the Great
    }
}