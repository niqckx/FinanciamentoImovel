package modelo;

public class Terreno extends Financiamento {

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    @Override
    public double calcularPagamentoMensal() {
        double parcelaBase = super.calcularPagamentoMensal();

        double parcelaComAcrecimo = parcelaBase * 0.02;

        return parcelaComAcrecimo;
    }
}
