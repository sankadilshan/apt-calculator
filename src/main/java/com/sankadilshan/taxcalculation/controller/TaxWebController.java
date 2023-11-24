package com.sankadilshan.taxcalculation.controller;

import com.sankadilshan.taxcalculation.FinalTaxResponse;
import com.sankadilshan.taxcalculation.TaxInput;
import com.sankadilshan.taxcalculation.TaxService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tax/web")
@AllArgsConstructor
public class TaxWebController {


    @Autowired
    private TaxService taxService;
    @GetMapping("/health")
    public String health() {
        return "health";
    }

    @GetMapping("/calculate")
    public String loadCalculate(Model model){
        model.addAttribute("isShowResult", false);
        model.addAttribute("taxInput", new TaxInput());
        model.addAttribute("taxFreeAmount", 0.0);
        model.addAttribute("netAmount", 0.0);
        return "calculator";
    }
    @PostMapping("/calculate")
    public String calculator(@ModelAttribute("taxInput") TaxInput taxInput, Model model){
        FinalTaxResponse finalTaxResponse = taxService.calculateTax(taxInput.getNetAmount(), taxInput.getTaxFreeAmount());
        model.addAttribute("totalTax", finalTaxResponse.getTotalTax());
        model.addAttribute("taxBreakdown", finalTaxResponse.getTaxResponse());
        model.addAttribute("isShowResult", true);
        return "calculator";
    }
}
