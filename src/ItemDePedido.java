package src;

public class ItemDePedido {
    private String descricao;
    private double valor;
    private int quantidade;

    public ItemDePedido(String descricao, double valor, int quantidade){
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return  "ITEM: " + descricao + " VALOR UNITÁRIO: R$" + valor + " QUANTIDADE: " + quantidade;
    }
}
