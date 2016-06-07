package com.eliftech.jurimik.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eliftech.jurimik.constants.Messages;
import com.eliftech.jurimik.constants.Parameters;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.CompanyUtils;

@WebServlet("/ViewCompanyServlet")
public class ViewCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Company company = new CompanyService().get(Long.parseLong(request.getParameter(Parameters.COMPANY_ID)));
			Map<Company, Integer> nestedChildren = new LinkedHashMap<>();
			nestedChildren = CompanyUtils.nestedChildren(nestedChildren, company.getId(), 0);
			request.setAttribute(Parameters.COMPANY, company);
			request.setAttribute(Parameters.MAP_COMPANIES, nestedChildren);
		} catch (NumberFormatException | UnknownCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("view-company.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Company company = new CompanyService().get(Long.parseLong(request.getParameter(Parameters.COMPANY_ID)));
			List<Company> childCompanies = new CompanyService().getChildren(company.getId());
			request.setAttribute(Parameters.COMPANY, company);
			request.setAttribute(Parameters.MESSAGE, Messages.OK);
			request.setAttribute(Parameters.COMPANIES, childCompanies);
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute(Parameters.MESSAGE, Messages.VOID);
			request.getRequestDispatcher("view-company.jsp").forward(request, response);			 
		}
	}

}
