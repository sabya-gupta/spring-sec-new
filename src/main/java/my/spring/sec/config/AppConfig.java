package my.spring.sec.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc //config for replace web xml
@ComponentScan(basePackages="my.spring.sec") //config for component scan for base package
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
	
	
}
