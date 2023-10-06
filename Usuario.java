import java.util.ArrayList;

public class Usuario {
    private int identificador;
    private String nome;
    private boolean admin;

    public Usuario(int identificador,String nome, boolean admin){
        this.identificador = identificador;
        this.nome = nome;
        this.admin = admin;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void contPedidos(ArrayList <Pedido> pedidos) {
        int qntTotal=0, qntAprovados=0, qntReprovados=0;
        for (Pedido p : pedidos) {
            qntTotal++;
            if (p.getStatusAprovacao()) {
                qntAprovados++;
            }
            else {
                qntReprovados++;
            }
        }

        System.out.printf("Quantidade de pedidos : [%d]\n", qntTotal);
        System.out.printf("Quantidade de pedidos aprovados : [%d] | [%.2f%%]\n", qntAprovados, ((double) qntAprovados/qntTotal)*100);
        System.out.printf("Quantidade de pedidos reprovados : [%d] | [%.2f%%]", qntReprovados, ((double) qntAprovados/qntReprovados)*100);
    }

    public double calculaMedia30(ArrayList <Pedido> pedidos, String dataAtual) {
        double valorTotal = 0, media = 0;
        int quantidade = 0;

        for (Pedido p : pedidos) {
            if(verificaData(p.getDataPedido(), dataAtual)) {
                quantidade++;
                valorTotal += p.getValorTotal();
            }
        }

        if (quantidade > 0) {
            media = valorTotal / quantidade;
        }

        return media;
    }

    private boolean verificaData(String dataPedido, String dataAtual) {
        String[] pPedido = dataPedido.split("-");
        String[] pAtual = dataAtual.split("-");

        int anoPedido = Integer.parseInt(pPedido[2]);
        int mesPedido = Integer.parseInt(pPedido[1]);
        int diaPedido = Integer.parseInt(pPedido[0]);

        int anoAtual = Integer.parseInt(pAtual[2]);
        int mesAtual = Integer.parseInt(pAtual[1]);
        int diaAtual = Integer.parseInt(pAtual[0]);

        int diferencaAno = anoAtual - anoPedido;
        int diferencaMes = mesAtual - mesPedido;
        int diferencaDia = diaAtual - diaPedido;
        
        return (diferencaAno == 0 && diferencaMes == 0 && diferencaDia <= 30);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "identificador=" + identificador +
                ", nome='" + nome + '\'' +
                ", admin=" + admin +
                '}';
    }
}
