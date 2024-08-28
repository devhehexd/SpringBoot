package com.example.jpatest.product;

import com.example.jpatest.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    //등록, 수정
    public ProductDto save(ProductDto productDto) {
        Product entity = productDao.save(new Product(productDto.getNum(), productDto.getName(),
                productDto.getPrice(), productDto.getAmount(),
                productDto.getId(), productDto.getImg()));
        return new ProductDto(entity.getNum(), entity.getName(), entity.getPrice(),
                entity.getAmount(), entity.getId(), entity.getImg(), null);
    }

    public ProductDto getProduct(int num) {
        Product product = productDao.findById(num).orElse(null);
        if (product != null) {
            return new ProductDto(product.getNum(), product.getName(), product.getPrice(),
                    product.getAmount(), product.getId(), product.getImg(), null);
        }
        return null;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> tmp = productDao.findAll();
        List<ProductDto> productList = new ArrayList<>();
        for (Product entitiy : tmp) {
            productList.add(new ProductDto(entitiy.getNum(), entitiy.getName(),
                    entitiy.getPrice(), entitiy.getAmount(),
                    entitiy.getId(), entitiy.getImg(), null));
        }
        return productList;
    }

    public void deleteProduct(int num) {
        productDao.deleteById(num);
    }

    public List<ProductDto> getBySeller(String id) {
        List<Product> tmp = productDao.findById(new Member(id, "", "", "", ""));
        List<ProductDto> productList = new ArrayList<>();
        for (Product entity : tmp) {
            productList.add(new ProductDto(entity.getNum(), entity.getName(),
                    entity.getPrice(), entity.getAmount(),
                    entity.getId(), entity.getImg(), null));
        }
        return productList;
    }

    public List<ProductDto> getByPrice(int p1, int p2) {
        List<Product> tmp = productDao.findByPriceBetween(p1, p2);
        List<ProductDto> productList = new ArrayList<>();
        for (Product entity : tmp) {
            productList.add(new ProductDto(entity.getNum(), entity.getName(),
                    entity.getPrice(), entity.getAmount(),
                    entity.getId(), entity.getImg(), null));
        }
        return productList;
    }

    public List<ProductDto> getByProductName(String name) {
        List<Product> tmp = productDao.findByNameLike("%" + name + "%");
        List<ProductDto> productList = new ArrayList<>();
        for (Product entity : tmp) {
            productList.add(new ProductDto(entity.getNum(), entity.getName(),
                    entity.getPrice(), entity.getAmount(),
                    entity.getId(), entity.getImg(), null));
        }
        return productList;
    }

}
