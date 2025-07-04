package util;

import java.util.Locale;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner sc;

    public InterfaceUsuario() {
        sc = new Scanner(System.in).useLocale(Locale.US);
    }

    public int pedirOpcao() {
        int opcao = -1;
        do {
            System.out.print("Digite a opção desejada: ");
            String linha = sc.nextLine();
            try {
                opcao = Integer.parseInt(linha.trim());
                if (opcao < 0 || opcao > 3) {
                    System.out.println("Opção inválida, tente de novo.");
                    opcao = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        } while (opcao == -1);
        return opcao;
    }

    public double pedirValorImovel() {
        double valor = -1;
        do {
            System.out.print("Digite o valor do imóvel (ex: 280000): ");
            String entrada = sc.nextLine().replace(".", "").replace(",", ".");
            try {
                valor = Double.parseDouble(entrada);
                if (valor <= 0) {
                    System.out.println("Valor precisa ser maior que zero. Tente de novo.");
                    valor = -1;
                }
            } catch (Exception e) {
                System.out.println("Número inválido, tente novamente.");
            }
        } while (valor <= 0);
        return valor;
    }

    public int pedirPrazoFinanciamento() {
        int prazo = -1;
        do {
            System.out.print("Digite o prazo do financiamento em anos: ");
            String linha = sc.nextLine();
            try {
                prazo = Integer.parseInt(linha.trim());
                if (prazo <= 0) {
                    System.out.println("Prazo tem que ser positivo. Tente de novo.");
                    prazo = -1;
                }
            } catch (Exception e) {
                System.out.println("Digite um número inteiro válido.");
            }
        } while (prazo <= 0);
        return prazo;
    }

    public double pedirTaxaJurosAnual() {
        double taxa = -1;
        do {
            System.out.print("Digite a taxa de juros anual (ex: 7.5): ");
            String linha = sc.nextLine().replace(",", ".");
            try {
                taxa = Double.parseDouble(linha.trim());
                if (taxa <= 0) {
                    System.out.println("Taxa tem que ser positiva. Tente de novo.");
                    taxa = -1;
                }
            } catch (Exception e) {
                System.out.println("Número inválido, tente de novo.");
            }
        } while (taxa <= 0);
        return taxa;
    }

    public double pedirAreaConstruida() {
        double area = -1;
        do {
            System.out.print("Área construída (m²): ");
            String linha = sc.nextLine().replace(",", ".");
            try {
                area = Double.parseDouble(linha.trim());
                if (area <= 0) {
                    System.out.println("Área tem que ser positiva. Tente de novo.");
                    area = -1;
                }
            } catch (Exception e) {
                System.out.println("Número inválido, tente de novo.");
            }
        } while (area <= 0);
        return area;
    }

    public double pedirAreaTerreno() {
        double area = -1;
        do {
            System.out.print("Área do terreno (m²): ");
            String linha = sc.nextLine().replace(",", ".");
            try {
                area = Double.parseDouble(linha.trim());
                if (area <= 0) {
                    System.out.println("Área tem que ser positiva. Tente de novo.");
                    area = -1;
                }
            } catch (Exception e) {
                System.out.println("Número inválido, tente de novo.");
            }
        } while (area <= 0);
        return area;
    }

    public boolean pedirPossuiQuintal() {
        String resposta;
        do {
            System.out.print("A casa tem quintal? (s/n): ");
            resposta = sc.nextLine().trim().toLowerCase();
            if (!resposta.equals("s") && !resposta.equals("n")) {
                System.out.println("Responda só com s ou n.");
            }
        } while (!resposta.equals("s") && !resposta.equals("n"));
        return resposta.equals("s");
    }

    public int pedirNumeroVagasGaragem() {
        int vagas = -1;
        do {
            System.out.print("Número de vagas na garagem: ");
            String linha = sc.nextLine();
            try {
                vagas = Integer.parseInt(linha.trim());
                if (vagas < 0) {
                    System.out.println("Número não pode ser negativo. Tente de novo.");
                    vagas = -1;
                }
            } catch (Exception e) {
                System.out.println("Número inválido. Tente de novo.");
            }
        } while (vagas < 0);
        return vagas;
    }

    public int pedirNumeroAndar() {
        int andar = Integer.MIN_VALUE;
        do {
            System.out.print("Número do andar: ");
            String linha = sc.nextLine();
            try {
                andar = Integer.parseInt(linha.trim());
            } catch (Exception e) {
                System.out.println("Número inválido. Tente de novo.");
                andar = Integer.MIN_VALUE;
            }
        } while (andar == Integer.MIN_VALUE);
        return andar;
    }

    public double pedirMetragem() {
        double metragem = -1;
        do {
            System.out.print("Metragem do terreno (m²): ");
            String linha = sc.nextLine().replace(",", ".");
            try {
                metragem = Double.parseDouble(linha.trim());
                if (metragem <= 0) {
                    System.out.println("Metragem tem que ser positiva. Tente de novo.");
                    metragem = -1;
                }
            } catch (Exception e) {
                System.out.println("Número inválido. Tente de novo.");
            }
        } while (metragem <= 0);
        return metragem;
    }

    public String pedirTipoZona() {
        String zona;
        do {
            System.out.print("Tipo da zona (residencial/comercial): ");
            zona = sc.nextLine().trim().toLowerCase();
            if (!zona.equals("residencial") && !zona.equals("comercial")) {
                System.out.println("Digite só 'residencial' ou 'comercial'.");
            }
        } while (!zona.equals("residencial") && !zona.equals("comercial"));
        return zona;
    }
}
