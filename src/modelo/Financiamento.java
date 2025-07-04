package modelo;

public abstract class Financiamento {
    private double valorImovel;
    private int prazo;
    private double taxaJuros;

    public Financiamento(double valorImovel, int prazo, double taxaJuros) {
        this.valorImovel = valorImovel;
        this.prazo = prazo;
        this.taxaJuros = taxaJuros;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazo() {
        return prazo;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public abstract double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException;

    @Override
    public String toString() {
        return String.format("Valor do imóvel: R$ %.2f, Prazo: %d anos, Taxa de juros: %.2f%%", valorImovel, prazo, taxaJuros);
    }
}
