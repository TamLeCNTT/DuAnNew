package com.baitapnhom.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String mota;
    private String image;
    private Date ngaySanXuat;
    private double gia;
    private int soluong;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="loaisanpham_id")
    private LoaiSanPham loaiSanPham;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nhasanxuat_id")
    private NhaSanXuat nhaSanXuat;
    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }
    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }
    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }
    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
    public SanPham() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public SanPham(String name, String mota, String image, Date ngaySanXuat, double gia, int soluong,
            LoaiSanPham loaiSanPham, NhaSanXuat nhaSanXuat) {
        super();
        this.name = name;
        this.mota = mota;
        this.image = image;
        this.ngaySanXuat = ngaySanXuat;
        this.gia = gia;
        this.soluong = soluong;
        this.loaiSanPham = loaiSanPham;
        this.nhaSanXuat = nhaSanXuat;
    }
    public double getGia() {
        return gia;
    }
    public void setGia(double gia) {
        this.gia = gia;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }
    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
		return id;
	}
    
    
    
}
