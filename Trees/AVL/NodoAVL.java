package Trees.AVL;
// Definición de la clase NodoAVL
public class NodoAVL {
    int clave, altura;
    NodoAVL hijoizquierdo, hijoderecho;

    // Constructor de la clase NodoAVL
    NodoAVL(int valor) {
        this.clave = valor;
        altura = 1;
    }
}
