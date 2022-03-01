/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package raj.vendingmachine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import raj.vendingmachine.dao.VendingMachinePersistenceException;
import raj.vendingmachine.dto.Item;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineServiceLayerImplTest {
    
    ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
        
    VendingMachineServiceLayer service = 
           ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);

    /**
     * Testing the case, when funds are not sufficient
     * @throws Exception 
     */
    @Test
    public void testValidateAmount() throws Exception {
        BigDecimal moneyEntered = new BigDecimal("2");
        int itemId = 1;
        
        try {
            service.validateAmount(moneyEntered, itemId);
            fail("Expected Insufficient Money Exception was not thrown.");
        } catch (VendingMachineInsufficientFundsException ex) {
            return;
        }   
    }
    
    /**
     * Testing the case when there are no exceptions
     * and item is sold
     * @throws Exception 
     */
    
    @Test
    public void testSellItem() throws Exception {
        BigDecimal moneyEntered = new BigDecimal("4");
        int itemId = 1;
        
        String itemName = "CocaCola";
        BigDecimal cost = new BigDecimal("3");
        int numberOfItemsAfterSoled = 9;
        
        try {
            Item itemSold = service.sellItem(moneyEntered, itemId);  
            
            assertNotNull(itemSold, "Item should not be null");
            
            assertEquals(itemName, itemSold.getName(), "Item sold should be a CocaCola");
            
            assertEquals(cost, itemSold.getCost(), "CocaCola csots 3");
            
            assertEquals(numberOfItemsAfterSoled, itemSold.getNumerOfItems(), "After selling one the number of items should be 9.");
            
        } catch ( VendingMachinePersistenceException
                | VendingMachineInsufficientFundsException
                | VendingMachineNoItemInventoryException ex) {
            
            fail("There should have been no exception thrown.");
        }   
    }
    
    /**
     * Calculating the change, in case if
     * User submits 4.21 euros 
     * and the cost of item is 3
     * @throws Exception 
     */
    
    @Test
    public void testCalculateChange() throws Exception {
        BigDecimal moneyEntered = new BigDecimal("4.21");
        int itemId = 1;
        
        List<BigDecimal> changeSplit = new ArrayList<>();
        changeSplit.add(BigDecimal.ONE);
        changeSplit.add(BigDecimal.ZERO);
        changeSplit.add(BigDecimal.ONE);
        changeSplit.add(BigDecimal.ZERO);
        changeSplit.add(BigDecimal.ZERO);
        changeSplit.add(BigDecimal.ONE);
        
        try {
            List<BigDecimal> changeResult = service.calculateChange(moneyEntered, itemId);
            
            assertEquals(changeSplit, changeResult, "The change should be 1 euro, 1 twenty and 1 one.");
            
        } catch ( VendingMachinePersistenceException ex) {       
            fail("There should have been no exception thrown.");
        }   
    }
}
