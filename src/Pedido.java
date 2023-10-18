package src;

public class Pedido {
    private static int id = 0;
    private Funcionario funcionario;
    private Departamento departamento;
    private String descricao;
    private double valorTotal;
    private boolean statusAprovacao;
    private int status;
    private String dataConclusao;
    private String dataPedido;

    public Pedido(Funcionario funcionario, String descricao, double valorTotal, boolean statusAprovacao, int status, String dataConclusao, String dataPedido, Departamento departamento) {
        this.id = id;
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.statusAprovacao = statusAprovacao;
        this.status = status;
        this.dataConclusao = dataConclusao;
        this.dataPedido = dataPedido;
        this.departamento = departamento;
        id++;
    }

    public boolean getStatusAprovacao() {
        return statusAprovacao;
    }

    public int getId(){
        return this.id;
    };

    public Funcionario getFuncionario(){
        return this.funcionario;
    };

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
