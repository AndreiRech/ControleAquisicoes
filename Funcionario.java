public class Funcionario extends Usuario{
    private Departamento departamento;

    public Funcionario(int identificador, String nome, boolean admin, Departamento departamento) {
        super(identificador, nome, admin);
        this.departamento = departamento;
    }
}
