package src;
import src.utils.Utils;

import java.util.*;

public class Main {
    public static List<Usuario> listaUsuarios = new ArrayList<>();
    public static Usuario usuarioAtual = null;

    public static List<Pedido> listaPedidos = new ArrayList<>();

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

    public static void relatorioPedidos() {
        int qntTotal=0, qntAprovados=0, qntReprovados=0;
        for (Pedido p : listaPedidos) {
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

    public static double calculaMediaUltimosPedidos() {
        double valorTotal = 0, media = 0;
        int quantidade = 0;

        for (Pedido p : listaPedidos) {
            if(Utils.verificaData(p.getDataPedido())) {
                quantidade++;
                valorTotal += p.getValorTotal();
            }
        }

        if (quantidade > 0) {
            media = valorTotal / quantidade;
        }

        return media;
    }

    public static void Menu(){
        Scanner scan = new Scanner(System.in);
        int opcao = 1;

        while(opcao !=0){
            if(!Objects.isNull(usuarioAtual)){
                System.out.println("USUÁRIO ATUAL: "+usuarioAtual.getIdentificador()+ " - " + usuarioAtual.getNome());
            }
            System.out.println("1 - Selecionar usuário");
            System.out.println("0 - Sair do programa");

            System.out.println();
            System.out.println("Selecione uma opção: ");

            opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    selecionaUsuario(scan);
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