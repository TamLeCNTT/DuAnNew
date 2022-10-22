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
public class NhaSanXuat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String location;
	private String motacty;
	private String email;
	private String phone;
	   @OneToMany(cascade = CascadeType.ALL,mappedBy = "nhaSanXuat",orphanRemoval = true)
	    private List<SanPham> sanphams=new ArrayList<>();
	public NhaSanXuat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public List<SanPham> getSanphams() {
        return sanphams;
    }


    public void setSanphams(List<SanPham> sanphams) {
        this.sanphams = sanphams;
    }


    public NhaSanXuat(String name, String location, String motacty,String email, String phone) {
		super();
		this.name = name;
		this.location = location;
		this.email = email;
		this.motacty = motacty;
		this.phone = phone;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}
	

	public void setMoTaCTY(String moTaCty) {
		this.motacty = moTaCty;
	}
	public String getMoTaCTY() {
		return motacty;
	}
}
