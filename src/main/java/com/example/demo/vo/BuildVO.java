package com.example.demo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.io.Serializable;
import lombok.Data;

@Data
public class BuildVO implements Serializable {

  @Excel(name = "总价", isImportField = "true")
  private Integer totalPrice;
  @Excel(name = "面积", isImportField = "true")
  private Double area;
  @Excel(name = "楼层", isImportField = "true")
  private Integer floor;
  @Excel(name = "栋", isImportField = "true")
  private Integer no;
  @Excel(name = "单元", isImportField = "true")
  private Integer unit;
  @Excel(name = "房号", isImportField = "true")
  private Integer number;
}
