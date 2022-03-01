/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author romeroalicia
 */
public enum Change {
    HUNDRED(new BigDecimal("100")),
    FIFTY(new BigDecimal("50")),
    TWENTY(new BigDecimal("20")),
    TEN(new BigDecimal("10")),
    FIVE(new BigDecimal("5")),
    ONE(new BigDecimal("1"));
    
    public final BigDecimal VALUE;
    
    private Change(BigDecimal value) {
        this.VALUE = value;
    }
}
