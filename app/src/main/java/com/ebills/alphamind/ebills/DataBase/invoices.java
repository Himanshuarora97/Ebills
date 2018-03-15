package com.ebills.alphamind.ebills.DataBase;

import com.orm.SugarRecord;

/**
 * Created by anmol on 15/3/18.
 */

public class invoices extends SugarRecord<invoices> {

    String number;
    String totalPrice;
    String invoiceDate;
    String isCancelled;

    // Invoices
    public invoices(String number, String totalPrice, String invoiceDate, String isCancelled) {
        this.number = number;
        this.totalPrice = totalPrice;
        this.invoiceDate = invoiceDate;
        this.isCancelled = isCancelled;
    }



}
