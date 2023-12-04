package uni.aed.drozdek.cap6.ejr6;

public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    // Inserta un nuevo nodo en el árbol
    public void insert(int key) {
        root = insertRec(root, key);
    }
    // Función auxiliar recursiva para insertar un nuevo nodo en el árbol
    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }
    // Elimina un nodo del árbol utilizando el enfoque de eliminación asimétrica
    public void deleteAsymmetric(int key) {
        root = deleteExtremeNode(root, key, false );
    }
    
    public void deleteSymmetric(int key) {
        root = deleteExtremeNode(root, key, true);
    }
    //Con este nuevo metodo reemplazamos los dos metodos deleteAsymmetricRec y deleteSymmetricRec
    // simplificando así el codigo
    private Node deleteExtremeNode(Node root, int key, boolean isLeft) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteExtremeNode(root.left, key, isLeft);
        } 
        else if (key > root.key) {
            root.right = deleteExtremeNode(root.right, key, isLeft);
        } 
        else {
            if (root.left == null && root.right == null) {
                return null;
            } 
            else if (root.left == null) {
                return root.right;
            } 
            else if (root.right == null) {
                return root.left;
            }

            Node extremeNode = findExtremeNode(root.left, isLeft);
            root.key = extremeNode.key;
            root.left = deleteExtremeNode(root.left, extremeNode.key, isLeft);
        }

        return root;
    }
    // Y analogamente con este metodo nuevo reemplazamos los metodos buscar sucesor y predecesor
    private Node findExtremeNode(Node node, boolean isLeft) {
        while ((isLeft && node.left != null) || (!isLeft && node.right != null)) {
            node = isLeft ? node.left : node.right;
        }
    return node;
    }
    // Función auxiliar recursiva para eliminar un nodo utilizando el enfoque de eliminación asimétrica
    /*private Node deleteAsymmetricRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteAsymmetricRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteAsymmetricRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            //Se busca al sucesor mas pequeño y no al mas grande
            Node successor = findSuccessor(root.right);
            root.key = successor.key;
            root.right = deleteAsymmetricRec(root.right, successor.key);
        }

        return root;
    }
    // Encuentra el sucesor (valor mínimo) en un subárbol dado
    private Node findSuccessor(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }*/
    // Elimina un nodo del árbol utilizando el enfoque de eliminación simétrica
    
    // Función auxiliar recursiva para eliminar un nodo utilizando el enfoque de eliminación simétrica
   /* private Node deleteSymmetricRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteSymmetricRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteSymmetricRec(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            Node predecessor = findPredecessor(root.left);
            root.key = predecessor.key;
            root.left = deleteSymmetricRec(root.left, predecessor.key);
        }

        return root;
    }
    // Encuentra el predecesor (valor máximo) en un subárbol dado
    private Node findPredecessor(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }*/
    // Obtiene la altura del árbol
    public int getHeight() {
        return getHeightRec(root);
    }
    // Función auxiliar recursiva para obtener la altura de un subárbol dado
    private int getHeightRec(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeightRec(root.left);
        int rightHeight = getHeightRec(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
    // Obtiene la longitud del camino interno del árbol
    public int getInternalPathLength() {
        return getInternalPathLengthRec(root, 0);
    }
    // Función auxiliar recursiva para obtener la longitud del camino interno de un subárbol dado
    private int getInternalPathLengthRec(Node root, int depth) {
        if (root == null) {
            return 0;
        }

        int leftPathLength = getInternalPathLengthRec(root.left, depth + 1); 
        int rightPathLength = getInternalPathLengthRec(root.right, depth + 1);

        return depth + leftPathLength + rightPathLength;
    }
}
