package com.gjw.mybatisplusgenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 郭经伟
 * @Date 2021/4/14
 * mybatis-plus自动生成器
 **/
public class GeneratorApp {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {

        String tableName=scanner("表名");


        //代码生成器
        AutoGenerator mpg=new AutoGenerator();

        //全局配置
        GlobalConfig gc=new GlobalConfig();
        //获得当前项目路径
        String projectPath=System.getProperty("user.dir");
        System.out.println(projectPath);
        //代码的输出位置
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("郭经伟");
        gc.setOpen(false);
        // 同文件生成覆盖
        gc.setFileOverride(true);
        gc.setEntityName("%s");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setIdType(IdType.AUTO);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);
        //全局配置设置到AutoGenerator里面
        // 数据源配置
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/intelligenttrafficdb?useSSL=false&&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        mpg.setDataSource(dataSourceConfig);
        //包配置
        PackageConfig pc=new PackageConfig();
        //模块名
        //pc.setModuleName("mybatisplusgenerator");
        //pc.setModuleName(moduleName);
        pc.setParent("com.gjw.intelligenttrafficbackend");
        pc.setMapper("dao");
        pc.setServiceImpl("impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        /**
         * 自定义输出设置
         */
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath +"/src/main/resources/mapper/" +  tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig=new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //策略设置
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略 pms_product--PmsProduct
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 列名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        // 实体使用Lombok
        strategy.setEntityLombokModel(true);
        // 是否使用@RestController注解
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        // 生成的表名
        strategy.setInclude(tableName);
        // 驼峰转连接符
        // strategy.setControllerMappingHyphenStyle(true);
        // 设置表前缀
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}
