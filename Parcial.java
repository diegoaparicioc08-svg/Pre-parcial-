public class Parcial {
    class Nodo {
        int valor;
        Nodo izquierda, derecha;

        public Nodo(int valor) {
            this.valor = valor;
            izquierda = derecha = null;
        }
    }

    class ArbolBST {
        Nodo raiz;

        Nodo insertar(Nodo raiz, int valor) {
            if (raiz == null) {
                return new Nodo(valor);
            }

            if (valor < raiz.valor) {
                raiz.izquierda = insertar(raiz.izquierda, valor);
            } else {
                raiz.derecha = insertar(raiz.derecha, valor);
            }

            return raiz;
        }

        int contarHojas(Nodo raiz) {
            if (raiz == null) {
                return 0;
            }
            if (raiz.izquierda == null && raiz.derecha == null) {
                return 1;
            }
            return contarHojas(raiz.izquierda) + contarHojas(raiz.derecha);
        }

        Nodo eliminar(Nodo raiz, int valor) {
            if (raiz == null) {
                return raiz;
            }

            if (valor < raiz.valor) {
                raiz.izquierda = eliminar(raiz.izquierda, valor);
            } else if (valor > raiz.valor) {
                raiz.derecha = eliminar(raiz.derecha, valor);
            } else {

                if (raiz.izquierda == null) {
                    return raiz.derecha;
                } else if (raiz.derecha == null) {
                    return raiz.izquierda;
                }

                raiz.valor = minValor(raiz.derecha);

                raiz.derecha = eliminar(raiz.derecha, raiz.valor);
            }

            return raiz;
        }

        int minValor(Nodo raiz) {
            int min = raiz.valor;
            while (raiz.izquierda != null) {
                min = raiz.izquierda.valor;
                raiz = raiz.izquierda;
            }
            return min;
        }

        void inorden(Nodo raiz) {
            if (raiz != null) {
                inorden(raiz.izquierda);
                System.out.print(raiz.valor + " ");
                inorden(raiz.derecha);
            }
        }
    }

    public static void main(String[] args) {
        Parcial parcial = new Parcial();
        
        ArbolBST arbol = parcial.new ArbolBST();

        arbol.raiz = arbol.insertar(arbol.raiz, 55);
        arbol.raiz = arbol.insertar(arbol.raiz, 25);
        arbol.raiz = arbol.insertar(arbol.raiz, 75);
        arbol.raiz = arbol.insertar(arbol.raiz, 15);
        arbol.raiz = arbol.insertar(arbol.raiz, 35);
        arbol.raiz = arbol.insertar(arbol.raiz, 65);
        arbol.raiz = arbol.insertar(arbol.raiz, 8555);
        arbol.raiz = arbol.insertar(arbol.raiz, 25);
        arbol.raiz = arbol.insertar(arbol.raiz, 75);
        arbol.raiz = arbol.insertar(arbol.raiz, 15);
        arbol.raiz = arbol.insertar(arbol.raiz, 35);
        arbol.raiz = arbol.insertar(arbol.raiz, 65);
        arbol.raiz = arbol.insertar(arbol.raiz, 85);

        arbol.raiz = arbol.insertar(arbol.raiz, 30);

        System.out.println("Recorrido inorden del árbol BST (antes de eliminar el 65):");
        arbol.inorden(arbol.raiz);
        System.out.println();

        System.out.println("Número de hojas en el árbol: " + arbol.contarHojas(arbol.raiz));

        arbol.raiz = arbol.eliminar(arbol.raiz, 65);

        System.out.println("Recorrido inorden del árbol BST (después de eliminar el 65):");
        arbol.inorden(arbol.raiz);
    }
}