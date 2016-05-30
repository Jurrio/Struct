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
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;

@WebServlet("/ListCompanyServlet")
public class ListCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = CompanyService.getAll();
		request.setAttribute(Parameters.COMPANIES, companies);
		request.setAttribute(Parameters.MESSAGE, Messages.VOID);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

}
