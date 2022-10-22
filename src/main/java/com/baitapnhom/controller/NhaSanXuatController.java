package com.baitapnhom.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baitapnhom.entity.NhaSanXuat;

import com.baitapnhom.renpository.NhaSanXuatRepository;

@Controller
@RequestMapping("nhasanxuat")
public class NhaSanXuatController {
    @Autowired
    HttpSession session;
    @Autowired
    private NhaSanXuatRepository nhaSanXuatRepository;

    @GetMapping("/danhsach")
    public String them(Model model, HttpSession session,
            @CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
      
        List<NhaSanXuat> listnhaSanXuats = nhaSanXuatRepository.findAll();
        model.addAttribute("listnhasanxuat", listnhaSanXuats);
        model.addAttribute("isNameCookie", cookieValue);
        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
        return "NhaSanXuat/danhsach";
    }

   @PostMapping("/them")
   public String thems(Model model, HttpSession session,
           @CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue,
            @RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email,
            @RequestParam(value = "phone") String phone,
			@RequestParam(value = "location") String location,
            @RequestParam(value = "motacty") String motacty) {

                NhaSanXuat nsx = nhaSanXuatRepository.findByName(name);
                if (nsx != null) {
                    model.addAttribute("nhasanxuat", nsx);
         
                    return "redirect:/nhasanxuat/danhsach";
         
                }
       NhaSanXuat nhaSanXuat = new NhaSanXuat( name,  location,  motacty, email,  phone);
       nhaSanXuatRepository.save(nhaSanXuat);
       return "redirect:/nhasanxuat/danhsach";

    //    nhaSanXuatRepository.save(nhaSanXuat);
    //    return "redirect:/nhasanxuat/danhsach";
   }

    @GetMapping("sua/{id}")
    public String sualsp(Model model, @PathVariable Long id, HttpSession session,
            @CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
        NhaSanXuat nhaSanXuat = nhaSanXuatRepository.findById(id).orElseThrow();
        if (nhaSanXuat != null) {
            model.addAttribute("nhasanxuat", nhaSanXuat);
            model.addAttribute("loi", "lỗi xảy ra khi sua");
            return "/NhaSanXuat/sua";
        }
        return "redirect:/nhasanxuat/danhsach";
    }

    @PostMapping("sua/{id}")
    public String sualsps(NhaSanXuat nhaSanXuat, @PathVariable Long id, Model model) {

        NhaSanXuat nhaSanXuats = nhaSanXuatRepository.findById(id).orElseThrow();

        nhaSanXuats.setEmail(nhaSanXuat.getEmail());
        nhaSanXuats.setLocation(nhaSanXuat.getLocation());
        nhaSanXuats.setName(nhaSanXuat.getName());

        nhaSanXuatRepository.save(nhaSanXuats);

        return "redirect:/nhasanxuat/danhsach";
    }

    @GetMapping("xoa/{id}")
    public String xoalsp(@PathVariable Long id) {
        NhaSanXuat nhaSanXuat = nhaSanXuatRepository.findById(id).orElseThrow();
        nhaSanXuatRepository.delete(nhaSanXuat);

        return "redirect:/nhasanxuat/danhsach";
    }

//    @GetMapping("danhsach")
//    public String danhsach(Model model, HttpSession session,
//            @CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
//        List<NhaSanXuat> listnhaSanXuats = nhaSanXuatRepository.findAll();
//        model.addAttribute("listnhasanxuat", listnhaSanXuats);
//        model.addAttribute("isNameCookie", cookieValue);
//        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
//
//        return "/NhaSanXuat/danhsach";
//    }
}
