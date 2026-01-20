package com.cirilo.cirilofood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
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
import com.cirilo.cirilofood.api.v1.model.CityModel;
import com.cirilo.cirilofood.api.v1.model.CuisineModel;
import com.cirilo.cirilofood.api.v1.model.FormPaymentModel;
import com.cirilo.cirilofood.api.v1.model.GroupModel;
import com.cirilo.cirilofood.api.v1.model.OrderResumeModel;
import com.cirilo.cirilofood.api.v1.model.PermissionModel;
import com.cirilo.cirilofood.api.v1.model.ProductModel;
import com.cirilo.cirilofood.api.v1.model.RestaurantBasicModel;
import com.cirilo.cirilofood.api.v1.model.StateModel;
import com.cirilo.cirilofood.api.v1.model.UserModel;
import com.cirilo.cirilofood.api.v1.openapi.model.CitiesModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.CuisinesModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.FormsPaymentModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.GroupsModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.LinksModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.OrdersResumeModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.PageableModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.PermissionsModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.ProductsModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.RestaurantsBasicModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.StatesModelOpenApi;
import com.cirilo.cirilofood.api.v1.openapi.model.UsersModelOpenApi;
import com.cirilo.cirilofood.api.v2.model.CityModelV2;
import com.cirilo.cirilofood.api.v2.model.CuisineModelV2;
import com.cirilo.cirilofood.api.v2.openapi.model.CitiesModelV2OpenApi;
import com.cirilo.cirilofood.api.v2.openapi.model.CuisinesModelV2OpenApi;
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
    public Docket apiDocketV1() {
        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cirilo.cirilofood.api"))
                .paths(PathSelectors.ant("/v1/**"))
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
                        typeResolver.resolve(PagedModel.class, OrderResumeModel.class),
                        OrdersResumeModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CityModel.class),
                        CitiesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, StateModel.class),
                        StatesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, FormPaymentModel.class),
                        FormsPaymentModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, GroupModel.class),
                        GroupsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, PermissionModel.class),
                        PermissionsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, ProductModel.class),
                        ProductsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, RestaurantBasicModel.class),
                        RestaurantsBasicModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, UserModel.class),
                        UsersModelOpenApi.class))
                .apiInfo(apiInfoV1())
                .tags(new Tag("Cities", "Manage the cities"),
                        new Tag("Groups", "Manage the groups"),
                        new Tag("Cuisines", "Manage the cuisines"),
                        new Tag("Forms Payment", "Manage the forms payment"),
                        new Tag("Orders", "Manage the orders"),
                        new Tag("Restaurants", "Manage the restaurants"),
                        new Tag("States", "Manage the states"),
                        new Tag("Products", "Manage the restaurant's products"),
                        new Tag("Users", "Manage the users"),
                        new Tag("Statistics", "Statistics"),
                        new Tag("Permissions", "Permissions"));

    }

    @Bean
    public Docket apiDocketV2() {
        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cirilo.cirilofood.api"))
                .paths(PathSelectors.ant("/v2/**"))
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
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CuisineModelV2.class),
                        CuisinesModelV2OpenApi.class))

                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CityModelV2.class),
                        CitiesModelV2OpenApi.class))
                .apiInfo(apiInfoV2())
                .tags(new Tag("Cities", "Manage the cities"),
                        new Tag("Cuisines", "Manage the cuisines"));

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

    public ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("CiriloFood API (Deprecated)")
                .description("API to clients and restaurants<br> " +
                        "<strong>This version is deprecated. " +
                        "Use version 2</strong>")
                .version("1")
                .contact(new Contact("CiriloFood", "https://www.cirilofood.com", "cirilofood@cirilofood.com"))
                .build();
    }

    public ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
                .title("CiriloFood API")
                .description("API to clients and restaurants")
                .version("2")
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
