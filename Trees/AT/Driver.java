package Trees.AT;
import java.util.*;

public class Driver {
 
    public static void main(String[] args) {
        
        ArithmethicTree arithmethicTree = new ArithmethicTree();
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        arithmethicTree.parseExpression(s);
        
        double res = arithmethicTree.resolve();
        System.out.println(res);
        sc.close();
    }

}

