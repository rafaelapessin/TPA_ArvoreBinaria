
// RAFAELA AMORIM PESSIN
// TPA - 2023/1
// ÁRVORE BINÁRIA

// IMPORTANDO AS BIBLIOTECAS
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.nio.file.FileSystems;
// import java.nio.file.Path;
// import java.util.Arrays;
import java.util.Scanner;
import tree.Aluno;              // classe Aluno
import tree.BinaryTree;         // classe BinaryTree

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    // private static Scanner entrada = new Scanner.nextLine();
    
    private static void println(String line){
        System.out.println(line);
    }

    // Método para exibir as estatíticas da árvore binária
    // Vai imprimir a quantidade de elementos, altura da árvore, maior elemento, menor elemento e pior caso
    private static void printStatisctsMatricula(BinaryTree<Aluno> tree){
        System.out.println("==========IMPRIMINDO ÁRVORE ==========");
        System.out.println("Quantidade de elementos: "+ tree.getAmountItems());
        System.out.println("Altura da árvore: "+ tree.getHeightTree());
        System.out.println("Maior elemento: " + tree.getBiggerItem().toString());
        System.out.println("Menor elemento: " + tree.getLesserItem().toString());
        System.out.println("Pior caso de busca: " + tree.getWorstCase().toString());
        System.out.println("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n");
    }

    // IMPLEMENTAR ESTE MÉTODO
    // Método para exibir as estatíticas da árvore binária
    // Vai imprimir a quantidade de elementos, altura da árvore, maior elemento, menor elemento e pior caso
    private static void printStatisctsNome(BinaryTree<Aluno> tree){
        System.out.println("==========IMPRIMINDO ÁRVORE ==========");
        System.out.println("Quantidade de elementos: "+ tree.getAmountItems());
        System.out.println("Altura da árvore: "+ tree.getHeightTree());
        System.out.println("Maior elemento: " + tree.getBiggerItem().toString());
        System.out.println("Menor elemento: " + tree.getLesserItem().toString());
        System.out.println("Pior caso de busca: " + tree.getWorstCase().toString());
        System.out.println("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n");
    }

    // Método para popular/preencher a árvore com os registros do arquivo gerado
    private static void fillTree(BinaryTree<Aluno> tree, String nameFile) throws IOException{
        System.out.println("===========CARREGANDO ÁRVORE==========");
        BufferedReader buffRead = new BufferedReader(new FileReader(nameFile));
		String linha = "";
        int qtdRegistros = Integer.parseInt(buffRead.readLine());           // Lê a primeira linha do arquivo que diz a quantidade de resgitros
		for(int i = 1; i <= qtdRegistros; i++){                             // Faz um loop de 1 até a quantidade de linhas do arquivo para preencher a árvore
            linha = buffRead.readLine();
            String[] line = linha.split(";");                        // Quebra a linha do arquivo através do separador ";" para pegar matrícula, nome e nota
            int matricula = Integer.parseInt(line[0]);
            String nome = line[1];
            float nota = Float.parseFloat(line[2]);
            
            Aluno student = new Aluno(matricula, nome, nota);               // Cria uma variável do tipo Aluno que tem matrícula, nome, e nota
            // System.out.println(student);
            tree.insertItem(student);                                       // Insere o Aluno na árvore
		}
		buffRead.close();
        System.out.println("===========>CARREGOU ÁRVORE<==========\n");   // Vai inserir todos os alunos do arquivo na árvore
    }
    
    // Método para buscar um aluno por matrícula
    // A saída é um print na tela: se achar a matrícula na árvore, imprime o nó; se não achar, imprime que a matrícula não existe
    private static void searchByMatricula(BinaryTree<Aluno> tree){
        System.out.println("===========BUSCANDO MATRICULA=========");
        System.out.println("Qual a matricula que deseja buscar? ");
        
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        // System.out.println("testando "+ a);
        long start = System.currentTimeMillis();
        Aluno item = tree.searchItem(a);                           // Pesquisa o elemento na árvore; retorna o nó se ele existir, senão retorna null
        long ends = System.currentTimeMillis();

        // System.out.println(ends);
        // System.out.println(start);
        System.out.println("Tempo de busca: " + (ends - start));
        
        if(item != null){                                         // Se a função searchItem() retornar o nó, é porque achou: printa o nó na tela
            println("Matrícula encontrada: " + item.toString());
        }else{
            System.out.println("Matrícula não existe");         // Se a função searchItem() retornar null, é porque não achou: printa que não existe
        }
        System.out.println("======================================\n");
    }

    // IMPLEMENTAR ESTE MÉTODO
    // Método para buscar um aluno por matrícula
    // A saída é um print na tela: se achar a matrícula na árvore, imprime o nó; se não achar, imprime que a matrícula não existe
    private static void searchByNome(BinaryTree<Aluno> tree){
        System.out.println("===========BUSCANDO NOME=========");
        System.out.println("Qual a nome que deseja buscar? ");
        
        int matricula = 0;
        String nome = entrada.next();
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        // System.out.println("testando "+ a);
        long start = System.currentTimeMillis();
        Aluno item = tree.searchItem(a);                           // Pesquisa o elemento na árvore; retorna o nó se ele existir, senão retorna null
        long ends = System.currentTimeMillis();

        // System.out.println(ends);
        // System.out.println(start);
        System.out.println("Tempo de busca: " + (ends - start));
        
        if(item != null){                                         // Se a função searchItem() retornar o nó, é porque achou: printa o nó na tela
            println("Nome encontrado: " + item.toString());
        }else{
            System.out.println("Nome não existe");         // Se a função searchItem() retornar null, é porque não achou: printa que não existe
        }
        System.out.println("======================================\n");
    }

    // Método para incluir um aluno na árvore
    private static void includeAluno(BinaryTree<Aluno> tree){
        System.out.println("=============INSERINDO ALUNO==========");
        System.out.println("Qual a matricula? ");           // Solicita a matrícula, nome e nota 
        int matricula = entrada.nextInt();
        
        System.out.println("Qual o nome? ");
        String nome = entrada.next();
        
        System.out.println("Qual a nota? ");
        float nota = entrada.nextFloat();
        
        Aluno aluno = new Aluno(matricula, nome, nota);     // Cria um novo aluno com os dados de entrada

        tree.insertItem(aluno);                             // Insere o aluno na árvore
        System.out.println("======================================\n");
    }

    // Excluindo um aluno por matrícula
    private static void deleteByMatricula(BinaryTree<Aluno> tree){
        System.out.println("=============DELETANDO ALUNO POR MATRÍCULA==========");
        System.out.println("Qual matricula deseja excluir?");
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        tree.removeItem(aluno);                     // Chama o método de remoção
        System.out.println("======================================\n");
    }

    // IMPLEMENTAR ESTE MÉTODO
    // Excluindo um aluno por matrícula
    private static void deleteByNome(BinaryTree<Aluno> tree){
        System.out.println("=============DELETANDO ALUNO POR NOME==========");
        System.out.println("Qual nome deseja excluir?");
        int matricula = 0;
        String nome = entrada.next();
        float nota = 0;
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        tree.removeItem(aluno);                     // Chama o método de remoção
        System.out.println("======================================\n");
    }
    
    // Sair o programa deve percorrer a árvore usando caminhamento "em ordem" e gerar um arquivo em que cada linha apresentará matrícula, nome e nota de um aluno, sempre separados por ";"
    private static void sair(BinaryTree<Aluno> tree){
        tree.walkInOrder();
    }

    // Menu de opções para o usuário
    private static void printMenu(){
        System.out.println("=================MENU=================");
        System.out.println("1 - Exibir estatísticas por matrícula");
        System.out.println("2 - Busca por matrícula");
        System.out.println("3 - Excluir por matrícula");
        System.out.println("4 - Incluir aluno");
        System.out.println("5 - Exibir estatísticas por nome");
        System.out.println("6 - Busca por nome");
        System.out.println("7 - Excluir por nome");
        System.out.println("8 - Caminhar em nível");
        System.out.println("9 - Sair");
        System.out.println("======================================");
    }

    private static int getSelection(){
        System.out.println("Escolha uma opção: ");
        return entrada.nextInt();
    }

    public static void main(String[] args){        
        BinaryTree<Aluno> tree = new BinaryTree<Aluno>();
        try {
            fillTree(tree, "entradaBalanceada1000000.txt");       // Passa o nome do arquivo gerado pelo GeradorArquivos
            // fillTree(tree, "entrada.txt");
            
            int selection;
            do {
                printMenu();
                selection = getSelection();
                switch (selection) {
                    case 1:
                        printStatisctsMatricula(tree);           // Exibe as estatísticas por matrícula: quantidade de elementos, altura da árvore, maior elemento, menor elemento e pior caso
                        break;
                    case 2:
                        searchByMatricula(tree);                // Busca por matrícula
                        break;
                    case 3:
                        deleteByMatricula(tree);                // Exclusão por matrícula
                        break;
                    case 4:
                        includeAluno(tree);                     // Incluir aluno
                        break;
                    case 5:
                        printStatisctsNome(tree);               // Exibe as estatísticas por nome: quantidade de elementos, altura da árvore, maior elemento, menor elemento e pior caso
                        break;
                    case 6:
                        searchByNome(tree);                     // Busca por nome
                        break;
                    case 7:
                        deleteByNome(tree);                     // Exclusão por nome
                        break;
                    case 8:
                        tree.walkInLevel();                     // Caminhando em nível
                        break;
                    case 9:
                        sair(tree);                             // Finaliza o programa
                        break;
                    default:
                        break;
                }
            } while(selection != 9);
        } catch (IOException e) {
            println("Erro ao abrir o arquivo");
        }
    }
}

