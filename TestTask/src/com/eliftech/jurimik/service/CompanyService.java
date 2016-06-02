package com.eliftech.jurimik.service;

import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.repository.CompanyRepository;

import java.util.List;

import com.eliftech.jurimik.exception.UnknownCompanyException;

public class CompanyService {

	public static boolean add(Company company) {
		return CompanyRepository.add(company);
	}

	public static List<Company> find(String parameter) throws UnknownCompanyException {
		return CompanyRepository.find(parameter);
	}

	public static Company get(long id) throws UnknownCompanyException {
		return CompanyRepository.get(id);
	}
	
	public static Company lazyGet(long parentId) {
		return CompanyRepository.lazyGet(parentId);
	}

	public static boolean delete(long id) {
		Company delCompany;
		try {
			delCompany = get(id);
			for (Company c : CompanyService.getChildren(delCompany)) {
				c.setParent(delCompany.getParent());
				CompanyService.update(c);
			}
		} catch (UnknownCompanyException e) {
			return false;
		}
		return CompanyRepository.delete(id);
	}

	public static boolean update(Company company) {
		return CompanyRepository.update(company);
	}

	public static List<Company> getAll() {
		return CompanyRepository.getAll();
	}

	public static List<Company> getChildren(Company company) {
		return CompanyRepository.getChildren(company);
	}
}
