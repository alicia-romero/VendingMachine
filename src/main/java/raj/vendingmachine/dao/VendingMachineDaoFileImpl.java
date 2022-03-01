/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    public final File INVENTORY_FILE;
    private final static String SEPARATOR = ",";
    private final List<Item> items = new ArrayList<>();

    public VendingMachineDaoFileImpl() {
        this.INVENTORY_FILE = new File("inventory.txt");
    }
    
    public VendingMachineDaoFileImpl(File invenoryFile) {
        this.INVENTORY_FILE = invenoryFile;
    }

    @Override
    public List<Item> getItems() {
        return this.items;
    }
    
    @Override
    public List<Item> getItemsInStore() {
        List<Item> itemsInStore = getItems().stream()
                .filter((item) -> item.getNumerOfItems() > 0)
                .collect(Collectors.toList());
        return itemsInStore; 
    }
    
    @Override
    public Item findItem(int itemId) {
        Item foundItem = this.items.get(itemId - 1);
        return foundItem;
    }
    
    @Override
    public Item findItemInStore(int itemId) {
        Item foundItem = this.items.get(itemId - 1);
        if (foundItem.getNumerOfItems() > 0) {
            return foundItem;
        } else {
            return null;
        }
    }
    
    // File I/O methods
    @Override
    public void readFile() throws VendingMachinePersistenceException {
        
        FileReader file = null;
        String[] tempArr = new String[3];
        Item tempItem = null;
        
        try {
            file = new FileReader(INVENTORY_FILE);
            BufferedReader lines = new BufferedReader(file);
            
            String currentLine = lines.readLine();
            while (currentLine != null) {
                tempArr = currentLine.split(SEPARATOR);
                
                String name = tempArr[0].trim();
                BigDecimal cost = new BigDecimal(tempArr[1].trim());
                int numberOfItems = Integer.parseInt(tempArr[2].trim());
                
                tempItem = new Item(name, cost, numberOfItems);
                this.items.add(tempItem);
                currentLine = lines.readLine();
            }
            file.close();
            
        } catch (FileNotFoundException ex) {
            throw new VendingMachinePersistenceException("Could not load Inventory", ex);
        } catch (IOException ex) {
            throw new VendingMachinePersistenceException("Could not load Inventory", ex);
        }
    }
    
    @Override
    public void writeFile() throws VendingMachinePersistenceException {
        FileWriter file = null;

        try {
            file = new FileWriter(INVENTORY_FILE);
            PrintWriter out = new PrintWriter(file);
         
            Collection<Item> items = getItems();
            for(Item item : items) {
                out.println(item.getName() + SEPARATOR
                        + item.getCost() + SEPARATOR
                        + item.getNumerOfItems());
                 out.flush();
            }
           
            out.close();
            
        } catch (IOException ex) {
            throw new VendingMachinePersistenceException("Could not load Inventory", ex);
        }   
    }
}
