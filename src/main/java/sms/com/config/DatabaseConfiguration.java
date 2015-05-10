package sms.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public abstract class DatabaseConfiguration {


    @Autowired
    protected JpaVendorAdapter jpaVendorAdapter;

    @Value("${database.driver}")
    private String database_driver;

    protected DataSource createDataSource(String url, String username, String password) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(database_driver);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }

    protected LocalContainerEntityManagerFactoryBean createEntityManagerFactory
            (DataSource dataSource, String modelPackage) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan(modelPackage);
        return lef;
    }


    protected PlatformTransactionManager createPlatformTransactionManager
            (LocalContainerEntityManagerFactoryBean factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager
                .setEntityManagerFactory(factory.getObject());
        return transactionManager;
    }

}