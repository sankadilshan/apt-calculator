package com.sankadilshan.taxcalculation;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class FinalTaxResponse {

    private Map<?, ?> taxResponse;
    private double totalTax;
}
