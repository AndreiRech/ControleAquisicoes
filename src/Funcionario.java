package src;

public class Funcionario extends Usuario{
    private Departamento departamento;

    public Funcionario(int identificador, String nome, boolean admin, Departamento departamento) {
        super(identificador, nome, admin);
        this.departamento = departamento;
    }

    public String toString(){
        return "Funcionario{" +
                ", departamento=" + departamento +
                ", identificador=" + getIdentificador() +
                ", nome=" + getNome() +
                ", admin=" + isAdmin();
    }
}
