package com.example;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import java.util.List;
import java.util.Scanner;
public class Main {
     public static void main(String[] args) {
       try(Scanner sc = new Scanner(System.in)) {
         
         ProductDAO dao = new ProductDAO();
 
         while (true) {
             System.out.println("\n=== Inventory Management ===");
             System.out.println("1. Add Product");
             System.out.println("2. View All Products");
             System.out.println("3. Update Product Quantity");
             System.out.println("4. Delete Product");
             System.out.println("5. Exit");
             System.out.print("Enter choice: ");
 
             int choice = sc.nextInt();
             sc.nextLine(); // consume newline
 
             switch (choice) {
                 case 1 -> {
                     System.out.print("Enter Name: ");
                     String name = sc.nextLine();
                     System.out.print("Enter Category: ");
                     String cat = sc.nextLine();
                     System.out.print("Enter Price: ");
                     double price = sc.nextDouble();
                     System.out.print("Enter Quantity: ");
                     int qty = sc.nextInt();
                     dao.addProduct(new Product(0, name, cat, price, qty));
                 }
                 case 2 -> {
                     List<Product> products = dao.getAllProducts();
                     System.out.printf("%-5s %-15s %-10s %-10s %-5s\n", "ID", "Name", "Category", "Price", "Qty");
                     for (Product p : products) {
                         System.out.println(p);
                     }
                 }
                 case 3 -> {
                     System.out.print("Enter Product ID: ");
                     int id = sc.nextInt();
                     System.out.print("Enter New Quantity: ");
                     int qty = sc.nextInt();
                     dao.updateProductQuantity(id, qty);
                 }
                 case 4 -> {
                     System.out.print("Enter Product ID: ");
                     int id = sc.nextInt();
                     dao.deleteProduct(id);
                 }
                 case 5 -> {
                     System.out.println("👋 Exiting...");
                     System.exit(0);
                 }
                 default -> System.out.println("Invalid choice!");
             }
         }
       } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
       }
    
    }
}