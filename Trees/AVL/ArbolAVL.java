package Trees.AVL;

public class ArbolAVL {
    public NodoAVL raiz;

    // Método para obtener la raíz del árbol
    public NodoAVL obtenerRaiz() {
        return raiz;
    }

    // Método privado para obtener la altura de un nodo
    private int altura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Método para obtener el máximo de dos números
    private int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    // Método para obtener el nodo con el valor máximo en un subárbol
    private NodoAVL nodoMax(NodoAVL node) {
        NodoAVL current = node;
        while (current.hijoderecho != null) {
            current = current.hijoderecho;
        }
        return current;
    }

    // Método para obtener el factor de equilibrio de un nodo
    public int obtenerFE(NodoAVL x) {
        if (x == null) {
            return 0;
        } else {
            return altura(x.hijoizquierdo) - altura(x.hijoderecho);
        }
    }

    // Rotación simple a la izquierda
    private NodoAVL rotacionIzquierda(NodoAVL c) {
        NodoAVL nuevaRaiz = c.hijoderecho;
        NodoAVL tempo = nuevaRaiz.hijoizquierdo;
        nuevaRaiz.hijoizquierdo = c;
        c.hijoderecho = tempo;

        // Actualización de las alturas
        c.altura = maximo(altura(c.hijoizquierdo), altura(c.hijoderecho)) + 1;
        nuevaRaiz.altura = maximo(altura(nuevaRaiz.hijoizquierdo), altura(nuevaRaiz.hijoderecho)) + 1;
        return nuevaRaiz;
    }

    // Rotación simple a la derecha
    private NodoAVL rotacionDerecha(NodoAVL c) {
        NodoAVL nuevaRaiz = c.hijoizquierdo;
        NodoAVL tempo = nuevaRaiz.hijoderecho;
        nuevaRaiz.hijoderecho = c;
        c.hijoizquierdo = tempo;

        // Actualización de las alturas
        c.altura = maximo(altura(c.hijoizquierdo), altura(c.hijoderecho)) + 1;
        nuevaRaiz.altura = maximo(altura(nuevaRaiz.hijoizquierdo), altura(nuevaRaiz.hijoderecho)) + 1;
        return nuevaRaiz;
    }

    // Método para insertar una clave en el árbol AVL
    public NodoAVL insertar(NodoAVL nodo, int clave) {
        if (nodo == null) {
            return new NodoAVL(clave);
        }
        if (clave < nodo.clave) {
            nodo.hijoizquierdo = insertar(nodo.hijoizquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.hijoderecho = insertar(nodo.hijoderecho, clave);
        } else {
            return nodo;
        }

        // Actualizacion de la altura del nodo actual
        nodo.altura = 1 + maximo(altura(nodo.hijoizquierdo), altura(nodo.hijoderecho));

        // Obtener el factor de equilibrio del nodo actual
        int equilibrio = obtenerFE(nodo);

        // Realizar las rotaciones necesarias según el factor de equilibrio
        if (equilibrio > 1 && clave < nodo.hijoizquierdo.clave) {
            return rotacionDerecha(nodo);
        }
        if (equilibrio < -1 && clave > nodo.hijoderecho.clave) {
            return rotacionIzquierda(nodo);
        }
        if (equilibrio > 1 && clave > nodo.hijoizquierdo.clave) {
            nodo.hijoizquierdo = rotacionIzquierda(nodo.hijoizquierdo);
            return rotacionDerecha(nodo);
        }
        if (equilibrio < -1 && clave < nodo.hijoderecho.clave) {
            nodo.hijoderecho = rotacionDerecha(nodo.hijoderecho);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Método de búsqueda de claves en el AVL
    public boolean buscar(int clave) {
        return buscarAVL(raiz, clave);
    }

    // Método de búsqueda de claves en el AVL de manera recursiva
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

    // Método para eliminar una clave del árbol AVL
    public void eliminar(int clave) {
        raiz = eliminarAVL(raiz, clave);
    }

    // Método privado para eliminar una clave del árbol AVL de manera recursiva
    private NodoAVL eliminarAVL(NodoAVL nodo, int clave) {
        if (nodo == null) {
            return nodo;
        }
        if (clave < nodo.clave) {
            nodo.hijoizquierdo = eliminarAVL(nodo.hijoizquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.hijoderecho = eliminarAVL(nodo.hijoderecho, clave);
        } else {
            // Nodo encontrado, realizar la eliminación
            if ((nodo.hijoizquierdo == null) || (nodo.hijoderecho == null)) {
                NodoAVL temp = null;
                if (temp == nodo.hijoizquierdo) {
                    temp = nodo.hijoderecho;
                } else {
                    temp = nodo.hijoizquierdo;
                }
                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                NodoAVL temp = nodoMax(nodo.hijoizquierdo);
                nodo.clave = temp.clave;
                nodo.hijoizquierdo = eliminarAVL(nodo.hijoizquierdo, temp.clave);
            }
        }
        if (nodo == null) {
            return nodo;
        }

        // Se actualiza la altura del nodo
        nodo.altura = maximo(altura(nodo.hijoizquierdo), altura(nodo.hijoderecho)) + 1;

        // Obtener el factor de equilibrio del nodo
        int equilibrio = obtenerFE(nodo);

        // Realizar rotaciones si es necesario
        if (equilibrio > 1 && obtenerFE(nodo.hijoizquierdo) >= 0) {
            return rotacionDerecha(nodo);
        }
        if (equilibrio < -1 && obtenerFE(nodo.hijoderecho) <= 0) {
            return rotacionIzquierda(nodo);
        }
        if (equilibrio > 1 && obtenerFE(nodo.hijoizquierdo) < 0) {
            nodo.hijoizquierdo = rotacionIzquierda(nodo.hijoizquierdo);
            return rotacionDerecha(nodo);
        }
        if (equilibrio < -1 && obtenerFE(nodo.hijoderecho) > 0) {
            nodo.hijoderecho = rotacionDerecha(nodo.hijoderecho);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Método para mostrar el árbol AVL
    public void mostrarArbol() {
        mostrarArbolAVL(raiz, 0);
    }

    // Método privado para mostrar el árbol AVL de manera recursiva
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
