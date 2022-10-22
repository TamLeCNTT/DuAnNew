package com.baitapnhom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baitapnhom.entity.SanPham;
import com.baitapnhom.renpository.ProductRepository;

import groovyjarjarantlr4.v4.Tool.Option;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void save(SanPham sanPham) {
        productRepository.save(sanPham);
    }

    public void delete(SanPham sanPham) {
        productRepository.delete(sanPham);
    }
    public Optional<SanPham> findByName(String name) {
        Optional<SanPham> sanpham=productRepository.findByName(name);
       
        return sanpham;
    }
    public SanPham findById(Long id) {
        SanPham sanpham=productRepository.findById(id).orElseThrow();
        return sanpham;
    }
    public List<SanPham> findAll() {
        List<SanPham> sanpham=productRepository.findAll();
        return sanpham;
    }



}
