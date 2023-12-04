package Trees;

import java.util.*;
import Trees.AT.*;
import Trees.Heap.*;
import Trees.AVL.*;

public class Menu {
    
    private static Heap heap;
    private static ArithmethicTree aTree; 
    private static ArbolAVL avl;
    private static Scanner sc;

    public static void main(String[] args) {
        heap = null;
        aTree = null;
        avl = null;

        sc = new Scanner(System.in);
        int op;

        do{
            System.out.println("Seleccione un arbol para trabajar");
            System.out.println("1.Arbol AVL\t2.Heap\t3.Arbol aritmetico\t4.Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    if (avl == null) {
                        avl = new ArbolAVL();
                    }
                    menu(avl);
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

    
    private static void menu(ArbolAVL arbol) {
        int opcion;

        do {
            System.out.println(" **** Menu ****");
            System.out.println("1. Agregar clave");
            System.out.println("2. Buscar clave");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Mostrar arbol AVL");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese la clave que deseas insertar: ");
                    int agregar = sc.nextInt();
                    arbol.raiz = arbol.insertar(arbol.raiz, agregar);
                    System.out.println("Clave insertada con Exito\n");
                    break;

                case 2:
                    System.out.print("Ingresa el valor que deseas buscar: ");
                    int buscarval = sc.nextInt();
                    if (arbol.buscar(buscarval)) {
                        System.out.println("El valor: " + buscarval + " SI se encuentra...\n");
                    } else {
                        System.out.println("El valor: " + buscarval + " NO se encuentra.,.\n");
                    }
                    break;

                case 3:
                    System.out.print("Ingresa la clave que deseas eliminar: ");
                    int eliminarval = sc.nextInt();
                    arbol.eliminar(eliminarval);
                    System.out.println("Se elimino la clave con exito...\n");
                    break;

                case 4:
                    System.out.println("Árbol AVL:");
                    arbol.mostrarArbol();
                    System.out.println(" ");
                    break;

                case 5:
                    System.out.println("ADIOSSSS... :)");
                    break;
                default:
                    System.out.println("Opcion NO valida, ingresa otro valor... >:v");
            }
        } while (opcion != 5);
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
                    System.out.println(aTree.preOrden());
                    System.out.println("Inorden:");
                    System.out.println(aTree.inOrden());
                    System.out.println("Postorden:");
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
