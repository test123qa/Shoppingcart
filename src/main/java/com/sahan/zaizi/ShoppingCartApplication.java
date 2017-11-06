package com.sahan.zaizi;

import com.sahan.zaizi.service.ProductService;
import com.sahan.zaizi.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShoppingCartApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShoppingCartApplication.class);
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ShoppingCartApplication.class, args);

		ProductService productService = applicationContext.getBean(ProductService.class);
		productService.saveInitialBatch();

	}
}
