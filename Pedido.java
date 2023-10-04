public class Pedido {
    private Funcionario funcionario;
    private String descricao;
    private double valorTotal;
    private boolean statusAprovacao;
    private int status;
    private String dataConclusao;
    private String dataPedido;

    public Pedido(Funcionario funcionario, String descricao, double valorTotal, boolean statusAprovacao, int status, String dataConclusao, String dataPedido) {
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.statusAprovacao = statusAprovacao;
        this.status = status;
        this.dataConclusao = dataConclusao;
        this.dataPedido = dataPedido;
    }
}
