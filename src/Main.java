package src;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = InicializadorDados.inicializacaoFuncionarios();
        Map<String, Departamento> departamentos = InicializadorDados.inicializacaoDepartamentos();

        Scanner in = new Scanner(System.in);

        Usuario u = new Usuario(1, "Teste", true);


        System.out.println("Insira uma data: ");    //(00-00-00)
        String data2 = in.next();

        if(u.verificaData(data2)){
                System.out.println("Menor que 30 dias");
        }


        double valorMaximo = departamentos.get("Financeiro").getDepartamentoEnum().getValorMaximoPedido();
        System.out.println(valorMaximo);

    }
}