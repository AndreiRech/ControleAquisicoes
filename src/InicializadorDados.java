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
        usuarios.add(new Usuario(1, "Rogerio", false, departamentos.get("Financeiro")));
        usuarios.add(new Usuario(2, "Claudia", true, null));
        usuarios.add(new Usuario(3, "Fernando", false, departamentos.get("Financeiro")));
        usuarios.add(new Usuario(4, "Felisberto", false, departamentos.get("RH")));
        usuarios.add(new Usuario(5, "Rosangela", true, null));
        usuarios.add(new Usuario(6, "Maria Claudia", false, departamentos.get("RH")));
        usuarios.add(new Usuario(7, "Robson", false, departamentos.get("Engenharia")));
        usuarios.add(new Usuario(8, "Juliano", true, null));
        usuarios.add(new Usuario(9, "Rafela", false, departamentos.get("Engenharia")));
        usuarios.add(new Usuario(10, "Luiza", false, departamentos.get("Manutencao")));
        usuarios.add(new Usuario(11, "Sibely", true, null));
        usuarios.add(new Usuario(12, "Shirley", false, departamentos.get("Manutencao")));
        usuarios.add(new Usuario(13, "Sonia",  false, departamentos.get("Vendas")));
        usuarios.add(new Usuario(14, "Marcelo", true, null));
        usuarios.add(new Usuario(15, "Roberto", false, departamentos.get("Vendas")));
        usuarios.add(new Usuario(16, "Ricardo", false, departamentos.get("TI")));
        usuarios.add(new Usuario(17, "Eduardo", true, null));
        usuarios.add(new Usuario(18, "Bruno", false, departamentos.get("TI")));
        usuarios.add(new Usuario(19, "Afonso", false, departamentos.get("Marketing")));
        usuarios.add(new Usuario(20, "Pedro", true, null));
        usuarios.add(new Usuario(21, "Abraao", false, departamentos.get("Marketing")));
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

    public static List<ItemDePedido> inicializaçãoItensDePedido(){
        ArrayList<ItemDePedido> itens = new ArrayList<>();
        itens.add(new ItemDePedido("Negrinho", 2.50, 50));
        itens.add(new ItemDePedido("Branquinho", 2, 50));
        itens.add(new ItemDePedido("Bolo", 100, 1));
        itens.add(new ItemDePedido("Pastelzinho de Carne", 4, 50));
        itens.add(new ItemDePedido("Pastelzinho de Frango", 3, 50));

        return itens;
    }


}
