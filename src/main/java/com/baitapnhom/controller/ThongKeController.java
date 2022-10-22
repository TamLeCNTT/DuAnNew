package com.baitapnhom.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thongke")
public class ThongKeController {
@GetMapping("/bieudo")
public String bieudo(Model model) {
    Map<String, Integer> surveyMap = new LinkedHashMap<>();
    surveyMap.put("Java", 40);
    surveyMap.put("Dev oops", 25);
    surveyMap.put("Python", 20);
    surveyMap.put(".Net", 15);
    model.addAttribute("surveyMap", surveyMap);
    return "ThongKe/bieudo";
}
}
