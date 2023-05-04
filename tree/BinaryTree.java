// RAFAELA AMORIM PESSIN
// TPA - 2023/1
// ÁRVORE BINÁRIA

package tree;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// import tree.ITree;

// Classe <BinaryTre>
public class BinaryTree <T extends Comparable<T>> extends ITree<T>{
    private Node<T> root;                           // Raiz da árvore
    private T lesserNode, biggerNode, worstCase;    // Menor nó, maior nó, pior caso
    private int heightTree = 0, amountItems = 0;    // Altura da árvore, quantidade de itens

    // Estrutura da árvore, setando tudo para null
    public BinaryTree(){
        this.root = null;
        this.lesserNode = null;     // menor nó
        this.biggerNode = null;     // maior nó
    }
    
    // Método para escrever no arquivo de saída <saida_EM_ORDEM.txt>
    public static void writeOutputInFile(String line){
        try (FileWriter arq = new FileWriter("saida_EM_ORDEM.txt", true)) {
            try (PrintWriter gravarArq = new PrintWriter(arq)) {
                gravarArq.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um objeto na árvore
    protected Node<T> insert(Node<T> root, Node<T> item){

        int result = item.getValue().compareTo((root.getValue()));  // Compara os nós (elemento de entrada com o atual a ser comparado)
        if(result == 0){                                            // Aqui retorna 0: os elementos são iguais
            this.root = item;                                       // Se for igual, atualizar dados
        }else{
            if(result < 0){                                         // Aqui retorna -1: A matrícula atual (de entrada do usuário) é menor que matrícula do nó que está sendo comparado
                if(root.getLeftChild() == null){
                    root.setLeftChild((item));
                } else {
                    insert(root.getLeftChild(), item);              // Se for menor, insere no filho a esquerda
                }
            } else {                                                // Aqui retorna 1: A matrícula atual (de entrada do usuário) é maior que matrícula do nó que está sendo comparado
                if(root.getRightChild() == null){
                    root.setRightChild((item));
                } else {
                    insert(root.getRightChild(), item);             // Se for maior, insere no filho a direita
                }
            }
        }
        return root;
    }

    // Método para inserir um objeto na árvore
    public void insertItem(T item) {
        //TO-DO: Implementar método de inserção
        Node<T> newNode = new Node<T>(item);        // Cria um novo nó
        if(this.root != null){                      // Se o nó for diferente de nulo, chama a função de inserção
            insert(this.root, newNode);
        } else {                                    // Senão, cria o nó
            this.root = newNode;
        }
        this.amountItems++;                         // Após inserir um elemento, incrementa a quantidade de elementos
    }

    // Pesquisa se o nome ou matrícula está na árvore
    // Se o elemento estiver na árvore, retorna o nó
    // Senão, retorna null
    private T search(Node<T> root, T item){                     // Passa a raiz/nó (nó a nó) e o elemento
        if(root != null){
            // 0 se a string for igual à outra string.
            // < 0 se a string for menor que a outra string
            // > 0 se a string for maior que a outra string (mais caracteres)
            int result = item.compareTo(root.getValue()); 
            if(result == 0){                                    // Se for igual, pega o valor
                return root.getValue();
            } else if(result < 0){                              // Se for menor, chama a função de busca e pega o filho a esquerda; busca a esquerda
                return search(root.getLeftChild(), item);
            } else {
                return search(root.getRightChild(), item);      // Se for maior, chama a função de busca e pega o filho a direita; busca a direita
            }
        }else {
            return null;
        }
    }

    // A função retorna o nó, se a matrícula ou o nome (genérico) existirem, senão retorna null
    // Passa o nome ou a matrícula para a função search() e procura se está na árvore
    public T searchItem(T item) {
        // System.out.println("passando o item "+ item);
        // System.out.println("get name "+item.getNome());
        // System.out.println("tipo "+item.getClass().getSimpleName());
        return search(this.root, item);
    }

    

    // Método que verifica se tem filho
    // Retorna  2 se tiver dois filhos
    // Retorna  1 se tiver 1 filho que esteja à esquerda
    // Retorna  0 se tiver 1 filho que esteja à direita
    // Retorna -1 se não tiver dois filhos
    private int haveChild(Node<T> dad){
        if(dad.getLeftChild() != null && dad.getRightChild() != null){
            return 2;
        } else if(dad.getLeftChild() != null){
            return 1;
        } else if(dad.getRightChild() != null){
            return 0;
        } else {
            return -1;
        }
    }

    // Método para remover filhos, tanto a esquerda, quanto a direita
    // Retorna  0 se não removerá
    // Retorna -1 se for o filho à esquerda
    // Retorna  1 se for o filho à direita
    private int anyOfMyChildWillBeRemoved(Node<T> dad, T item){
        Node<T> child = dad.getLeftChild();
        if(child != null){
            int result = child.getValue().compareTo(item);
            if(result == 0){
                return -1;          // Remove filho a esquerda
            }
            child = dad.getRightChild();
            result = child.getValue().compareTo(item);
            if(result == 0){
                return 1;           // Remove filho a direta
            }
        }
        return 0;                   // Não remove filho
    }

    // Método para remover filho, passando o nó e a posição (1 ou -1)
    // 1 será filho a direita; -1 será filho a esquerda
    private void removeChild(Node<T> root, Node<T> child, int lado){
        int childHaveChild;
        childHaveChild = haveChild(child);  // verifica se o nó tem filhos
        if(childHaveChild == 2){    // Se filho tiver 2 filhos
            if(lado == -1){
                root.setLeftChild(child.getLeftChild());        // Atribui valor do filho a esquerda
            } else if(lado == 1){
                root.setRightChild(child.getLeftChild());       // Atribui valor do filho a direita
            }
            insert(this.root, child.getRightChild());           // Insere filho a direita
        } else if(childHaveChild == 1){     // O filho tiver apenas 1 filho à esquerda
            if(lado == -1){
                root.setLeftChild(child.getLeftChild());        // Atribui valor do filho a esquerda
            } else if(lado == 1) {
                root.setRightChild(child.getLeftChild());       // Atribui valor do filho a direita
            }
        } else if(childHaveChild == 0){     // O filho tiver apenas 1 filho à direita
            if(lado == -1){
                root.setLeftChild(child.getRightChild());       // Atribui valor do filho a esquerda
            } else if(lado == 1) {
                root.setRightChild(child.getRightChild());      // Atribui valor do filho a direita
            }
        } else { // O filho não ter filhos, seta pra null
            root.setLeftChild(null);
            root.setRightChild(null);
        }
    }

    // Método para remover filho a esquerda
    // Chama o método removeChild() passando o filho a esquerda e a posição de remoção -1
    private void removeLeftChild(Node<T> root){
        removeChild(root, root.getLeftChild(), -1);
    }

    // Método para remover filho a direita
    // Chama o método removeChild() passando o filho a direita e a posição de remoção 1
    private void removeRightChild(Node<T> root){
        removeChild(root, root.getRightChild(), 1);
    }
    
    // Método para remover a raiz
    private void removeRoot(){
        int haveChild = haveChild(this.root);                   // Retorna se o nó tem filhos
        if(haveChild == 2){                                     // Se o nó tiver 2 filhos, a raiz, recebe o filho a esquerda e chama o método de inserção
            this.root = this.root.getLeftChild();
            insert(this.root, this.root.getRightChild());
        } else if(haveChild == 1){                              // Se o nó tiver 1 filho que esteja à esquerda, a raiz recebe o filho a esquerda
            this.root = this.root.getLeftChild();
        } else if (haveChild == 0){
            this.root = this.root.getRightChild();              // Se o nó tiver 1 filho que esteja à direita, a raiz recebe o filho a direta
        } else {                                                // Se o nó não tiver dois filhos, retorna null para a raiz
            this.root = null;
        }
    }

    // Método para remover o nó
    private void remove(Node<T> root, T item){
        if(root != null){
            int result = item.compareTo(root.getValue());
            // System.out.println(String.format("result = %d", result));
            if(result == 0){                            // Só vai acontecer caso deseje remover a raiz
                int childRemove = anyOfMyChildWillBeRemoved(root, item);
                if(childRemove == -1){                  // Caso do filho esquerdo do nó atual ser removido
                    removeLeftChild(root);
                } else if(childRemove == 1){            // Caso do filho direito do nó atual ser removido
                    removeRightChild(root);
                }
            } else {
                int childRemove = anyOfMyChildWillBeRemoved(root, item);
                if(result < 0){
                    if(childRemove == -1){              // Caso do filho esquerdo do nó atual ser removido
                        removeLeftChild(root);
                    } else {
                        remove(root.getLeftChild(), item);
                    }
                } else {
                    if(childRemove == 1){               // Caso do filho direito do nó atual ser removido
                        removeRightChild(root);
                    } else {
                        remove(root.getRightChild(), item);
                    }
                }
            }
        }
    }

    // Método para remover um elemento da árvore
    public void removeItem(T item) {
        if(this.amountItems <= 0){          // Verifica a quantidade de itens da árvore e impede falhas para caso de árvore vazia
            return;         
        }
        // TO-DO: Implementar método de remoção
        if(item.compareTo(this.root.getValue()) == 0){
            removeRoot();
        }else {
            remove(this.root, item);
        }
        this.amountItems--;                 // Atualiza a quantidade de itens da árvore (subtrai)
    }

    // Método de caminhamento em ordem auxiliar
    private void walkInOrderAux(Node<T> root){
        // TO-DO: Implementar método de caminhar em ordem
        if(root != null){
            walkInOrderAux(root.getLeftChild());                // Visita o filho a esquerda (chamada recursiva)
            writeOutputInFile(root.getValue().toString());      // Escreve no arquivo de saída o nó corrente
            walkInOrderAux(root.getRightChild());               // Visita o filho a direita (chamada recursiva)
        }
    }

    // Método de caminhamento em ordem
    public void walkInOrder(){
        System.out.println("==Escrevendo em 'saida_EM_ORDEM.txt'==");
        long tempoInicial = System. currentTimeMillis();
        walkInOrderAux(this.root);                                          // Chama a função de caminhamento em ordem auxiliar
        long tempoFinal = System. currentTimeMillis();
        System.out.println("Tempo total para caminhamento em ordem em ms: " + (tempoFinal - tempoInicial));
        System.out.println("======================================");
    }

    // Método de caminhamento em nível auxiliar
    // Usar altura da árvore para implementar
    // Caminha descendo por cada filho, incrementando até cada nível
    /* 
    1. Começa da raiz
    2. Gera um intervalo de inicio 0 (primeiro nível da árvore) até a altura da árvore
    3. Desce e imprime as informações se o nó atual for daquela nível que tem que ser impresso naquele momento
    3.1 Para saber o nó é do nível ou não, podemos passar uma variável na chamada informando o próximo nível
    */
    private void walkInLevelAux(Node<T> root, int levelWanted, int levelCurrent){
        if(levelWanted == levelCurrent){                                                    // Verifica se está no nível; se o nível procurado é igual o nível corrente, escreve no arquivo de saída
            writeOutputInFile(root.getValue().toString());
        } else if(levelCurrent < levelWanted) { 
            int haveChild = haveChild(root);                                                // Retorna o número de filhos
            if(haveChild == 2){                                                             // Se tiver dois filhos
                walkInLevelAux(root.getLeftChild(), levelWanted, levelCurrent + 1);         // Visita o filho a esquerda
                walkInLevelAux(root.getRightChild(), levelWanted, levelCurrent + 1);        // Visita o filho a direita
            } else if(haveChild == 1){                                                      // Se tiver 1 filho que esteja à esquerda
                walkInLevelAux(root.getLeftChild(), levelWanted, levelCurrent + 1);         // Visita o filho a esquerda
            } else if(haveChild == 0){                                                      // Se tiver 1 filho que esteja à direita
                walkInLevelAux(root.getRightChild(), levelWanted, levelCurrent + 1);        // Visita o filho a direita
            }
        }
    }
  
    // Método de caminhamento em nível
    public void walkInLevel(){
        System.out.println("=========Caminhando em Nível:=========");
        long tempoInicial = System. currentTimeMillis();        
        updateHeightTree(this.root);                                     // Atualiza a altura da árvore
        for(int i = 0;i <= this.heightTree;i++){
            walkInLevelAux(this.root, i, 0);                // Chama a função de caminhamento em nível auxiliar
        }
        long tempoFinal = System. currentTimeMillis();
        System.out.println("Tempo total para caminhamento em nível em ms: " + (tempoFinal - tempoInicial));
        System.out.println("=========>Caminhou em Nível<==========");
    }

    // Método para saber a altura da árvore
    private int heightTree(Node<T> root, int level){
        // Pega quantos níveis possui a arvore
        if(root != null){
            level++;
            if(root.getLeftChild() != null && root.getRightChild() != null){
                int heightE = heightTree(root.getLeftChild(), level);
                int heightD = heightTree(root.getRightChild(), level);
                if(heightE > heightD){
                    return heightE;
                } else {
                    return heightD;
                }
            } else if(root.getLeftChild() != null){
                return heightTree(root.getLeftChild(), level);
            } else if(root.getRightChild() != null){
                return heightTree(root.getRightChild(), level);
            }
        }
        return level;
    }

    // Método para atualizar a altura da árvore
    private void updateHeightTree(Node<T> root){
        this.heightTree = heightTree(root, -1);
    }

    // Método que retona a altura da árvore atualizada
    public int getHeightTree() {
        updateHeightTree(this.root);
        return heightTree;
    }

    // Método que retorna a quantidade de elementos da árvores
    public int getAmountItems(){
        return amountItems;
    }

    // Método que atualiza o menor nó da árvore
    private void updateLesserNode(Node<T> root){
        if(root.getLeftChild() != null){                // A verificação é feita com os filhos a esquerda pois os elementos menores estão a esquerda da raiz
            updateLesserNode(root.getLeftChild());
        } else {
            this.lesserNode = root.getValue();
        }
    }

    // Método que retorna o menor nó da árvore atualizado
    public T getLesserItem(){
        updateLesserNode(root);
        return lesserNode;
    }
    
    // Método que atualiza o maior nó da árvore
    private void updateBiggerNode(Node<T> root){
        if(root.getRightChild() != null){               // A verificação é feita com os filhos a direita pois os elementos maiores estão a direita da raiz
            updateBiggerNode(root.getRightChild());
        } else {
            this.biggerNode = root.getValue();
        }
    }

    // Método que retorna o maior nó da árvore atualizado
    public T getBiggerItem(){
        updateBiggerNode(root);
        return biggerNode;
    }

    // Método que atualiza o pior caso de busca
    // O pior caso será aquele mais profundo na árvore
    // Pega a raiz, anda nível por nível, verificando se os nós possui filhos abaixo
    // Quando não tiver mais filhos, verifica quem tem o nível mais alto
    private void updateWorstCase(Node<T> root, int levelWanted, int levelCurrent){
        if(levelWanted == levelCurrent){
            this.worstCase = root.getValue();
        } else if(levelCurrent < levelWanted) { 
            int haveChild = haveChild(root);
            if(haveChild == 2){                                                             // Se tiver dois filhos
                updateWorstCase(root.getLeftChild(), levelWanted, levelCurrent + 1);
                updateWorstCase(root.getRightChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 1){                                                      // Se tiver 1 filho que esteja à esquerda
                updateWorstCase(root.getLeftChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 0){                                                      // Se tiver 1 filho que esteja à direita
                updateWorstCase(root.getRightChild(), levelWanted, levelCurrent + 1);
            }
        }
    }
    
    // Método retorna o pior caso de busca
    public T getWorstCase(){
        updateHeightTree(this.root);
        updateWorstCase(this.root, this.heightTree, 0);
        return this.worstCase;
    }

    @Override
    public void insertElemento(T valor) {
        throw new UnsupportedOperationException("Unimplemented method 'insertElemento'");
    }

    // Retorna a altura em relação a um nó específico
    public int alturaNo(Node<T> root){
        if (root == null){
            return -1;
        }
        updateHeightTree(root);
        return heightTree;
    }

    public int fatorBalanceamento(Node<T> rootEsquerda, Node<T> rootDireita){
        int alturaEsquerda, alturaDireita;
        if (rootEsquerda == null){
            alturaEsquerda = -1; 
        } else {
            alturaEsquerda = alturaNo(rootEsquerda.getLeftChild());
        }

        if (rootDireita == null){
            alturaDireita = -1; 
        } else {
            alturaDireita = alturaNo(rootEsquerda.getRightChild());
        }
        return alturaDireita - alturaEsquerda;
    }

}