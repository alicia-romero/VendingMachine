/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package raj.vendingmachine.dao;

import java.util.List;
import java.io.File;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineDaoFileImplTest {
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    /**
     * Setting up a test file
     * test.txt has to items in it:
     * Doritos,4.2,0
     * CocaCola,3,10
     * @throws Exception 
     */
    
    @BeforeEach
    public void setUp() throws Exception {
        File testFile = new File("test.txt");
        testDao = new VendingMachineDaoFileImpl(testFile);
        testDao.readFile();
    }
    
    @Test
    public void testGetItems() {
        String firstItemName = "Doritos";
        BigDecimal firstCost = new BigDecimal("4.2");
        int firstNumberOfItems = 0;
        String secondItemName = "CocaCola";
        BigDecimal secondCost = new BigDecimal("3");
        int secondNumberOfItems = 10;
        
        List<Item> resultList = testDao.getItems();
        
        assertEquals(2, resultList.size(), "List of Items in Store should have two items");
        
        assertEquals(firstItemName, resultList.get(0).getName(), "Item has to be named Doritos");
        assertEquals(secondItemName, resultList.get(1).getName(), "Item has to be named CocaCola");
        
        assertEquals(firstCost, resultList.get(0).getCost(), "Items cost must be 4.2");
        assertEquals(secondCost, resultList.get(1).getCost(), "Items cost must be 3");
        
        assertEquals(firstNumberOfItems, resultList.get(0).getNumerOfItems(), "Items in inventory must be 0");
        assertEquals(secondNumberOfItems, resultList.get(1).getNumerOfItems(), "Items in inventory must be 10");
    }

    @Test
    public void testGetItemsInStore() {
        String itemName = "CocaCola";
        BigDecimal cost = new BigDecimal("3");
        int numberOfItems = 10;
        List<Item> resultList = testDao.getItemsInStore();
        
        assertEquals(1, resultList.size(), "List of Items in Store should have one item");
        
        assertEquals(itemName, resultList.get(0).getName(), "Item has to be named CocaCola");
        
        assertEquals(cost, resultList.get(0).getCost(), "Items cost must be 3");
        
        assertEquals(numberOfItems, resultList.get(0).getNumerOfItems(), "Items in inventory must be 10");
    }
    
    @Test
    public void testFindItem() {
        int firstId = 1;
        String firstItemName = "Doritos";
        BigDecimal firstCost = new BigDecimal("4.2");
        int firstNumberOfItems = 0;
        
        int secondId = 2;
        String secondItemName = "CocaCola";
        BigDecimal secondCost = new BigDecimal("3");
        int secondNumberOfItems = 10;
        
        Item firstItem = testDao.findItem(firstId);
        Item secondItem = testDao.findItem(secondId);
        
        
        assertEquals(firstItemName, firstItem.getName(), "Item has to be named Doritos");
        assertEquals(secondItemName, secondItem.getName(), "Item has to be named CocaCola");
        
        assertEquals(firstCost, firstItem.getCost(), "Items cost must be 4.2");
        assertEquals(secondCost, secondItem.getCost(), "Items cost must be 3");
        
        assertEquals(firstNumberOfItems, firstItem.getNumerOfItems(), "Items in inventory must be 0");
        assertEquals(secondNumberOfItems, secondItem.getNumerOfItems(), "Items in inventory must be 10");
    }
    
    @Test
    public void testFindItemInStore() {
        int firstId = 1;
        
        int secondId = 2;
        String secondItemName = "CocaCola";
        BigDecimal secondCost = new BigDecimal("3");
        int secondNumberOfItems = 10;
        
        Item firstItem = testDao.findItemInStore(firstId);
        Item secondItem = testDao.findItemInStore(secondId);
        
        assertNull(firstItem, "There are 0 Doritos in inventory, so it should be null");
        
        assertEquals(secondItemName, secondItem.getName(), "Item has to be named CocaCola");
        
        assertEquals(secondCost, secondItem.getCost(), "Items cost must be 3");
        
        assertEquals(secondNumberOfItems, secondItem.getNumerOfItems(), "Items in inventory must be 10");
    }  
}
