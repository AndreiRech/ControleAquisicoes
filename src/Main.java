package src;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static List<Usuario> listaUsuarios = new ArrayList<>();

    public static List<ItemDePedido> itens = new ArrayList<>();

    protected static List<Pedido> listaPedidos = new ArrayList<>();
    public static Map<String, Departamento> departamentos = new HashMap<>();
    public static Usuario usuarioAtual = new Usuario(0, "Visitante", null);

    public static ArrayList<Integer> adminIds = new ArrayList<>();

    public static void registraAdminIds(ArrayList<Integer> adminIds){
        for(int i=0; i<listaUsuarios.size(); i++){
            if(listaUsuarios.get(i) instanceof Administrador){
                adminIds.add(listaUsuarios.get(i).getIdentificador());
            }
        }
    }

    public static void registraPedido(Funcionario funcionario, Departamento departamento, String dataPedido, String dataConclusao, List<ItemDePedido> itens) {

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
            System.out.println("Pedido registrado!");
            System.out.println("============================");
        }else{
            System.out.println("Data inválida ou valor máximo do departamento excedido.");
        }
    }

    public static void contPedidos(List <Pedido> pedidos) {
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
        System.out.printf("Quantidade de pedidos reprovados : [%d] | [%.2f%%]", qntReprovados, ((double) qntReprovados/qntTotal)*100);
    }

    public static double calculaMedia30(List <Pedido> pedidos) {
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

    public static void totalDepartamento30(List<Pedido> pedidos, Map<String, Departamento> departamentos) {
        for (Map.Entry<String, Departamento> entry : departamentos.entrySet()) {
            Departamento d = entry.getValue();
            double valorTotalDepartamento = 0;
            for (Pedido p : pedidos) {
                if (verificaData(p.getDataPedido()) && p.getDepartamento().getIdentificador() == d.getIdentificador()) {
                    valorTotalDepartamento += p.getValorTotal();
                }
            }
            System.out.printf("Valor total do departamento %s nos últimos 30 dias: %.2f\n", d.getIdentificador(), valorTotalDepartamento);
        }
    }

    public static void maiorPedidoAberto(List <Pedido> pedidos) {
        Pedido maiorP = null;
        double maiorV = 0;

        for (Pedido p : pedidos) {
            if (p.getStatus() == StatusPedido.ABERTO) {
                if (maiorP == null || p.getValorTotal() > maiorV) {
                    maiorP = p;
                    maiorV = p.getValorTotal();
                }
            }
        }

        System.out.println(maiorP);
    }

    public static boolean verificaData(String dataPedido) {
        String[] pPedido = dataPedido.split("-");

        if (pPedido.length != 3) {
            return false;
        }

        int anoPedido = 2000+Integer.parseInt(pPedido[2]);
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

        System.out.println("Digite o NÚMERO DO PEDIDO a ser removido");
        int id = scan.nextInt();

        boolean wasRemoved = false;

        for(Pedido p : listaPedidos){
            if(p.getNumeroPedido() == id){
                listaPedidos.remove(p);
                wasRemoved = true;
                System.out.println("Pedido removido com sucesso.");
                break;
            }
        }

        if(!wasRemoved){
            System.out.println("Pedido não encontrado.");
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
            if (usuarioAtual instanceof Administrador) {
                menuAdmin();
            } else {
                menuFuncionario();
            }
        }
    }

    public static void menu(){
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
    public static void menuFuncionario(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;

        do {
            System.out.println();
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
                    System.out.println("Digite a data de conclusão do Pedido (00-00-00): ");
                    String data2 = scan.next();
                    registraPedido(usuarioAtual.buscarFuncionario(usuarioAtual.getIdentificador(), listaUsuarios), usuarioAtual.getDepartamento(), data1, data2, itens);
                    break;
                case 3:
                    removePedidoPorId();
                    break;
                default:
                    break;
            }
        }while(!(usuarioAtual instanceof Administrador));
    }

    public static void menuAdmin(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;
        int id = 0;

        Administrador admin = new Administrador(usuarioAtual.getIdentificador(), usuarioAtual.getNome());

        do {
            System.out.println();
            System.out.println("===MENU DE ADMINISTRADOR===");

            System.out.println("USUÁRIO ATUAL: " + usuarioAtual.getIdentificador() + " - " + usuarioAtual.getNome());

            System.out.println("1 - Trocar usuário");
            System.out.println("2 - Registrar novo pedido");
            System.out.println("3 - Listar todos os pedidos entre duas datas");
            System.out.println("4 - Buscar pedidos por funcionário solicitante");
            System.out.println("5 - Buscar pedidos pela descrição de um item");
            System.out.println("6 - Visualizar os detalhes de um pedido para aprová-lo ou rejeitá-lo");
            System.out.println("7 - Remover pedido");
            System.out.println("8 - Exibir número total de pedidos");
            System.out.println("9 - Exibir o número de pedidos efetuados nos últimos 30 dias");
            System.out.println("10 - Exibir o valor total de cada categoria nos últimos 30 dias");
            System.out.println("11 - Exibir detalhes do pedido de aquisição de maior valor ainda aberto.");
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

                    boolean isAdmin = false;

                    for(int i=0; i<6; i++){
                        if(id == adminIds.get(i)){
                            System.out.println("ERRO: Funcionário é ADMINISTRADOR");
                            isAdmin =  true;
                            break;
                        }
                    }

                    if(isAdmin)
                        break;

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
                    break;
                case 7:
                    removePedidoPorId();
                    break;
                case 8:
                    contPedidos(listaPedidos);
                    break;
                case 9:
                    calculaMedia30(listaPedidos);
                    break;
                case 10:
                    totalDepartamento30(listaPedidos, departamentos);
                    break;
                case 11:
                    maiorPedidoAberto(listaPedidos);
                    break;
                default:
                    break;
            }
        } while(opcao !=0);
    }
    public static void main(String[] args) {

        List<Usuario> usuarios = InicializadorDados.inicializacaoUsuarios();
        listaUsuarios.addAll(usuarios);

        registraAdminIds(adminIds);

        List<ItemDePedido> itensDePedido = InicializadorDados.inicializacaoItensDePedido();
        itens.addAll(itensDePedido);

        departamentos = InicializadorDados.inicializacaoDepartamentos();

        menu();

        double valorMaximo = departamentos.get("Financeiro").getDepartamentoEnum().getValorMaximoPedido();
        System.out.println(valorMaximo);
    }
}