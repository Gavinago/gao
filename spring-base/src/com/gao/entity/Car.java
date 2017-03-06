package com.gao.entity;

public class Car {
	private Integer id;
	private String brand;
	private String regNo;
	
	
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	private Double price;
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(Integer id, String brand, Double price) {
		super();
		this.id = id;
		this.brand = brand;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", price=" + price + "]";
	}
	
	
	
	public void begin(){
		System.out.println("汽车开始----------------------");
	}
	
	public void dis(){
		System.out.println("汽车报废---------------------");
	}
	
}
