package a.b.c.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import a.b.c.dto.TestDto;


public class EventFormatter implements Formatter<TestDto> {

	@Override
	public String print(TestDto object, Locale locale) {
		return null;
	}

	@Override
	public TestDto parse(String text, Locale locale) throws ParseException {
		TestDto dto = new TestDto();
		dto.setId(Integer.parseInt(text));
		return dto;
	}
	 

}