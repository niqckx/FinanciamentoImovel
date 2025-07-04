package modelo;

public class Apartamento extends Financiamento {
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

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public void setVagasGaragem(int vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    @Override
    public double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        double jurosMensal = (getTaxaJuros() / 100) / 12;
        double valorBaseMensal = getValorImovel() / (getPrazo() * 12);
        double pagamentoMensal = valorBaseMensal + (valorBaseMensal * jurosMensal);
        return pagamentoMensal;
    }

    @Override
    public String toString() {
        return String.format("Apartamento - Andar: %d, Vagas na garagem: %d, %s",
                andar, vagasGaragem, super.toString());
    }
}
