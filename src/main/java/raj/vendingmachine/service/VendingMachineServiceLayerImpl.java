/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import raj.vendingmachine.dao.VendingMachineAuditDao;
import raj.vendingmachine.dao.VendingMachineDao;
import raj.vendingmachine.dao.VendingMachinePersistenceException;
import raj.vendingmachine.dto.Change;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    private final VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Item> getItemsInStore() {
        return dao.getItemsInStore();
    }

    @Override
    public BigDecimal getUsersMoney(String moneyEntered) {
        BigDecimal euros = new BigDecimal(moneyEntered);
        return euros.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void validateAmount(BigDecimal moneyEntered, int itemId)
            throws VendingMachineInsufficientFundsException {
        
        Item choosenItem = dao.findItemInStore(itemId);
        if (choosenItem != null) {
            BigDecimal cost = choosenItem.getCost();
            if (cost.compareTo(moneyEntered) > 0) {
                throw new VendingMachineInsufficientFundsException(
                        "Sorry, not enough money to purchase the item.");
            }
        }
    }

    @Override
    public Item sellItem(BigDecimal moneyEntered, int itemId) throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException {
        Item choosenItem = dao.findItemInStore(itemId);
        
        if (choosenItem != null) {
            validateAmount(moneyEntered, itemId);
            int numberOfItems = choosenItem.getNumerOfItems();
            if (numberOfItems > 0) {
                numberOfItems--;
                choosenItem.setNumerOfItems(numberOfItems);
                auditDao.writeAuditEntry("One " + choosenItem.getName() + " SOLD");
                if (numberOfItems == 0) {
                    auditDao.writeAuditEntry(choosenItem.getName() +  " OUT OF STOCK");
                }
            } else {
                throw new VendingMachineNoItemInventoryException("Sorry this item is OUT OF STOCK");
            }
        }
        return choosenItem;
    }
    
    @Override
    public List<BigDecimal> calculateChange(BigDecimal amountEntered, int itemId) throws
            VendingMachinePersistenceException {
        
        Item choosenItem = dao.findItem(itemId);
        BigDecimal changeInEuro = amountEntered.subtract(choosenItem.getCost()).setScale(2, RoundingMode.HALF_UP);
        List<BigDecimal> changeSplit = new ArrayList();
        BigDecimal changeInCents = changeInEuro.multiply(Change.HUNDRED.VALUE); // convert it to cents
        
        Change[] cents = {Change.HUNDRED, Change.FIFTY, Change.TWENTY, Change.TEN, Change.FIVE, Change.ONE};
        
        for (int i = 0; i < cents.length; i++) {
            BigDecimal currentCahange = changeInCents.divide(cents[i].VALUE, 0, RoundingMode.FLOOR);
            changeSplit.add(currentCahange);
            changeInCents = changeInCents.remainder(cents[i].VALUE);
        }
        return changeSplit;
    }   
    
    @Override
    public void loadInventory() throws
            VendingMachinePersistenceException {
        
        dao.readFile();
    }
    
    @Override
    public void updateInventory() throws
            VendingMachinePersistenceException {
        
        dao.writeFile();
    }
    

}
