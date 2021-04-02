package com.example.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

        /**
         * <p>
         * 读取控制台内容
         * </p>
         */
        public static String scanner(String tip) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder help = new StringBuilder();
            help.append("请输入要生成表名" + tip + "：");
            System.out.println(help.toString());
            if (scanner.hasNext()) {
                String ipt = scanner.next();
                if (StringUtils.isNotEmpty(ipt)) {
                    return ipt;
                }
            }
            throw new MybatisPlusException("请输入正确的表名" + tip + "！");
        }

        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath =  "C://Users//Administrator//IdeaProjects"; //System.getProperty("user.dir");
            System.out.println("projectPath 路径："+projectPath);
            //生成文件位置
            gc.setOutputDir(projectPath + "/src/main/java");
            gc.setAuthor("chenzz");
            gc.setFileOverride(false); //第二次生成会把第一次生成的覆盖掉
            gc.setServiceName("%sService"); //生成的service接口名字首字母是否为I，这样设置就没有
            gc.setBaseResultMap(true); //生成resultMap
            gc.setIdType(IdType.AUTO); //设置主键策略为自增
            gc.setDateType(DateType.ONLY_DATE); //设置日期格式
             gc.setSwagger2(true); //实体属性 Swagger2 注解1
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/guli?useUnicode=true&useSSL=false&characterEncoding=utf8");
            // dsc.setSchemaName("public");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("123");
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            //分类包名
            String classify="acl";
            //pc.setModuleName(scanner("模块名"));
            pc.setParent("com.example.demo");
            pc.setController("controller"+"."+classify);
            pc.setEntity("entity"+"."+classify);
            pc.setService("service"+"."+classify);
            pc.setServiceImpl("service"+"."+classify+".impl");
            pc.setMapper("mapper"+"."+classify);
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl";
            // 如果模板引擎是 velocity
            // String templatePath = "/templates/mapper.xml.vm";

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                   // System.out.println("pc.getModuleName():"+pc.getModuleName());
                    //System.out.println(" tableInfo.getEntityName() :"+ tableInfo.getEntityName() );
                    return projectPath + "/src/main/resources/mapper/"
                            + classify//pc.getModuleName()//这里为null不然生产一个null文件夹
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();

            // 配置自定义输出模板
            //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
            // templateConfig.setEntity("templates/entity2.java");
            // templateConfig.setService();
            // templateConfig.setController();

            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            // 公共父类
            //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
            // 写于父类中的公共字段
            //strategy.setSuperEntityColumns("id");
            strategy.setInclude(scanner("acl_user_role,acl_user,acl_role_permission,acl_role,acl_permission").split(","));
            strategy.setControllerMappingHyphenStyle(true);
            //表面前缀
            strategy.setTablePrefix("acl_");
            //strategy.setTablePrefix(pc.getModuleName() + "_");
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();

    }
}

