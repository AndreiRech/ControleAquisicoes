package src;
import java.util.List;

public class Pedido {
    private static int id = 0;
    private Funcionario funcionario;
    private Departamento departamento;
    private String dataPedido;
    private String dataConclusao;
    private StatusPedido status;
    private List<ItemDePedido> itens;
    private double valorTotal;

    private int numeroPedido;

    public Pedido(Funcionario funcionario, Departamento departamento, String dataPedido, String dataConclusao, StatusPedido status, List<ItemDePedido> itens,  double valorTotal, int numeroPedido) {
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.dataPedido = dataPedido;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.numeroPedido = numeroPedido;
    }

    public void adicionarItem(ItemDePedido item) {
        itens.add(item);
    }

    public void setStatusAprovacao(StatusPedido status) {
        this.status = status;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public int getId(){
        return this.id;
    };

    public String getDataPedido() {
        return dataPedido;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }


    @Override
    public String toString() {
        return "==== DETALHES DO PEDIDO #" + numeroPedido +" ====" +
                "\nFUNCIONÁRIO RESPONSÁVEL: " + funcionario.getIdentificador() + " - " + funcionario.getNome() +
                "\nDEPARTAMENTO RESPONSÁVEL: " + departamento +
                "\nDATA DO PEDIDO: " + dataPedido +
                "\nDATA/PREVISÃO DE CONCLUSÃO: " + dataConclusao +
                "\nSTATUS: " + status +
                "\nITENS: " + itens +
                "\nTOTAL DO PEDIDO: R$" + valorTotal;
    }
}
