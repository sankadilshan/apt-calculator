package com.sankadilshan.taxcalculation;

import lombok.*;

@Data
@Builder
public class TaxInput {

    double netAmount;
    double taxFreeAmount;
}
