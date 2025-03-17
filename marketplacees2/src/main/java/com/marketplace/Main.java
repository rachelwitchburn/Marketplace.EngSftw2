package com.marketplace;

import java.util.Scanner;
import com.marketplace.model.Buyer;
import com.marketplace.model.Store;
import com.marketplace.service.BuyerService;
import com.marketplace.service.StoreService;
import com.marketplace.repository.BuyerRepository;
import com.marketplace.repository.StoreRepository;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialização dos repositórios e serviços
        BuyerRepository buyerRepository = new BuyerRepository();
        BuyerService buyerService = new BuyerService(buyerRepository);
        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);

        while (true) {
            System.out.println("\n------ MENU PRINCIPAL ------");
            System.out.println("1. Gerenciar Compradores");
            System.out.println("2. Gerenciar Lojas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (choice) {
                case 1:
                    manageBuyers(scanner, buyerService);
                    break;
                case 2:
                    manageStores(scanner, storeService);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu de gerenciamento de compradores
    private static void manageBuyers(Scanner scanner, BuyerService buyerService) {
        while (true) {
            System.out.println("\n------ MENU DE COMPRADORES ------");
            System.out.println("1. Adicionar Comprador");
            System.out.println("2. Listar Compradores");
            System.out.println("3. Atualizar Comprador");
            System.out.println("4. Remover Comprador");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (choice) {
                case 1:
                    // Adicionar comprador
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();

                    Buyer newBuyer = new Buyer(name, email, password, cpf, address);
                    buyerService.addBuyer(newBuyer);
                    System.out.println("Comprador adicionado com sucesso!");
                    break;

                case 2:
                    // Listar compradores
                    System.out.println("\nLista de Compradores:");
                    for (Buyer buyer : buyerService.listBuyers()) {
                        System.out.println(buyer);
                    }
                    break;

                case 3:
                    // Atualizar comprador
                    System.out.print("Digite o ID do comprador a ser atualizado: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Novo Nome: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nova Senha: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Novo CPF: ");
                    String newCpf = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String newAddress = scanner.nextLine();

                    Buyer updatedBuyer = new Buyer(newName, newEmail, newPassword, newCpf, newAddress);
                    updatedBuyer.setId(idToUpdate);
                    if (buyerService.updateBuyer(updatedBuyer)) {
                        System.out.println("Comprador atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar comprador.");
                    }
                    break;

                case 4:
                    // Remover comprador
                    System.out.print("Digite o ID do comprador a ser removido: ");
                    int idToRemove = scanner.nextInt();
                    if (buyerService.removeBuyer(idToRemove)) {
                        System.out.println("Comprador removido com sucesso!");
                    } else {
                        System.out.println("Erro ao remover comprador.");
                    }
                    break;

                case 5:
                    // Voltar ao menu principal
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu de gerenciamento de lojas atualizado
    private static void manageStores(Scanner scanner, StoreService storeService) {
        while (true) {
            System.out.println("\n------ MENU DE LOJAS ------");
            System.out.println("1. Adicionar Loja");
            System.out.println("2. Listar Lojas");
            System.out.println("3. Atualizar Loja");
            System.out.println("4. Remover Loja");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (choice) {
                case 1:
                    // Adicionar loja
                    System.out.print("Nome da Loja: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();

                    Store newStore = new Store(name, email, password, cnpj, address);
                    storeService.addStore(newStore);
                    System.out.println("Loja adicionada com sucesso!");
                    break;

                case 2:
                    // Listar lojas
                    System.out.println("\nLista de Lojas:");
                    for (Store store : storeService.listStores()) {
                        System.out.println(store); // Usa o toString da Store
                    }
                    break;

                case 3:
                    // Atualizar loja
                    System.out.print("Digite o ID da loja a ser atualizada: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer

                    System.out.print("Novo Nome da Loja: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nova Senha: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Novo CNPJ: ");
                    String newCnpj = scanner.nextLine();
                    System.out.print("Novo Endereço: ");
                    String newAddress = scanner.nextLine();

                    Store updatedStore = new Store(newName, newEmail, newPassword, newCnpj, newAddress);
                    updatedStore.setId(idToUpdate); // Setar o ID correto
                    if (storeService.updateStore(updatedStore)) {
                        System.out.println("Loja atualizada com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar loja.");
                    }
                    break;

                case 4:
                    // Remover loja
                    System.out.print("Digite o ID da loja a ser removida: ");
                    int idToRemove = scanner.nextInt();
                    if (storeService.removeStore(idToRemove)) {
                        System.out.println("Loja removida com sucesso!");
                    } else {
                        System.out.println("Erro ao remover loja.");
                    }
                    break;

                case 5:
                    // Voltar ao menu principal
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}