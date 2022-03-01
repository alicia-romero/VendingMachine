/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raj.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import raj.vendingmachine.dao.VendingMachinePersistenceException;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public interface VendingMachineServiceLayer {
    
    public List<Item> getItemsInStore();
    
    /**
     * Converts the Users submitted amount
     * to BigDecimal with scale of 2
     * @param moneyEntered
     * @return 
     */
    
    public BigDecimal getUsersMoney(String moneyEntered);
    
    /**
     * Validates the submitted amount compared to
     * the cost of chosen item
     * @param moneyEntered
     * @param itemId
     * @throws VendingMachineInsufficientFundsException 
     */
    public void validateAmount(BigDecimal moneyEntered, int itemId) throws
            VendingMachineInsufficientFundsException;
    
    /**
     * Sells the item, if there is stock in inventory
     * updates the number of items in inventory
     * @param moneyEntered
     * @param itemId
     * @return
     * @throws VendingMachinePersistenceException
     * @throws VendingMachineInsufficientFundsException
     * @throws VendingMachineNoItemInventoryException 
     */
    public Item sellItem(BigDecimal moneyEntered, int itemId) throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException;
    
    /**
     * Calculates the change returned to the user
     * Returns the number of euros, fifty, twenty, ten, five and one cents
     * uses Change Enum
     * @param amountEntered
     * @param itemId
     * @return
     * @throws VendingMachinePersistenceException 
     */
    public List<BigDecimal> calculateChange(BigDecimal amountEntered, int itemId) throws
            VendingMachinePersistenceException;
    
    public void loadInventory() throws
            VendingMachinePersistenceException;
    
    public void updateInventory() throws
            VendingMachinePersistenceException;
}
