package cdu.lll.app3.service.impl;

import cdu.lll.app3.dao.ProductDao;
import cdu.lll.app3.dao.impl.ProductDaoImpl;
import cdu.lll.app3.model.Product;
import cdu.lll.app3.service.ProductService;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao=new ProductDaoImpl();

    @Override
    public List<Product> findByPage(int page, int pageSize) {
        return productDao.findByPage((page-1)*pageSize,pageSize);
    }

    @Override
    public List<Product> findByPage(String sPage, String sPageSize) {
        int page=1;
        int pageSize=10;
        if (sPage!=null||!sPage.equals("")){
            page=Integer.parseInt(sPage);
            if (page<1){
                page=1;
            }
        }
        if (sPageSize!=null||!sPageSize.equals("")){
            pageSize=Integer.parseInt(sPageSize);
        }
        return findByPage(page,pageSize);
    }

    @Override
    public List<Product> findByPage(String sPage) {
        return findByPage(sPage,null);
    }

    @Override
    public Product get(String sid) {
        if (sid==null||sid.equals("")){
            return null;
        }
        return get(Integer.parseInt(sid));
    }

    @Override
    public Product get(int id) {
        return productDao.get(id);
    }

    @Override
    public int count() {
        return productDao.count();
    }

    @Override
    public boolean add(Product product) {
        if (product==null){
            return false;
        }
        return productDao.insert(product)==1?true:false;
    }

    @Override
    public boolean del(String sid) {
        if (sid==null||sid.equals("")){
            return false;
        }
        return productDao.delete(Integer.parseInt(sid))==1?true:false;
    }

    @Override
    public boolean mod(Product product) {
        if (product == null) {
            return false;
        }
        return productDao.update(product)==1?true:false;
    }

    @Override
    public boolean updateStock(Map<Integer, Integer> products) {//库存
        if (products==null||products.isEmpty()){
            return false;
        }
        for (int pId:products.keySet()){
            Product product=get(pId);
            product.setStock(product.getStock()-products.get(pId));
        }
        return false;
    }
}
