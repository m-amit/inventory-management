package com.inventory.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.management.exception.IMSException;
import com.inventory.management.model.DateRange;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductResponse;
import com.inventory.management.model.ProductsAddedRemoved;
import com.inventory.management.model.Response;
import com.inventory.management.service.IMSServices;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("ims")
public class IMSRestController {

  private static final Logger logger = LoggerFactory.getLogger(IMSRestController.class);

  @Autowired
  private IMSServices imsServices;

  @PostMapping("add-product")
  @ApiOperation(value = "Adds new products to system", response = Response.class)
  public Response addProduct(@RequestBody Product product) {
    logger.info("Entering IMSRestController :: {}{}", "addProduct method Req payload ->", product);
    Response response = null;
    try {
      imsServices.addProduct(product);
      response = getSuccessResponse("Succesfully added product");
    } catch (IMSException e) {
      logger.error("Error in IMSRestController :: addProduct -> {}", e);
      response = getErrorResponse(e.getStatusMessage());
    }
    logger.info("Exiting IMSRestController :: {}", "addProduct method");
    return response;
  }

  @GetMapping("remove-product/{productName}")
  @ApiOperation(value = "removes added product from system by productId", response = Response.class)
  public Response removeProduct(@PathVariable("productName") String productName) {
    logger.info("Entering IMSRestController :: {}{}", "removeProduct method productId ->", productName);
    Response response = null;
    try {
      imsServices.removeProduct(productName);
      response = getSuccessResponse("Succesfully removed product");
    } catch (IMSException e) {
      logger.error("Error in IMSRestController :: removeProduct -> {}", e);
      response = getErrorResponse(e.getStatusMessage());
    }
    logger.info("Exiting IMSRestController :: {}", "removeProduct method");
    return response;
  }

  @GetMapping("view-product-detail/{productName}")
  @ApiOperation(value = "gets product details by productName", response = ProductResponse.class)
  public ProductResponse viewProduct(@PathVariable("productName") String productName) {
    logger.info("Entering IMSRestController :: {}{}", "viewProduct method productId ->", productName);
    ProductResponse response = null;
    try {
      Product product = imsServices.viewProduct(productName);
      response = new ProductResponse();
      response.setProductDetails(product);
      response.setResponse(getSuccessResponse("Succesfully fetched product"));
    } catch (IMSException e) {
      logger.error("Error in IMSRestController :: viewProduct -> {}", e);
      response = new ProductResponse();
      response.setResponse(getErrorResponse(e.getStatusMessage()));
    }
    logger.info("Exiting IMSRestController :: {}", "viewProduct method");
    return response;
  }

  @PostMapping("fetch-all-added-removed")
  @ApiOperation(value = "gets all product added and removed between dates", response = ProductResponse.class)
  public ProductResponse fetchProductInTimeRange(@RequestBody DateRange dateRange) {
    logger.info("Entering IMSRestController :: {}{}", "fetchProductInTimeRange method payload ->", dateRange);
    ProductResponse response = null;
    try {
      List<ProductsAddedRemoved> products = imsServices.fetchProductbetween(dateRange);
      response = new ProductResponse();
      response.setProductList(products);
      response.setResponse(getSuccessResponse("Succesfully fetched product"));
    } catch (IMSException e) {
      logger.error("Error in IMSRestController :: viewProduct -> {}", e);
      response = new ProductResponse();
      response.setResponse(getErrorResponse(e.getStatusMessage()));
    }
    logger.info("Exiting IMSRestController :: {}", "viewProduct method");
    return response;
  }

  private Response getSuccessResponse(String responseMessage){
    Response response = new Response();
    response.setResponseCode("Success");
    response.setResponseMessage(responseMessage);
    return response;
  }

  private Response getErrorResponse(String responseMessage){
    Response response = new Response();
    response.setResponseCode("Failure");
    response.setResponseMessage(responseMessage);
    return response;
  }
}
