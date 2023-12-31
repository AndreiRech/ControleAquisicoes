package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrador extends Usuario {
    public Administrador(int identificador, String nome) {
        super(identificador, nome, null);
    }

    public boolean isAdmin() {
        return true;
    }

    public void listarPedidos(String dataInicial, String dataFinal, List<Pedido> listaPedidos) {
        List<Pedido> pedidosEntreDatas = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {

            if (dataInicial.equals(pedido.getDataPedido()) && dataFinal.equals(pedido.getDataConclusao())) {
                pedidosEntreDatas.add(pedido);
                System.out.println(pedido);
            }
        }
        if(pedidosEntreDatas.isEmpty())
            System.out.println("Não foram efetuados pedidos nesse período.");
    }

    public void buscaPorFuncionario(int id, List<Pedido> listaPedidos) {
        List<Pedido> pedidosDoFuncionario = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getFuncionario().getIdentificador()==id){
                pedidosDoFuncionario.add(pedido);
                System.out.println(pedido);
            }
        }
        if(pedidosDoFuncionario.isEmpty())
            System.out.println("Nenhum pedido foi registrado por esse funcionário.");
    }

    public void buscaPorDescricao(String descricaoItem, List<Pedido> listaPedidos) {
        List<Pedido> pedidosComDescricaoItem = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            for (ItemDePedido item : pedido.getItens()) {
                if (item.getDescricao().equalsIgnoreCase(descricaoItem)) {
                    pedidosComDescricaoItem.add(pedido);
                    break;
                }
            }
        }
        if(pedidosComDescricaoItem.isEmpty())
            System.out.println("Não há nenhum pedido com essa descrição.");

        for (Pedido pedido : pedidosComDescricaoItem){
            System.out.println(pedido);
        }
    }

    public void visualizaPedido(Pedido pedido) {

        Scanner in = new Scanner(System.in);
        int opcao = 3;

        if(pedido==null)
            System.out.println("Não existe um pedido registrado sob esse número.");
        else {
            if(pedido.getStatus().equals(StatusPedido.APROVADO)){
                System.out.println("Pedido já aprovado.");

            } else if (pedido.getStatus().equals(StatusPedido.REPROVADO)) {
                System.out.println("Pedido já reprovado.");

            } else {
            System.out.println(pedido);

            System.out.println();
            System.out.println("O que deseja fazer?");
            System.out.println("(1) Aprovar pedido");
            System.out.println("(2) Reprovar pedido");
            System.out.println("(3) Manter pedido em aberto");
            System.out.println("Selecione uma opção: ");
            opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    pedido.setStatusAprovacao(StatusPedido.APROVADO);
                    System.out.println("Pedido aprovado.");
                    break;
                case 2:
                    pedido.setStatusAprovacao(StatusPedido.REPROVADO);
                    System.out.println("Pedido reprovado.");
                    break;
                case 3:
                    System.out.println("Pedido ainda em aberto.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;

                }
            }
        }
    }


    public String toString(){
        return "Administrador{" +
                ", identificador=" + getIdentificador() +
                ", nome=" + getNome() +
                ", admin=" + isAdmin();
    }
}
