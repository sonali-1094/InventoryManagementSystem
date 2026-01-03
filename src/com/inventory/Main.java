package com.inventory;

import com.inventory.collections.InventoryManagementSystem;
import com.inventory.model.Product;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== INVENTORY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Quantity");
            System.out.println("3. View Products (Sorted)");
            System.out.println("4. Low Stock Alerts");
            System.out.println("5. Transaction History");
            System.out.println("6. Inventory Statistics");
            System.out.println("7. Undo Last Update");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("SKU: ");
                    String sku = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    ims.addProduct(new Product(sku, name, cat, price, qty));
                    break;

                case 2:
                    System.out.print("SKU: ");
                    String updateSku = sc.nextLine();
                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();
                    ims.updateProductQuantity(updateSku, newQty);
                    break;

                case 3:
                    System.out.print("Sort by (sku/price/value/name): ");
                    String sort = sc.nextLine();
                    ims.displayProductsSortedBy(sort);
                    break;

                case 4:
                    ims.displayLowStockAlerts();
                    break;

                case 5:
                    System.out.print("How many transactions? ");
                    int count = sc.nextInt();
                    ims.displayTransactionHistory(count);
                    break;

                case 6:
                    ims.displayInventoryStatistics();
                    break;

                case 7:
                    ims.undoLastUpdate();
                    break;

                case 8:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
