package com.eliftech.jurimik.util;

import java.util.List;

import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;

public class CompanyUtils {
	
	public static boolean isChildrenId(long id, long newParentId) throws UnknownCompanyException {
		Company company = new CompanyService().get(id);
		List<Company> children = company.getChildren();
		
		if (!children.isEmpty()) {
			for (Company child : children) {
				if (child.getId() == newParentId) return true;
				else isChildrenId(child.getId(), newParentId);
			}
		}
		
		return false;
		
	}

}
