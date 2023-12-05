package Trees.Heap;

import java.util.LinkedList;

public class Util {
    /*
    * Retorna una lista ligada que contiene los elementos del arbol en recorrido preOrden
    */
    static LinkedList<Integer> postOrden(Node node){
        LinkedList<Integer> list = new LinkedList<>();
        postOrden(node, list);
        return list;
    }
    /*
     * Se ocupa para obtener el recorrido
     */
    private static void postOrden(Node node, LinkedList<Integer> list){
        if(node != null){
            postOrden(node.left, list);
            postOrden(node.right, list);
            list.add(node.value);
        }
    }
    /*
     * Retorna una lista ligada que contiene los elementos del arbol en recorrido preOrden
     */
    static LinkedList<Integer> preOrden(Node node){
        LinkedList<Integer> list = new LinkedList<>();
        preOrden(node, list);
        return list;
    }
    /*
     * Se ocupa para obtener el recorrido
     */
    private static void preOrden(Node node, LinkedList<Integer> list){
        if(node != null){
            list.add(node.value);
            preOrden(node.left, list);
            preOrden(node.right, list);
        }
    }
    /*
     * Retorna una lista ligada que contiene los elementos del arbol en recorrido inOrden
     */
    static LinkedList<Integer> inOrden(Node node){
        LinkedList<Integer> list = new LinkedList<>();
        inOrden(node, list);
        return list;
    }
    /*
     * Se ocupa para obtener el recorrido
     */
    private static void inOrden(Node node, LinkedList<Integer> list){
        if(node != null){
            inOrden(node.left, list);
            list.add(node.value);
            inOrden(node.right, list);
        }
    }

}
