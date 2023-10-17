package src;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private Funcionario funcionario;
    private Departamento departamento;
    private String descricao;
    private double valorTotal;

    private boolean statusAprovacao;
    private String dataConclusao;
    private String dataPedido;
    private StatusPedido status;
    private List<ItemDePedido> itens;

    public Pedido(Funcionario funcionario, String descricao, double valorTotal, boolean statusAprovacao, StatusPedido status, String dataConclusao, String dataPedido, Departamento departamento) {
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.dataPedido = dataPedido;
        this.dataConclusao = null;
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
        this.valorTotal = valorTotal;
    }

    public void adicionarItem(ItemDePedido item) {
        itens.add(item);
    }

    public void setStatusAprovacao(StatusPedido status) {
        this.status = status;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
        this.dataPedido = dataPedido;
        this.departamento = departamento;
    }

    public boolean isStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(boolean statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "funcionario=" + funcionario +
                ", departamento=" + departamento +
                ", dataPedido='" + dataPedido + '\'' +
                ", dataConclusao='" + dataConclusao + '\'' +
                ", status=" + status +
                ", itens=" + itens +
                ", valorTotal=" + valorTotal +
                '}';
    }

    public Departamento getDepartamento() {
        return departamento;
    }

}
