package a.b.c;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import a.b.c.validator.Eventvalidator;


@Controller
public class HomeController {

    @Autowired
    MessageSource messageSource;
 
   
    @GetMapping("/")
    @ResponseBody
    public String defalut() {
    	return "test";
    }
    
	@ResponseBody
	@RequestMapping(value = "/messageSourceTest", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
        Locale.setDefault(Locale.ROOT);
		String enName = messageSource.getMessage("name", new String[] {}, Locale.getDefault());
		String krName = messageSource.getMessage("name", new String[] {}, Locale.KOREA);
		System.out.println(messageSource.getMessage(
				          "content", new String[] {enName, "Seoul"}, Locale.getDefault()));
		System.out.println(messageSource.getMessage(
		                  "content", new String[] {krName, "¼­¿ï"}, Locale.KOREA));
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	// http://localhost:8082/validationTest?id=123
	@GetMapping("/validationTest")
    @ResponseBody
    public String validationTest(TestDto dto){

        BeanPropertyBindingResult errors = 
        		new BeanPropertyBindingResult(dto, "dto");

		Eventvalidator eValidator = new Eventvalidator();
		eValidator.validate(dto, errors);
		
        if(errors.hasErrors()) {
        	errors.getAllErrors().forEach( e -> {
        		System.out.println("===Error code===");
        		System.out.println(e.getCode());
        		System.out.println(e.getDefaultMessage());
        	});
        }
      
        return "list";
    }

	
	@ResponseBody
	@GetMapping("/formattertest/{name}")
	public String formatterTest(@PathVariable("name") TestDto dto) {
		System.out.println(dto.getName());
		return dto.toString();
	}
	
}
