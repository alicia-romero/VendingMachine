/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author romeroalicia
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {
    public static final File AUDIT_FILE = new File("audit.txt");

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        FileWriter file = null;
        PrintWriter out;

        try {
            file = new FileWriter(AUDIT_FILE, true);
            out = new PrintWriter(file);

            
        } catch (IOException ex) {
            throw new VendingMachinePersistenceException("Could not load Audit file", ex);
        } 
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp + " : " + entry);
        out.flush();
    }
    
}
