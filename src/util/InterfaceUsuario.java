package util;

import java.util.Locale;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner sc;

    public InterfaceUsuario() {
        sc = new Scanner(System.in).useLocale(Locale.US);
    }

    public double pedirValorImovel() {
        double valor = -1;
        do {
            System.out.print("Digite o valor do imóvel (ex: 280.000,00 ou 280.000): ");
            String entrada = sc.next();

            // Remove pontos usados como separador de milhar e troca vírgula por ponto decimal
            entrada = entrada.replace(".", "").replace(",", ".");

            if (isNumeroValido(entrada)) {
                valor = Double.parseDouble(entrada);
                if (valor <= 0) {
                    System.out.println("O valor deve ser positivo. Tente novamente.");
                    valor = -1;
                }
            } else {
                System.out.println("Valor inválido. Digite um número válido.");
            }
        } while (valor <= 0);

        return valor;
    }

    // Método auxiliar para validar se a string representa um número double válido
    private boolean isNumeroValido(String str) {
        // Deve conter só dígitos e no máximo um ponto
        int countPontos = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.') {
                countPontos++;
                if (countPontos > 1) return false;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return str.length() > 0;
    }


    public int pedirPrazoFinanciamento() {
        int prazo;
        do {
            System.out.print("Digite o prazo do financiamento em anos: ");
            while (!sc.hasNextInt()) {
                System.out.print("Valor inválido. Digite um número inteiro válido: ");
                sc.next();
            }
            prazo = sc.nextInt();
            if (prazo <= 0) {
                System.out.println("O prazo deve ser um número inteiro positivo. Tente novamente.");
            }
        } while (prazo <= 0);
        return prazo;
    }

    public double pedirTaxaJurosAnual() {
        double taxa;
        do {
            System.out.print("Digite a taxa de juros anual (ex: 7.5): ");
            while (!sc.hasNextDouble()) {
                System.out.print("Valor inválido. Digite um número válido: ");
                sc.next();
            }
            taxa = sc.nextDouble();
            if (taxa <= 0) {
                System.out.println("A taxa de juros deve ser positiva. Tente novamente.");
            }
        } while (taxa <= 0);
        return taxa;
    }
}
