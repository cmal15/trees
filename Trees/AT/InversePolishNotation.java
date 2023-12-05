package Trees.AT;
import java.util.LinkedList;
import java.util.Stack;

public class InversePolishNotation {
    
    private static Stack<Double> stack;

    public InversePolishNotation(){
    }

    private static boolean isNumber(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static double resolve(LinkedList<String> list){
        double res = 0, op1, op2;
        stack = new Stack<>();
        for(String s: list){
            if(isNumber(s)){
                stack.push(Double.parseDouble(s));
            }else{

                op1 = stack.pop();
                op2 = stack.pop();
                switch (s) {
                    case "+":
                        res = op1+op2;    
                        break;
                    case "-":
                        res = op1-op2;    
                        break;
                    case "*":
                        res = op1*op2;    
                        break;
                    case "/":
                        res = op2/op1;    
                        break;
                    default:
                        break;
                }
                stack.push(res);
            }
        }
        return (stack.pop());
    }

}
