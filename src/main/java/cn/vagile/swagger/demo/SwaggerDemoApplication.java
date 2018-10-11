package cn.vagile.swagger.demo;

import cn.vagile.swagger.demo.controller.PetController;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        PetController.class
})
public class SwaggerDemoApplication {
    @Autowired
    private TypeResolver typeResolver;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SwaggerDemoApplication.class, args);
    }

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDate.class, String.class)
                //.genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true);
    }
}
