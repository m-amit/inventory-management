package com.inventory.management.service;

import java.util.List;

import com.inventory.management.exception.IMSException;
import com.inventory.management.model.DateRange;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductsAddedRemoved;

/**
 * The interface Ims services.
 */
public interface IMSServices {

  /**
   * Add product.
   *
   * @param product the product
   * @throws IMSException the ims exception
   */
  void addProduct(Product product) throws IMSException;

  /**
   * Remove product.
   *
   * @param productId the product id
   * @throws IMSException the ims exception
   */
  void removeProduct(String productId) throws IMSException;

  /**
   * View product product.
   *
   * @param productId the product id
   * @return the product
   * @throws IMSException the ims exception
   */
  Product viewProduct(String productId) throws IMSException;

  /**
   * Fetch productbetween list.
   *
   * @param dateRange the date range
   * @return the list
   * @throws IMSException the ims exception
   */
  List<ProductsAddedRemoved> fetchProductbetween(DateRange dateRange) throws IMSException;
}
