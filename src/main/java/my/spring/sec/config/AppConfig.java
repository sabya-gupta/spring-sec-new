package my.spring.sec.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc //config for replace web xml
@ComponentScan(basePackages= {"my.spring.sec", "my.spring.rest", "my.spring.entity"}) //config for component scan for base package
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

	
	@Autowired
	Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	//config for view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/pages/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
	@Bean
	public DataSource securityDataSource () {
		
		//create a connection pool
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		
		//set driver class
		try {
			pooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		//log connection props
		logger.info(">>>url : "+env.getProperty("jdbc.url"));
		
		//set db connecton props
		pooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		pooledDataSource.setUser(env.getProperty("jdbc.user"));
		pooledDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool props
		pooledDataSource.setInitialPoolSize
				(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		
		pooledDataSource.setMinPoolSize
				(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));

		pooledDataSource.setMaxPoolSize
		(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));

		pooledDataSource.setMaxIdleTime
		(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));

		
		return pooledDataSource;
	}

	
	//JPA RElated
	@Bean
	public DataSource bdDataSource () {
		
		//create a connection pool
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		
		//set driver class
		try {
			pooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		//log connection props
		logger.info(">>>url : "+env.getProperty("BD.jdbc.url"));
		
		//set db connecton props
		pooledDataSource.setJdbcUrl(env.getProperty("BD.jdbc.url"));
		pooledDataSource.setUser(env.getProperty("BD.jdbc.user"));
		pooledDataSource.setPassword(env.getProperty("BD.jdbc.password"));
		
		//set connection pool props
		pooledDataSource.setInitialPoolSize
				(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		
		pooledDataSource.setMinPoolSize
				(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));

		pooledDataSource.setMaxPoolSize
		(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));

		pooledDataSource.setMaxIdleTime
		(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));

		
		return pooledDataSource;
	}
	
	@Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(bdDataSource ());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("my.spring.entity");
 
        Properties jpaProperties = new Properties();
     
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                env.getRequiredProperty("hibernate.hbm2ddl.auto")
        );
 
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy", 
//                env.getRequiredProperty("hibernate.ejb.naming_strategy")
//        );
 
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", 
                env.getRequiredProperty("hibernate.show_sql")
        );
 
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", 
                env.getRequiredProperty("hibernate.format_sql")
        );
 
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
    }

//	@Bean("mysecondbean")
//    LocalContainerEntityManagerFactoryBean entityManagerFactory2() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(securityDataSource());
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPackagesToScan("my.spring.entity");
// 
//        Properties jpaProperties = new Properties();
//     
//        //Configures the used database dialect. This allows Hibernate to create SQL
//        //that is optimized for the used database.
//        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
// 
//        //Specifies the action that is invoked to the database when the Hibernate
//        //SessionFactory is created or closed.
//        jpaProperties.put("hibernate.hbm2ddl.auto", 
//                env.getRequiredProperty("hibernate.hbm2ddl.auto")
//        );
// 
//        //Configures the naming strategy that is used when Hibernate creates
//        //new database objects and schema elements
////        jpaProperties.put("hibernate.ejb.naming_strategy", 
////                env.getRequiredProperty("hibernate.ejb.naming_strategy")
////        );
// 
//        //If the value of this property is true, Hibernate writes all SQL
//        //statements to the console.
//        jpaProperties.put("hibernate.show_sql", 
//                env.getRequiredProperty("hibernate.show_sql")
//        );
// 
//        //If the value of this property is true, Hibernate will format the SQL
//        //that is written to the console.
//        jpaProperties.put("hibernate.format_sql", 
//                env.getRequiredProperty("hibernate.format_sql")
//        );
// 
//        entityManagerFactoryBean.setJpaProperties(jpaProperties);
// 
//        return entityManagerFactoryBean;
//    }
	
	
    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory((EntityManagerFactory) entityManagerFactory().getNativeEntityManagerFactory());
        return transactionManager;
    }

//    @Bean
//    JpaTransactionManager transactionManager2() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory((EntityManagerFactory) entityManagerFactory2().getNativeEntityManagerFactory());
//        return transactionManager;
//    }
}
