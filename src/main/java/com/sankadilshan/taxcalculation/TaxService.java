package com.sankadilshan.taxcalculation;

public interface TaxService {

    FinalTaxResponse calculateTax(double amount, double taxFreeAmount);
}
