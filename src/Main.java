package src;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static List<Usuario> listaUsuarios = new ArrayList<>();
    public static List<Pedido> listaPedidos = new ArrayList<>();
    public static Usuario usuarioAtual = null;

    public static void contPedidos(ArrayList <Pedido> pedidos) {
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

    public static double calculaMedia30(ArrayList <Pedido> pedidos, String dataAtual) {
        double valorTotal = 0, media = 0;
        int quantidade = 0;

        for (Pedido p : pedidos) {
            if(verificaData(p.getDataPedido())) {
                quantidade++;
                valorTotal += p.getValorTotal();
            }
        }

        if (quantidade > 0) {
            media = valorTotal / quantidade;
        }

        return media;
    }

    public static void totalDepartamento30(ArrayList<Pedido> pedidos, ArrayList<Departamento> departamentos) {
        for (Departamento d : departamentos) {
            double valorTotalDepartamento = 0;
            for (Pedido p : pedidos) {
                if (verificaData(p.getDataPedido()) && p.getDepartamento().getIdentificador() == d.getIdentificador()) {
                    valorTotalDepartamento += p.getValorTotal();
                }
            }
            System.out.printf("Valor total do departamento %d nos últimos 30 dias: %.2f\n", d.getIdentificador(), valorTotalDepartamento);
        }
    }

    public static Pedido maiorPedidoAberto(ArrayList <Pedido> pedidos) {
        Pedido maiorP = null;
        double maiorV = 0;

        for (Pedido p : pedidos) {
            if (p.getStatusAprovacao()) {
                if (maiorP == null || p.getValorTotal() > maiorV) {
                    maiorP = p;
                    maiorV = p.getValorTotal();
                }
            }
        }

        return maiorP;
    }

    public static boolean verificaData(String dataPedido) {
        String[] pPedido = dataPedido.split("-");

        if (pPedido.length != 3) {
            return false;
        }

        int anoPedido = Integer.parseInt(pPedido[2]);
        int mesPedido = Integer.parseInt(pPedido[1]);
        int diaPedido = Integer.parseInt(pPedido[0]);

        if (anoPedido>0 && anoPedido<2024 && mesPedido>0 && mesPedido<13 && diaPedido>0 && diaPedido<32) {
            LocalDate dtDataPedido = LocalDate.of(anoPedido, mesPedido, diaPedido);
            LocalDate dtDataAtual = LocalDate.now();

            long dias = ChronoUnit.DAYS.between(dtDataPedido, dtDataAtual);
            return dias <= 30;
        }

        return false;
    }

    public static void removePedidoPorId(){
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        Optional<Pedido> pedidoFiltrado = listaPedidos.stream().filter(pedido -> pedido.getId() == id).findFirst();
        if(pedidoFiltrado.isPresent()){
            if(pedidoFiltrado.get().getFuncionario().getIdentificador() == usuarioAtual.getIdentificador()){
                listaPedidos.remove(pedidoFiltrado.get());
                System.out.println("Pedido excluido com sucesso");
            }else{
                System.out.println("Pedido só pode ser excluido por seu criador");
            }
        }
        else{
            System.out.println("Pedido não encontrado");
        }
    }

    public static void selecionaUsuario(Scanner scan){
        int id = 0;

        System.out.println("=======SELEÇÃO DE USUÁRIO========");
        System.out.println("Os usuários cadastrados estão dispostos na lista abaixo: ");
        for (Usuario listaUsuario : listaUsuarios) {
            System.out.println(listaUsuario.getIdentificador() + " - " + listaUsuario.getNome());
        }

        System.out.println("==================================");
        System.out.println("Insira o identificador do usuário a ser selecionado");
        id = scan.nextInt();

        for(Usuario usuario : listaUsuarios){
            if(id == usuario.getIdentificador()){
                usuarioAtual = usuario;
            }
        }

        if(Objects.isNull(usuarioAtual)){
            System.out.println("Usuário não encontrado!");
        }

    }

    public static void Menu(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;

        while(opcao !=0){
            if(!Objects.isNull(usuarioAtual)){
                System.out.println("USUÁRIO ATUAL: "+usuarioAtual.getIdentificador()+ " - " + usuarioAtual.getNome());
            }
            System.out.println("1 - Selecionar usuário");
            System.out.println("5 - Excluir pedido por id");
            System.out.println("0 - Sair do programa");

            System.out.println();
            System.out.println("Selecione uma opção: ");

            opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    selecionaUsuario(scan);
                case 5:
                    removePedidoPorId();
                    break;
                default:
                    break;
            }
        }


    }
    public static void main(String[] args) {
        List<Funcionario> funcionarios = InicializadorDados.inicializacaoFuncionarios();

        listaUsuarios.addAll(funcionarios);

        Map<String, Departamento> departamentos = InicializadorDados.inicializacaoDepartamentos();

        Menu();

        double valorMaximo = departamentos.get("Financeiro").getDepartamentoEnum().getValorMaximoPedido();
        System.out.println(valorMaximo);

    }
}