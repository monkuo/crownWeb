package org.crown.generate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 程式碼生成器父類
 * </p>
 *
 * @author Caratacus
 */
public class SuperGenerator {

    /**
     * 獲取TemplateConfig
     *
     * @return
     */
    protected TemplateConfig getTemplateConfig() {
        return new TemplateConfig().setXml(null);
    }

    /**
     * 獲取InjectionConfig
     *
     * @return
     */
    protected InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                "/templates/mapper.xml.vm") {
            // 自定義輸出檔案目錄
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getResourcePath() + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
    }

    /**
     * 獲取PackageConfig
     *
     * @return
     */
    protected PackageConfig getPackageConfig(String packagePath) {
        return new PackageConfig()
                .setParent(packagePath)
                .setController("controller")
                .setEntity("model.entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
    }

    /**
     * 獲取StrategyConfig
     *
     * @param tableName
     * @return
     */
    protected StrategyConfig getStrategyConfig(String prefix, String tableName) {
        List<TableFill> tableFillList = getTableFills();
        return new StrategyConfig()
                .setCapitalMode(false)// 全局大寫命名
                .setTablePrefix(prefix)// 去除字首
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                //自定義實體父類
                .setSuperEntityClass("com.okbone.framework.model.BaseModel")
                // 自定義實體，公共欄位
                .setSuperEntityColumns("id")
                .setTableFillList(tableFillList)
                // 自定義 mapper 父類
                .setSuperMapperClass("com.okbone.framework.mapper.BaseMapper")
                // 自定義 controller 父類
                .setSuperControllerClass("com.okbone.framework.controller.SuperController")
                // 自定義 service 實現類父類
                .setSuperServiceImplClass("com.okbone.framework.service.impl.BaseServiceImpl")
                // 自定義 service 介面父類
                .setSuperServiceClass("com.okbone.framework.service.BaseService")
                // 【實體】是否生成欄位常量（預設 false）
                .setEntityColumnConstant(true)
                // 【實體】是否為構建者模型（預設 false）
                .setEntityBuilderModel(false)
                // 【實體】是否為lombok模型（預設 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true)
                // Boolean類型欄位是否移除is字首處理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setRestControllerStyle(false)
                .setRestControllerStyle(true)
                //.setInclude(new String[] { "user" }) // 需要生成的表
                .setInclude(tableName);
    }

    /**
     * 獲取TableFill策略
     *
     * @return
     */
    protected List<TableFill> getTableFills() {
        // 自定義需要填充的欄位
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("createUid", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateUid", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }

    /**
     * 獲取DataSourceConfig
     *
     * @return
     */
    protected DataSourceConfig getMysqlDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)// 資料庫類型
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if (fieldType.toLowerCase().contains("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().contains("tinyint")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().contains("date")) {
                            return DbColumnType.LOCAL_DATE;
                        }
                        if (fieldType.toLowerCase().contains("time")) {
                            return DbColumnType.LOCAL_TIME;
                        }
                        if (fieldType.toLowerCase().contains("datetime")) {
                            return DbColumnType.LOCAL_DATE_TIME;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("521")
                .setUrl("jdbc:mysql://127.0.0.1:3306/crown?characterEncoding=utf8");
    }

    protected DataSourceConfig getPostgresqlDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.POSTGRE_SQL)// 資料庫類型
                .setTypeConvert(new PostgreSqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if (fieldType.toLowerCase().contains("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().contains("tinyint")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().contains("date")) {
                            return DbColumnType.LOCAL_DATE;
                        }
                        if (fieldType.toLowerCase().contains("time")) {
                            return DbColumnType.LOCAL_TIME;
                        }
                        if (fieldType.toLowerCase().contains("datetime")) {
                            return DbColumnType.LOCAL_DATE_TIME;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .setDriverName("org.postgresql.Driver")
                .setUsername("zvntvkzoyjjuwt")
                .setPassword("f2c5b78ca2f7ac7b0b1d0baa4ab30150a1e7d9f0c9fd964d71a5b40732643582")
                .setUrl("jdbc:postgresql://ec2-54-83-38-174.compute-1.amazonaws.com:5432/d5a87ip5cr3ba3?allow=true");
    }

    /**
     * 獲取GlobalConfig
     *
     * @return
     */
    protected GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(getJavaPath())//輸出目錄
                .setFileOverride(false)// 是否覆蓋檔案
                .setActiveRecord(false)// 開啟 activeRecord 模式
                .setEnableCache(false)// XML 二級快取
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(false)// XML columList
                .setKotlin(false) //是否生成 kotlin 程式碼
                .setOpen(false)
                .setAuthor("Caratacus") //作者
                //自定義檔案命名，注意 %s 會自動填充表實體屬性！
                .setEntityName("%s")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sRestController");
    }


    /**
     * 獲取根目錄
     *
     * @return
     */
    private String getRootPath() {
        String file = Objects.requireNonNull(SuperGenerator.class.getClassLoader().getResource("")).getFile();
        return new File(file).getParentFile().getParentFile().getParent();
    }

    /**
     * 獲取JAVA目錄
     *
     * @return
     */
    protected String getJavaPath() {
        return getRootPath() + "/src/main/java";
    }

    /**
     * 獲取Resource目錄
     *
     * @return
     */
    protected String getResourcePath() {
        return getRootPath() + "/src/main/resources";
    }

    /**
     * 獲取AutoGenerator
     *
     * @param tableName
     * @return
     */
    protected AutoGenerator getAutoGenerator(String packagePath, String prefix, String tableName, DataSourceConfig config) {
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 資料來源配置
                .setDataSource(config)
//                .setDataSource(getMysqlDataSourceConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(prefix, tableName))
                // 包配置
                .setPackageInfo(getPackageConfig(packagePath))
                // 注入自定義配置，可以在 VM 中使用 cfg.abc 設定的值
                .setCfg(getInjectionConfig())
                .setTemplate(getTemplateConfig());
    }

}
