import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ru.smolina.controllers.IngredientByIdConverter;
import ru.smolina.domains.Ingredient;
import ru.smolina.repositories.IngredientRepository;

public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	IngredientByIdConverter converter;
	
	@Autowired
	public WebConfig(IngredientByIdConverter converter) {
		this.converter = converter;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		
		registry.addConverter((Converter<?, ?>) converter);
	}

}
