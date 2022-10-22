package com.baitapnhom.renpository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baitapnhom.entity.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends  JpaRepository<LoaiSanPham, Long> {
LoaiSanPham findByName(String name);
}
