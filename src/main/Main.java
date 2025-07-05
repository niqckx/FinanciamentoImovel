package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import util.AumentoMaiorDoQueJurosException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String ARQUIVO_SERIAL = "financiamentos.ser";

    public static void main(String[] args) {
        //limpeza do arquivo no início... assim, não puxa todos os históricos
        try {
            new FileOutputStream(ARQUIVO_SERIAL).close();
            System.out.println("Arquivo de financiamentos limpo no início da execução.");
        } catch (IOException e) {
            System.out.println("Erro ao limpar o arquivo: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        ArrayList<Financiamento> financiamentos = carregarFinanciamentos();

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
                System.out.println("Saindo e salvando financiamentos...");
                salvarFinanciamentos(financiamentos);
                System.out.println("Financiamentos salvos com sucesso em " + ARQUIVO_SERIAL);


                System.out.println("\nFinanciamentos cadastrados nesta sessão:");
                for (Financiamento f : financiamentos) {
                    System.out.println(f);
                }
                break;
            }

            try {
                double valorImovel = lerDouble(sc, "Digite o valor do imóvel (ex: 280000): ");
                int prazo = lerInt(sc, "Digite o prazo do financiamento em anos: ");
                double taxaJuros = lerDouble(sc, "Digite a taxa de juros anual (ex: 7.5): ");

                Financiamento f = null;

                switch (opcao) {
                    case 1:
                        boolean temQuintal = lerBoolean(sc, "A casa tem quintal? (s/n): ");
                        double areaConstruida = lerDouble(sc, "Área construída (m²): ");
                        double areaTerreno = lerDouble(sc, "Área do terreno (m²): ");
                        f = new Casa(valorImovel, prazo, taxaJuros, temQuintal, areaConstruida, areaTerreno);
                        break;

                    case 2:
                        int andar = lerInt(sc, "Número do andar: ");
                        int vagasGaragem = lerInt(sc, "Número de vagas na garagem: ");
                        f = new Apartamento(valorImovel, prazo, taxaJuros, andar, vagasGaragem);
                        break;

                    case 3:
                        double metragem = lerDouble(sc, "Metragem do terreno (m²): ");
                        String tipoZona;
                        do {
                            System.out.print("Tipo da zona (residencial/comercial): ");
                            tipoZona = sc.nextLine().trim().toLowerCase();
                            if (!tipoZona.equals("residencial") && !tipoZona.equals("comercial")) {
                                System.out.println("Entrada inválida. Digite 'residencial' ou 'comercial'.");
                            }
                        } while (!tipoZona.equals("residencial") && !tipoZona.equals("comercial"));

                        f = new Terreno(valorImovel, prazo, taxaJuros, metragem, tipoZona);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        continue;
                }


                financiamentos.add(f);


                exibirResumoFinanciamento(
                        opcao == 1 ? "Casa" : opcao == 2 ? "Apartamento" : "Terreno",
                        f,
                        prazo
                );

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

    private static void exibirResumoFinanciamento(String tipo, Financiamento f, int prazoAnos) {
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

    private static void salvarFinanciamentos(ArrayList<Financiamento> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIAL))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar financiamentos: " + e.getMessage());
        }
    }

    private static ArrayList<Financiamento> carregarFinanciamentos() {
        ArrayList<Financiamento> lista = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SERIAL))) {
            lista = (ArrayList<Financiamento>) ois.readObject();
            System.out.println("Financiamentos carregados do arquivo.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de financiamentos não encontrado. Iniciando lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar financiamentos: " + e.getMessage());
        }
        return lista;
    }
}
