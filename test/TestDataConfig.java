import configs.DataConfig;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class TestDataConfig extends DataConfig {

    public static final Map<String, String> appTestingConfMap = new HashMap<String, String>();

    // This variable is being used by the Controller test class to ensure that data is not being saved on the actual database during
    // test
    static {
        appTestingConfMap.put("db.default.driver", "org.h2.Driver");
        appTestingConfMap.put("db.default.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        appTestingConfMap.put("db.default.dialect", "org.hibernate.dialect.H2Dialect");
        appTestingConfMap.put("db.default.user", "root");
        appTestingConfMap.put("db.default.password", "");
    }

    @Bean
    @Override
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("models");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Bean
    @Override
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        return dataSource;
    }

}