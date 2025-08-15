package com.cirilo.cirilofood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.api.openapi.model.CitiesModelOpenApi;
import com.cirilo.cirilofood.api.openapi.model.LinksModelOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.api.model.OrderResumeModel;
import com.cirilo.cirilofood.api.openapi.model.CuisinesModelOpenApi;
import com.cirilo.cirilofood.api.openapi.model.OrderResumeModelOpenApi;
import com.cirilo.cirilofood.api.openapi.model.PageableModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {
        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cirilo.cirilofood.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                // .ignoredParameterTypes(CityModel.class, CuisineModel.class) // if i want ignore some classes
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                // .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                // .name("fields")
                // .description("Properties names to filter response separated by comma")
                // .parameterType("query")
                // .modelRef(new ModelRef("string"))
                // .build()))
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
                        Resource.class, File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CuisineModel.class), CuisinesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, OrderResumeModel.class),
                        OrderResumeModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CityModel.class),
                        CitiesModelOpenApi.class))
                .apiInfo(apiInfo())
                .tags(new Tag("Cities", "Manage the cities"),
                        new Tag("Groups", "Manage the groups"),
                        new Tag("Cuisines", "Manage the cuisines"),
                        new Tag("Forms Payment", "Manage the forms payment"),
                        new Tag("Orders", "Manage the orders"),
                        new Tag("Restaurants", "Manage the restaurants"),
                        new Tag("States", "Manage the states"),
                        new Tag("Products", "Manage the restaurant's products"),
                        new Tag("Users", "Manage the users"),
                        new Tag("Statistic", "Statistics"),
                        new Tag("Permissions", "Permissions")
                        );

    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid request (client error)")
                        .responseModel(new ModelRef("Problem"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal Server Error")
                        .responseModel(new ModelRef("Problem"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Resource has no representation that could be accepted by the consumer")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .responseModel(new ModelRef("Problem"))
                        .message("Request refused because the body is in an unsupported format")
                        .build());
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .responseModel(new ModelRef("Problem"))
                        .message("Invalid request (client error)")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .responseModel(new ModelRef("Problem"))
                        .message("Internal Server Error")
                        .build());
    }

    private List<ResponseMessage> globalGetResponseMessages() {

        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .responseModel(new ModelRef("Problem"))
                        .message("Internal Server Error")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Resource has no representation that could be accepted by the consumer")
                        .build());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CiriloFood API")
                .description("API to clients and restaurants")
                .version("1")
                .contact(new Contact("CiriloFood", "https://www.cirilofood.com", "cirilofood@cirilofood.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
