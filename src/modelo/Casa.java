package modelo;

import util.AumentoMaiorDoQueJurosException;


public class Casa extends Financiamento {
    private static final long serialVersionUID = 1L;

    private boolean temQuintal;
    private double areaConstruida;
    private double areaTerreno;

    public Casa(double valorImovel, int prazo, double taxaJuros, boolean temQuintal, double areaConstruida, double areaTerreno) {
        super(valorImovel, prazo, taxaJuros);
        this.temQuintal = temQuintal;
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
    }

    public boolean isTemQuintal() {
        return temQuintal;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }

    public void setTemQuintal(boolean temQuintal) {
        this.temQuintal = temQuintal;
    }

    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public void setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    @Override
    public double calcularPagamentoMensal() throws util.AumentoMaiorDoQueJurosException {
        double jurosMensal = (getTaxaJuros() / 100) / 12 * getValorImovel();
        double valorBaseMensal = getValorImovel() / (getPrazo() * 12);
        double acrescimo = 80;

        if (acrescimo > jurosMensal / 2) {
            throw new util.AumentoMaiorDoQueJurosException(
                    String.format("Acréscimo de R$ %.2f é maior do que a metade dos juros mensais (R$ %.2f).", acrescimo, jurosMensal / 2)
            );
        }

        return valorBaseMensal + jurosMensal + acrescimo;
    }

    @Override
    public String toString() {
        return String.format("Casa - Quintal: %s, Área construída: %.2f m², Área do terreno: %.2f m², %s",
                temQuintal ? "Sim" : "Não", areaConstruida, areaTerreno, super.toString());
    }
}
