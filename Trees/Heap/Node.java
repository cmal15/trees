package Trees.Heap;

public class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    public Node(int value){
        this.value = value;
        left = right = parent = null;
    }

    public Node(int value, Node left, Node right, Node parent){
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public boolean isLeaf(){
        if(this.left == null && this.right == null){
            return true;
        }
        return false;
    }

    public boolean isFull(){
        if(this.left != null && this.right != null){
            return true;
        }
        return false;
    }

    public boolean parentIsGreater(){
        if(this.left != null){
            if(this.left.value > this.value){
                return false;
            }
        }
        if(this.right != null){
            if(this.right.value > this.value){
                return false;
            }
        }
        return true;
    }

    public void swap(Node otherNode) {
        // Intercambia los valores
        int tempValue = this.value;
        this.value = otherNode.value;
        otherNode.value = tempValue;
    }

}
