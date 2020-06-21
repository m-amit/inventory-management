package com.inventory.management.service.impl;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.inventory.management.enums.AddedOrRemoved;
import com.inventory.management.exception.IMSException;
import com.inventory.management.model.DateRange;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductsAddedRemoved;
import com.inventory.management.service.IMSServices;

@Service
public class IMSServiceImpl implements IMSServices {
  private static final Logger logger = LoggerFactory.getLogger(IMSServiceImpl.class);

  private Map<String, ProductsAddedRemoved> addedProduct = null;

  private TreeMap<Date, String> productTimeTree = null;

  @Override
  public void addProduct(Product product) throws IMSException {
    logger.info("Entering IMSServiceImpl :: addProduct");
    if (null == addedProduct) {
      addedProduct = new ConcurrentHashMap<>();
    }
    if(null == productTimeTree){
      productTimeTree = new TreeMap<>();
    }
    if (null == product.getProductName() || product.getProductName().isEmpty()) {
      throw new IMSException("Product name should not be null or empty");
    }
    if (addedProduct.containsKey(product.getProductName())) {
      ProductsAddedRemoved existingProduct = addedProduct.get(product.getProductName());
      if (existingProduct.getProduct().equals(product)) {
        throw new IMSException("Product has already been added please try adding different product");
      }
    }
    addedProduct.put(product.getProductName(), new ProductsAddedRemoved(product, AddedOrRemoved.ADDED));
    productTimeTree.put(new Date(), product.getProductName());
    logger.info("Exiting IMSServiceImpl :: addProduct");
  }

  @Override
  public void removeProduct(String productName) throws IMSException {
    logger.info("Entering IMSServiceImpl :: removeProduct");
    if (null == addedProduct) {
      throw new IMSException("No Product is present with us");
    }
    if (null == productName || productName.isEmpty()) {
      throw new IMSException("Product name should not be null or empty");
    }
    if (!addedProduct.containsKey(productName)) {
      throw new IMSException("Product is not present with us");
    }
    addedProduct.get(productName).makeRemoved(AddedOrRemoved.REMOVED);
    logger.info("Exiting IMSServiceImpl :: removeProduct");
  }

  @Override
  public Product viewProduct(String productName) throws IMSException {
    logger.info("Entering IMSServiceImpl :: viewProduct");
    if(null == addedProduct || !addedProduct.containsKey(productName)){
      throw new IMSException("Product is not present with us");
    }
    ProductsAddedRemoved productsAddedRemoved = addedProduct.get(productName);
    if(productsAddedRemoved.getType().equals(AddedOrRemoved.REMOVED)){
      throw new IMSException("Product is not present with us");
    }
    logger.info("Exiting IMSServiceImpl :: viewProduct");
    return productsAddedRemoved.getProduct();
  }

  @Override
  public List<ProductsAddedRemoved> fetchProductbetween(DateRange dateRange) throws IMSException {
    logger.info("Entering IMSServiceImpl :: fetchProductbetween");
    if (null == productTimeTree) {
      throw new IMSException("No product is present with us");
    }
    List<ProductsAddedRemoved> productList = new ArrayList<>();
    Date fromDate = Date.from(dateRange.getFromDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date toDate = Date.from(dateRange.getToDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    Collection<String> productName = productTimeTree.subMap(fromDate, true, toDate, true).values();
    for (String tmp : productName) {
      productList.add(addedProduct.get(tmp));
    }
    if(CollectionUtils.isEmpty(productList)){
      throw new IMSException("No product is added or removed between "+ dateRange.getFromDate() + ", "+ dateRange.getToDate());
    }
    logger.info("Exiting IMSServiceImpl :: fetchProductbetween");
    return productList;
  }
}
