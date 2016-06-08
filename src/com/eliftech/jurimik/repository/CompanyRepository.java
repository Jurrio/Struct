package com.eliftech.jurimik.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eliftech.jurimik.builder.CompanyBuilder;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.util.CompanyConverter;
import com.eliftech.jurimik.util.CompanyUtils;

public class CompanyRepository {

	public boolean add(Company company) {
		String query = "INSERT INTO company (name, earnings, totalEarnings, parent) VALUES ('" + company.getName()
				+ "', '" + company.getEarnings() + "', '" + company.getTotalEarnings() + "', '"
				+ CompanyUtils.parentId(company) + "');";
		if (new Connector().executeUpdate(query) > 0)
			return true;
		return false;
	}

	public Company get(long id) throws UnknownCompanyException, SQLException {
		String query = "SELECT * FROM company WHERE id = " + id + ";";
		ResultSet rs = new Connector().executeQuery(query);
		while (rs.next()) {
			return CompanyConverter.convertCompanyFromResultSet(rs);
		}
		rs.close();

		return new CompanyBuilder("null", 0).build(); // TODO temporary

	}

	public Company lazyGet(long id) throws SQLException {
		String query = "SELECT * FROM company WHERE id = " + id + ";";
		ResultSet rs = new Connector().executeQuery(query);
		while (rs.next()) {
			return CompanyConverter.lazyConvertCompanyFromResultSet(rs);
		}
		return null;
	}

	public List<Company> find(String pattern) throws UnknownCompanyException, SQLException {
		String query = "SELECT * FROM company WHERE name, earnings LIKE '%" + pattern + "%';";
		ResultSet rs = new Connector().executeQuery(query);
		List<Company> resultList = new ArrayList<>();
		while (rs.next()) {
			resultList.add(CompanyConverter.lazyConvertCompanyFromResultSet(rs));
		}
		rs.close();
		return resultList;
	}

	public boolean delete(long id) {
		String query = "DELETE FROM company WHERE id = '" + id + "';";
		System.out.println(query);
		if (new Connector().executeUpdate(query) > 0)
			return true;
		return false;
	}

	public boolean update(Company company) {
		String query = "UPDATE company SET name = '" + company.getName() + "', earnings = '" + company.getEarnings()
				+ "', totalEarnings = '" + company.getTotalEarnings() + "', parent = '" + CompanyUtils.parentId(company)
				+ "' WHERE id = '" + company.getId() + "';";
		if (new Connector().executeUpdate(query) > 0)
			return true;
		return false;
	}

	public List<Company> getChildren(long id) throws SQLException {
		String query = "SELECT * FROM company WHERE parent = " + id + ";";
		ResultSet rs = new Connector().executeQuery(query);
		List<Company> children = new ArrayList<>();
		while (rs.next()) {
			Company company = null;
			company = CompanyConverter.lazyConvertCompanyFromResultSet(rs);

			children.add(company);
		}
		return children;
	}

	public List<Company> getAll() throws UnknownCompanyException, SQLException {
		String query = "SELECT * FROM company;";
		ResultSet rs = new Connector().executeQuery(query);
		List<Company> companies = new ArrayList<>();
		while (rs.next()) {
			Company company = null;
			company = CompanyConverter.convertCompanyFromResultSet(rs);

			companies.add(company);
		}
		return companies;
	}
}
