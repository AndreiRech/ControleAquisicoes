package src;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario{

    public Funcionario(int identificador, String nome, Departamento departamento) {
        super(identificador, nome, departamento);
    }

    public boolean isAdmin(){
        return false;
    }

    public String toString(){
        return "Funcionario: " + getNome() +
                "\nDepartamento=" + getDepartamento() +
                "\nIdentificador=" + getIdentificador() +
                "\nPermissão de administrador: " + isAdmin();
    }
}
