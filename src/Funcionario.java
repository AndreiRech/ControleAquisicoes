package src;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario{

    public Funcionario(Usuario usuario) {
        super(usuario.getIdentificador(), usuario.getNome(), usuario.getDepartamento());
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
