package com.eliftech.jurimik.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eliftech.jurimik.constants.Messages;
import com.eliftech.jurimik.constants.Parameters;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.SafeRemover;

@WebServlet("/DeleteCompanyServlet")
public class DeleteCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Parameters.MESSAGE, Messages.VOID);
		request.setAttribute(Parameters.COMPANIES, CompanyService.getAll());
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter(Parameters.COMPANY_ID));
		try {
			SafeRemover.changeParentCompany(CompanyService.get(id));
		} catch (UnknownCompanyException e) {
			System.out.println("!!!");
		}
		/*boolean isDeleted = */
		CompanyService.delete(id);
			
//		if (isDeleted) {
		request.setAttribute(Parameters.MESSAGE, Messages.DELETE_SUCCESS);
				
//			}
//			else request.setAttribute(Parameters.MESSAGE, Messages.DELETE_FAIL);
		request.setAttribute(Parameters.COMPANIES, CompanyService.getAll());
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		
	}

}
