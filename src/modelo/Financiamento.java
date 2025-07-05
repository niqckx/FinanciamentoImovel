package modelo;

import util.AumentoMaiorDoQueJurosException;

import java.io.Serializable;

public abstract class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private double valorImovel;
    private int prazo; // em anos
    private double taxaJuros; // anual em %

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

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public abstract double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException;

    @Override
    public String toString() {
        return String.format("Valor do imóvel: R$ %.2f, Prazo: %d anos, Taxa de juros anual: %.2f%%",
                valorImovel, prazo, taxaJuros);
    }
}
