package src;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private Funcionario funcionario;
    private Departamento departamento;
    private String dataPedido;
    private String dataConclusao;
    private StatusPedido status;
    private List<ItemDePedido> itens;
    private double valorTotal;

    public Pedido(Funcionario funcionario, Departamento departamento, String dataPedido, String dataConclusao, List<ItemDePedido> itens,  double valorTotal) {
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
}