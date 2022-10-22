package com.baitapnhom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baitapnhom.entity.LoaiSanPham;
import com.baitapnhom.renpository.LoaiSanPhamRepository;

@Controller
@RequestMapping("/loaisanpham")
public class LoaiSanPhamCotroller {
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;
    @GetMapping("them")
    public String themlsp(Model model) {
        LoaiSanPham LoaiSanPham=new LoaiSanPham();
        model.addAttribute("loaisanpham", LoaiSanPham);
        model.addAttribute("loi", "lỗi xảy ra khi thêm");
        
        return "/LoaiSanPham/them";
    }
    @PostMapping("them")
    public String thems(LoaiSanPham LoaiSanPham,Model model) {
        System.out.println(LoaiSanPham.getName());
      
        LoaiSanPham lsp=loaiSanPhamRepository.findByName(LoaiSanPham.getName());
        
        if (lsp!=null) {
           
            model.addAttribute("loaisanpham", lsp);
            model.addAttribute("loi",  "lỗi xảy ra khi thêm");
            return "/LoaiSanPham/them";
           
        }
        loaiSanPhamRepository.save(LoaiSanPham);
        return "redirect:/loaisanpham/them";
    }
    
    @GetMapping("sua/{id}")
    public String sualsp(Model model,@PathVariable Long id) {
        LoaiSanPham LoaiSanPham=loaiSanPhamRepository.findById(id).orElseThrow();
        if(LoaiSanPham!=null) {
        model.addAttribute("loaisanpham", LoaiSanPham);
        model.addAttribute("loi",  "lỗi xảy ra khi sửa");
        return "/LoaiSanPham/sua";
        }
        return "/LoaiSanPham/danhsach";
    }
    @PostMapping("sua/{id}")
    public String sualsps(LoaiSanPham loaiSanPham,@PathVariable Long id  ,  Model model) {
     
        LoaiSanPham lsp=loaiSanPhamRepository.findById(id).orElseThrow();
     
        lsp.setImage(loaiSanPham.getImage());
        lsp.setName(loaiSanPham.getName());
        loaiSanPhamRepository.save(lsp);
        
        return "redirect:/loaisanpham/danhsach";
    }
    @GetMapping("xoa/{id}")
    public String xoalsp(@PathVariable Long id) {
        LoaiSanPham lsp=loaiSanPhamRepository.findById(id).orElseThrow();
        loaiSanPhamRepository.delete(lsp);
        
        return "redirect:/loaisanpham/danhsach";
    }
    
    
    @GetMapping("danhsach")
    public String danhsach(Model model) {
        List<LoaiSanPham> listLoaiSanPham=loaiSanPhamRepository.findAll();
        model.addAttribute("listloaisanpham", listLoaiSanPham);
     
        
        return "/LoaiSanPham/danhsach";
    }

}
