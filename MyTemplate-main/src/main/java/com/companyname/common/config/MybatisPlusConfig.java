package com.companyname.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlusConfig  mybatisplus配置,更改包结构时也需要改这
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/2/10 1:30
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.companyname.projectname.mapper.*")
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @author fxbin
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
