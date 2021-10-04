package a.b.c.application;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import a.b.c.formatter.EventFormatter;

@Configuration
@EnableWebMvc
@ComponentScan("a.b.c")
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer{
	
	
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
	
		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			registry.jsp("/WEB-INF/views/",".jsp");
		}
	
	   @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addFormatter(new EventFormatter());
	   }

	   
	   @Bean
	    public MultipartResolver multipartResolver () throws IOException {
	        final int MAX_SIZE = 10 * 1024 * 1024; // 10MB

	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	        multipartResolver.setMaxInMemorySize(MAX_SIZE);
	        multipartResolver.setMaxUploadSizePerFile(MAX_SIZE);
			multipartResolver.setDefaultEncoding("UTF-8");
	        multipartResolver.setMaxInMemorySize(0);
	    //  multipartResolver.setUploadTempDir(new FileSystemResource("C:\\upload\\temp"));
	         
	        return multipartResolver;
	    }
	    
	    
		
		/*******************************************************************
		 * 
		 * DispatcherServlet이 처리하지 못한 요청을 DefaultServlet에게 넘겨주는 역할
		 *서버에 파일을 업로드하는 경우 선언해준다.
		 *
		 *******************************************************************/
	    @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
	    }
	  
	   
}
