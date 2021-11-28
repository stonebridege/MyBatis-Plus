package com.stonebridge.itcastmpspringboot;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@MapperScan("com.stonebridge.itcastmpspringboot.mapper") //设置mapper接口的扫描包
public class ItcastMpSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItcastMpSpringbootApplication.class, args);
    }

    /**
     * 分页插件
     * //
     */


}
