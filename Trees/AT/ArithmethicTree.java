package Trees.AT;
//package

import java.util.*;

public class ArithmethicTree {
    private HashMap<String, Integer> opMap;
    static char[] operators={'+','*','-','/'};
    static String[] stringOperators={"+","*","-","/"};
    Node root;
    public ArithmethicTree(){
        opMap = new HashMap<>();
        opMap.put("+", 1);
        opMap.put("-", 1);
        opMap.put("*", 2);
        opMap.put("/", 2);
        opMap.put("^", 3);
    }
 
    public void parseExpression(String s){
        String notation = postFix(s), token;
        System.out.println("Notation: " + notation);
        Stack<Node> nodes = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(notation, " ");
        int tokens = tokenizer.countTokens();
        
        for(int i = 0; i < tokens; i++){
            token = tokenizer.nextToken();
            //System.out.println("token: "+token);
            if(isOperator(token.charAt(0))){
                Node opeNode = new Node(Character.toString(token.charAt(0)));
                opeNode.right = nodes.pop();
                opeNode.left = nodes.pop();
                nodes.push(opeNode);
                System.out.println(opeNode.info + " lc: " + opeNode.left.info + " rc: "+ opeNode.right.info);
            }else{
                nodes.push(new Node(Character.toString(token.charAt(0))));
            }
        }
        root = nodes.pop();

    }

    public String postFix(String s){
        Stack<String> stack = new Stack<>();
        if(s.charAt(s.length()-1) != ')'){
            s += ")";
        }
        if(s.charAt(0) != '('){
            stack.push("(");
        }

        String notation = "";

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //System.out.println(c);
            if(Character.isDigit(c)){
                //Digit
                String digits = "";
                
                while(Character.isDigit(s.charAt(i))){
                    //digits.concat(Character.toString(s.charAt(i)));
                    digits += Character.toString(s.charAt(i));
                    //System.out.println("digits: " + digits);
                    i++;
                }
                i--;
                notation += digits + " ";

            }else if(opMap.containsKey(Character.toString(c))){
                //Operator
                while(!stack.empty()){
                    
                    String ope = stack.pop();
                    if(ope == "("){
                        stack.push(ope);
                        break;
                    }
                    
                    if(opMap.containsKey(ope)){
                        if(opMap.get(ope) < opMap.get(Character.toString(c))){
                            stack.push(ope);
                            break;
                        }
                    }
                    notation += ope + " ";
                
                }
                stack.push(Character.toString(c));                
            
            }else if(c == '('){
                //Open parenthesis
                stack.push(Character.toString(c));
            
            }else{
                //close parenthesis
                //System.out.println(c);
                
                //System.out.println("Stack: " + stack.toString());
                while(stack.peek() != "(") {
                    //System.out.println("stack: " + stack.toString());
                    notation += stack.pop() + " ";
                }
                stack.pop();
                
            }
        }
        //System.out.println("Notation: " + notation + " si se hizo"  );
        return notation;
    }

    public static boolean isOperator(char s){
        for(char c:operators){
            if(s == c){
                return true;
            }
        }
        return false;
    }

    public static boolean isOperator(String s){
        for(String c:stringOperators){
            if(s == c){
                return true;
            }
        }
        return false;
    }

    public double resolve(){
        double res;

        LinkedList<String> list = postOrden();
        System.out.println("List AT: " + list.toString());
        res = InversePolishNotation.resolve(list);

        return res;
    }

    public LinkedList<String> postOrden(){
        LinkedList<String> list = new LinkedList<>();
        
        postOrden(root, list);

        return list;
    }

    private void postOrden(Node current, LinkedList<String> list){
        if(current == null){
            return;
        }
        if(current.left != null){
            postOrden(current.left,list);
        }
        if(current.right != null){
            postOrden(current.right,list);
        }
        list.add(current.info);
    }
    
    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        return false;
    }

/*
    public static LinkedList<String> postOrden(Node node){
        LinkedList<String> list = new LinkedList<>();
        postOrdenRecursive(node, list);
        return list;
    } 

    private static void postOrdenRecursive(Node node, LinkedList<String> list){
        if(node != null){
            postOrdenRecursive(node.left, list);
            postOrdenRecursive(node.right, list);
            list.add(node.info);
        }
    }
*/

    public LinkedList<String> preOrden(){
        LinkedList<String> list = new LinkedList<>();
        preOrden(root, list);
        return list;
    }

    private void preOrden(Node node, LinkedList<String> list){
        if(node != null){
            list.add(node.info);
            preOrden(node.left, list);
            preOrden(node.right, list);
        }
    }

    public LinkedList<String> inOrden(){
        LinkedList<String> list = new LinkedList<>();
        inOrden(root, list);
        return list;
    }

    private void inOrden(Node node, LinkedList<String> list){
        if(node != null){
            inOrden(node.left, list);
            list.add(node.info);
            inOrden(node.right, list);
        }
    }
    
}