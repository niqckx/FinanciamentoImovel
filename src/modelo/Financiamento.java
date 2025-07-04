package modelo;

public class Financiamento {
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double calcularPagamentoMensal() {
        double taxaJurosMensal = (taxaJurosAnual / 100) / 12; // dividindo por 100 para converter % para decimal
        return (valorImovel / (prazoFinanciamento * 12)) * (1 + taxaJurosMensal);
    }

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * prazoFinanciamento * 12;
    }


    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public void exibirResumoFinanciamento() {
        System.out.println("Resumo do Financiamento:");
        System.out.printf("Valor do imóvel: R$ %.2f%n", valorImovel);
        System.out.printf("Prazo: %d anos%n", prazoFinanciamento);
        System.out.printf("Taxa de juros anual: %.2f%%%n", taxaJurosAnual);
        System.out.printf("Pagamento mensal: R$ %.2f%n", calcularPagamentoMensal());
        System.out.printf("Pagamento total: R$ %.2f%n", calcularTotalPagamento());
    }
}
