package src.utils;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Utils {
    public static boolean verificaData(String dataPedido) {
        String[] pPedido = dataPedido.split("-");

        int anoPedido = Integer.parseInt(pPedido[2]);
        int mesPedido = Integer.parseInt(pPedido[1]);
        int diaPedido = Integer.parseInt(pPedido[0]);

        LocalDate dtDataPedido = LocalDate.of(anoPedido, mesPedido, diaPedido);
        LocalDate dtDataAtual = LocalDate.now();

        long dias = ChronoUnit.DAYS.between(dtDataPedido, dtDataAtual);


        return dias <= 30;
    }
}
