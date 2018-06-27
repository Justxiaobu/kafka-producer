package com.tbl.api.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * author sunbin
 * date 2018/6/11 14:23
 * description swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("CLIENT_ID").description("身份").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        tokenPar1.name("CLIENT_SECRET").description("口令").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
        pars.add(tokenPar1.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tbl.api.kafka.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * API文档主信息对象
     * @return
     */
    private ApiInfo apiInfo(){
        ApiInfo apiInfo= (new ApiInfoBuilder())
                .title("生产者接口")
                .description("API")
                .version("1.0")
                .build();
        return apiInfo;
    }
}
