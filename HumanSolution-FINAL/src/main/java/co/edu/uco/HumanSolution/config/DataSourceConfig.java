package co.edu.uco.HumanSolution.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configuración del DataSource con pool de conexiones HikariCP.
 * Este pool de conexiones resuelve el problema de "Connection has been closed"
 * al gestionar automáticamente el ciclo de vida de las conexiones.
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.maximum-pool-size:10}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle:5}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.connection-timeout:30000}")
    private long connectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout:600000}")
    private long idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime:1800000}")
    private long maxLifetime;

    /**
     * Crea y configura el DataSource con HikariCP.
     * HikariCP es el pool de conexiones más rápido y eficiente para Java.
     *
     * @return el DataSource configurado
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // Configuración básica
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);

        // Configuración del pool
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);

        // Configuraciones adicionales de rendimiento
        config.setAutoCommit(true);
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("HumanSolutionHikariCP");

        // Configuraciones para evitar fugas de conexiones
        config.setLeakDetectionThreshold(60000); // 60 segundos

        return new HikariDataSource(config);
    }
}
