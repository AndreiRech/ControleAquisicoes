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
        for (Pedido pedido : pedidos) {
            qntTotal++;
            if (pedido.getStatusAprovacao()) {
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

    @Override
    public String toString() {
        return "Usuario{" +
                "identificador=" + identificador +
                ", nome='" + nome + '\'' +
                ", admin=" + admin +
                '}';
    }
}
