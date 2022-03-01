/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import raj.vendingmachine.controller.VendingMachineController;
import raj.vendingmachine.dao.VendingMachinePersistenceException;
import raj.vendingmachine.service.VendingMachineInsufficientFundsException;
import raj.vendingmachine.service.VendingMachineNoItemInventoryException;

/**
 *
 * @author romeroalicia
 */
public class App {
    public static void main(String[] args) throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException {
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
