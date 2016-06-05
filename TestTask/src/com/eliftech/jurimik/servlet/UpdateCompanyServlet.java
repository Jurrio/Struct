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
import com.eliftech.jurimik.exception.IllegalIdException;
import com.eliftech.jurimik.exception.UnknownCompanyException;
import com.eliftech.jurimik.model.Company;
import com.eliftech.jurimik.service.CompanyService;
import com.eliftech.jurimik.util.CompanyUtils;
import com.eliftech.jurimik.util.EarningsConverter;
import com.eliftech.jurimik.util.ParamUtils;

@WebServlet("/UpdateCompanyServlet")
public class UpdateCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Parameters.MESSAGE, Messages.VOID);
		long id = Long.parseLong(request.getParameter(Parameters.COMPANY_ID));
		try {
			Company company = new CompanyService().get(id);
			request.setAttribute(Parameters.COMPANY, company);
			request.setAttribute(Parameters.COMPANY_ID, company.getId());
			request.setAttribute(Parameters.COMPANIES, new CompanyService().getAll());
		} catch (NumberFormatException | UnknownCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("edit-company.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Company company = new CompanyService().get(Long.parseLong(request.getParameter(Parameters.COMPANY_ID)));
			
			String name = company.getName();
			long earnings = company.getEarnings();
			long parentId = (company.getParent() == null) ? 0 : company.getParent().getId();
			
			if (ParamUtils.isNotBlank(request.getParameter(Parameters.COMPANY))) {
				name = request.getParameter(Parameters.NAME);
			}
			if (ParamUtils.isNotBlank(request.getParameter(Parameters.EARNINGS))) {
				earnings = EarningsConverter.get(request.getParameter(Parameters.EARNINGS));
			}
			if (ParamUtils.isNotBlank(request.getParameter(Parameters.PARENT))) {
				long newParentId = Long.parseLong(request.getParameter(Parameters.PARENT));
				if (newParentId == company.getId()) {
					throw new IllegalIdException("This id is itself!");
				}
				if (!CompanyUtils.isChildrenId(company.getId(), newParentId)) {
					parentId = newParentId;
				}
			}
			
			Company updatedCompany = new CompanyBuilder(name, earnings).id(company.getId())
					.parent(new CompanyService().lazyGet(parentId)).build();
						
//			company.setName(request.getParameter(Parameters.NAME));
//			company.setEarnings(EarningsConverter.get(request.getParameter(Parameters.EARNINGS)));
			
		
			boolean isUpdated = new CompanyService().update(updatedCompany);
			
			if (isUpdated) {
				request.setAttribute(Parameters.MESSAGE, Messages.UPDATE_SUCCESS);
			} else {
				request.setAttribute(Parameters.MESSAGE, Messages.UPDATE_FAIL);
			}
			
			request.setAttribute(Parameters.COMPANIES, new CompanyService().getAll());
						
		} catch (UnknownCompanyException e) {
			request.setAttribute(Parameters.MESSAGE, e.getMessage());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalFormatEarningsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);;
		}
	}

}
