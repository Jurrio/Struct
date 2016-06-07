package com.eliftech.jurimik.util;

import java.sql.SQLException;
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
		return (company.getParent() == null) ? -1 : company.getParent().getId();
	}
	
	public static Map<Company, Integer> nestedChildren(Map<Company, Integer> result, long id, int nested) throws UnknownCompanyException, SQLException {
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
	
	public static long calculateTotalEarnings(Company company) throws SQLException {
		List<Company> children = new CompanyService().getChildren(company.getId());
		long result = company.getEarnings();
		for (Company child : children) {
			result += calculateTotalEarnings(child);
		}
		return result;
	}

	public static void updateParentTotalEarnings(Company company) throws UnknownCompanyException, SQLException {
		long id;
		if ((id = parentId(company)) > 0) {
			Company parent = new CompanyService().get(id);
			long total = parent.getEarnings() + CompanyUtils.calculateTotalEarnings(company);
			parent.setTotalEarnings(total);
			new CompanyService().update(parent);
			updateParentTotalEarnings(parent);
		}
		// TODO Auto-generated method stub
		
	}

}
