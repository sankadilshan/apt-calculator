package com.sankadilshan.taxcalculation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxInput {

    double netAmount;
    double taxFreeAmount;
}
