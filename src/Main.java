import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Usuario u = new Usuario(1, "Teste", true);


        System.out.println("Insira uma data: ");
        String data2 = scan.next();

        if(u.verificaData(data2)){
            System.out.println("Menor que 30 dias");
        }

    }
}