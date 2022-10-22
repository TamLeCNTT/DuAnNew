package com.baitapnhom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baitapnhom.entity.SanPham;
import com.baitapnhom.renpository.ProductRepository;
import com.baitapnhom.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping({"/sanpham",""})

public class ProductController {
    @Autowired
    private HttpSession session;
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    
    @GetMapping("/sanpham")
    public String card_show(Model model,@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
        
        System.out.println("\nSession trong san pham "+session.getAttribute("isNameSession"));
        System.out.println("Cookie trong san pham "+cookieValue);
        model.addAttribute("isNameCookie", cookieValue);
        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
        System.out.println("01221255");
        
        return "/sanpham/sanpham-show";
    }

    @GetMapping("/giohang")
    public String card_giohang(Model model,@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
        model.addAttribute("isNameCookie", cookieValue);
        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
        return "/card/card_giohang";
    }
    @PostMapping("/card_show")
    public String card_show(@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue,Model model) {
        model.addAttribute("isNameCookie", cookieValue);
        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
        return "/card/card-show";
    }
    
    @GetMapping("/card_xacnhan")
    public String card_xacnhan() {
        return "/card/card_xacnhan";
    }
    @GetMapping("/add-card")
    public @ResponseBody String addCard(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        List<SanPham>listSanpham=new ArrayList<>();;
        SanPham sanpham=productService.findById(id);
        
        int flag=0;
        if (session.getAttribute("card")!=null) {
            //lay sesion
            listSanpham=(List<SanPham>)session.getAttribute("card");
            for(SanPham sp : listSanpham)
                if(sp.getId()==sanpham.getId()) {
                    sp.setSoluong(sp.getSoluong()+sanpham.getSoluong());
                    flag=1;
                }
        }
      
          
      
         if (listSanpham.isEmpty() || flag==0) {
             listSanpham.add(sanpham);
         }
         //tạo session
         session.setAttribute("card",listSanpham);
         listSanpham=(List<SanPham>)session.getAttribute("card");
         System.out.println(listSanpham.size());
         session.setAttribute("soluong", listSanpham.size());
         for(SanPham sp : listSanpham)
         System.out.println(sp.getName()+" next ");
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(listSanpham.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

//     @GetMapping("/danhsach")
//     public String them(Model model, HttpSession session,
//             @CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
      
//         List<NhaSanXuat> listnhaSanXuats = nhaSanXuatRepository.findAll();
//         model.addAttribute("listnhasanxuat", listnhaSanXuats);
//         model.addAttribute("isNameCookie", cookieValue);
//         model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
//         return "NhaSanXuat/danhsach";
//     }

//    @PostMapping("/them")
//    public String thems(Model model, HttpSession session,
//            @CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue,
//             @RequestParam(value = "name") String name,
// 			@RequestParam(value = "email") String email,
//             @RequestParam(value = "phone") String phone,
// 			@RequestParam(value = "location") String location,
//             @RequestParam(value = "motacty") String motacty) {
//        NhaSanXuat nhaSanXuat = new NhaSanXuat( name,  location,  motacty, email,  phone);
//        nhaSanXuatRepository.save(nhaSanXuat);
//        return "redirect:/nhasanxuat/danhsach";
//     //    NhaSanXuat nsx = nhaSanXuatRepository.findByName(name);
//     //    if (nsx != null) {
//     //        model.addAttribute("nhasanxuat", nsx);

//     //        return "redirect:/nhasanxuat/danhsach";

//     //    }
//     //    nhaSanXuatRepository.save(nhaSanXuat);
//     //    return "redirect:/nhasanxuat/danhsach";
//    }

}


