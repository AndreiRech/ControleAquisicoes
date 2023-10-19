package src;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static List<Usuario> listaUsuarios = new ArrayList<>();

    public static List<ItemDePedido> itens = new ArrayList<>();

    protected static List<Pedido> listaPedidos = new ArrayList<>();

    public static Usuario usuarioAtual = new Usuario(0, "Visitante", false, null);

    public static void registraPedido(Funcionario funcionario, Departamento departamento, String dataPedido, String dataConclusao, List<ItemDePedido> itens) {
        if(!funcionario.isAdmin()){
            Random random = new Random();
            int numeroPedido = random.nextInt(1000000);
            double valorTotal = 0;
            for (ItemDePedido item : itens){
                valorTotal += item.getValor()*item.getQuantidade();
            }
            if (valorTotal<=departamento.getDepartamentoEnum().getValorMaximoPedido()) {
                Pedido novoPedido = new Pedido(funcionario, funcionario.getDepartamento(), dataPedido, dataConclusao, StatusPedido.ABERTO, itens, valorTotal, numeroPedido);
                listaPedidos.add(novoPedido);
                System.out.println(novoPedido);
                System.out.println("Pedido registrado");
            }else{
                System.out.println("Valor máximo por departamento excedido.");
            }
        }else{
            System.out.println("Usuário não pode ser admin");
        }
    }

    public static void contPedidos(ArrayList <Pedido> pedidos) {
        int qntTotal=0, qntAprovados=0, qntReprovados=0;
        for (Pedido p : pedidos) {
            qntTotal++;
            if (p.getStatus()  == StatusPedido.APROVADO) {
                qntAprovados++;
            }
            else if(p.getStatus()  == StatusPedido.REPROVADO) {
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
            if (p.getStatus() == StatusPedido.APROVADO) {
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

        int anoPedido = Integer.parseInt(pPedido[2]);
        int mesPedido = Integer.parseInt(pPedido[1]);
        int diaPedido = Integer.parseInt(pPedido[0]);

        LocalDate dtDataPedido = LocalDate.of(anoPedido, mesPedido, diaPedido);
        LocalDate dtDataAtual = LocalDate.now();

        long dias = ChronoUnit.DAYS.between(dtDataPedido, dtDataAtual);


        return dias <= 30;
    }

    public static void removePedidoPorId(){
        System.out.println("Digite o id do pedido a ser removido");
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

        for (Usuario usuario : listaUsuarios) {
            if (id == usuario.getIdentificador()) {
                usuarioAtual = usuario;
                break; // Encerramos o loop quando encontramos o novo usuário.
            }
        }

        if (Objects.isNull(usuarioAtual)) {
            System.out.println("Usuário não encontrado!");
        } else {
            // Após selecionar o novo usuário, verifique se ele é um administrador.
            if (usuarioAtual.getAdmin()) {
                menuAdmin();
            } else {
                MenuFuncionario();
            }
        }
    }

    public static void Menu(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;

        if (!Objects.isNull(usuarioAtual)) {
            System.out.println("USUÁRIO ATUAL: " + usuarioAtual.getNome());
        }
        System.out.println("1 - Selecionar usuário");
        System.out.println("0 - Sair do programa");

        System.out.println();
        System.out.println("Selecione uma opção: ");

        opcao = scan.nextInt();

        switch (opcao) {
            case 1:
                selecionaUsuario(scan);
                break;
            default:
                break;
        }
    }
    public static void MenuFuncionario(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;

        do {
            System.out.println("===MENU DE FUNCIONÁRIO===");

            if (!Objects.isNull(usuarioAtual)) {
                System.out.println("USUÁRIO ATUAL: " + usuarioAtual.getNome());
            }


            System.out.println("1 - Selecionar usuário");
            System.out.println("2 - Registrar novo pedido");
            System.out.println("3 - Remover pedido");
            System.out.println("0 - Sair do programa");

            System.out.println();
            System.out.println("Selecione uma opção: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    selecionaUsuario(scan);
                    break;
                case 2:
                    System.out.println("Digite a data do Pedido (00-00-00): ");
                    String data1 = scan.next();
                    System.out.println("Digite a data de conclusão Pedido (00-00-00): ");
                    String data2 = scan.next();
                    System.out.println("Digite o identificador do funcionário responsável: ");
                    int id = scan.nextInt();
                    registraPedido(usuarioAtual.buscarFuncionario(id, listaUsuarios), usuarioAtual.buscarFuncionario(id, listaUsuarios).getDepartamento(), data1, data2, itens);
                    break;
                case 3:
                    removePedidoPorId();
                    break;
                default:
                    break;
            }
        }while(!usuarioAtual.getAdmin());
    }

    public static void menuAdmin(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;
        int id = 0;
        Administrador admin = new Administrador(usuarioAtual);

        while(opcao !=0){
            System.out.println("===MENU DE ADMINISTRADOR===");

            System.out.println("USUÁRIO ATUAL: "+usuarioAtual.getIdentificador()+ " - " + usuarioAtual.getNome());


            System.out.println("1 - Trocar usuário");
            System.out.println("2 - Registrar novo pedido");
            System.out.println("3 - Listar todos os pedidos entre duas datas");
            System.out.println("4 - Buscar pedidos por funcionário solicitante");
            System.out.println("5 - Buscar pedidos pela descrição de um item");
            System.out.println("6 - Visualizar os detalhes de um pedido para aprová-lo ou rejeitá-lo");
            System.out.println("7 - Remover pedido");
            System.out.println("0 - Sair do programa");

            System.out.println();
            System.out.println("Selecione uma opção: ");

            opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    selecionaUsuario(scan);
                case 2:
                    System.out.println("Digite a data do Pedido (00-00-00): ");
                    String data1 = scan.next();
                    System.out.println("Digite a data de conclusão Pedido (00-00-00): ");
                    String data2 = scan.next();
                    System.out.println("Digite o identificador do funcionário responsável: ");
                    id = scan.nextInt();
                    registraPedido(usuarioAtual.buscarFuncionario(id, listaUsuarios), usuarioAtual.buscarFuncionario(id, listaUsuarios).getDepartamento(), data1, data2, itens);
                    break;
                case 3:
                    System.out.println("Digite a data inicial(00-00-00): ");
                    String datai = scan.next();
                    System.out.println("Digite a data final(00-00-00): ");
                    String dataf = scan.next();
                    admin.listarPedidos(datai, dataf, listaPedidos);
                    break;
                case 4:
                    System.out.println("Digite o identificador do funcionário solicitante: ");
                    id = scan.nextInt();
                    admin.buscaPorFuncionario(id, listaPedidos);
                    break;
                case 5:
                    System.out.println("Digite a descrição do item que deseja buscar: ");
                    String desc = scan.next();
                    admin.buscaPorDescricao(desc, listaPedidos);
                   break;
                case 6:
                    System.out.println("Digite o numero do pedido que deseja visualizar: ");
                    int num = scan.nextInt();
                    admin.visualizaPedido(admin.buscaPedido(num, listaPedidos));
                case 7:
                    removePedidoPorId();
                default:
                    break;
            }
        }
    }
    public static void main(String[] args) {
        List<Usuario> usuarios = InicializadorDados.inicializacaoUsuarios();
        listaUsuarios.addAll(usuarios);

        List<ItemDePedido> itensDePedido = InicializadorDados.inicializaçãoItensDePedido();
        itens.addAll(itensDePedido);

        Map<String, Departamento> departamentos = InicializadorDados.inicializacaoDepartamentos();

        Menu();

        double valorMaximo = departamentos.get("Financeiro").getDepartamentoEnum().getValorMaximoPedido();
        System.out.println(valorMaximo);
    }
}