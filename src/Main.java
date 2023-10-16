package src;
import java.util.*;

public class Main {
    public static List<Usuario> listaUsuarios = new ArrayList<>();
    public static Usuario usuarioAtual = null;

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