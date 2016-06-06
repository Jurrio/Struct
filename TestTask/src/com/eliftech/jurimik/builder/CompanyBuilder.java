package com.eliftech.jurimik.builder;

import java.util.List;

import com.eliftech.jurimik.model.Company;

public class CompanyBuilder {
	
	private final String name;
	private final long earnings;
	private long id;
	private Company parent;
	private List<Company> children; 
	private long totalEarnings;
	
	public CompanyBuilder(String name, long earnings) {
		this.name = name;
		this.earnings = earnings;
	}
	
	public CompanyBuilder id(long id) {
		this.id = id;
		return this;
	}
	
	public CompanyBuilder parent(Company parent) {
		this.parent = parent;
		return this;
	}
	
	public CompanyBuilder children(List<Company> children) {
		this.children = children;
		return this;
	}
	
	public CompanyBuilder totalEarnings(long totalEarnings) {
		this.totalEarnings = totalEarnings;
		return this;
	}
	
	public Company build() {
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		company.setEarnings(earnings);
		company.setParent(parent);
		company.setChildren(children);
		company.setTotalEarnings(totalEarnings);
		return company;
	}
}
