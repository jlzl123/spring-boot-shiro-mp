package cn.lsh.common.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		//HttpMessageConverters http消息转换器
		// TODO Auto-generated method stub
		//构建自己的转换器，这里用的是阿里的FastJson包里的消息转换器
		FastJsonHttpMessageConverter fastMessageConverter=new FastJsonHttpMessageConverter();
		FastJsonConfig config=new FastJsonConfig();
		//BrowserCompatible 中文都会序列化为uXXXX格式，
		config.setSerializerFeatures(SerializerFeature.BrowserCompatible);
		fastMessageConverter.setFastJsonConfig(config);
		converters.add(fastMessageConverter);
	}
	
}
