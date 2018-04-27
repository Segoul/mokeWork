package com.moke.mokeWork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类  swagger-ui.html
 * @author Segoul
 *
 */

@Configuration  //通过@Configuration注解，让Spring来加载该类配置
@EnableSwagger2  //通过@EnableSwagger2注解来启用Swagger2
public class SwaggerConfig {

	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //创建Api基本信息，可省略
                .select()  //选择那些路径和api会生成document
                //apis()可以指定有某个注解的方法需要生成API文档，还可以指定扫描哪个包来生成文档
                .apis(RequestHandlerSelectors.basePackage("com.moke.mokeWork"))  // 对所有api进行监控，
                .paths(PathSelectors.any())  // 对所有路径进行监控
                .build();
    }
	
	/**
	 * 创建Api基本信息，这些信息会展示在文档页面中
	 * 非必要
	 * @return
	 */
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("雨哥哥项目")
                .termsOfServiceUrl("https://weibo.com/yylovedmr1314/home?wvr=5")
                .contact("雨哥哥")
                .version("1.0")
                .build();
    }
}
