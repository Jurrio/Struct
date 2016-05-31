package com.eliftech.jurimik.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eliftech.jurimik.builder.CompanyBuilder;
import com.eliftech.jurimik.constants.Messages;
import com.eliftech.jurimik.constants.Parameters;
import com.eliftech.jurimik.exception.IllegalFormatEarningsException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.EarningsConverter;

/**
 * Servlet implementation class AddCompanyServlet
 */
@WebServlet("/AddCompanyServlet")
public class AddCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Parameters.MESSAGE, Messages.VOID);
		request.setAttribute(Parameters.COMPANIES, CompanyService.getAll());
		request.getRequestDispatcher("add-company.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String name = request.getParameter(Parameters.NAME);
			long earnings = EarningsConverter.get(request.getParameter(Parameters.EARNINGS));
			long parentCompany = Long.parseLong(request.getParameter(Parameters.PARENT));
		
			Company company = new CompanyBuilder(name, earnings).parent(parentCompany).build();
		
//			boolean isAdded = 
			CompanyService.add(company);
//			if (isAdded) 
			request.setAttribute(Parameters.MESSAGE, Messages.ADD_SUCCESS);
//			else request.setAttribute(Parameters.MESSAGE, Messages.ADD_FAIL);
		} catch (IllegalFormatEarningsException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} finally {
			request.setAttribute(Parameters.COMPANIES, CompanyService.getAll());
			request.getRequestDispatcher("add-company.jsp").forward(request, response);
		}
		
	}

}