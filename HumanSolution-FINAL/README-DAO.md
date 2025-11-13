# Refactorización de DAOs con DataSource

## Problema Resuelto

**Error Original:**
```
DAO-EXISTS: Error al verificar existencia de evaluación: This connection has been closed.
```

Este error ocurría porque los DAOs estaban gestionando conexiones de base de datos de forma manual, lo que causaba que las conexiones se cerraran prematuramente o no se reutilizaran correctamente.

## Solución Implementada

Se ha refactorizado toda la capa de acceso a datos (DAO) para utilizar **inyección de dependencias de DataSource** con pool de conexiones **HikariCP**.

### Arquitectura de la Solución

```
┌─────────────────────────────────────────┐
│      Spring Boot Application            │
└─────────────────┬───────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────┐
│     DataSourceConfig (Bean)             │
│  - Configura HikariCP Pool              │
│  - Gestiona ciclo de vida conexiones   │
└─────────────────┬───────────────────────┘
                  │ Inyecta DataSource
                  ▼
┌─────────────────────────────────────────┐
│          BaseDAO                        │
│  - Recibe DataSource en constructor     │
│  - Proporciona getConnection()          │
│  - Gestiona cierre de conexiones       │
└─────────────────┬───────────────────────┘
                  │ Herencia
                  ▼
┌─────────────────────────────────────────┐
│  EvaluacionDesempenoDAO                 │
│  UsuarioDAO                             │
│  ContratoDAO                            │
│  - Usan conexiones del pool             │
│  - No cierran el DataSource             │
│  - Try-with-resources cierra conexiones │
└─────────────────────────────────────────┘
```

## Componentes Creados

### 1. Configuración (`DataSourceConfig.java`)

Configura el pool de conexiones HikariCP con:
- Pool máximo: 10 conexiones
- Pool mínimo: 5 conexiones
- Timeout de conexión: 30 segundos
- Timeout de idle: 10 minutos
- Detección de fugas: 60 segundos

```java
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        // Configuración del pool...
        return new HikariDataSource(config);
    }
}
```

### 2. Clase Base (`BaseDAO.java`)

Proporciona funcionalidad común para todos los DAOs:

```java
public abstract class BaseDAO {
    protected final DataSource dataSource;

    protected BaseDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
```

### 3. DAOs Implementados

#### EvaluacionDesempenoDAO
- `exists(UUID id)` - **Resuelve el error original**
- `existsByUsuarioAndFecha(UUID idUsuario, LocalDate fecha)`
- `create(EvaluacionDesempeno evaluacion)`
- `findById(UUID id)`
- `findByUsuario(UUID idUsuario)`
- `findAll()`
- `update(EvaluacionDesempeno evaluacion)`
- `delete(UUID id)`

#### UsuarioDAO
- `exists(UUID id)`
- `existsByEmail(String email)`
- `create(Usuario usuario)`
- `findById(UUID id)`
- `findByEmail(String email)`
- `findAll()`
- `update(Usuario usuario)`
- `delete(UUID id)`

#### ContratoDAO
- `exists(UUID id)`
- `isVigente(UUID id)`
- `create(Contrato contrato)`
- `findById(UUID id)`
- `findByUsuario(UUID idUsuario)`
- `findVigentesByUsuario(UUID idUsuario)`
- `findAll()`
- `update(Contrato contrato)`
- `delete(UUID id)`

### 4. Entidades de Dominio

- `EvaluacionDesempeno.java` - Con todos los campos de la tabla
- `Usuario.java` - Información básica de usuarios
- `Contrato.java` - Gestión de contratos

### 5. Controlador REST de Ejemplo

`EvaluacionDesempenoController.java` - Demuestra el uso correcto de los DAOs:

```java
@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionDesempenoController {
    private final EvaluacionDesempenoDAO evaluacionDAO;

    // Spring inyecta el DAO con DataSource configurado
    public EvaluacionDesempenoController(EvaluacionDesempenoDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }
}
```

## Patrón de Uso

### ✅ Correcto - Con DataSource

```java
@Repository
public class MiDAO extends BaseDAO {

    public MiDAO(DataSource dataSource) {
        super(dataSource);
    }

    public boolean exists(UUID id) {
        String sql = "SELECT COUNT(*) FROM tabla WHERE id = ?";

        // Try-with-resources cierra automáticamente
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }
}
```

### ❌ Incorrecto - Gestión manual

```java
public class MiDAO {
    private Connection conn; // Mala práctica

    public void connect() {
        // Crear conexión manualmente
    }

    public boolean exists(UUID id) {
        // Usar this.conn que puede estar cerrada
    }
}
```

## Ventajas de esta Arquitectura

1. **Pool de Conexiones**: Reutilización eficiente de conexiones
2. **Gestión Automática**: Spring y HikariCP manejan el ciclo de vida
3. **Detección de Fugas**: HikariCP detecta conexiones no cerradas
4. **Thread-Safe**: Seguro para entornos multi-hilo
5. **Escalabilidad**: Configuración ajustable según necesidades
6. **Mantenibilidad**: Código limpio y centralizado
7. **Testabilidad**: Fácil de mockear para pruebas unitarias

## Configuración (application.properties)

```properties
# Pool de conexiones HikariCP
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=600000
```

## Próximos Pasos

1. Implementar servicios que usen estos DAOs
2. Agregar validaciones de negocio en capa de servicio
3. Implementar transacciones con `@Transactional`
4. Agregar pruebas unitarias e integración
5. Implementar manejo de excepciones personalizado

## Estructura de Paquetes

```
co.edu.uco.HumanSolution/
├── config/
│   └── DataSourceConfig.java
├── dao/
│   ├── BaseDAO.java
│   ├── EvaluacionDesempenoDAO.java
│   ├── UsuarioDAO.java
│   └── ContratoDAO.java
├── domain/
│   ├── EvaluacionDesempeno.java
│   ├── Usuario.java
│   └── Contrato.java
├── controller/
│   └── EvaluacionDesempenoController.java
└── initializer/
    └── HumanSolutionApplication.java
```

## Notas Importantes

- **Nunca** cerrar el DataSource desde un DAO
- **Siempre** usar try-with-resources para Connection, PreparedStatement y ResultSet
- Los DAOs son **@Repository** para ser detectados por Spring
- El DataSource es **singleton** y thread-safe
- HikariCP viene incluido con spring-boot-starter-data-jpa

## Autores

- Samuel y Andrés
- Refactorización DAO: 2025
