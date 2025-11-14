package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.UsuarioDTOAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioBusiness;
import co.edu.uco.HumanSolution.business.business.impl.UsuarioBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.UsuarioFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class UsuarioFacadeImpl implements UsuarioFacade {

    private DAOFactory daoFactory;

    public UsuarioFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
        System.out.println("🟢 [FACADE] UsuarioFacadeImpl construido correctamente");
    }

    @Override
    public void register(UsuarioDTO dto) {
        System.out.println("🔵 [FACADE] register() - Iniciando registro de usuario");
        try {
            daoFactory.initTransaction();
            System.out.println("🔵 [FACADE] Transacción iniciada");

            var domain = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDomain(dto);
            System.out.println("🔵 [FACADE] DTO convertido a Domain");

            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            System.out.println("🔵 [FACADE] Business creado, llamando a register");

            business.register(domain);
            System.out.println("🔵 [FACADE] Usuario registrado en Business");

            daoFactory.commitTransaction();
            System.out.println("✅ [FACADE] Transacción confirmada exitosamente");

        } catch (HumanSolutionException exception) {
            System.err.println("❌ [FACADE] HumanSolutionException en register: " + exception.getMessage());
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (Exception exception) {
            System.err.println("❌ [FACADE] Exception genérica en register: " + exception.getMessage());
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade registrando usuario: " + exception.getMessage(),
                    "Error al registrar usuario",
                    exception
            );

        } finally {
            System.out.println("🔵 [FACADE] Cerrando conexión después de register");
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioDTO> list() {
        System.out.println("🔵 [FACADE] ==================== LIST INICIADO ====================");
        try {
            System.out.println("🔵 [FACADE] Creando UsuarioBusinessImpl...");
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            System.out.println("🔵 [FACADE] UsuarioBusinessImpl creado correctamente");

            System.out.println("🔵 [FACADE] Llamando a business.list()...");
            var domains = business.list();

            System.out.println("🔵 [FACADE] Resultado de business.list():");
            System.out.println("   - domains es null? " + (domains == null));
            if (domains != null) {
                System.out.println("   - Cantidad de domains: " + domains.size());
                System.out.println("   - Tipo de lista: " + domains.getClass().getName());
            }

            if (domains == null) {
                System.err.println("❌ [FACADE] ERROR CRÍTICO: business.list() devolvió NULL");
                System.err.println("❌ [FACADE] Devolviendo lista vacía para evitar error 500");
                return List.of();
            }

            System.out.println("🔵 [FACADE] Convirtiendo domains a DTOs...");
            var dtos = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDTOList(domains);

            System.out.println("🔵 [FACADE] Resultado de conversión a DTOs:");
            System.out.println("   - dtos es null? " + (dtos == null));
            if (dtos != null) {
                System.out.println("   - Cantidad de DTOs: " + dtos.size());
                System.out.println("   - Tipo de lista: " + dtos.getClass().getName());
                if (!dtos.isEmpty()) {
                    System.out.println("   - Primer DTO: " + dtos.get(0));
                }
            }

            System.out.println("✅ [FACADE] list() completado exitosamente con " + (dtos != null ? dtos.size() : 0) + " elementos");
            return dtos;

        } catch (HumanSolutionException exception) {
            System.err.println("❌ [FACADE] HumanSolutionException en list(): " + exception.getMessage());
            System.err.println("❌ [FACADE] User message: " + exception.getUserMessage());
            exception.printStackTrace();
            throw exception;

        } catch (Exception exception) {
            System.err.println("❌ [FACADE] Exception genérica en list(): " + exception.getMessage());
            System.err.println("❌ [FACADE] Tipo de excepción: " + exception.getClass().getName());
            exception.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando usuarios: " + exception.getMessage(),
                    "Error al listar usuarios",
                    exception
            );

        } finally {
            System.out.println("🔵 [FACADE] Cerrando conexión después de list()");
            daoFactory.closeConnection();
            System.out.println("🔵 [FACADE] ==================== LIST FINALIZADO ====================");
        }
    }

    @Override
    public UsuarioDTO findById(UUID id) {
        System.out.println("🔵 [FACADE] findById() - Buscando usuario con ID: " + id);
        try {
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            System.out.println("🔵 [FACADE] Business creado, llamando a findById");

            var domain = business.findById(id);
            System.out.println("🔵 [FACADE] Domain obtenido: " + (domain != null ? "Encontrado" : "NULL"));

            var dto = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDTO(domain);
            System.out.println("✅ [FACADE] Usuario encontrado y convertido a DTO");
            return dto;

        } catch (HumanSolutionException exception) {
            System.err.println("❌ [FACADE] HumanSolutionException en findById: " + exception.getMessage());
            exception.printStackTrace();
            throw exception;

        } catch (Exception exception) {
            System.err.println("❌ [FACADE] Exception genérica en findById: " + exception.getMessage());
            exception.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando usuario: " + exception.getMessage(),
                    "Error al buscar usuario",
                    exception
            );

        } finally {
            System.out.println("🔵 [FACADE] Cerrando conexión después de findById");
            daoFactory.closeConnection();
        }
    }

    @Override
    public UsuarioDTO findByEmail(String email) {
        System.out.println("🔵 [FACADE] findByEmail() - Buscando usuario con email: " + email);
        try {
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            System.out.println("🔵 [FACADE] Business creado, llamando a findByEmail");

            var domain = business.findByEmail(email);
            System.out.println("🔵 [FACADE] Domain obtenido: " + (domain != null ? "Encontrado" : "NULL"));

            var dto = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDTO(domain);
            System.out.println("✅ [FACADE] Usuario encontrado y convertido a DTO");
            return dto;

        } catch (HumanSolutionException exception) {
            System.err.println("❌ [FACADE] HumanSolutionException en findByEmail: " + exception.getMessage());
            exception.printStackTrace();
            throw exception;

        } catch (Exception exception) {
            System.err.println("❌ [FACADE] Exception genérica en findByEmail: " + exception.getMessage());
            exception.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando usuario por email: " + exception.getMessage(),
                    "Error al buscar usuario",
                    exception
            );

        } finally {
            System.out.println("🔵 [FACADE] Cerrando conexión después de findByEmail");
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(UsuarioDTO dto) {
        System.out.println("🔵 [FACADE] update() - Actualizando usuario con ID: " + dto.getId());
        try {
            daoFactory.initTransaction();
            System.out.println("🔵 [FACADE] Transacción iniciada");

            var domain = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDomain(dto);
            System.out.println("🔵 [FACADE] DTO convertido a Domain");

            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            System.out.println("🔵 [FACADE] Business creado, llamando a update");

            business.update(domain);
            System.out.println("🔵 [FACADE] Usuario actualizado en Business");

            daoFactory.commitTransaction();
            System.out.println("✅ [FACADE] Transacción confirmada exitosamente");

        } catch (HumanSolutionException exception) {
            System.err.println("❌ [FACADE] HumanSolutionException en update: " + exception.getMessage());
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (Exception exception) {
            System.err.println("❌ [FACADE] Exception genérica en update: " + exception.getMessage());
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando usuario: " + exception.getMessage(),
                    "Error al actualizar usuario",
                    exception
            );

        } finally {
            System.out.println("🔵 [FACADE] Cerrando conexión después de update");
            daoFactory.closeConnection();
        }
    }

    @Override
    public void delete(UUID id) {
        System.out.println("🔵 [FACADE] delete() - Eliminando usuario con ID: " + id);
        try {
            daoFactory.initTransaction();
            System.out.println("🔵 [FACADE] Transacción iniciada");

            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            System.out.println("🔵 [FACADE] Business creado, llamando a delete");

            business.delete(id);
            System.out.println("🔵 [FACADE] Usuario eliminado en Business");

            daoFactory.commitTransaction();
            System.out.println("✅ [FACADE] Transacción confirmada exitosamente");

        } catch (HumanSolutionException exception) {
            System.err.println("❌ [FACADE] HumanSolutionException en delete: " + exception.getMessage());
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (Exception exception) {
            System.err.println("❌ [FACADE] Exception genérica en delete: " + exception.getMessage());
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando usuario: " + exception.getMessage(),
                    "Error al eliminar usuario",
                    exception
            );

        } finally {
            System.out.println("🔵 [FACADE] Cerrando conexión después de delete");
            daoFactory.closeConnection();
        }
    }
}