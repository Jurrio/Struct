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

@WebServlet("/DatabaseCheckServlet")
public class DatabaseCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Parameters.MESSAGE, Messages.VOID);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			new CompanyService().getAll();
			request.setAttribute(Parameters.MESSAGE, Messages.OK);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (UnknownCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
