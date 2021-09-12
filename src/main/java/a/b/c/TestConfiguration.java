package a.b.c;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class TestConfiguration {

	@Bean
	public MessageSource messageSource() {
		// var : java 10 ~
	    var messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
}
