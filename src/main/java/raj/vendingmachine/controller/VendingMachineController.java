/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import raj.vendingmachine.dao.VendingMachinePersistenceException;
import raj.vendingmachine.dto.Item;
import raj.vendingmachine.service.VendingMachineInsufficientFundsException;
import raj.vendingmachine.service.VendingMachineNoItemInventoryException;
import raj.vendingmachine.service.VendingMachineServiceLayer;
import raj.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineController {
    VendingMachineView view;
    VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }
   
    public void run() throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException {
        
        readFile();
        boolean exit = false;

        while (!exit) {
            String selection = selectStart();
            if (selection.equals("0")) {
                exit = true;
            } else {
                sellItem();
            }
        }
        writeFile();
        sayGoodbye();
    }
   
    public String selectStart() {
        List<Item> items = new ArrayList<>(service.getItemsInStore());
        String selection = view.displayMainMenu(items);
        return  selection;
    }
    
    public void sayGoodbye() {
        view.displayGoodbye();
    }
    
    public BigDecimal askMoney() {
        String moneyEntered = view.askForUsersMoney();
        BigDecimal money = service.getUsersMoney(moneyEntered);

        if (money.compareTo(BigDecimal.ZERO) > 0) {
            return money;
        } else {
            view.displayMoneyNotEnterd();
            view.displayReturnBackToMainMenu();
            return null;
        }
    }
    
    public void sellItem()  throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException {
        
        String moneyEntered = view.askForUsersMoney();
        BigDecimal money = service.getUsersMoney(moneyEntered);
        
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            int selectionIndex = view.askForMenuSelection();
            try {
                service.validateAmount(money, selectionIndex);
               
                Item soldItem = service.sellItem(money, selectionIndex);
                view.displayItemSold(soldItem);
                view.displayChangeReturned(service.calculateChange(money, selectionIndex)); 
            } catch (VendingMachineInsufficientFundsException e) {
                view.displayNotValidAmount(money, e.getMessage());
            } catch (VendingMachineNoItemInventoryException e) {
                view.displayItemOutOfStock(e.getMessage());
            }
            view.displayReturnBackToMainMenu();
        } else {
            view.displayMoneyNotEnterd();
            view.displayReturnBackToMainMenu();
        } 
    }
    
    public void readFile() throws VendingMachinePersistenceException {
        service.loadInventory();
    }
    
    public void writeFile() throws VendingMachinePersistenceException {
        service.updateInventory();
    }
}
