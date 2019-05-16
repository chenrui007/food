package com.example.demo.web;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.entity.Build;
import com.example.demo.service.IBuildService;
import com.example.demo.vo.BuildVO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p> 前端控制器 </p>
 *
 * @author ChenRui
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/templates/build")
public class BuildController {

  @Autowired
  private IBuildService buildService;

  @PostMapping("/save")
  public R save(@RequestParam(value = "file") MultipartFile file) {
    ImportParams params = new ImportParams();
    params.setTitleRows(1);
    params.setHeadRows(1);
    List<BuildVO> buildVOList;
    try {
      buildVOList = ExcelImportUtil.importExcel(
          file.getInputStream(),
          BuildVO.class, params);
    } catch (Exception e) {
      return R.failed("文件内容错误，请重新上传");
    }
    List<Build> buildList = new ArrayList<>();
    buildVOList.stream().forEach(it -> {
      Build build = new Build();
      build.setTotalPrice(it.getTotalPrice());
      build.setArea(it.getArea());
      DecimalFormat df = new DecimalFormat("#.00");
      build.setUnitPrice(String.valueOf(df.format(it.getTotalPrice() / it.getArea())));
      build.setDownPayments(String.valueOf(it.getTotalPrice() * 0.3));
      build.setFloor(it.getFloor());
      double monthRate = 5.635 / (100 * 12);//月利率
      double preLoan = (it.getTotalPrice() * 0.7 * monthRate * Math.pow((1 + monthRate), 360)) / (
          Math.pow((1 + monthRate), 360) - 1);//每月还款金额
      build.setMonthlySupply(String.valueOf(df.format(preLoan)));
      build.setProperty(String.valueOf(it.getArea() * 2.18));
      build.setNo(it.getNo());
      build.setUnit(it.getUnit());
      build.setNumber(it.getNumber());
      buildList.add(build);
    });
    return buildService.saveBatch(buildList) ? R.ok("") : R.failed("");
  }

  @GetMapping("/list")
  public R list() {
    List<Build> buildList = buildService
        .list(new LambdaQueryWrapper<Build>().orderByDesc(Build::getFloor));
    return R.ok(buildList);
  }
}
