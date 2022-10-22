package com.baitapnhom.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LoaiSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String image;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "loaiSanPham",orphanRemoval = true)
	private List<SanPham> sanphams=new ArrayList<>();
	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<SanPham> getSanphams() {
        return sanphams;
    }
    public void setSanphams(List<SanPham> sanphams) {
        this.sanphams = sanphams;
    }
    public LoaiSanPham(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	
	
}



