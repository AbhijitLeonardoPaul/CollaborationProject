package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.UserDao;
import com.niit.collaboration.dao.UserDaoImpl;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;
/*import com.niit.binder.model.Event;*/
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.ForumComment;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;
import com.niit.collaboration.model.User;

@Configuration						//@Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
@ComponentScan("com.niit.collaboration")		//@ComponentScan annotation is used to specify the base packages to scan.
//@ComponentScan(basePackages = "com.niit.binder", excludeFilters = @Filter(type = FilterType.ANNOTATION, value = AppConfig.class))
@EnableTransactionManagement		/**when we are using @Configuration i.e XML free configuration and need to 
									 * connect to database with hibernate. We need to use @EnableTransactionManagement.
									 */
public class ApplicationContextConfig {
	@Bean(name="dataSource")			//setup dataSource to desired database
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/**
		 * Simple implementation of the standard JDBC DataSource interface, configuring the plain old JDBC DriverManager via 
		 * bean properties, and returning a new Connection from every getConnection call.
		 */
				/*--- Database connection settings ---*/
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");		//specify the driver...
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");		//specify the db_url...
		dataSource.setUsername("SA");		//specify the db_username...
		dataSource.setPassword("SA");		//specify the db_password...
		

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		dataSource.setConnectionProperties(connectionProperties);		
		return dataSource;                                    // we are using oracle db for our project...
	}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "sessionFactory")			//sessionfactory creates the session for the application...
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		//specify all the model classes... 
		sessionBuilder.addAnnotatedClass(User.class);			
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		/*sessionBuilder.addAnnotatedClass(Event.class);*/	
		sessionBuilder.addAnnotatedClass(Friend.class);	
		sessionBuilder.addAnnotatedClass(Job.class);		
		sessionBuilder.addAnnotatedClass(Forum.class);	
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		
	
		
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	
	return transactionManager;
	}
	
	@Autowired
	@Bean(name = "userDao")
	public UserDao getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserDaoImpl(sessionFactory);
	}
}
