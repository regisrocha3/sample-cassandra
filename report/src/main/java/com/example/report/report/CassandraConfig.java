package com.example.report.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Classe de configuracao do Cassandra
 * 
 * @author regis.rocha
 *
 */
@Configuration
@PropertySource(value = "classpath:cassandra.properties")
@EnableCassandraRepositories(basePackages = {"com.example.report.report"})
public class CassandraConfig {

	/**
	 * @Inject
	 */
	@Autowired
	private Environment environment;
	
	
	/**
	 * Creating bean cluster
	 * 
	 * @return CassandraClusterFactoryBean
	 */
	@Bean
	public CassandraClusterFactoryBean cluster() {
		final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		
		cluster.setContactPoints(environment.getProperty("cassandra.contactpoints"));
		cluster.setPort(Integer.valueOf(environment.getProperty("cassandra.port")));
		
		return cluster;
	}
	
    @Bean
    public CassandraMappingContext mappingContext() {
    	final CassandraMappingContext context = new CassandraMappingContext();
    	
    	try {
			context.setInitialEntitySet(CassandraEntityClassScanner.scan(Report.class));
			context.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), environment.getProperty("cassandra.keyspace")));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	return context;
    	
    }
    
    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }
    
    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        final CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(environment.getProperty("cassandra.keyspace"));
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);

        return session;
    }
    
    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}
