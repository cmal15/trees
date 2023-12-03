package Trees.Heap;

import java.util.*;

public class Heap{
    Node root;
    //int size;

    public Heap(){
        root = null;
    }

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

    public void deleteRoot(){
        if(root == null){
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

    private void heapify(){
        heapify(root);
    }

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

    public LinkedList<Integer> inOrden(){
        return Util.inOrden(root);
    }

    public LinkedList<Integer> preOrden(){
        return Util.preOrden(root);
    }

    public LinkedList<Integer> postOrden(){
        return Util.postOrden(root);
    }


}
