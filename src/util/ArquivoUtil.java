package util;

import modelo.*;
import modelo.AumentoMaiorDoQueJurosException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static void salvarEmArquivoTexto(List<Financiamento> lista, String nomeArquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Financiamento f : lista) {
                StringBuilder linha = new StringBuilder();
                linha.append(f.getValorImovel()).append(";");
                try {
                    linha.append(f.calcularPagamentoMensal());
                } catch (AumentoMaiorDoQueJurosException e) {
                    linha.append("Erro no cálculo");
                }
                linha.append(";");
                linha.append(f.getTaxaJuros()).append(";");
                linha.append(f.getPrazo()).append(";");

                if (f instanceof Casa) {
                    Casa casa = (Casa) f;
                    linha.append("Casa;")
                            .append(casa.isTemQuintal()).append(";")
                            .append(casa.getAreaConstruida()).append(";")
                            .append(casa.getAreaTerreno());
                } else if (f instanceof Apartamento) {
                    Apartamento apto = (Apartamento) f;
                    linha.append("Apartamento;")
                            .append(apto.getAndar()).append(";")
                            .append(apto.getVagasGaragem());
                } else if (f instanceof Terreno) {
                    Terreno terreno = (Terreno) f;
                    linha.append("Terreno;")
                            .append(terreno.getMetragem()).append(";")
                            .append(terreno.getTipoZona());
                }

                bw.write(linha.toString());
                bw.newLine();
            }
        }
    }

    public static List<Financiamento> lerDeArquivoTexto(String nomeArquivo) throws IOException {
        List<Financiamento> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 5) continue; // validação simples

                double valorImovel = Double.parseDouble(partes[0]);
                // Ignorar valor do financiamento calculado salvo (partes[1])
                double taxaJuros = Double.parseDouble(partes[2]);
                int prazo = Integer.parseInt(partes[3]);
                String tipo = partes[4];

                switch (tipo) {
                    case "Casa":
                        boolean temQuintal = Boolean.parseBoolean(partes[5]);
                        double areaConstruida = Double.parseDouble(partes[6]);
                        double areaTerreno = Double.parseDouble(partes[7]);
                        Casa casa = new Casa(valorImovel, prazo, taxaJuros, temQuintal, areaConstruida, areaTerreno);
                        lista.add(casa);
                        break;

                    case "Apartamento":
                        int andar = Integer.parseInt(partes[5]);
                        int vagasGaragem = Integer.parseInt(partes[6]);
                        Apartamento apto = new Apartamento(valorImovel, prazo, taxaJuros, andar, vagasGaragem);
                        lista.add(apto);
                        break;

                    case "Terreno":
                        double metragem = Double.parseDouble(partes[5]);
                        String tipoZona = partes[6];
                        Terreno terreno = new Terreno(valorImovel, prazo, taxaJuros, metragem, tipoZona);
                        lista.add(terreno);
                        break;
                }
            }
        }
        return lista;
    }
}
