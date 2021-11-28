package com.andryjkost.superheroes.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;



@Configuration
@OpenAPIDefinition(info = @Info(title = "Marvel",description = "Superhero API",
        version = "1.0",contact =@Contact(name = "Andrej Kostin",email = "aokostin@edu.hse.ru",url ="https://vk.com/escanorrrrr" )))
public class SwaggerConfig {

}