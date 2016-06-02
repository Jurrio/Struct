package com.eliftech.jurimik.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eliftech.jurimik.builder.CompanyBuilder;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;

public class CompanyConverter {

	public static Company convertCompanyFromResultSet(ResultSet rs) throws SQLException, UnknownCompanyException {
		String name = rs.getString("name");
		long earnings = rs.getLong("earnings");
		CompanyBuilder foundCompany = new CompanyBuilder(name, earnings).id(rs.getLong("id")).parent(CompanyService.lazyGet(rs.getLong("parent"))).children(CompanyService.getChildren(rs.getLong("id")));
		
		return foundCompany.build();
	}


}
