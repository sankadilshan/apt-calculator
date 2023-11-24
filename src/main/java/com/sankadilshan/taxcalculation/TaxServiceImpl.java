package com.sankadilshan.taxcalculation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaxServiceImpl implements TaxService {

    private final Map<Integer, TaxResponse> taxMap = new HashMap<>();
    List<Integer> percentage = Arrays.asList(6, 12, 18, 24, 30, 36);
    @Value("${tax.taxPartAmount}")
    private final double taxPartAmount = 41666.67;

    public FinalTaxResponse calculateTax(double amount, double taxFreeAmount) {
        taxMap.clear();
        calculateTaxExtended(amount, taxFreeAmount);
        return getEndResponse(taxMap);
    }

    private void calculation(int percentageIndex, double partAmount) {
        double taxAmount = Math.round((partAmount * percentage.get(percentageIndex) / 100) * 100.00) / 100.00;
        taxMap.put(percentageIndex, TaxResponse.builder().percentage(percentage.get(percentageIndex)).amount(taxAmount).build());
    }

    private FinalTaxResponse getEndResponse(Map<Integer, TaxResponse> taxMap) {
        double totalTax = 0d;
        for (Map.Entry<Integer, TaxResponse> taxResponse : taxMap.entrySet()) {
            TaxResponse value = taxResponse.getValue();
            totalTax += value.amount;
        }
        double roundOff = Math.round(totalTax * 100.0) / 100.0;
        return FinalTaxResponse.builder().taxResponse(taxMap).totalTax(roundOff).build();
    }

    private void calculateTaxExtended(double netAmount, double taxFreeAmount) {
        taxMap.clear();

        double amountNotRelief = netAmount - taxFreeAmount;
        if (amountNotRelief > 0) {
            for (int pr : percentage) {
                if (amountNotRelief > 0) {
                    if (pr == percentage.get(percentage.size() - 1)) {
                        calculation2(pr, amountNotRelief);
                    } else {
                        double v = amountNotRelief - taxPartAmount;
                        double selectedAmount = v > 0 ? taxPartAmount : amountNotRelief;
                        calculation2(pr, selectedAmount);
                        if (v > 0) {
                            amountNotRelief = v;
                        } else {
                            amountNotRelief = 0;
                        }
                    }
                }
            }
        }
    }

    private void calculation2(int pr, double partAmount) {
        double taxAmount = Math.round((partAmount * pr / 100) * 100.00) / 100.00;
        taxMap.put(percentage.indexOf(pr), TaxResponse.builder().percentage(pr).amount(taxAmount).build());
    }
}
