package view;

import java.util.*;
import model.*;
import persistencia.Persistencia;
import excecoes.*;

/**
 * Classe que representa a interface de usuário para o sistema de locação de automóveis.
 */
public class InterfaceUsuario {

    private Scanner scanner;
    private Persistencia persistencia;

    /**
     * Construtor da classe InterfaceUsuario.
     */
    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
        this.persistencia = new Persistencia();
        persistencia.carregarDados();
    }

    /**
     * Método principal para exibir o menu e executar as operações.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Locadora de Automóveis ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Automóvel");
            System.out.println("3. Apresentar Clientes");
            System.out.println("4. Apresentar Automóveis");
            System.out.println("5. Alugar Automóvel");
            System.out.println("6. Devolver Automóvel");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarAutomovel();
                    break;
                case 3:
                    apresentarClientes();
                    break;
                case 4:
                    apresentarAutomoveis();
                    break;
                case 5:
                    alugarAutomovel();
                    break;
                case 6:
                    devolverAutomovel();
                    break;
                case 7:
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }

    private void cadastrarCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            persistencia.cadastrarCliente(new Cliente(nome, cpf));
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (CpfDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarAutomovel() {
        System.out.print("Placa do automóvel: ");
        String placa = scanner.nextLine();
        System.out.print("Tipo (1: Popular, 2: Médio, 3: Grande): ");
        int tipo = scanner.nextInt();
        System.out.print("Ano do modelo: ");
        int ano = scanner.nextInt();
        System.out.print("Valor base da diária: ");
        double valorBase = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer

        Automovel automovel = null;
        switch (tipo) {
            case 1:
                automovel = new AutomovelPopular(placa, ano, valorBase);
                break;
            case 2:
                automovel = new AutomovelMedio(placa, ano, valorBase);
                break;
            case 3:
                automovel = new AutomovelGrande(placa, ano, valorBase);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        try {
            persistencia.cadastrarAutomovel(automovel);
            System.out.println("Automóvel cadastrado com sucesso!");
        } catch (PlacaDuplicadaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void apresentarClientes() {
        List<Cliente> clientes = persistencia.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\n--- Clientes Cadastrados ---");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void apresentarAutomoveis() {
        List<Automovel> automoveis = persistencia.listarAutomoveis();
        if (automoveis.isEmpty()) {
            System.out.println("Nenhum automóvel cadastrado.");
        } else {
            System.out.println("\n--- Automóveis Cadastrados ---");
            for (Automovel automovel : automoveis) {
                System.out.println(automovel);
            }
        }
    }

    private void alugarAutomovel() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = persistencia.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        List<Automovel> disponiveis = persistencia.listarAutomoveisDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum automóvel disponível.");
            return;
        }

        System.out.println("\n--- Automóveis Disponíveis ---");
        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + disponiveis.get(i));
        }

        System.out.print("Escolha o automóvel (número): ");
        int escolha = scanner.nextInt() - 1;
        System.out.print("Número de dias de locação: ");
        int dias = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (escolha < 0 || escolha >= disponiveis.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        try {
            persistencia.alugarAutomovel(disponiveis.get(escolha), cliente, dias);
            System.out.println("Automóvel alugado com sucesso!");
        } catch (AutomovelIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void devolverAutomovel() {
        System.out.print("Placa do automóvel: ");
        String placa = scanner.nextLine();
        Automovel automovel = persistencia.buscarAutomovel(placa);
        if (automovel == null || !automovel.isAlugado()) {
            System.out.println("Automóvel não encontrado ou não está alugado.");
            return;
        }

        System.out.print("Número de dias desde a locação: ");
        int dias = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        double valor = persistencia.devolverAutomovel(automovel, dias);
        System.out.printf("Devolução realizada com sucesso! Valor a pagar: R$ %.2f%n", valor);
    }

    private void sair() {
        persistencia.salvarDados();
        System.out.println("Dados salvos. Saindo do sistema...");
    }
    
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        interfaceUsuario.exibirMenu();
    }
}
