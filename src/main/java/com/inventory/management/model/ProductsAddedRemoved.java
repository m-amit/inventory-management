package com.inventory.management.model;

import java.io.Serializable;

import com.inventory.management.enums.AddedOrRemoved;

public class ProductsAddedRemoved implements Serializable {

  private static final long serialVersionUID = -1662848555812033904L;

  private Product product;
  private AddedOrRemoved type;

  public ProductsAddedRemoved(Product product, AddedOrRemoved addedOrRemoved) {
    this.product = product;
    type = addedOrRemoved;
  }

  public void makeRemoved(AddedOrRemoved removed){
    this.type = removed;
  }

  public Product getProduct() {
    return product;
  }

  public AddedOrRemoved getType() {
    return type;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("ProductsAddedRemoved{");
    sb.append("product=").append(product);
    sb.append(", type=").append(type);
    sb.append('}');
    return sb.toString();
  }
}
