import java.util.List;
import java.util.Scanner;

public class Main {
    private static Biblioteca biblio = new Biblioteca();
    static Scanner inputTeclado = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        limpar();
        String menu = """
                ====== SYSBIBLIO ======
                Escolha uma das opções abaixo:
                1 - Adicionar novo livro
                2 - Pesquisar livro por título
                3 - Listar todos os livros
                4 - Remover livro por título
                5 - Pesquisar apartir de um ano
                0 - Sair
                """;
        int opcao;
            do {
            opcao = inputNumerico(inputTeclado, menu);
            switch (opcao) {
                case 1:
                    limpar();
                    adicionar(inputTeclado);
                    break;
                case 2:
                    limpar();
                    pesquisarPorTitulo();
                    break;
                case 3:
                    limpar();
                    pesquisarTodos();
                    break;
                 case 4:
                    limpar();
                    removerPorTitulo();
                    break;
                case 5:
                    limpar();
                    pesquisarPorAno();
                    break;
                case 0:
                    limpar();
                    System.out.println("Encerrando programa ...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);
    }

    private static void adicionar(Scanner inputTeclado) { //adiciona novos livros
        System.out.println("Digite o título do livro:");
        String titulo = inputTeclado.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = inputTeclado.nextLine();
        int anoPublicacao = inputNumerico(inputTeclado, "Digite o ano da publicação:");
        int numeroPaginas = inputNumerico(inputTeclado, "Digite o número de páginas:");

        Livro novoLivro;

        int tipoLivro = inputNumerico(inputTeclado, "Qual o tipo do livro: 1-Físico, 2 Digital");
        if (tipoLivro == 1) {
            novoLivro = new LivroFisico();
            System.out.println("Digite as dimensões do livro:");
            String dimensoes = inputTeclado.nextLine();
            int numeroExemplares = inputNumerico(inputTeclado, "Digite o número de exemplares:");

            LivroFisico novoLivroFisico = (LivroFisico) novoLivro;
            novoLivroFisico.setDimensoes(dimensoes);
            novoLivroFisico.setNumeroExemplares(numeroExemplares);
            //((LivroFisico) novoLivro).setDimensoes(dimensoes);
        } else {
            novoLivro = new LivroDigital();
            System.out.println("Digite o formato do arquivo:");
            String formatoArquivo = inputTeclado.nextLine();

            LivroDigital novoLivroDigital = (LivroDigital) novoLivro;
            novoLivroDigital.setFormatoArquivo(formatoArquivo);

        }

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setNumeroPaginas(numeroPaginas);

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    public static void limpar(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }


    public static void pesquisarTodos() { // mostra todos os livros
        List<Livro> livros = biblio.pesquisarTodos();
        if (livros.isEmpty()) {
            System.out.println("NENHUM LIVRO CADASTRADO");
        } else {
            System.out.println("Livros Cadastrados:");
            for (Livro livro : livros) {
                System.out.println(livro.toString());
            }
        }  
    }


    private static int inputNumerico(Scanner lerTeclado, String mensagem) { // le um numero do teclado
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void pesquisarPorTitulo(){  // pesquisa livros de acordo com palavras no titulo
        System.out.println("Pesquise por titulo:");
        String titulo = inputTeclado.nextLine();
        System.out.println("Livros encontrados:");
        var livros = biblio.pesquisarPorTitulo(titulo);
        for (Livro livro : livros ) {
            System.out.println(">" + livro.toString());
        }
    }
    private static void removerPorTitulo() throws Exception{
        System.out.println("Atenção: TODOS os livros que contem essa palava vão ser excluidos do acervo");
        System.out.println("digite o termo para excluir os livros");
        String titulo = inputTeclado.nextLine();
        biblio.removerPorTitulo(titulo);
        }
    private static void pesquisarPorAno() {
        System.out.println("Pesquisa por ano, resultará em livros apartir do ano digitado.");
        int ano = inputNumerico(inputTeclado, "digite um ano:");
        List<Livro> resultado = biblio.pesquisaPorAno(ano);
        if (resultado.isEmpty()) {
            System.out.println("NENHUM RESULTADO");
        } else {
            System.out.println("Livros correspondentes:");
            for (Livro livro : resultado) {
                System.out.println(livro.toString());
            }
        }
    }
}
