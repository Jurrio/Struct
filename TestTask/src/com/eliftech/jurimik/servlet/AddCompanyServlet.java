package com.eliftech.jurimik.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eliftech.jurimik.builder.CompanyBuilder;
import com.eliftech.jurimik.constants.Messages;
import com.eliftech.jurimik.constants.Parameters;
import com.eliftech.jurimik.exception.IllegalFormatEarningsException;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.EarningsConverter;

@WebServlet("/AddCompanyServlet")
public class AddCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute(Parameters.MESSAGE, Messages.VOID);
			request.setAttribute(Parameters.COMPANIES, new CompanyService().getAll());
			request.getRequestDispatcher("add-company.jsp").forward(request, response);
		} catch (UnknownCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String name = request.getParameter(Parameters.NAME);
			long earnings = EarningsConverter.get(request.getParameter(Parameters.EARNINGS));
			long parentId = Long.parseLong(request.getParameter(Parameters.PARENT));
		
			CompanyBuilder cBuilder = new CompanyBuilder(name, earnings);
			
			if (parentId != 0) {
				cBuilder = cBuilder.parent(new CompanyService().lazyGet(parentId));
			}
		
			boolean isAdded = new CompanyService().add(cBuilder.build());
			if (isAdded) {
				request.setAttribute(Parameters.MESSAGE, Messages.ADD_SUCCESS);
			} else  {
				request.setAttribute(Parameters.MESSAGE, Messages.ADD_FAIL);
			}
			
			request.setAttribute(Parameters.COMPANIES, new CompanyService().getAll());
			
		} catch (IllegalFormatEarningsException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("add-company.jsp").forward(request, response);
		}
		
	}

}
