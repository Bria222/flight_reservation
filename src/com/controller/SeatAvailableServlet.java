package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.DataConnection;

/**
 * Servlet implementation class SeatAvailableServlet
 */
@WebServlet("/SeatAvailableServlet")
public class SeatAvailableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String f_number;
	String date;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		f_number=request.getParameter("inputFlightCode");
		date= request.getParameter("inputFlightDate");
		DataConnection dc = new DataConnection();
		ResultSet rs= dc.seatAvailability(f_number,date);
		try {
			if(rs.next())
			{rs.previous();
				request.setAttribute("Result", rs);
			}
			else
			{
				request.setAttribute("Result", "Invalid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("seatCheck.jsp");
		dispatch.forward(request, response);
	}

}
