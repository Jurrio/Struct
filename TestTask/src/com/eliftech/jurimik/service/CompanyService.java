package com.eliftech.jurimik.service;

import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.repository.CompanyRepository;
import com.eliftech.jurimik.util.CompanyUtils;

import java.sql.SQLException;
import java.util.List;

import com.eliftech.jurimik.exception.UnknownCompanyException;

public class CompanyService {

	public boolean add(Company company) {
		CompanyUtils.updateTotalEarnings(company);
		return new CompanyRepository().add(company);
	}

	public List<Company> find(String parameter) throws UnknownCompanyException {
		return new CompanyRepository().find(parameter);
	}

	public Company get(long id) throws UnknownCompanyException {
		return new CompanyRepository().get(id);
	}
	
	public Company lazyGet(long parentId) throws SQLException {
		return new CompanyRepository().lazyGet(parentId);
	}

	public boolean delete(long id) {
		Company delCompany;
		try {
			List<Company> children = this.getChildren(id);
			delCompany = get(id);
			for (Company c : children) {
				c.setParent(delCompany.getParent());
				this.update(c);
			}
		} catch (UnknownCompanyException e) {
			return false;
		}
		return new CompanyRepository().delete(id);
	}

	public boolean update(Company company) {
		return new CompanyRepository().update(company);
	}

	public List<Company> getAll() throws UnknownCompanyException {
		return new CompanyRepository().getAll();
	}

	public List<Company> getChildren(long id) {
		return new CompanyRepository().getChildren(id);
	}
}
