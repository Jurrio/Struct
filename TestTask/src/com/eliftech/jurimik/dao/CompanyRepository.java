package com.eliftech.jurimik.dao;

import java.util.ArrayList;
import java.util.List;

import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;

public class CompanyRepository {
	
	private static List<Company> companies = new ArrayList<>(); //temporary solve. Will change to database
	
	private static long id = 0;
	
	public static boolean add(Company company) {
		if (companies.contains(company)) return false;
		company.setId(nextId());
		return companies.add(company);
	}
	
	public static Company get(long id) throws UnknownCompanyException {
		for (Company c : companies) {
			if (c.getId() == id) return c;
		}
		throw new UnknownCompanyException("This id: \"" + id + "\" not allowed for any company");
	}
	
	public static Company find(String name) throws UnknownCompanyException {
		for (Company c : companies) {
			if (c.getName().equals(name)) return c;
		}
		throw new UnknownCompanyException("This name: \"" + name + "\" not allowed for any company");
	}
	
	public static boolean delete(long id) {
		for (Company c : companies) {
			if (c.getId() == id) {
				companies.remove(c);
				return !companies.contains(c);
			}
		}
		return false;
	}

	private static long nextId() {
		return ++id;
	}
}
