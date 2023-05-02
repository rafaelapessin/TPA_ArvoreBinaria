// RAFAELA AMORIM PESSIN
// TPA - 2023/1
// ÁRVORE BINÁRIA

package tree;

// Classe <Aluno>
public class Aluno implements Comparable<Aluno>{

    // Tipo de indexação (matrícula ou nome)
    static public enum TypesSearch {
		BY_NAME(0),BY_MATRICULA(1);
		public int valorCarta;
		TypesSearch(int valor) {
		    valorCarta = valor;
		}
	}

    private int matricula;
    private String nome;
    private float nota;
    private TypesSearch typeSearch;

    // A estrutura do aluno foi definida por: matrícula, nome e nota
    public Aluno(int matricula, String nome, float nota, TypesSearch ts){
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
        this.typeSearch = ts;
    }

    @Override
    // O método compara as matrículas
    // Se a matrícula atual (de entrada do usuário) for igual a matrícula do nó que está sendo comparado, retorna 0
    // Se for menor, retorna -1; se for maior, retorna 1
    public int compareTo(Aluno a) {
        // executa busca por nome
        if(this.typeSearch == TypesSearch.BY_NAME)
        {
            // if(this.nome == a.getNome()){         // nome = entrada do usuário; a = nome do nó
            //     return 0;
            // } else if(this.nome < a.getNome()){
            //     return -1;
            // } else {
            //     return 1;
            // }
            return this.nome.compareTo(a.getNome());
        }
        // executa busca por matrícula
        else if (this.typeSearch == TypesSearch.BY_MATRICULA)
        {
            if(this.matricula == a.getMatricula()){         // matricula = entrada do usuário; a = matricula do nó
                return 0;
            } else if(this.matricula < a.getMatricula()){
                return -1;
            } else {
                return 1;
            }
        }
        return 0;        
    }
    
    // Método criado para obter a matrícula do aluno
    public int getMatricula() {
        return matricula;
    }

    // Método criado para obter o nome do aluno 
    public String getNome(){
        return this.nome;
    }
    
    // Método criado para obter a nota do aluno 
    public float getNota(){
        return nota;
    }

    // Método criado para atribuir valor a variável nota
    public float setNota(){
        return nota;
    }

    @Override
    // O método retorna matrícula, nome e nota formatados
    public String toString() {
        return String.format("%d;%s;%.2f", this.matricula, this.nome, this.nota);
    }
}