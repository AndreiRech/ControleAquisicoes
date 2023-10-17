package src;

public class Pedido {
    private Funcionario funcionario;
    private Departamento departamento;
    private String descricao;
    private double valorTotal;
    private boolean statusAprovacao;
    private int status;
    private String dataConclusao;
    private String dataPedido;

    public Pedido(Funcionario funcionario, String descricao, double valorTotal, boolean statusAprovacao, int status, String dataConclusao, String dataPedido, Departamento departamento) {
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.statusAprovacao = statusAprovacao;
        this.status = status;
        this.dataConclusao = dataConclusao;
        this.dataPedido = dataPedido;
        this.departamento = departamento;
    }

    public boolean getStatusAprovacao() {
        return statusAprovacao;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
