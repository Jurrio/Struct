package com.eliftech.jurimik.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public Company get(long id) throws SQLException, UnknownCompanyException {
		String query = "SELECT * FROM company WHERE id = " + id + ";";
		ResultSet rs = new Connector().executeQuery(query);
		if (!rs.next()) {
			rs.close();
			throw new UnknownCompanyException("No company with this id:" + id);
		}
		else {
			rs.first();
			Company result = CompanyConverter.convertCompanyFromResultSet(rs);
			rs.close();
			return result;
		}
	}

	public Company lazyGet(long id) throws SQLException, UnknownCompanyException {
		String query = "SELECT * FROM company WHERE id = " + id + ";";
		ResultSet rs = new Connector().executeQuery(query);
		if (!rs.next()) {
			rs.close();
			throw new UnknownCompanyException("No company with this id:" + id);
		} else {
			Company result = CompanyConverter.lazyConvertCompanyFromResultSet(rs);
			rs.close();
			return result;
		}
	}

	public List<Company> find(String pattern) throws SQLException {
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
			children.add(CompanyConverter.lazyConvertCompanyFromResultSet(rs));
		}
		return children;
	}

	public List<Company> getAll() throws SQLException, UnknownCompanyException {
		String query = "SELECT * FROM company;";
		ResultSet rs = new Connector().executeQuery(query);
		List<Company> companies = new ArrayList<>();
		while (rs.next()) {
			companies.add(CompanyConverter.convertCompanyFromResultSet(rs));
		}
		return companies;
	}
}
