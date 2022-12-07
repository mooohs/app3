package cdu.lll.app3.controller;

import cdu.lll.app3.model.Customer;
import cdu.lll.app3.model.Product;
import cdu.lll.app3.service.CartService;
import cdu.lll.app3.service.ProductService;
import cdu.lll.app3.service.impl.CartServiceImpl;
import cdu.lll.app3.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class cartServlet extends HttpServlet {
    ProductService productService=new ProductServiceImpl();
    CartService cartService=new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要顾客已登录
        HttpSession session= req.getSession();
        Customer customer=(Customer) session.getAttribute("customer");
        if (customer==null){
            resp.sendRedirect("login.do");
            return;
        }

        //获取顾客的购物车
        Map<Integer,Integer>map=cartService.getProducts(customer.getId());
        Map<Integer, Product>productMap=new HashMap<>();
        Map<Integer,Integer>productNumMap=new HashMap<>();

        BigDecimal money=new BigDecimal(0);
        int i=0;
        for (int productId: map.keySet()){
            Product product=productService.get(productId);
            int buyNum=map.get(productId);//数量
            productMap.put(++i,product);
            productNumMap.put(i,buyNum);
            money=money.add(product.getSalePrice().multiply(new BigDecimal(buyNum)));//两数相乘
        }

        session.setAttribute("products",map);
        session.setAttribute("productMap",productMap);
        session.setAttribute("productNumMap",productNumMap);
        session.setAttribute("money",money);




    }
}
