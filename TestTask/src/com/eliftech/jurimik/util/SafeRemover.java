package com.eliftech.jurimik.util;

import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;

public class SafeRemover {

	public static void changeParentCompany(Company company) {
		for (Company c : CompanyService.getChild(company)) {
			c.setParentId(company.getParentId());
			c.setParentName(company.getParentName());
			CompanyService.update(c);
		}
	}
}