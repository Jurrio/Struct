package com.eliftech.jurimik.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eliftech.jurimik.builder.CompanyBuilder;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;

public class CompanyRepository {
		
	public static boolean add(Company company) {
		String query = "INSERT INTO company (name, earnings, parent) VALUES ('" + company.getName() + 
				"', '" + company.getEarnings() + "', '" + company.getParentId() + "');";
		if (Connector.executeUpdate(query) > 0) return true;
		return false;
	}
	
	public static Company get(long id) throws UnknownCompanyException {
		String query = "SELECT * FROM company WHERE id = " + id + ";";
		ResultSet rs = Connector.executeQuery(query);
		try {
			while(rs.next()) {
				return CompanyBuilder.buildFromResultSet(rs);
			}
			rs.close();
		} catch (SQLException e) {
			
		}
		
		return new CompanyBuilder("null", 0).build(); //TODO temporary
		
	}
	
	public static List<Company> find(String pattern) throws UnknownCompanyException {
		String query = "SELECT * FROM company WHERE name, earnings LIKE '%" + pattern + "%';";
		ResultSet rs = Connector.executeQuery(query);
		List<Company> resultList = new ArrayList<>();
		try {
			while(rs.next()) {
				 resultList.add(CompanyBuilder.buildFromResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {
			
		}
		return resultList;
	}
	
	public static boolean delete(long id) {
		String query = "DELETE FROM company WHERE id = '" + id + "';";
		System.out.println(query);
		if (Connector.executeUpdate(query) > 0) return true;
		return false;
	}
	
	public static boolean update(Company company) {
		String query = "UPDATE company SET name = '" + company.getName() + "', earnings = '"
				+ company.getEarnings() + "', parent = '" + company.getParentId() + 
				"' WHERE id = '" + company.getId() + "';";
		if (Connector.executeUpdate(query) > 0) return true;
		return false;
	}
	
	public static List<Company> getChildren(Company parent) {
		String query = "SELECT * FROM company WHERE parent = " + parent.getId() + ";";
		ResultSet rs = Connector.executeQuery(query);
		List<Company> children = new ArrayList<>();
		try {
			while (rs.next()) {
				Company company = null;
				try {
					company = CompanyBuilder.buildFromResultSet(rs);
				} catch (UnknownCompanyException e) {
					e.printStackTrace();
				}
								
				children.add(company);
			}
		} catch (SQLException e) {
			
		}
		return children;
	}
	
	public static List<Company> getAll() {
		String query = "SELECT * FROM company;";
		ResultSet rs = Connector.executeQuery(query); 
		List<Company> companies = new ArrayList<>();
		try {
			while (rs.next()) {
				Company company = null;
				try {
					company = CompanyBuilder.buildFromResultSet(rs);
				} catch (UnknownCompanyException e) {
					e.printStackTrace();
				}
				
				companies.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}

}
