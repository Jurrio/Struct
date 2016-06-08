package com.eliftech.jurimik.service;

import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.repository.CompanyRepository;
import com.eliftech.jurimik.util.CompanyUtils;

import java.sql.SQLException;
import java.util.List;

import com.eliftech.jurimik.exception.UnknownCompanyException;

public class CompanyService {

	public boolean add(Company company) throws UnknownCompanyException, SQLException {
		boolean isAdded = new CompanyRepository().add(company);
		if (isAdded) {
			CompanyUtils.updateParentTotalEarnings(company);
		}
		return isAdded;
	}

	public List<Company> find(String parameter) throws UnknownCompanyException, SQLException {
		return new CompanyRepository().find(parameter);
	}

	public Company get(long id) throws UnknownCompanyException, SQLException {
		return new CompanyRepository().get(id);
	}

	public Company lazyGet(long parentId) throws UnknownCompanyException, SQLException {
		return new CompanyRepository().lazyGet(parentId);
	}

	public boolean delete(long id) throws UnknownCompanyException, SQLException {
		Company delCompany;
		boolean isDeleted;
		List<Company> children = this.getChildren(id);
		delCompany = get(id);
		for (Company c : children) {
			c.setParent(delCompany.getParent());
			this.update(c);
		}
		isDeleted = new CompanyRepository().delete(id);
		if (isDeleted && delCompany.getParent() != null) {
			CompanyUtils.calculateTotalEarnings(get(CompanyUtils.parentId(delCompany)));
		}
		return isDeleted;
	}

	public boolean update(Company company) {
		return new CompanyRepository().update(company);
	}

	public List<Company> getAll() throws UnknownCompanyException, SQLException {
		return new CompanyRepository().getAll();
	}

	public List<Company> getChildren(long id) throws SQLException {
		return new CompanyRepository().getChildren(id);
	}
}
