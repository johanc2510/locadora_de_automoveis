package persistencia;

import model.*;
import excecoes.*;
import java.io.*;
import java.util.*;

/**
 * Classe responsável pela persistência dos dados no sistema.
 */
public class Persistencia {

    private static final String ARQUIVO_CLIENTES = "clientes.dat";
    private static final String ARQUIVO_AUTOMOVEIS = "automoveis.dat";
    private static final String ARQUIVO_LOCACOES = "locacoes.dat";

    private List<Automovel> automoveis;
    private List<Cliente> clientes;
    private List<Locacao> locacoes;

    public Persistencia() {
        this.automoveis = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.locacoes = new ArrayList<>();
    }

    /**
     * Carrega os dados salvos em arquivos.
     */
    public void carregarDados() {
        this.clientes = carregarArquivo(ARQUIVO_CLIENTES);
        this.automoveis = carregarArquivo(ARQUIVO_AUTOMOVEIS);
        this.locacoes = carregarArquivo(ARQUIVO_LOCACOES);
    }

    /**
     * Salva os dados em arquivos.
     */
    public void salvarDados() {
        salvarArquivo(ARQUIVO_CLIENTES, clientes);
        salvarArquivo(ARQUIVO_AUTOMOVEIS, automoveis);
        salvarArquivo(ARQUIVO_LOCACOES, locacoes);
    }

    private <T> List<T> carregarArquivo(String caminho) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private <T> void salvarArquivo(String caminho, List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void cadastrarCliente(Cliente cliente) throws CpfDuplicadoException {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cliente.getCpf())) {
                throw new CpfDuplicadoException("Já existe um cliente com o CPF " + cliente.getCpf());
            }
        }
        clientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void cadastrarAutomovel(Automovel automovel) throws PlacaDuplicadaException {
        for (Automovel a : automoveis) {
            if (a.getPlaca().equals(automovel.getPlaca())) {
                throw new PlacaDuplicadaException("Já existe um automóvel com a placa " + automovel.getPlaca());
            }
        }
        automoveis.add(automovel);
    }

    public List<Automovel> listarAutomoveis() {
        return new ArrayList<>(automoveis);
    }

    public Automovel buscarAutomovel(String placa) {
        for (Automovel automovel : automoveis) {
            if (automovel.getPlaca().equals(placa)) {
                return automovel;
            }
        }
        return null;
    }

    public List<Automovel> listarAutomoveisDisponiveis() {
        List<Automovel> disponiveis = new ArrayList<>();
        for (Automovel automovel : automoveis) {
            if (!automovel.isAlugado()) {
                disponiveis.add(automovel);
            }
        }
        return disponiveis;
    }

    public void alugarAutomovel(Automovel automovel, Cliente cliente, int dias) throws AutomovelIndisponivelException {
        if (automovel.isAlugado()) {
            throw new AutomovelIndisponivelException("Automóvel já está alugado.");
        }
        Locacao locacao = new Locacao(cliente, automovel, dias);
        locacoes.add(locacao);
        automovel.setAlugado(true);
    }

    public double devolverAutomovel(Automovel automovel, int diasRealizados) {
        Locacao locacaoEncontrada = null;
        for (Locacao locacao : locacoes) {
            if (locacao.getAutomovel().equals(automovel)) {
                locacaoEncontrada = locacao;
                break;
            }
        }

        if (locacaoEncontrada == null) {
            throw new IllegalArgumentException("Locação não encontrada.");
        }

        locacoes.remove(locacaoEncontrada);
        automovel.setAlugado(false);

        int diasContratados = locacaoEncontrada.getDias();
        double valorBase = locacaoEncontrada.getAutomovel().calcularDiaria(diasContratados);

        if (diasRealizados > diasContratados) {
            double multa = (diasRealizados - diasContratados) * 0.10 * valorBase;
            return valorBase + multa;
        }

        return valorBase;
    }
}
