import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Financiamento;

public class SerializacaoUtil {
    public static void salvarFinanciamentos(String caminhoArquivo, ArrayList<Financiamento> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(lista);
            System.out.println("Financiamentos salvos com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar financiamentos: " + e.getMessage());
        }
    }
}
