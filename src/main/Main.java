package main;

import modelo.*;
import util.InterfaceUsuario;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        // 1º financiamento: entrada do usuário
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazo = interfaceUsuario.pedirPrazoFinanciamento();
        double taxa = interfaceUsuario.pedirTaxaJurosAnual();
        financiamentos.add(new Financiamento(valorImovel, prazo, taxa));

        // 2 financiamentos de Casa (valores fixos)
        financiamentos.add(new Casa(280000, 20, 8.0));
        financiamentos.add(new Casa(220000, 15, 7.5));

        // 2 financiamentos de Apartamento (valores fixos)
        financiamentos.add(new Apartamento(300000, 25, 9.0));
        financiamentos.add(new Apartamento(180000, 10, 6.5));

        // 1 financiamento de Terreno (valor fixo)
        financiamentos.add(new Terreno(150000, 12, 8.5));

        double totalImoveis = 0;
        double totalFinanciamentos = 0;

        for (int i = 0; i < financiamentos.size(); i++) {
            Financiamento f = financiamentos.get(i);
            double valorImovelFin = f.getValorImovel();
            double valorFinanciamento = f.calcularTotalPagamento();
            String tipo = f.getClass().getSimpleName();

            System.out.printf("%s %d – valor do imóvel: R$ %.2f, valor do financiamento: R$ %.2f.%n",
                    tipo, i + 1, valorImovelFin, valorFinanciamento);

            totalImoveis += valorImovelFin;
            totalFinanciamentos += valorFinanciamento;
        }

        System.out.printf("Total de todos os imóveis: R$ %.2f, total de todos os financiamentos: R$ %.2f.%n",
                totalImoveis, totalFinanciamentos);
    }
}
