package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicializadorDados {

    public static List<Funcionario> inicializacaoFuncionarios(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        Map<String, Departamento> departamentos = inicializacaoDepartamentos();


        //inicializacao dos funcionarios

        funcionarios.add(new Funcionario(1, "Rogerio", false, departamentos.get("Financeiro")));
        funcionarios.add(new Funcionario(2, "Claudia",true, departamentos.get("Financeiro")));
        funcionarios.add(new Funcionario(3, "Fernando",false, departamentos.get("Financeiro")));
        funcionarios.add(new Funcionario(4, "Felisberto",false, departamentos.get("RH")));
        funcionarios.add(new Funcionario(5, "Rosangela", true, departamentos.get("RH")));
        funcionarios.add(new Funcionario(6, "Maria Claudia",false, departamentos.get("RH")));
        funcionarios.add(new Funcionario(7, "Robson", false, departamentos.get("Engenharia")));
        funcionarios.add(new Funcionario(8, "Juliano",true, departamentos.get("Engenharia")));
        funcionarios.add(new Funcionario(9, "Rafela",false, departamentos.get("Engenharia")));
        funcionarios.add(new Funcionario(10, "Luiza",false, departamentos.get("Manutancao")));
        funcionarios.add(new Funcionario(11, "Sibely",true, departamentos.get("Manutancao")));
        funcionarios.add(new Funcionario(12, "Shirley",false, departamentos.get("Manutancao")));
        funcionarios.add(new Funcionario(13, "Sonia", false, departamentos.get("Vendas")));
        funcionarios.add(new Funcionario(14, "Marcelo",true, departamentos.get("Vendas")));
        funcionarios.add(new Funcionario(15, "Roberto",false, departamentos.get("Vendas")));
        funcionarios.add(new Funcionario(16, "Ricardo",false, departamentos.get("TI")));
        funcionarios.add(new Funcionario(17, "Eduardo",true, departamentos.get("TI")));
        funcionarios.add(new Funcionario(18, "Bruno", false, departamentos.get("TI")));
        funcionarios.add(new Funcionario(19, "Afonso",false, departamentos.get("Marketing")));
        funcionarios.add(new Funcionario(20, "Pedro",true, departamentos.get("Marketing")));
        funcionarios.add(new Funcionario(21, "Abraao",false, departamentos.get("Marketing")));

        return funcionarios;

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


}
