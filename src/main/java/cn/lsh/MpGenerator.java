package cn.lsh;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoGenerator mpg=new AutoGenerator();
		
		//全局配置
		GlobalConfig gc=new GlobalConfig();
		gc.setOutputDir("D:\\A161107004\\spring-boot-shiro-mp\\src\\main\\java");
		gc.setFileOverride(true);//是否覆盖文件
		gc.setActiveRecord(true);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(false);
		gc.setAuthor("lsh");
        //自定义文件命名，注意 %s 会自动填充表实体属性！,以下都是默认值，可以不设置
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);
		
		//数据源配置
		DataSourceConfig dsc=new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		// 自定义数据库表字段类型转换【可选】
		dsc.setTypeConvert(new MySqlTypeConvert(){
			
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				// TODO Auto-generated method stub
				System.out.println("转换类型：" + fieldType);
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/shiro-mp?characterEncoding=utf8");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);
		
		//策略配置
		StrategyConfig strategy=new StrategyConfig();
		strategy.setTablePrefix(new String[]{"t_"});//配置表的前缀
//		strategy.setExclude(new String[]{"t_user","t_role","t_permission"});//排除生成的表
		//表名生成策略,采用驼峰式
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 字段名生成策略
//       strategy.setFieldNming(NamingStrategy.underline_to_camel);
//       自定义实体父类
//       strategy.setSuperEntityClass("com.fcs.demo.TestEntity");
//       自定义实体，公共字段
//       strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
//       自定义 mapper 父类
//       strategy.setSuperMapperClass("com.fcs.demo.TestMapper");
//       自定义 service 父类
//       strategy.setSuperServiceClass("com.fcs.demo.TestService");
//       自定义 service 实现类父类
//       strategy.setSuperServiceImplClass("com.fcs.demo.TestServiceImpl");
//       自定义 controller 父类
//       strategy.setSuperControllerClass("com.fcs.demo.TestController");
//       【实体】是否生成字段常量（默认 false）
//       public static final String ID = "test_id";
//       strategy.setEntityColumnConstant(true);
//       【实体】是否为构建者模型（默认 false）
//       public User setName(String name) {this.name = name; return this;}
//       strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);
		
		//包配置
		PackageConfig pc=new PackageConfig();
		pc.setParent("cn.lsh");
		pc.setModuleName("admin");
		mpg.setPackageInfo(pc);
		
		mpg.execute();
	}

}
