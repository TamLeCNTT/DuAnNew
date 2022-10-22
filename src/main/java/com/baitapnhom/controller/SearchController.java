package com.baitapnhom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baitapnhom.entity.SanPham;
import com.baitapnhom.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;

@Controller

public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
//    
//    public String seach(Model model, @Param("keyword") String keyword) {
//        List<SanPham> listProducts = searchService.listAll(keyword);
//        model.addAttribute("listsanpham", listProducts);
//        model.addAttribute("keyword", keyword);
//        return "Search/search";
//    }
    public @ResponseBody String SearchProduct(HttpServletRequest request) {
        String keyword = request.getParameter("name");
        System.out.println(keyword);
        List<SanPham> listsanphams;
        if (keyword!="") {
         listsanphams = searchService.listAll(keyword);
        }
        else
           listsanphams =null;
            
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listsanphams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

}
