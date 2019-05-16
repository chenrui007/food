package mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GeneratorServiceEntity {

  public static void main(String[] args) {
    GeneratorServiceEntity ge = new GeneratorServiceEntity();
    ge.generateCode();
  }

  @Test
  public void generateCode() {

    String packageName = "com.anoah.wedu.modules.sysweb.modules.sys"; //修改为代码生成的基础包
    boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
    String[] tableNames = new String[]{"article","article_info","article_message","category","user_attention","user_collection","user_info"};
    generateByTables(serviceNameStartWithI, packageName, tableNames);
  }

  private void generateByTables(boolean serviceNameStartWithI, String packageName,
      String... tableNames) {
    GlobalConfig config = new GlobalConfig();
    String dbUrl = "jdbc:mysql://106.12.208.111:3306/zhaoming";
    dbUrl = dbUrl + "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8";
    dbUrl = dbUrl + "&useSSL=false&serverTimezone=GMT%2B8";//mysql8版本需要加上serverTimezone
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.MYSQL)
        .setUrl(dbUrl)
        .setUsername("root")
        .setPassword("ChenRui!475369")
        .setDriverName(
            "com.mysql.cj.jdbc.Driver");//#mysql8开始是com.mysql.cj.jdbc.Driver，老版本是com.mysql.jdbc.Driver
    StrategyConfig strategyConfig = new StrategyConfig();
    strategyConfig
        .setCapitalMode(true)
        .setEntityLombokModel(false)
        .setDbColumnUnderline(true)
        .setNaming(NamingStrategy.underline_to_camel)
        .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
    String classPath = "D:\\tmp\\code"; //代码生成的地址
    String currentUser = System.getProperty("user.name"); //获取当前用户名
    System.out.println("Current user is " + currentUser);
    config.setActiveRecord(false)
        .setAuthor("ZhaoMing")
        .setOutputDir(classPath)
        .setFileOverride(true)
        .setServiceName("%sService")
        .setServiceImplName("%sServiceImpl");
    if (!serviceNameStartWithI) {
      config.setServiceName("%sService");
    }
    new AutoGenerator().setGlobalConfig(config)
        .setDataSource(dataSourceConfig)
        .setStrategy(strategyConfig)
        .setPackageInfo(
            new PackageConfig()
                .setParent(packageName)
                .setEntity("entity")

        ).execute();
  }

  private void generateByTables(String packageName, String... tableNames) {
    generateByTables(true, packageName, tableNames);
  }

  public String getProjectPath(String projectName) {
    String classPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
    int indexStart = classPath.indexOf(projectName);
    String prefix = "file:/";
    int indexStartB = classPath.indexOf(prefix);
    if (indexStartB >= 0) {
      indexStartB = indexStartB + prefix.length();
    }
    String projectPath = classPath.substring(indexStartB, indexStart + projectName.length());
    return projectPath;
  }
}
