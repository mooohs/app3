package cdu.lll.app3.controller;

import cdu.lll.app3.model.Customer;
import cdu.lll.app3.service.CustomerService;
import cdu.lll.app3.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    CustomerService customerService=new CustomerServiceImpl();


    //get请求是显示请求，请求信息放在URL后，post请求是隐士请求，请求参数信息放在http协议的第四部分
    //get请求，URL显示请求信息，信息不安全，post请求，URL隐藏参数信息，信息安全
    //get请求URL有限制，可以传较小数据，post请求URL长度不变，信息存储在http协议第四部分，长度无限制，不会出现数据丢失
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name=req.getParameter("name");
        String password=req.getParameter("password");

        HttpSession session= req.getSession();
        Customer customer= customerService.get(name,password);
        if (customer !=null){
            session.setAttribute("customer",customer);
        }
        resp.sendRedirect("list");
    }
}
