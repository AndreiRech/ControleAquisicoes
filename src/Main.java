package src;
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
            StatusPedido status = StatusPedido.ABERTO;
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

    public static void selecionaUsuario(Scanner scan) {
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
        Funcionario f = new Funcionario(usuarioAtual);
        int opcao = 1;

        do {
            System.out.println("===MENU DE FUNCIONÁRIO===");

            if (!Objects.isNull(usuarioAtual)) {
                System.out.println("USUÁRIO ATUAL: " + usuarioAtual.getNome());
            }


            System.out.println("1 - Selecionar usuário");
            System.out.println("2 - Registrar novo pedido");
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
            System.out.println("0 - Sair do programa");

            System.out.println();
            System.out.println("Selecione uma opção: ");

            opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    selecionaUsuario(scan);
                    break;
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