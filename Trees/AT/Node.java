package Trees.AT;

public class Node {

    public String info;
    public Node parent, left, right;
    public Node(){
        this.info = "";
        parent = left = right = null;
    }

    public Node(String info){
        this.info = info;
        parent = left = right = null;
    }

    public Node(String info, Node left, Node right){
        this.info = info;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf(){
        if(this.left == null && this.right == null){
            return true;
        }else{
            return false;
        }
    } 

    public void setInfo(String s){
        info = s;
    }

    public String getInfo(){
        return info;
    }

}
