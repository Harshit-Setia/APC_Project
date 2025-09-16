package com.aic.inventorymanagement;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aic.inventorymanagement.model.Product;
import com.aic.inventorymanagement.service.ProductService;

@SpringBootApplication
public class InventorymanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventorymanagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductService service) {
        return args -> {
            Scanner sc = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n=== Inventory Management System ===");
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. View All Products");
                System.out.println("5. View Product By ID");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = readInt(sc);

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter price: ");
                        double price = readDouble(sc);
                        System.out.print("Enter quantity: ");
                        int quantity = readInt(sc);

                        Product p = new Product(0, name, category, price, quantity);
                        service.addProduct(p);
                        System.out.println("Product added!");
                    }
                    case 2 -> {
                        System.out.print("Enter product ID to update: ");
                        int id = readInt(sc);
                        System.out.print("Enter new name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter new category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter new price: ");
                        double price = readDouble(sc);
                        System.out.print("Enter new quantity: ");
                        int quantity = readInt(sc);

                        Product p = new Product(id, name, category, price, quantity);
                        service.updateProduct(p);
                        System.out.println("Product updated!");
                    }
                    case 3 -> {
                        System.out.print("Enter product ID to delete: ");
                        int id = readInt(sc);
                        service.deleteProduct(id);
                        System.out.println("Product deleted!");
                    }
                    case 4 -> {
                        List<Product> products = service.getAllProducts();
                        System.out.println("\n--- Product List ---");
                        for (Product prod : products) {
                            System.out.println(prod);
                        }
                    }
                    case 5 -> {
                        System.out.print("Enter product ID: ");
                        int id = readInt(sc);
                        Product prod = service.getProductById(id);
                        if (prod != null) {
                            System.out.println("Product: " + prod);
                        } else {
                            System.out.println("Product not found!");
                        }
                    }
                    case 6 -> {
                        exit = true;
                        System.out.println("Exiting... Bye!");
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }
            sc.close();
        };
    }

    // Helper methods for input validation
    private int readInt(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number! Please enter again: ");
            }
        }
    }

    private double readDouble(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number! Please enter again: ");
            }
        }
    }
}
