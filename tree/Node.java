// RAFAELA AMORIM PESSIN
// TPA - 2023/1
// ÁRVORE BINÁRIA

package tree;

// Classe <Node> - Nó da árvore
public class Node <T extends Comparable<T>>{
    private T value;                            // o valor do nó é genérico,  podendo ser a matrícula ou o nome do aluno
    private Node<T> leftChild, rightChild;      // o nó tem um filho a esqueda e um filho a direita    

    // Declarando o nó da árvore
    // Contém o valor genérico (nome ou matrícula), filho a direta e filho a esquerda
    public Node(T value){
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    // Método criado para atribuir valor
    public void setValue(T value){
        this.value = value;
    }

    // Método criado para obter valor 
    public T getValue(){
        return this.value;
    }

    // Método criado para atribuir valor do filho a esquerda
    public void setLeftChild(Node<T> node) {
        this.leftChild = node;
    }

    // Método criado para atribuir valor do filho a direita
    public void setRightChild(Node<T> node) {
        this.rightChild = node;
    }

    // Método criado para obter o valor do filho a esquerda
    public Node<T> getLeftChild() {
        return leftChild;
    }

    // Método criado para obter o valor do filho a direita
    public Node<T> getRightChild() {
        return rightChild;
    }
}