package sms.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "localEntityManagerFactory",
        transactionManagerRef = "localTransactionManager",
        basePackages = {"sms.com.repository"})
public class LocalDBConfig extends DatabaseConfiguration {

    private static final String MODEL_PACKAGE = "sms.com.model";

    @Value("${local.database.url}")
    private String local_database_url;

    @Value("${local.database.username}")
    private String local_database_username;

    @Value("${local.database.password}")
    private String local_database_password;


    /**
     * Primary because this DataSource will be using for most operations.
     * Another DataSource is using for querying remote server to get configuration properties.
     */

    @Primary
    @Bean(name = "localDataSource")
    public DataSource dataSource() {
        DataSource dataSource =
                createDataSource(local_database_url, local_database_username,
                                 local_database_password);
        return dataSource;
    }

    @Primary
    @Bean(name = "localEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return createEntityManagerFactory(dataSource(), MODEL_PACKAGE);
    }

    @Primary
    @Bean(name = "localTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return createPlatformTransactionManager(entityManagerFactory());
    }

}