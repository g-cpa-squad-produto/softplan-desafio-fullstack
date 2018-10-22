package org.gradle;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories("org.gradle")
//public class DataBaseConfig {
//	@Bean
//    public DataSource dataSource() {
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//             
//            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//            dataSource.setUrl("jdbc:mysql://localhost:3306/analise");
//            dataSource.setUsername("root");
//            dataSource.setPassword("abcd");
//            return dataSource;
//    }
//	
//  
//}
