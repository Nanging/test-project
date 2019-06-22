package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Product;
import com.sd.demo.entity.ProductState;
@Repository
public interface ProductStateDao extends JpaRepository<ProductState,Long> {

}
