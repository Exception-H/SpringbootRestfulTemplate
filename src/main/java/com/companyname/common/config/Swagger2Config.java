
package com.companyname.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @ClassName Swagger2Config
 * @Discription swagger2配置类 改包结构时也需要同步修改
 * @Date 2020/1/5 16:53
 * @Version V1.0
 */
@Configuration
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket( DocumentationType.SWAGGER_2 )
                .apiInfo( apiInfo() )
                .select()
                // 自行修改为自己的包路径
                .apis( RequestHandlerSelectors.basePackage( "com.companyname.projectname.controller" ) )
                .paths( PathSelectors.any() )
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title( "Swagger接口查询" )
                .version( "1.0" )
                .build();
    }

}

