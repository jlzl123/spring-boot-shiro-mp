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
		
		//ȫ������
		GlobalConfig gc=new GlobalConfig();
		gc.setOutputDir("D:\\A161107004\\spring-boot-shiro-mp\\src\\main\\java");
		gc.setFileOverride(true);//�Ƿ񸲸��ļ�
		gc.setActiveRecord(true);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(false);
		gc.setAuthor("lsh");
        //�Զ����ļ�������ע�� %s ���Զ�����ʵ�����ԣ�,���¶���Ĭ��ֵ�����Բ�����
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);
		
		//����Դ����
		DataSourceConfig dsc=new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		// �Զ������ݿ���ֶ�����ת������ѡ��
		dsc.setTypeConvert(new MySqlTypeConvert(){
			
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				// TODO Auto-generated method stub
				System.out.println("ת�����ͣ�" + fieldType);
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/shiro-mp?characterEncoding=utf8");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);
		
		//��������
		StrategyConfig strategy=new StrategyConfig();
		strategy.setTablePrefix(new String[]{"t_"});//���ñ��ǰ׺
//		strategy.setExclude(new String[]{"t_user","t_role","t_permission"});//�ų����ɵı�
		//�������ɲ���,�����շ�ʽ
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// �ֶ������ɲ���
//       strategy.setFieldNming(NamingStrategy.underline_to_camel);
//       �Զ���ʵ�常��
//       strategy.setSuperEntityClass("com.fcs.demo.TestEntity");
//       �Զ���ʵ�壬�����ֶ�
//       strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
//       �Զ��� mapper ����
//       strategy.setSuperMapperClass("com.fcs.demo.TestMapper");
//       �Զ��� service ����
//       strategy.setSuperServiceClass("com.fcs.demo.TestService");
//       �Զ��� service ʵ���ุ��
//       strategy.setSuperServiceImplClass("com.fcs.demo.TestServiceImpl");
//       �Զ��� controller ����
//       strategy.setSuperControllerClass("com.fcs.demo.TestController");
//       ��ʵ�塿�Ƿ������ֶγ�����Ĭ�� false��
//       public static final String ID = "test_id";
//       strategy.setEntityColumnConstant(true);
//       ��ʵ�塿�Ƿ�Ϊ������ģ�ͣ�Ĭ�� false��
//       public User setName(String name) {this.name = name; return this;}
//       strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);
		
		//������
		PackageConfig pc=new PackageConfig();
		pc.setParent("cn.lsh");
		pc.setModuleName("admin");
		mpg.setPackageInfo(pc);
		
		mpg.execute();
	}

}
