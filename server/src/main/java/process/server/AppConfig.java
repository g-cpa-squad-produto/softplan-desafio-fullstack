package process.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages="process.server.dao")
public class AppConfig {

	@Bean
    public HikariDataSource dataSource() {
		
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/processManagment");
        config.setUsername("process");
        config.setPassword("process");
        config.addDataSourceProperty("hibernate.hbm2ddl.auto", "update");
        config.addDataSourceProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        config.addDataSourceProperty("hibernate.show_sql", "true");
        
        return new HikariDataSource(config);
        
    }
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
	
}
