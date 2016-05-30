package com.eliftech.jurimik.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/ViewCompanyServlet")
public class ViewCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Company company = CompanyService.get(Long.parseLong(request.getParameter(Parameters.COMPANY_ID)));
			List<Company> childCompanies = CompanyService.getChild(company);
			request.setAttribute(Parameters.COMPANY, company);
			request.setAttribute(Parameters.MESSAGE, Messages.OK);
			request.setAttribute(Parameters.COMPANIES, childCompanies);
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} finally {
			request.setAttribute(Parameters.MESSAGE, Messages.VOID);
			request.getRequestDispatcher("view-company.jsp").forward(request, response);			 
		}
	}

}
