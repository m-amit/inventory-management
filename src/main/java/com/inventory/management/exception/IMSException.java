package com.inventory.management.exception;

public class IMSException extends Exception {

  private static final long serialVersionUID = -553040917983022464L;

  private String statusMessage;

  public IMSException(String statusMessage){
    super(statusMessage);
    this.statusMessage = statusMessage;
  }

  public String getStatusMessage() {
    return statusMessage;
  }
}
