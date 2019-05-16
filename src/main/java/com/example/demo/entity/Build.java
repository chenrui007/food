package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-22
 */
@Data
@Accessors(chain = true)
public class Build {

  private static final long serialVersionUID = 1L;

  private Integer floor;

  @TableField("total_price")
  private Integer totalPrice;

  private Double area;

  @TableField("unit_price")
  private String unitPrice;

  @TableField("down_payments")
  private String downPayments;

  @TableField("monthly_supply")
  private String monthlySupply;

  private String property;

  private Integer no;
  private Integer unit;

  private Integer number;


}
