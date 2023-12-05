package Trees.Heap;

public class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    /*
     * Constructor para inicializar el valor
     */
    public Node(int value){
        this.value = value;
        left = right = parent = null;
    }

    /*
     * Inicializa todos los atributos
     */
    public Node(int value, Node left, Node right, Node parent){
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /*
     * Retorna verdadero si un nodo es una hoja
     */
    public boolean isLeaf(){
        if(this.left == null && this.right == null){
            return true;
        }
        return false;
    }

    /*
     * Retorna verdadero si el nodo tiene ambos hijos distintos de null
     */
    public boolean isFull(){
        if(this.left != null && this.right != null){
            return true;
        }
        return false;
    }

    /*
     * Retorna verdadero si ambos hijos son menores que el padre
     */
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

    /*
     * Intercambia los valores de dos nodos
     */
    public void swap(Node otherNode) {
        int tempValue = this.value;
        this.value = otherNode.value;
        otherNode.value = tempValue;
    }

}
