package com.sankadilshan.taxcalculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxCalculationApplication implements CommandLineRunner {

    private TaxController taxController;

    @Autowired
    public TaxCalculationApplication(TaxController taxController) {
        this.taxController = taxController;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaxCalculationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TaxInput taxInput = TaxInput.builder().netAmount(302315d).taxFreeAmount(100000d).build();
        taxController.calculateTax(taxInput);
    }
}
