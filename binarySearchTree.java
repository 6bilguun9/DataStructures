import javax.swing.tree.TreeNode;

class Node<T>{
    public Node<T> left;
    public Node<T> right;
    public T data;

    public Node(T data){
        this.left = null;
        this.right = null;
        this.data = data;
    }
}

public class binarySearchTree<T extends Comparable <T>> {
    private Node<T> root;
    public binarySearchTree(){
        this.root = null;
    }
    public Node<T> insertRec(Node<T> root, T val){
        if(root == null){
            Node<T> newNode = new Node<T>(val);
            return newNode;
        }
        if(val.compareTo(root.data) < 0){
            root.left = insertRec(root.left, val);
        }
        else if(val.compareTo(root.data) == 0){
            System.out.println("Already in the tree");
            return root;
        }
        else{
            root.right = insertRec(root.right, val);
        }
        return root;
    }
    public void insert(T val){
        Node<T> newNode = new Node<T>(val);
        if(root == null){
            root = newNode;
            return;
        }
        Node<T> curr = root;
        while(true){
            if(val.compareTo(curr.data) < 0){
                if(curr.left == null){
                    curr.left = newNode;
                    return;
                }
                curr = curr.left;
            }
            else if(val.compareTo(curr.data) == 0){
                System.out.println("Already in the tree");
                return;
            }
            else{
                if(curr.right == null){
                    curr.right = newNode;
                    return;
                }
                curr = curr.right;
            }
        }
    }
    public Node<T> findMin(){
        Node<T> curr = root;
        if(root == null){
            return root;
        }
        while(curr.left != null){
            curr = curr.left;
        }
        return curr;
    }
    public Node<T> findMax(){
        if(root == null){
            return null;
        }
        Node<T> curr = root;
        while(curr.right != null){
            curr = curr.right;
        }
        return curr;
    }
    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        else{
            return false;
        }
    }
    public Node<T> removeRec(Node<T> root, T val){
        if(root == null){
            return root;
        }
        if(val.compareTo(root.data) < 0){
            root.left = removeRec(root.left, val);
        }
        else if(val.compareTo(root.data) > 0){
            root.right = removeRec(root.right, val);
        }
        else{
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            else{
                Node<T> curr = root.right;
                while(curr.left != null){
                    curr = curr.left;
                }
                root.data = curr.data;
                root.right = removeRec(root.right, curr.data);
            }
        }
        return root;
    }
    public void remove(T val){
        Node<T> curr = root;
        Node<T> parent = null;

        while(curr != null && !curr.data.equals(val)){
            parent = curr;
            if(val.compareTo(curr.data) < 0){
                curr = curr.left;
            }
            else if(val.compareTo(curr.data) > 0){
                curr = curr.right;
            }
        }
        if(curr == null){
            System.out.println("No such value exist");
            return;
        }
        if(curr.left == null || curr.right == null){
            Node<T> child = (curr.left != null)? curr.left : curr.right;
            if(parent == null){
            root = child;
            }else if (parent.left == curr){
            parent.left = child;
            }else{
            parent.right = child;
            }
        }
        else{
            Node<T> temp = curr;
            Node<T> min = curr.right;
            while(min.left != null){
                temp = min;
                min = min.left;
            }
            curr.data = min.data;
            if(temp.left == min){
                temp.left = min.right;
            }
            else{
                temp.right = min.right;
            }
        }
    }
    public boolean search(T val){
        Node<T> curr = root;
        while(curr != null){
            if(val.compareTo(curr.data) > 0){
                curr = curr.right;
            }
            else if(val.compareTo(curr.data) < 0){
                curr = curr.left;
            }
            else{
                return true;
            }
        }
        return false;
    }
    public void inOrder(Node<T> root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }
    public void preorder(Node<T> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(Node<T> root) {
        if (root == null) {
            return;
        }  
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }
    

}
