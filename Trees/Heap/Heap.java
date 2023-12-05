package Trees.Heap;

import java.util.*;

public class Heap{
    Node root;

    /*
     * Constructor vacio con raiz nula
     */
    public Heap(){
        root = null;
    }

    /*
     * Inserta la clave en el heap respetando su estructura
     */
    public void insert(int n){
        if(root == null){
            root = new Node(n);
            return;
        }
        Node parent = findNextParetNode();
        Node newNode;
        if(parent.left == null){
            parent.left = new Node(n,null,null, parent);
            newNode = parent.left;
        }else{
            parent.right = new Node(n, null, null, parent);
            newNode = parent.right;
        }
        Node current = newNode;
        while(current.parent != null && current.value > current.parent.value){
            current.swap(current.parent);
            current = current.parent;
        }
    }

    /*
     * Elimina la raiz del arbol realizando los cambios para que se respeten las reglas del heap
     */
    public void deleteRoot(){
        if(root == null){
            return;
        }
        if(root.isLeaf()){
            root = null;
            return;
        }
        Node current = findLastNode();
        current.swap(root);

        if(current.parent != null){
            if(current.parent.left.equals(current)){
                current.parent.left = null;
            }else{
                current.parent.right = null;
            }
            heapify();
        }else{
            root = null;
        }
    }

    /*
     * Se ocupa para realizar los cambios para que se respeten las reglas del heap
     */
    private void heapify(){
        heapify(root);
    }

    /*
     * Realiza los cambios correspondientes desde el nodo recibido para que se respeten las reglas del heap
     */
    private void heapify(Node node){
        if(node == null){
            return;
        }
        int maxValue = node.value;
        Node maxNode = node;

        if(node.left != null && node.left.value > maxValue){
            maxValue = node.left.value;
            maxNode = node.left;
        }

        if(node.right != null && node.right.value > maxValue){
            maxNode = node.right;
        }

        if(maxNode != node){
            node.swap(maxNode);
            heapify(maxNode);
        }
    }

    /*
     * Encuentra el ultimo nodo en el arbol
     */
    public Node findLastNode(){
        Queue<Node> q = new LinkedList<>();
        Node current = root;
        do{
            if(current.left != null){
                q.add(current.left);
            }
            if(current.right != null){
                q.add(current.right);
            }
            
            current = q.poll();
        }while(!q.isEmpty());
        return current;
    }

    /*
     * Encuentra el siguiente nodo que puede ser padre
     */
    private Node findNextParetNode(){
        Queue<Node> q = new LinkedList<>();
        Node current = root;
        while(current.isFull()){
            if(current.left != null){
                q.add(current.left);
            }
            if(current.right != null){
                q.add(current.right);
            }
            current = q.poll();
        }
        return current;
    }

    /*
     * Realiza el recorrido inOrden del arbol
     */
    public LinkedList<Integer> inOrden(){
        return Util.inOrden(root);
    }

    /*
     * Realiza el recorrido preOrden del arbol
     */
    public LinkedList<Integer> preOrden(){
        return Util.preOrden(root);
    }

    /*
     * Realiza el recorrido postOrden del arbol
     */
    public LinkedList<Integer> postOrden(){
        return Util.postOrden(root);
    }


}
