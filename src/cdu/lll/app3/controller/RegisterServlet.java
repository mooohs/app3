package cdu.lll.app3.controller;

import cdu.lll.app3.model.Customer;
import cdu.lll.app3.service.CustomerService;
import cdu.lll.app3.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    CustomerService customerService=new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Customer customer=new Customer();
        customer.setName(req.getParameter("name"));
        customer.setPassword(req.getParameter("password"));

        if (customerService.add(customer)){
            resp.sendRedirect("login.do");
        //    req.getRequestDispatcher("login.do").forward(req,resp);
        }else {
            resp.sendRedirect("list");
        //    req.getRequestDispatcher("register.do").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
