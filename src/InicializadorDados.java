package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicializadorDados {

    public static List<Usuario> inicializacaoUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Map<String, Departamento> departamentos = inicializacaoDepartamentos();


        //inicializacao dos usuários
        usuarios.add(new Usuario(1, "Rogerio", departamentos.get("Financeiro")));
        usuarios.add(new Administrador(2, "Claudia"));
        usuarios.add(new Usuario(3, "Fernando",  departamentos.get("Financeiro")));
        usuarios.add(new Usuario(4, "Felisberto", departamentos.get("RH")));
        usuarios.add(new Administrador(5, "Rosangela"));
        usuarios.add(new Usuario(6, "Maria Claudia", departamentos.get("RH")));
        usuarios.add(new Usuario(7, "Robson", departamentos.get("Engenharia")));
        usuarios.add(new Administrador(8, "Juliano"));
        usuarios.add(new Usuario(9, "Rafela", departamentos.get("Engenharia")));
        usuarios.add(new Usuario(10, "Luiza", departamentos.get("Manutencao")));
        usuarios.add(new Administrador(11, "Sibely"));
        usuarios.add(new Usuario(12, "Shirley", departamentos.get("Manutencao")));
        usuarios.add(new Usuario(13, "Sonia", departamentos.get("Vendas")));
        usuarios.add(new Administrador(14, "Marcelo"));
        usuarios.add(new Usuario(15, "Roberto", departamentos.get("Vendas")));
        usuarios.add(new Usuario(16, "Ricardo", departamentos.get("TI")));
        usuarios.add(new Administrador(17, "Eduardo"));
        usuarios.add(new Usuario(18, "Bruno", departamentos.get("TI")));
        usuarios.add(new Usuario(19, "Afonso", departamentos.get("Marketing")));
        usuarios.add(new Administrador(20, "Pedro"));
        usuarios.add(new Usuario(21, "Abraao", departamentos.get("Marketing")));
        return usuarios;

    }

    public static Map<String, Departamento> inicializacaoDepartamentos() {
        Map<String, Departamento> departamentos = new HashMap<>();
        //inicializacao dos departamentos
        //nao da para usar 3 casas decimais no identificador por causa do problema "integer number too large"
        //nao vale a pena mudar para String pois dificulta busca e comaparacao

         departamentos.put("Financeiro", new Departamento(1, "Financeiro", DepartamentoEnum.FINANCEIRO));
         departamentos.put("RH", new Departamento(2, "Recursos Humanos", DepartamentoEnum.RH));
         departamentos.put("Engenharia", new Departamento(3, "Engenharia", DepartamentoEnum.ENGENHARIA));
         departamentos.put("Manutencao", new Departamento(4, "Manutenção", DepartamentoEnum.MANUTENCAO));
         departamentos.put("Vendas", new Departamento(5, "Vendas", DepartamentoEnum.VENDAS));
         departamentos.put("TI", new Departamento(6, "Tecnologia da Informação", DepartamentoEnum.TI));
         departamentos.put("Marketing", new Departamento(7, "Marketing", DepartamentoEnum.MAREKETING));

        return departamentos;

    }

    public static List<ItemDePedido> inicializacaoItensDePedido(){
        ArrayList<ItemDePedido> itens = new ArrayList<>();
        itens.add(new ItemDePedido("Negrinho", 2.50, 50));
        itens.add(new ItemDePedido("Branquinho", 2, 50));
        itens.add(new ItemDePedido("Enfeite de Bolo Revestido em Ouro", 7500, 1));
        itens.add(new ItemDePedido("Pastelzinho de Carne", 4, 50));
        itens.add(new ItemDePedido("Pastelzinho de Frango", 3, 50));

        return itens;
    }


}
