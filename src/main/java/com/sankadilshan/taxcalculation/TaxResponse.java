package com.sankadilshan.taxcalculation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxResponse {

    int percentage;
    double amount;
}
