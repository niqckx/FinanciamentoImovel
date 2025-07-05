package modelo;

public class Terreno extends Financiamento {
    private static final long serialVersionUID = 1L;

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

    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() throws modelo.AumentoMaiorDoQueJurosException {
        double jurosMensal = (getTaxaJuros() / 100) / 12 * getValorImovel();
        double valorBaseMensal = getValorImovel() / (getPrazo() * 12);
        double acrescimo = 0;

        if (tipoZona.equalsIgnoreCase("comercial")) {
            acrescimo = 150;
        }

        if (acrescimo > jurosMensal / 2) {
            throw new modelo.AumentoMaiorDoQueJurosException(
                    String.format("Acréscimo de R$ %.2f é maior do que a metade dos juros mensais (R$ %.2f).", acrescimo, jurosMensal / 2)
            );
        }

        return valorBaseMensal + jurosMensal + acrescimo;
    }

    @Override
    public String toString() {
        return String.format("Terreno - Metragem: %.2f m², Tipo de zona: %s, %s",
                metragem, tipoZona, super.toString());
    }
}
