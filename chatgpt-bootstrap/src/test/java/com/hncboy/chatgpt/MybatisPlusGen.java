package com.hncboy.chatgpt;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import java.sql.SQLException;

/**
 * @author david
 * @since 2023/4/14
 */
public class MybatisPlusGen {
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL", "root", "root");

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 数据库配置
                .dataSourceConfig((scanner, builder) -> builder.schema(scanner.apply("请输入表名称")))
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("david")))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("com.hncboy.chatgpt")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("user")))
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                   .templateEngine(new EnjoyTemplateEngine())
                 */
                .execute();
    }
}
