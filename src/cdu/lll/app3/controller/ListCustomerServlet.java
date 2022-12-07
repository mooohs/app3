package cdu.lll.app3.controller;

import cdu.lll.app3.model.Customer;
import cdu.lll.app3.model.Product;
import cdu.lll.app3.service.CustomerService;
import cdu.lll.app3.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/list")
public class ListCustomerServlet extends HttpServlet {

    CustomerService customerService=new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sPage=req.getParameter("page");
        int page=1;
        int pageSize=10;
        if (sPage!=null&&!sPage.equals("")){
            page=Integer.parseInt(sPage);
        }
        int count=customerService.count();
        int pageCount=count%pageSize>0? count/pageSize+1:count/pageSize;
        List<Customer> customerList=customerService.findByPage(page,pageSize);
        req.setAttribute("customerList",customerList);
        req.setAttribute("page",page);
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("count",count);

        req.getRequestDispatcher("customer/list.do").forward(req,resp);
    }
}
