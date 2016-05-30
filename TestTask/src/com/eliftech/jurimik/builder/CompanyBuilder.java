package com.eliftech.jurimik.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;

public class CompanyBuilder {
	
	private final String name;
	private final long earnings;
	private long parent;
	
	public CompanyBuilder(String name, long earnings) {
		this.name = name;
		this.earnings = earnings;
	}
	
	public CompanyBuilder parent(long parentId) {
		if (parentId != 0) {
			this.parent = parentId;
		}
		return this;
	}
	
	public Company build() {
		Company company = new Company();
		company.setName(name);
		company.setEarnings(earnings);
		company.setParentId(parent);
		return company;
	}
	
	public static Company buildFromResultSet(ResultSet rs) throws SQLException, UnknownCompanyException {
		Company foundCompany = new Company();
		foundCompany.setId(rs.getLong("id"));
		foundCompany.setName(rs.getString("name"));
		foundCompany.setEarnings(rs.getLong("earnings"));
		foundCompany.setParentId(rs.getLong("parent"));
		foundCompany.setParentName(CompanyService.get(foundCompany.getParentId()).getName());
		
		return foundCompany;
	}
}
