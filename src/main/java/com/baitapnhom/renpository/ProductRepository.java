package com.baitapnhom.renpository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baitapnhom.entity.SanPham;

public interface ProductRepository extends JpaRepository<SanPham, Long> {
    Optional<SanPham>  findByName(String name);
   
}
