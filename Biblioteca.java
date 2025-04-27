import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    //BD em memória
    private List<Livro> acervo = new ArrayList<>();

    public Livro adicionar(Livro novoLivro) throws Exception {
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty()) {
            throw new Exception("titulo invalido, o livro precisa de um titulo para ser registrado");
        }
        else if(novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty()) {
            throw new Exception("Por favor digite um Nome, esse texto não pode estar nullo");
        }
        else if(novoLivro.getAnoPublicacao() < 1400 || novoLivro.getAnoPublicacao() > LocalDate.now().getYear()){
            throw new Exception("Coloque um ano de publição valido, maior que 1400, menor que " + LocalDate.now().getYear());
        }
        else if(novoLivro.getnumPaginas() <= 0){
            throw new Exception("O numero de paginas deve ser maior que 0.");
        }
        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())) {
                throw new Exception("titulo invalido. Já existe um livro cadastrado com este título");
            }
        }
        acervo.add(novoLivro);

        return novoLivro;
    }


    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> pesquisarTodos() {
        return acervo;
    }
    public void removerPorTitulo(String titulo) throws Exception{
        boolean i = false;
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : acervo) {
            if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                acervo.remove(livro);
                resultado.add(livro);
                System.out.println("livros excluidos: " + resultado);
                System.out.println("Quantidade de livros excluidos: [" + resultado.size()+"]");
                i = true;
                break;
            }
        }
        if (!i){
            throw new Exception("titulo não corresponde a nenhum livro");
        }
    }
    public List<Livro> pesquisaPorAno(int ano){
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() >= ano ){
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

}
