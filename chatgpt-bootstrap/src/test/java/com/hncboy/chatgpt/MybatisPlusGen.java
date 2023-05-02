package com.hncboy.chatgpt;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import java.sql.SQLException;

/**
 * @author david
 * @since 2023/4/14
 */
public class MybatisPlusGen extends BaseGeneratorTest {

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
//         DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
//                .Builder("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL", "root", "root");
//        initDataSource(DATA_SOURCE_CONFIG.build());

        DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
                .Builder("jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true", "root", "root");

        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 数据库配置
//                .dataSourceConfig((scanner, builder) -> builder.schema(scanner.apply("请输入表名称")))
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author("david"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.hncboy.chatgpt"))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude("product", "order"))
                .execute();
    }
}
