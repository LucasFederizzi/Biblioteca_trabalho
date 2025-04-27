import java.util.Calendar;

public abstract class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas;
  
    @Override //annotations
    public String toString() {
        String descricao = 
            "Título: " + getTitulo() +
            " - Autor: " + getAutor() +
            " - Ano: " + getAnoPublicacao() +
            " - Tempo da publicação " + calcularTempoPublicacao();
            
        return descricao;
    }
    public abstract String getFormato();

    public int calcularTempoPublicacao(){
        int publicacao = getAnoPublicacao();
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int tempoPublicacao = (anoAtual - publicacao);
        return tempoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
    public int getnumPaginas() {
        return numeroPaginas;
    }
    public void setnumPaginas(int numPaginas) {
        this.numeroPaginas = numPaginas;
    }
}