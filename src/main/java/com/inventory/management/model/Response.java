package com.inventory.management.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Response implements Serializable {

  private static final long serialVersionUID = -7295394672547783067L;

  @ApiModelProperty(value = " response code to identify failure or success", required = true)
  private String responseCode;

  @ApiModelProperty(value = "explanation of responseCode", required = true)
  private String responseMessage;

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Response{");
    sb.append("responseCode='").append(responseCode).append('\'');
    sb.append(", responseMessage='").append(responseMessage).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
