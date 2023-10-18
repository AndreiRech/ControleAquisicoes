package src;

public class Departamento {
    private int identificador;
    private String nome;
    private DepartamentoEnum departamentoEnum;

    public Departamento(int identificador, String nome, DepartamentoEnum departamentoEnum){
        this.identificador = identificador;
        this.nome = nome;
        this.departamentoEnum = departamentoEnum;
    }

    public DepartamentoEnum getDepartamentoEnum() {
        return departamentoEnum;
    }

    @Override
    public String toString() {
        return identificador + " - " + nome;
    }
}
