package com.inventory.collections;

import com.inventory.model.Product;
import com.inventory.comparators.PriceComparator;
import com.inventory.comparators.ValueComparator;
import com.inventory.comparators.NameComparator;

import java.util.*;

public class InventoryManagementSystem {

    private HashSet<Product> productSet;
    private LinkedList<String> transactionHistory;
    private Stack<Product> undoStack;
    private Queue<Product> lowStockQueue;

    private int totalProducts;
    private double totalInventoryValue;

    public InventoryManagementSystem() {
        productSet = new HashSet<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
        lowStockQueue = new LinkedList<>();
    }

    // Add product
    public void addProduct(Product product) {
        if (productSet.add(product)) {
            totalProducts++;
            totalInventoryValue += product.getInventoryValue();

            transactionHistory.addFirst(
                    "ADD: " + product.getSku() + " (" + product.getQuantity() + ") " + new Date()
            );

            if (product.getQuantity() < 10) {
                lowStockQueue.add(product);
            }

            System.out.println("✅ Product added successfully!");
        } else {
            System.out.println("❌ SKU already exists!");
        }
    }

    // Update quantity
    public void updateProductQuantity(String sku, int newQty) {
        for (Product p : productSet) {
            if (p.getSku().equals(sku)) {

                // Save undo state
                undoStack.push(new Product(
                        p.getSku(),
                        p.getName(),
                        p.getCategory(),
                        p.getPrice(),
                        p.getQuantity()
                ));

                int oldQty = p.getQuantity();
                p.setQuantity(newQty);

                totalInventoryValue -= p.getPrice() * oldQty;
                totalInventoryValue += p.getPrice() * newQty;

                transactionHistory.addFirst(
                        "UPDATE: " + sku + " from " + oldQty + " to " + newQty + " " + new Date()
                );

                System.out.println("✅ Quantity updated!");
                return;
            }
        }
        System.out.println("❌ Product not found!");
    }

    // Undo last update
    public void undoLastUpdate() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }

        Product prev = undoStack.pop();
        updateProductQuantity(prev.getSku(), prev.getQuantity());
        System.out.println("↩ Undo completed!");
    }

    // Display sorted products
    public void displayProductsSortedBy(String criteria) {
        List<Product> list = new ArrayList<>(productSet);

        switch (criteria.toLowerCase()) {
            case "sku":
                Collections.sort(list);
                break;
            case "price":
                Collections.sort(list, new PriceComparator());
                break;
            case "value":
                Collections.sort(list, new ValueComparator());
                break;
            case "name":
                Collections.sort(list, new NameComparator());
                break;
            default:
                System.out.println("Invalid sort type!");
                return;
        }

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // Low stock
    public void displayLowStockAlerts() {
        if (lowStockQueue.isEmpty()) {
            System.out.println("No low stock items!");
            return;
        }

        System.out.println("⚠ Low Stock Items:");
        for (Product p : lowStockQueue) {
            System.out.println(p.getSku() + " - " + p.getQuantity());
        }
    }

    // Transaction history
    public void displayTransactionHistory(int count) {
        int shown = 0;
        for (String t : transactionHistory) {
            if (shown++ == count) break;
            System.out.println(t);
        }
    }

    // Statistics
    public void displayInventoryStatistics() {
        System.out.println("Total Products: " + totalProducts);
        System.out.println("Total Inventory Value: ₹" + totalInventoryValue);
    }
}

