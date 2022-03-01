/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raj.vendingmachine.dao;

import java.util.List;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public interface VendingMachineDao {
    
    public List<Item> getItems();
    
    public List<Item> getItemsInStore();
    
    /**
     * itemIds in the App start from 1
     * but the List, which stores them starts from 0
     * so I had to subtract one in the implementation
     * @param itemId in the App
     * @return 
     */
    
    public Item findItem(int itemId);
    
    public Item findItemInStore(int itemId);
    
    public void readFile() throws VendingMachinePersistenceException;
    
    public void writeFile() throws VendingMachinePersistenceException;
}
