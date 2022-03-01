/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import raj.vendingmachine.dao.VendingMachineDao;
import raj.vendingmachine.dao.VendingMachinePersistenceException;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        this.onlyItem = new Item("CocaCola", new BigDecimal("3"), 10);
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(this.onlyItem);
        return items;
    }

    @Override
    public List<Item> getItemsInStore() {
        return getItems();
    }

    @Override
    public Item findItem(int index) {
        return this.onlyItem;
    }

    @Override
    public Item findItemInStore(int itemId) {
        return this.onlyItem;
    }

    @Override
    public void readFile() throws VendingMachinePersistenceException {
    }

    @Override
    public void writeFile() throws VendingMachinePersistenceException {
    }
    
}
