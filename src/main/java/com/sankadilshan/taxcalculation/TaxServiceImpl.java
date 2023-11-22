package com.sankadilshan.taxcalculation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaxServiceImpl implements TaxService {

    int[] percentage = new int[]{6, 12, 18, 24, 30, 36};
    private final Map<Integer, TaxResponse> taxMap = new HashMap<>();
    @Value("${tax.taxPartAmount}")
    private double taxPartAmount;

    public FinalTaxResponse calculateTax(double amount, double taxFreeAmount) {
        if (amount - taxFreeAmount < 0) {
            taxMap.put(0, TaxResponse.builder().percentage(0).amount(0d).build());
            return FinalTaxResponse.builder().taxResponse(taxMap).totalTax(0d).build();
        }

        double taxNoFreeAmount = amount - taxFreeAmount;
        int percentageIndex = 0;
        do {
            double v = taxNoFreeAmount - taxPartAmount;
            if (v < 0) {
                calculation(percentageIndex, taxNoFreeAmount);
                taxNoFreeAmount = 0;
            } else if (v > 0) {
                calculation(percentageIndex, taxPartAmount);
                taxNoFreeAmount = v;
                percentageIndex++;
            }
        } while (taxNoFreeAmount > 0);

        return getEndResponse(taxMap);
    }

    private void calculation(int percentageIndex, double partAmount) {
        double taxAmount = partAmount * percentage[percentageIndex] / 100;
        taxMap.put(percentageIndex, TaxResponse.builder().percentage(percentage[percentageIndex]).amount(taxAmount).build());
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
}
