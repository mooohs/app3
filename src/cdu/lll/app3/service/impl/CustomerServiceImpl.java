package cdu.lll.app3.service.impl;

import cdu.lll.app3.dao.CustomerDao;
import cdu.lll.app3.dao.impl.CustomerDaoImpl;
import cdu.lll.app3.model.Customer;
import cdu.lll.app3.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<Customer> findByPage(int page, int pageSize) {
        return customerDao.findByPage((page - 1) * pageSize, pageSize);
    }

    @Override
    public List<Customer> findByPage(String sPage, String sPageSize) {
        int page = 1;
        int pageSize = 10;
        if (sPage != null || !sPage.equals("")) {
            page = Integer.parseInt(sPage);
            if (page < 1) {
                page = 1;
            }
        }
        if (sPageSize != null || !sPageSize.equals("")) {
            pageSize = Integer.parseInt(sPageSize);
        }
        return findByPage(page, pageSize);
    }

    @Override
    public List<Customer> findByPage(String spage) {
        return findByPage(spage, null);
    }

    @Override
    public Customer get(String sid) {
        if (sid == null || sid.equals("")) {
            return null;
        }
        return customerDao.get(Integer.parseInt(sid));
    }

    @Override
    public Customer get(String name, String password) {
        return customerDao.get(name, password);
    }

    @Override
    public int count() {
        return customerDao.count();
    }

    @Override
    public boolean add(Customer customer) {
        if (customer == null) {
            return false;
        }
        return customerDao.insert(customer) == 1 ? true : false;
    }
}
