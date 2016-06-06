package com.eliftech.jurimik.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	public static long parentId(Company company) {
		return (company.getParent() == null) ? 0 : company.getParent().getId();
	}
	
	public static Map<Company, Integer> nestedChildren(Map<Company, Integer> result, long id, int nested) throws UnknownCompanyException {
		List<Company> children = new CompanyService().getChildren(id);
		nested++;
		for (Company child : children) {
			result.put(child, nested);
			System.out.println(child.getId() + " " + child.getName());
			result.putAll(nestedChildren(result, child.getId(), nested));
		}
		nested--;
		
		return result;
	}

}
