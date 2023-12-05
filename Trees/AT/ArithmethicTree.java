package Trees.AT;

import java.util.*;

public class ArithmethicTree {
    private HashMap<String, Integer> opMap;
    static char[] operators={'+','*','-','/'};
    static String[] stringOperators={"+","*","-","/"};
    Node root;
    /*
     * Constructor para el arbol aritmetico inicializando el opMap para la jerarquia de operaciones
     */
    public ArithmethicTree(){
        opMap = new HashMap<>();
        opMap.put("+", 1);
        opMap.put("-", 1);
        opMap.put("*", 2);
        opMap.put("/", 2);
        opMap.put("^", 3);
    }
 
    /*
    * Recibe una expresion como cadena y genera el arbol de expresion aritmetica
    */
    public void parseExpression(String s){
        String notation = postFix(s), token;
        System.out.println("Notation: " + notation);
        Stack<Node> nodes = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(notation, " ");
        int tokens = tokenizer.countTokens();
        
        for(int i = 0; i < tokens; i++){
            token = tokenizer.nextToken();
            if(isOperator(token.charAt(0))){
                Node opeNode = new Node(Character.toString(token.charAt(0)));
                opeNode.right = nodes.pop();
                opeNode.left = nodes.pop();
                nodes.push(opeNode);
            }else{
                nodes.push(new Node(Character.toString(token.charAt(0))));
            }
        }
        root = nodes.pop();

    }

    /*
     * Obtiene la notacion postfija a partir de una cadena recibida
     */
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
            if(Character.isDigit(c)){
                //Digit
                String digits = "";
                
                while(Character.isDigit(s.charAt(i))){
                    digits += Character.toString(s.charAt(i));
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
                
                while(stack.peek() != "(") {
                    notation += stack.pop() + " ";
                }
                stack.pop();
                
            }
        }
        return notation;
    }

    /*
     * Retorna un verdadero si el caracter recibido es un operador
     */
    public static boolean isOperator(char s){
        for(char c:operators){
            if(s == c){
                return true;
            }
        }
        return false;
    }

    /*
     * Retorna verdadero si la cadena recibida es un operador
     */
    public static boolean isOperator(String s){
        for(String c:stringOperators){
            if(s == c){
                return true;
            }
        }
        return false;
    }

    /*
     * Retorna el resultado de la expresion ingresada en el arbol como un double
     */
    public double resolve(){
        double res;

        LinkedList<String> list = postOrden();
        System.out.println("List AT: " + list.toString());
        res = InversePolishNotation.resolve(list);

        return res;
    }
    
    /*
     * Retorna una lista ligada que contiene los elementos del arbol en recorrido postOrden
     */
    public LinkedList<String> postOrden(){
        LinkedList<String> list = new LinkedList<>();
        
        postOrden(root, list);

        return list;
    }

    /*
     * Se ocupa para obtener el recorrido
     */
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
    
    /*
     * Retorna verdadero si el arbol esta vacio
     */
    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        return false;
    }

    /*
     * Retorna una lista ligada que contiene los elementos del arbol en recorrido preOrden
     */
    public LinkedList<String> preOrden(){
        LinkedList<String> list = new LinkedList<>();
        preOrden(root, list);
        return list;
    }

    /*
     * Se ocupa para obtener el recorrido
     */
    private void preOrden(Node node, LinkedList<String> list){
        if(node != null){
            list.add(node.info);
            preOrden(node.left, list);
            preOrden(node.right, list);
        }
    }

    /*
     * Retorna una lista ligada que contiene los elementos del arbol en recorrido inOrden
     */
    public LinkedList<String> inOrden(){
        LinkedList<String> list = new LinkedList<>();
        inOrden(root, list);
        return list;
    }

    /*
     * Se ocupa para obtener el recorrido
     */
    private void inOrden(Node node, LinkedList<String> list){
        if(node != null){
            inOrden(node.left, list);
            list.add(node.info);
            inOrden(node.right, list);
        }
    }
    
}