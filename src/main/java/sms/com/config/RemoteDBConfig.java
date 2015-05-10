package sms.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "remoteEntityManagerFactory",
        transactionManagerRef = "remoteTransactionManager",
        basePackages = {"sms.com.remote.repository"})
public class RemoteDBConfig extends DatabaseConfiguration {

    private static final String MODEL_PACKAGE = "sms.com.remote.model";

    @Value("${remote.database.url}")
    private String remote_database_url;

    @Value("${remote.database.username}")
    private String remote_database_username;

    @Value("${remote.database.password}")
    private String remote_database_password;


    @Bean(name = "remoteDataSource")
    public DataSource dataSource() {
        DataSource dataSource =
                createDataSource(remote_database_url, remote_database_username,
                                 remote_database_password);
        return dataSource;
    }

    @Bean(name = "remoteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return createEntityManagerFactory(dataSource(), MODEL_PACKAGE);
    }

    @Bean(name = "remoteTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return createPlatformTransactionManager(entityManagerFactory());
    }

}