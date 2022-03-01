/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.List;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineView {
    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public String displayMainMenu(List<Item> items) {
        io.print("*** Vending Machine ***");
        io.print("0. Exit");
        
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int index = i + 1;
            io.print(index + ". " + item.getName() + ": " + item.getCost().toString() + " euro");
        }

        return io.readString("Please hit enter to continue or 0 to exit");  
    }
    
    // Ask User to enter Money
    public String askForUsersMoney() {
        return io.readString("Please insert your money (in euro) to start.");
    }
    
    public void displayMoneyNotEnterd() {
        io.print("Please enter valid amount of money");
    }
    
    // Ask for User's selection
    public int askForMenuSelection() {
        return io.readInt("Pleas enter the number of the item you want to buy");
    }
    
    public void displayNotValidAmount(BigDecimal amount, String message) {
        io.print(message + " You only entered " + amount.toString() + " euro.");
    }
    
    public void displayItemSold(Item choosenItem) {
        io.print("You bought one " + choosenItem.getName());
    }
    
    public void displayItemOutOfStock(String message) {
        io.print(message);
    }
    
    public void displayChangeReturned(List<BigDecimal> change) {
        String message = "";
        String[] labels = {"euro", "fifty", "twenty", "ten", "five", "one"};
        String[] labelsPlural = {"euro", "fifties", "twenties", "tens", "fives", "ones"};
        for (int i = 0; i < labels.length; i++) {
            if (!change.get(i).equals(BigDecimal.ZERO)) {
                if (message.isEmpty()) {
                    message += "Your change is ";
                }
                if (change.get(i).equals(BigDecimal.ONE)) {
                    message += change.get(i).toString() + " " + labels[i] + " ";
                } else {
                    message += change.get(i).toString() + " " + labelsPlural[i] + " ";
                }
            }
        }
        io.print(message);
    }
    
    public String displayReturnBackToMainMenu() {
        return io.readString("Please hit enter to continue");
    }
    
    public void displayGoodbye() {
        io.print("Goodbye!");
    }
}
