package src;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
public class Usuario {
    private int identificador;
    private String nome;
    private boolean admin;

    public Usuario(int identificador,String nome, boolean admin){
        this.identificador = identificador;
        this.nome = nome;
        this.admin = admin;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return identificador + " - " + nome;
    }
}
