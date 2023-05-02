// RAFAELA AMORIM PESSIN
// TPA - 2023/1
// ÁRVORE BINÁRIA

package tree;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;

// Classe AVLTree
public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {

    private Node<T> root;                           // Raiz da árvore
    // private T lesserNode, biggerNode, worstCase;    // Menor nó, maior nó, pior caso
    // private int heightTree = 0, amountItems = 0;    // Altura da árvore, quantidade de itens
    
    // Rotação à esquerda
    private Node<T> rotacaoEsquerda(Node<T> r){
        Node<T> filho = r.getRightChild();
        r.setRightChild(filho.getLeftChild());
        filho.setLeftChild(r);

        // atualizar altura

        return filho;       // retorna o nó que era filho; ao final da rotação o nó passou a ser a raiz
    }

    // Rotação à direita
    private Node<T> rotacaoDireta(Node<T> r){
        Node<T> filho = r.getLeftChild();
        r.setLeftChild(filho.getRightChild());
        filho.setRightChild(r);

        // atualizar altura

        return filho;       // retorna o nó que era filho; ao final da rotação o nó passou a ser a raiz
    }

    // Rotação esquerda-direita
    private Node<T> rotacaoEsquerdaDireta(Node<T> r){
        r.setLeftChild(rotacaoEsquerda(r.getLeftChild()));      // rotação a esquerda no filho a esquerda
        return rotacaoDireta(r);                                // rotação a direita na raiz
    }

    // Rotação direita-esquerda
    private Node<T> rotacaoDiretaEsquerda(Node<T> r){
        r.setRightChild(rotacaoDireta(r.getRightChild()));      // rotação a direita no filho a direita
        return rotacaoEsquerda(r);                              // rotação a esquerda na raiz
    }

    // Método de inclusão
    public void insertElemento(T valor){
        Node<T> novoNo = new Node<T>(valor);
        if (this.root ==  null){
            this.root = novoNo;
        }
        else{
            this.root = adicionarRecursao(this.root, novoNo);
        }
    }
    
    // protected Node<T> adicionarRecursao(Node<T> noAtual, Node<T> novoNo){
    //     // se o novo nó for menor que o nó atual, caminha para a esquerda
    //     if ( novoNo.getValue().compareTo(noAtual.getValue()) < 0 ){
    //         if ( noAtual.getLeftChild() == null ){
    //             noAtual.setLeftChild(novoNo);
    //         }
    //         else{
    //             noAtual.setLeftChild(adicionarRecursao((noAtual.getLeftChild()), novoNo));
    //         }
    //     }
    //     // se o novo nó for maior ou igual ao nó atual, caminha para a direita
    //     else{
    //         if ( noAtual.getRightChild() == null ){
    //             noAtual.setRightChild(novoNo);
    //         }
    //         else{
    //             noAtual.setRightChild(adicionarRecursao((noAtual.getRightChild()), novoNo));
    //         }
    //     }
    //     // atualizar altura
    //     return noAtual;
    // }

    // @Override
    protected Node<T> adicionarRecursao(Node<T> raiz, Node<T> novoNo){
        // raiz = super.adicionarRecursao(raiz, novoNo);
        raiz = adicionarRecursao(raiz, novoNo);
        if (raiz.fatorBalanceamento() > 1){
            if(raiz.getRightChild().fatorBalanceamento() > 0){
                raiz = this.rotacaoEsquerda(raiz);
            }
            else{
                raiz = this.rotacaoDiretaEsquerda(raiz);
            }
        }
        else if (raiz.fatorBalanceamento() <=1 ){
            if(raiz.getLeftChild().fatorBalanceamento() < 0){
                raiz = this.rotacaoDireta(raiz);
            }
            else{
                raiz = this.rotacaoEsquerdaDireta(raiz);
            }
        }
        return raiz;
    }

}
