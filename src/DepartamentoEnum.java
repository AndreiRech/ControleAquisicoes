package src;

public enum DepartamentoEnum {
    FINANCEIRO(9800),
    RH(7300),
    ENGENHARIA(15500),
    MANUTENCAO(9000),
    VENDAS(7500),
    TI(11000),
    MAREKETING(10500);


    private double valorMaximoPedido;

    DepartamentoEnum(double valorMaximoPedido){
        this.valorMaximoPedido = valorMaximoPedido;
    }

    public double getValorMaximoPedido(){
        return valorMaximoPedido;
    }
}
