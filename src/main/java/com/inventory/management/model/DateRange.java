package com.inventory.management.model;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DateRange implements Serializable {
  private static final long serialVersionUID = -8035592468458397629L;

  @ApiModelProperty(value = "from date", example = "2020-06-21")
  private LocalDate fromDate;

  @ApiModelProperty(value = "from date", example = "2020-06-21")
  private LocalDate toDate;

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DateRange{");
    sb.append("fromDate=").append(fromDate);
    sb.append(", toDate=").append(toDate);
    sb.append('}');
    return sb.toString();
  }
}
