package com.sankadilshan.taxcalculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tax/web")
public class TaxWebController {

    @GetMapping("/health")
    public String health() {
        return "health";
    }
}
