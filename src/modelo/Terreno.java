package modelo;

import util.AumentoMaiorDoQueJurosException;

public class Terreno extends Financiamento {
    private double metragem;
    private String tipoZona;

    public Terreno(double valorImovel, int prazo, double taxaJuros, double metragem, String tipoZona) {
        super(valorImovel, prazo, taxaJuros);
        this.metragem = metragem;
        this.tipoZona = tipoZona;
    }

    public double getMetragem() {
        return metragem;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException {

        double jurosMensal = (getTaxaJuros() / 100) / 12 * getValorImovel();


        double valorBaseMensal = getValorImovel() / (getPrazo() * 12);


        double acrescimo = 0;

        if (tipoZona.equalsIgnoreCase("comercial")) {
            acrescimo = 150;
        }


        if (acrescimo > jurosMensal / 2) {
            throw new AumentoMaiorDoQueJurosException(
                    String.format("Acréscimo de R$ %.2f é maior do que a metade dos juros mensais (R$ %.2f).", acrescimo, jurosMensal / 2)
            );
        }

        return valorBaseMensal + jurosMensal + acrescimo;
    }

    @Override
    public String toString() {
        return String.format("Terreno - Metragem: %.2f m², Tipo de zona: %s", metragem, tipoZona);
    }
}
