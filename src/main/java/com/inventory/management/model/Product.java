package com.inventory.management.model;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel
public class Product implements Serializable {
  private static final long serialVersionUID = 1916240080499108109L;

  @ApiModelProperty(value = "product name", example = "laptop")
  private String productName;

  @ApiModelProperty(value = "product color", example = "black")
  private String productColor;

  @ApiModelProperty(value = "product price", example = "india")
  private String productOrigin;

  @ApiModelProperty(value = "product name", example = "xyz")
  private String productBrand;

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductColor() {
    return productColor;
  }

  public void setProductColor(String productColor) {
    this.productColor = productColor;
  }

  public String getProductOrigin() {
    return productOrigin;
  }

  public void setProductOrigin(String productOrigin) {
    this.productOrigin = productOrigin;
  }

  public String getProductBrand() {
    return productBrand;
  }

  public void setProductBrand(String productBrand) {
    this.productBrand = productBrand;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Product{");
    sb.append("productName='").append(productName).append('\'');
    sb.append(", productColor='").append(productColor).append('\'');
    sb.append(", productPrice='").append(productOrigin).append('\'');
    sb.append(", productBrand='").append(productBrand).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Product product = (Product) o;
    return Objects.equals(productName, product.productName) &&
      Objects.equals(productColor, product.productColor) &&
      Objects.equals(productOrigin, product.productOrigin) &&
      Objects.equals(productBrand, product.productBrand);
  }

  @Override public int hashCode() {
    return Objects.hash(productName, productColor, productOrigin, productBrand);
  }
}
