package com.sankadilshan.taxcalculation;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class TaxController {

    @Autowired
    private TaxService taxservice;

    @GetMapping("/tax-calculate")
    public FinalTaxResponse calculateTax(@RequestBody TaxInput taxInput) {
        FinalTaxResponse finalTaxResponse = taxservice.calculateTax(taxInput.getNetAmount(), taxInput.getTaxFreeAmount());
        System.out.println(finalTaxResponse.toString());
        return finalTaxResponse;
    }
}
