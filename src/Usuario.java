package src;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int identificador;
    private String nome;
    private boolean admin;
    private List<Pedido> pedidos;

    public Usuario(int identificador,String nome, boolean admin){
        this.identificador = identificador;
        this.nome = nome;
        this.admin = admin;
        this.pedidos = new ArrayList<>();
    }

    public Pedido registraPedido(Funcionario funcionario, Departamento departamento, String dataPedido, String dataConclusao, boolean statusAprovacao, List<ItemDePedido> itens, double valorTotal, String descricao, StatusPedido statusPedido) {
        Pedido novoPedido = new Pedido(funcionario, descricao, valorTotal, statusAprovacao, statusPedido, dataConclusao, dataPedido, departamento);
        pedidos.add(novoPedido);
        System.out.println("Pedido registrado com sucesso. \nFuncionario responsável: " + funcionario + "\nDepartamento responsável: " + departamento + "\nData: " + dataPedido + "\nConclusão em: " + dataConclusao + "\nStatus: " + statusPedido + "\nItens: " + itens + "\nTotal: R$" + valorTotal);
        return novoPedido;
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

    public void contPedidos(ArrayList <Pedido> pedidos) {
        int qntTotal=0, qntAprovados=0, qntReprovados=0;

        for (Pedido p : pedidos) {
            switch (p.getStatus()) {
                case APROVADO -> qntAprovados++;
                case REPROVADO -> qntReprovados++;
                default -> {
                }
            }
        }

        System.out.printf("Quantidade de pedidos : [%d]\n", qntTotal);
        System.out.printf("Quantidade de pedidos aprovados : [%d] | [%.2f%%]\n", qntAprovados, ((double) qntAprovados/qntTotal)*100);
        System.out.printf("Quantidade de pedidos reprovados : [%d] | [%.2f%%]", qntReprovados, ((double) qntAprovados/qntReprovados)*100);
    }


    public double calculaMedia30(ArrayList <Pedido> pedidos, String dataAtual) {
        double valorTotal = 0, media = 0;
        int quantidade = 0;

        for (Pedido p : pedidos) {
            if(verificaData(p.getDataPedido())) {
                quantidade++;
                valorTotal += p.getValorTotal();
            }
        }

        if (quantidade > 0) {
            media = valorTotal / quantidade;
        }

        return media;
    }

    public void totalDepartamento30(ArrayList<Pedido> pedidos, ArrayList<Departamento> departamentos) {
        for (Departamento d : departamentos) {
            double valorTotalDepartamento = 0;
            for (Pedido p : pedidos) {
                if (verificaData(p.getDataPedido()) && p.getDepartamento().getIdentificador() == d.getIdentificador()) {
                    valorTotalDepartamento += p.getValorTotal();
                }
            }
            System.out.printf("Valor total do departamento %d nos últimos 30 dias: %.2f\n", d.getIdentificador(), valorTotalDepartamento);
        }
    }

    public Pedido maiorPedidoAberto(ArrayList <Pedido> pedidos) {
        Pedido maiorP = null;
        double maiorV = 0;

        for (Pedido p : pedidos) {
            if (p.isStatusAprovacao()) {
                if (maiorP == null || p.getValorTotal() > maiorV) {
                    maiorP = p;
                    maiorV = p.getValorTotal();
                }
            }
        }

        return maiorP;
    }

    public boolean verificaData(String dataPedido) {
        String[] pPedido = dataPedido.split("-");

        int anoPedido = Integer.parseInt(pPedido[2]);
        int mesPedido = Integer.parseInt(pPedido[1]);
        int diaPedido = Integer.parseInt(pPedido[0]);

        LocalDate dtDataPedido = LocalDate.of(anoPedido, mesPedido, diaPedido);
        LocalDate dtDataAtual = LocalDate.now();

        long dias = ChronoUnit.DAYS.between(dtDataPedido, dtDataAtual);


        return dias <= 30;
    }

    @Override
    public String toString() {
        return identificador + " - " + nome;
    }
}
