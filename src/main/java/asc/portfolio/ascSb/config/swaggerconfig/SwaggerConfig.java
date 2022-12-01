package asc.portfolio.ascSb.config.swaggerconfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info().title("ASC Open Api")
                                .version("v1.0")
                                .description("open Api test")
                );
    }

    @Bean
    public OpenApiCustomiser customGlobalOpenApiHeader() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name(HttpHeaders.AUTHORIZATION)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
//                .bearerFormat("JWT")
                .scheme("bearer");

        return openApi -> openApi
                .addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .getComponents().addSecuritySchemes("jwt token", securityScheme);
    }

//    @Bean
//    public GroupedOpenApi publicUserApi() {
//        return GroupedOpenApi.builder()
//                .pathsToMatch("/**")
//                .packagesToScan()
//    }

//    @Bean
//    public OpenApiCustomiser customGlobalOpenApiHeader() {
//        Parameter jwt = new Parameter()
//                .name("jwToken")
//                .in("header")
//                .required(true)
//                .description("header description")
//                .schema(new StringSchema());
//
//        return openApi -> openApi.getPaths().values().forEach(
//                operation -> operation
//                        .addParametersItem(jwt)
//        );
//    }

//    @Bean
//    public OpenAPI openAPI() {
////        SecurityScheme jwtAuth = new SecurityScheme()
////                .type(SecurityScheme.Type.HTTP)
////                .scheme("bearer")   //bearer: JWT 타입, basic: id, password
////                .bearerFormat("JWT")
////                .in(SecurityScheme.In.HEADER)
////                .name(HttpHeaders.AUTHORIZATION);
//
////        return new OpenAPI()
////                .components(new Components().addSecuritySchemes("JWT", jwtAuth));
//
//        return new OpenAPI()
//                .components(new Components()
//                        .addHeaders("Authorization", new Header()
//                                .description("Auth header")
//                                .schema(new Schema<>().format("base64"))
//                        ));
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("basicScheme", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("basic"))
//                        .addParameters("myHeader1", new Parameter().in("header")
//                                .schema(new StringSchema())
//                                .name("myHeader1")).addHeaders("myHeader2", new Header().description("myHeader2 header").schema(new StringSchema())))
//                .info(new Info()
//                        .title("Petstore API")
//                        .description("This is a sample server Petstore server. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
}
