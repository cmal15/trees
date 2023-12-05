package Trees.AT;

public class Node {

    public String info;
    public Node parent, left, right;
    
    /*
     * Constructor vacio para definir todo como nulo o vacio
     */
    public Node(){
        this.info = "";
        parent = left = right = null;
    }

    /*
     * Constructor que define el atributo info
     */
    public Node(String info){
        this.info = info;
        parent = left = right = null;
    }

    /*
     * Constructor que define todos los atributos de la instancia
     */
    public Node(String info, Node left, Node right){
        this.info = info;
        this.left = left;
        this.right = right;
    }

    /*
     * Metodo que retorna un booleano verdadero si el nodo que lo llama tiene ambos hijos como null
     */
    public boolean isLeaf(){
        if(this.left == null && this.right == null){
            return true;
        }else{
            return false;
        }
    } 

    /*
     * Setter para el atributo info
     */
    public void setInfo(String s){
        info = s;
    }

    /*
     * Getter para el atributo info
     */
    public String getInfo(){
        return info;
    }

}
