package com.eliftech.jurimik.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eliftech.jurimik.constants.Messages;
import com.eliftech.jurimik.constants.Parameters;
import com.eliftech.jurimik.exception.IllegalFormatEarningsException;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.EarningsConverter;

@WebServlet("/UpdateCompanyServlet")
public class UpdateCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Parameters.MESSAGE, Messages.VOID);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Company company = CompanyService.get(Long.parseLong(request.getParameter(Parameters.COMPANY_ID)));
			company.setName(request.getParameter(Parameters.NAME));
			company.setParent(CompanyService.lazyGet(Long.parseLong(request.getParameter(Parameters.PARENT))));
			company.setEarnings(EarningsConverter.get(request.getParameter(Parameters.EARNINGS)));
			
			boolean isUpdated = CompanyService.update(company);
			
			if (isUpdated) {
				request.setAttribute(Parameters.MESSAGE, Messages.UPDATE_SUCCESS);
			} else {
				request.setAttribute(Parameters.MESSAGE, Messages.UPDATE_FAIL);
			}
			
			
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} catch (IllegalFormatEarningsException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);;
		}
	}

}
