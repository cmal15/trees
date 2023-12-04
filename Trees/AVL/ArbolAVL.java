package Trees.AVL;

public class ArbolAVL {
    NodoAVL raiz;

    public NodoAVL obtenerRaiz() {
        return raiz;
    }

    // Se obtiene la altura de un nodo
    private int altura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Obtener el máximo de dos números
    private int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    private NodoAVL nodoMax(NodoAVL node) {
        NodoAVL current = node;
        while (current.hijoderecho != null) {
            current = current.hijoderecho;
        }
        return current;
    }

    // Metodo para obtener el factor de equilibriop
    public int obtenerFE(NodoAVL x) {
        if (x == null) {
            return 0;
        } else {
            return altura(x.hijoizquierdo) - altura(x.hijoderecho);
        }
    }

    // Rotación simple a la IZQUIERDA
    private NodoAVL rotacionIzquierda(NodoAVL c) {
        NodoAVL nuevaRaiz = c.hijoderecho;
        NodoAVL tempo = nuevaRaiz.hijoizquierdo;

        // Se realiza la rotacion
        nuevaRaiz.hijoizquierdo = c;
        c.hijoderecho = tempo;

        // Actualizacion de las alturas
        c.altura = maximo(altura(c.hijoizquierdo), altura(c.hijoderecho)) + 1;
        nuevaRaiz.altura = maximo(altura(nuevaRaiz.hijoizquierdo), altura(nuevaRaiz.hijoderecho)) + 1;

        return nuevaRaiz;
    }

    // Rotación simple a la DERECHA
    private NodoAVL rotacionDerecha(NodoAVL c) {
        NodoAVL nuevaRaiz = c.hijoizquierdo;
        NodoAVL tempo = nuevaRaiz.hijoderecho;

        // Se realiza la rotacion
        nuevaRaiz.hijoderecho = c;
        c.hijoizquierdo = tempo;

        // Actualizacion de las alturas
        c.altura = maximo(altura(c.hijoizquierdo), altura(c.hijoderecho)) + 1;
        nuevaRaiz.altura = maximo(altura(nuevaRaiz.hijoizquierdo), altura(nuevaRaiz.hijoderecho)) + 1;
        return nuevaRaiz;
    }

    // Insertar una clave en el árbol AVL
    public NodoAVL insertar(NodoAVL nodo, int clave) {
        if (nodo == null) {
            return new NodoAVL(clave);
        }
        if (clave < nodo.clave) {
            nodo.hijoizquierdo = insertar(nodo.hijoizquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.hijoderecho = insertar(nodo.hijoderecho, clave);
        } else {
            // si la clave esta duplicada retorna el mismo nodo encontrado
            return nodo;
        }

        // Aqui se actualiza la altura
        nodo.altura = 1 + maximo(altura(nodo.hijoizquierdo), altura(nodo.hijoderecho));

        // Obtenemos el factor de equilibrio del nodo actual
        int equilibrio = obtenerFE(nodo);

        // Realizamos las rotaciones necesarias, las cuales podemos ver a continuacion:

        // Rotación simple a la derecha
        if (equilibrio > 1 && clave < nodo.hijoizquierdo.clave) {
            return rotacionDerecha(nodo);
        }

        // Rotación simple a la izquierda
        if (equilibrio < -1 && clave > nodo.hijoderecho.clave) {
            return rotacionIzquierda(nodo);
        }

        // Rotación doble a la derecha-izquierda
        if (equilibrio > 1 && clave > nodo.hijoizquierdo.clave) {
            nodo.hijoizquierdo = rotacionIzquierda(nodo.hijoizquierdo);
            return rotacionDerecha(nodo);
        }

        // Rotación doble a la izquierda-derecha
        if (equilibrio < -1 && clave < nodo.hijoderecho.clave) {
            nodo.hijoderecho = rotacionDerecha(nodo.hijoderecho);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Metodo de busqueda de claves en el AVL
    public boolean buscar(int clave) {
        return buscarAVL(raiz, clave);
    }

    private boolean buscarAVL(NodoAVL nodo, int clave) {
        if (nodo == null) {
            return false;
        }
        if (clave == nodo.clave) {
            return true;
        } else if (clave < nodo.clave) {
            return buscarAVL(nodo.hijoizquierdo, clave);
        } else {
            return buscarAVL(nodo.hijoderecho, clave);
        }
    }

    // Eliminar una clave del árbol AVL
    public void eliminar(int clave) {
        raiz = eliminarAVL(raiz, clave);
    }

    private NodoAVL eliminarAVL(NodoAVL nodo, int clave) {
        if (nodo == null) {
            return nodo;
        }
        if (clave < nodo.clave) {
            nodo.hijoizquierdo = eliminarAVL(nodo.hijoizquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.hijoderecho = eliminarAVL(nodo.hijoderecho, clave);
        } else {
            // El nodo es igua a la clave, por lo que se elimina
            // Nodo con un solo hijo
            if ((nodo.hijoizquierdo == null) || (nodo.hijoderecho == null)) {
                NodoAVL temp = null;
                if (temp == nodo.hijoizquierdo) {
                    temp = nodo.hijoderecho;
                } else {
                    temp = nodo.hijoizquierdo;
                }

                // Caso cuando no tiene hijos
                if (temp == null) {
                    nodo = null;
                } else {
                    // Cuanto tiene un hijo
                    nodo = temp;
                }
            } else {
                // Nodo con dos hijos, obtener el predecesor
                NodoAVL temp = nodoMax(nodo.hijoizquierdo);

                // Copiar el prdecesor al nodo actual
                nodo.clave = temp.clave;

                // Eliminar el predecesor
                nodo.hijoizquierdo = eliminarAVL(nodo.hijoizquierdo, temp.clave);
            }
        }

        // Si el árbol tiene un solo nodo
        if (nodo == null) {
            return nodo;
        }

        // Se actualiza la altura
        nodo.altura = maximo(altura(nodo.hijoizquierdo), altura(nodo.hijoderecho)) + 1;

        // Se obtiene el factor de equilibrio
        int equilibrio = obtenerFE(nodo);

        // Realizar rotaciones si es necesario, las cuales son las siguientes:
        // Rotación simple a la derecha
        if (equilibrio > 1 && obtenerFE(nodo.hijoizquierdo) >= 0) {
            return rotacionDerecha(nodo);
        }

        // Rotación simple a la izquierda
        if (equilibrio < -1 && obtenerFE(nodo.hijoderecho) <= 0) {
            return rotacionIzquierda(nodo);
        }

        // Rotación doble a la izquierda-derecha
        if (equilibrio > 1 && obtenerFE(nodo.hijoizquierdo) < 0) {
            nodo.hijoizquierdo = rotacionIzquierda(nodo.hijoizquierdo);
            return rotacionDerecha(nodo);
        }

        // Rotación doble a la derecha-izquierda
        if (equilibrio < -1 && obtenerFE(nodo.hijoderecho) > 0) {
            nodo.hijoderecho = rotacionDerecha(nodo.hijoderecho);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Mostrar el árbol AVL
    public void mostrarArbol() {
        mostrarArbolAVL(raiz, 0);
    }

    private void mostrarArbolAVL(NodoAVL nodo, int nivel) {
        if (nodo != null) {
            mostrarArbolAVL(nodo.hijoderecho, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("   ");
            }
            System.out.println(nodo.clave);
            mostrarArbolAVL(nodo.hijoizquierdo, nivel + 1);
        }
    }
}
