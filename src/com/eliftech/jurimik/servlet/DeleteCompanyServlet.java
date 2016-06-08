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
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.service.CompanyService;

@WebServlet("/DeleteCompanyServlet")
public class DeleteCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute(Parameters.MESSAGE, Messages.VOID);
			request.setAttribute(Parameters.COMPANIES, new CompanyService().getAll());
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, Messages.LIST_ALL_ERROR + e.getMessage());
			// e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute(Parameters.MESSAGE, Messages.SQL_ERROR + e.getMessage());
			// e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter(Parameters.COMPANY_ID));

			boolean isDeleted = new CompanyService().delete(id);

			if (isDeleted) {
				request.setAttribute(Parameters.MESSAGE, Messages.DELETE_SUCCESS);
			} else {
				request.setAttribute(Parameters.MESSAGE, Messages.DELETE_FAIL);
			}

			request.setAttribute(Parameters.COMPANIES, new CompanyService().getAll());
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
			// e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute(Parameters.MESSAGE, Messages.SQL_ERROR + e.getMessage());
			// e.printStackTrace();
		}

	}

}
