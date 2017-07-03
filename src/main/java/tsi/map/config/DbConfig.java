package tsi.map.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tsi.map.commons.GlobalConstant;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;


@PropertySource(value = "classpath:env.properties")
@EnableTransactionManagement()
@EnableSpringDataWebSupport
@EnableJpaRepositories(basePackages = "tsi.map.repo")
@Configuration
public class DbConfig {

	@Autowired
	Environment env;
	
	@Bean(name = "dataSource")
    @Primary
	public HikariDataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();

        ds.setMaximumPoolSize(50);
        ds.setConnectionTestQuery("SELECT 1");
        ds.setDataSourceClassName(env.getRequiredProperty("db.datasource"));
        ds.addDataSourceProperty("url", env.getRequiredProperty("db.url"));
        ds.addDataSourceProperty("user", env.getRequiredProperty("db.username"));
        ds.addDataSourceProperty("password", env.getRequiredProperty("db.password"));
        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 250);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        ds.setLeakDetectionThreshold(20000);

		return ds;
	}
	
	@Bean
    @Primary
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
	
	@Bean
    @Primary
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        txManager.setEntityManagerFactory(entityManagerFactory);
        txManager.setJpaDialect(jpaDialect);
        
        return txManager; 
	 }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") HikariDataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("my");
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] {GlobalConstant.MODEL_PACK });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);
        vendorAdapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));
        vendorAdapter.setDatabase(Database.MYSQL);

        Properties properties = new Properties();
        properties.setProperty("hibernate.id.new_generator_mappings",env.getRequiredProperty("hibernate.id.new_generator_mappings"));

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);
        em.afterPropertiesSet();

        return em;
    }

}
