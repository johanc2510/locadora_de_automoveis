package persistencia;

import java.io.*;
import java.util.List;

/**
 * Classe utilitária para salvar e carregar objetos em arquivos usando serialização.
 */
public class Persistencia {

    /**
     * Salva uma lista de objetos em um arquivo.
     *
     * @param lista   A lista de objetos a ser salva.
     * @param caminho O caminho do arquivo onde os dados serão armazenados.
     * @param <T>     O tipo de objetos na lista (deve ser Serializable).
     * @throws IOException Se ocorrer um erro ao salvar os dados.
     */
    public static <T extends Serializable> void salvarDados(List<T> lista, String caminho) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
        }
    }

    /**
     * Carrega uma lista de objetos de um arquivo.
     *
     * @param caminho O caminho do arquivo de onde os dados serão carregados.
     * @param <T>     O tipo de objetos na lista (deve ser Serializable).
     * @return Uma lista de objetos carregados.
     * @throws IOException            Se ocorrer um erro ao ler os dados.
     * @throws ClassNotFoundException Se as classes dos objetos não forem encontradas.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> carregarDados(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new java.util.ArrayList<>();
        }
    }
}
