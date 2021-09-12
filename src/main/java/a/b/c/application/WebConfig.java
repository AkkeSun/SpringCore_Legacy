package a.b.c.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import a.b.c.formatter.EventFormatter;

@Configuration
@EnableWebMvc
@ComponentScan("a.b.c")
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer{

	   @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addFormatter(new EventFormatter());
	   }
}
