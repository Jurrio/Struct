package com.eliftech.jurimik.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eliftech.jurimik.constants.Messages;
import com.eliftech.jurimik.constants.Parameters;
import com.eliftech.jurimik.exception.SearchEmptyException;
import com.eliftech.jurimik.exception.SearchManyParamException;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.ParamUtils;

@WebServlet("/SearchCompanyServlet")
public class SearchCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String searchValue = ParamUtils.checkSearchValue(request.getParameter(Parameters.SEARCH));
			List<Company> findResult = new CompanyService().find(searchValue);
			
			if (!findResult.isEmpty()) {
				request.setAttribute(Parameters.COMPANIES, findResult);
				request.setAttribute(Parameters.MESSAGE, Messages.LIST_OF_COMPANIES);
			} else {
				request.setAttribute(Parameters.MESSAGE, Messages.EMPTY_LIST);
			}

		} catch (SearchManyParamException | SearchEmptyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
	}

}
