package modelo;

import util.AumentoMaiorDoQueJurosException;
public class Apartamento extends Financiamento {
    private static final long serialVersionUID = 1L;

    private int andar;
    private int vagasGaragem;

    public Apartamento(double valorImovel, int prazo, double taxaJuros, int andar, int vagasGaragem) {
        super(valorImovel, prazo, taxaJuros);
        this.andar = andar;
        this.vagasGaragem = vagasGaragem;
    }

    public int getAndar() {
        return andar;
    }

    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public void setVagasGaragem(int vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    @Override
    public double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        double jurosMensal = (getTaxaJuros() / 100) / 12;
        double valorBaseMensal = getValorImovel() / (getPrazo() * 12);
        return valorBaseMensal + (valorBaseMensal * jurosMensal);
    }

    @Override
    public String toString() {
        return String.format("Apartamento - Andar: %d, Vagas na garagem: %d, %s",
                andar, vagasGaragem, super.toString());
    }
}
