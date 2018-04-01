package com.jd.test;

public class User {

	private String name;
	
	private String age;
	
	private Address address;

	public User() {
	}
	
	
	public User(String name, String age, Address address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public String getAge() {
		return age;
	}


	public Address getAddress() {
		return address;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", address=" + address + "]";
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


		
	
}
