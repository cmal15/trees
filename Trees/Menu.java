package Trees;

import java.util.*;
import Trees.AT.*;
import Trees.Heap.*;

public class Menu {
    
    private static Heap heap;
    private static ArithmethicTree aTree; 
    private static Scanner sc;

    public static void main(String[] args) {
        heap = null;
        aTree = null;

        sc = new Scanner(System.in);
        int op;

        do{
            System.out.println("Seleccione un arbol para trabajar");
            System.out.println("1.Arbol AVL\t2.Heap\t3.Arbol aritmetico\t4.Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    /*
                     * Menu AVL
                     */
                    break;
                case 2:
                    if(heap == null){
                        heap = new Heap();
                    }
                    menu(heap);
                    break;
                case 3:
                    if(aTree == null){
                        aTree = new ArithmethicTree();
                    }
                    menu(aTree);
                    break;
            
                default:
                    break;
            }

        }while(op != 4);
        

        sc.close();

    }

    private static void menu(Heap heap){
        int op, aux;
        do{
            System.out.println("Seleccione:");
            System.out.println("1.Agregar clave\t2.Eliminar raiz\t3.Mostrar arbol\t4.Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Ingrese clave: ");
                    aux = sc.nextInt();
                    heap.insert(aux);
                    break;
                case 2:
                    try{
                        heap.deleteRoot();
                        System.out.println("Se elimino la raiz");
                    }catch(Exception e){
                        System.out.println("Algo salio mal");
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Preorden:");
                    System.out.println(heap.preOrden());
                    System.out.println("Inorden:");
                    System.out.println(heap.inOrden());
                    System.out.println("Postorden:");
                    System.out.println(heap.postOrden());
                    break;
            
                default:
                    break;
            }
        }while(op != 4);
        
    }

    private static void menu(ArithmethicTree aTree){
        int op;
        String s;
        do{
            System.out.println("Seleccione");
            System.out.println("1.Ingresar expresion\t2.Mostrar arbol\t3.Resolver\t4.Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Ingrese expersion:");
                    sc.nextLine();
                    s = sc.nextLine();
                    aTree.parseExpression(s);
                    break;
                case 2:
                    if(aTree.isEmpty()){
                        System.out.println("No se ha registrado una expresion");
                        break;
                    }
                    System.out.println("Preorden:");
                    System.out.println(aTree.postOrden());
                    System.out.println("Preorden:");
                    System.out.println(aTree.postOrden());
                    System.out.println("Preorden:");
                    System.out.println(aTree.postOrden());
                    break;
                case 3:
                    if(aTree.isEmpty()){
                        System.out.println("No se ha registrado una expresion");
                        break;
                    }
                    double res = aTree.resolve();
                    System.out.println("Resultado: " + res);                   
                    break;
            
                default:
                    break;
            }
        }while(op != 4);
    }

}
