package Trees.Heap;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        int op, aux;
        Heap heap = new Heap();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1.Insertar\t2.Eliminar raiz");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    aux = sc.nextInt();
                    heap.insert(aux);
                    System.out.println("Se inserto");
                    break;
                case 2:
                    heap.deleteRoot();
                    System.out.println("Se elimino la raiz");
                    break;
            
                default:
                    break;
            }
            System.out.println("Preorden");
            System.out.println(Util.preOrden(heap.root));
            System.out.println("Inorden");
            System.out.println(Util.inOrden(heap.root));
            System.out.println("Postorden");
            System.out.println(Util.postOrden(heap.root));
        }while(op != 3);

        sc.close();
    }

}
