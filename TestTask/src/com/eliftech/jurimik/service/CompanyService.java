package com.eliftech.jurimik.service;

import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.repository.CompanyRepository;

import java.util.List;

import com.eliftech.jurimik.exception.UnknownCompanyException;

public class CompanyService {

	public static void add(Company company) {
		CompanyRepository.add(company);
	}

	public static Company find(String parameter) throws UnknownCompanyException {
		return CompanyRepository.find(parameter);
	}

	public static Company get(long id) throws UnknownCompanyException {
		return CompanyRepository.get(id);
	}

	public static void delete(long id) {
		CompanyRepository.delete(id);
	}

	public static void update(Company company) {
		CompanyRepository.update(company);
	}

	public static List<Company> getAll() {
		return CompanyRepository.getAll();
	}

	public static List<Company> getChild(Company company) {
		return CompanyRepository.getChildren(company);
	}

}
