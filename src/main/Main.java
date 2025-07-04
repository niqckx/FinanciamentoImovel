package main;

import modelo.Apartamento;
import modelo.AumentoMaiorDoQueJurosException;
import modelo.Casa;
import modelo.Terreno;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha o tipo de financiamento:");
            System.out.println("1 - Casa");
            System.out.println("2 - Apartamento");
            System.out.println("3 - Terreno");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            if (opcao == 0) {
                System.out.println("Sair");
                break;
            }

            try {
                double valorImovel = lerDouble(sc, "Digite o valor do imóvel (ex: 280000): ");
                int prazo = lerInt(sc, "Digite o prazo do financiamento em anos: ");
                double taxaJuros = lerDouble(sc, "Digite a taxa de juros anual (ex: 7.5): ");

                switch (opcao) {
                    case 1:
                        boolean temQuintal = lerBoolean(sc, "A casa tem quintal? (s/n): ");
                        double areaConstruida = lerDouble(sc, "Área construída (m²): ");
                        double areaTerreno = lerDouble(sc, "Área do terreno (m²): ");

                        Casa casa = new Casa(valorImovel, prazo, taxaJuros, temQuintal, areaConstruida, areaTerreno);
                        exibirResumoFinanciamento("Casa", casa, prazo);
                        break;

                    case 2:
                        int andar = lerInt(sc, "Número do andar: ");
                        int vagasGaragem = lerInt(sc, "Número de vagas na garagem: ");

                        Apartamento apto = new Apartamento(valorImovel, prazo, taxaJuros, andar, vagasGaragem);
                        exibirResumoFinanciamento("Apartamento", apto, prazo);
                        break;

                    case 3:
                        double metragem = lerDouble(sc, "Metragem do terreno (m²): ");
                        System.out.print("Tipo da zona (residencial/comercial): ");
                        String tipoZona = sc.nextLine();

                        Terreno terreno = new Terreno(valorImovel, prazo, taxaJuros, metragem, tipoZona);
                        exibirResumoFinanciamento("Terreno", terreno, prazo);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Use números com ponto (ex: 7.5).");
            }
        }

        sc.close();
    }

    private static double lerDouble(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(sc.nextLine().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static int lerInt(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
            }
        }
    }

    private static boolean lerBoolean(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = sc.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) return true;
            if (resposta.equals("n")) return false;
            System.out.println("Digite apenas 's' ou 'n'.");
        }
    }

    private static void exibirResumoFinanciamento(String tipo, modelo.Financiamento f, int prazoAnos) {
        System.out.println("\n" + tipo + " cadastrado com sucesso!");
        System.out.println(f);
        try {
            double mensal = f.calcularPagamentoMensal();
            double total = mensal * prazoAnos * 12;
            System.out.printf("Valor do imóvel: R$ %.2f\n", f.getValorImovel());
            System.out.printf("Pagamento mensal: R$ %.2f\n", mensal);
            System.out.printf("Valor total do financiamento: R$ %.2f\n", total);
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println("Erro no cálculo do pagamento mensal: " + e.getMessage());
        }
    }
}
