package modelo;

public class Apartamento extends Financiamento {

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    @Override
    public double calcularPagamentoMensal() {
        // Passo 1: converter taxa anual (%) para mensal (decimal)
        double taxaMensal = (getTaxaJurosAnual() / 100) / 12;

        // Passo 2: total de meses
        int meses = getPrazoFinanciamento() * 12;

        // Passo 3: fórmula PRICE
        double base = 1 + taxaMensal;
        double potencia = Math.pow(base, meses);

        double parcela = (getValorImovel() * taxaMensal * potencia) / (potencia - 1);
        return parcela;
    }
}
