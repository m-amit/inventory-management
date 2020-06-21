package com.inventory.management.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable {

  private static final long serialVersionUID = -853156573530881327L;

  @ApiModelProperty(notes = "Common response type", required = true)
  private Response response;

  @ApiModelProperty(notes = "product detail for single product")
  private Product productDetails;

  @ApiModelProperty(notes = "List of added and removed products in a given time span")
  private List<ProductsAddedRemoved> productList;

  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  public Product getProductDetails() {
    return productDetails;
  }

  public void setProductDetails(Product productDetails) {
    this.productDetails = productDetails;
  }

  public List<ProductsAddedRemoved> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductsAddedRemoved> productList) {
    this.productList = productList;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("ProductResponse{");
    sb.append("response=").append(response);
    sb.append(", productDetails=").append(productDetails);
    sb.append(", productList=").append(productList);
    sb.append('}');
    return sb.toString();
  }
}
