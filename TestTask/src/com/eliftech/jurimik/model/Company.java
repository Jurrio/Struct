package com.eliftech.jurimik.model;

import java.util.List;

public class Company {
	
	private long id;
	private String name;
	private long earnings;
//	private long parentId;
//	private String parentName;
	private Company parent;
	private List<Company> children;
	
	public Company() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getEarnings() {
		return earnings;
	}
	public void setEarnings(long earnings) {
		this.earnings = earnings;
	}

	public Company getParent() {
		return parent;
	}
	public void setParent(Company parent) {
		this.parent = parent;
	}

	public List<Company> getChildren() {
		return children;
	}
	public void setChildren(List<Company> children) {
		this.children = children;
	}
	
/*	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}*/
}
