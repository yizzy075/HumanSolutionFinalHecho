package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.UsuarioEntityAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.entity.UsuarioEntity;

import java.util.List;
import java.util.UUID;

public final class UsuarioBusinessImpl implements UsuarioBusiness {

    private DAOFactory daoFactory;

    public UsuarioBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void register(UsuarioDomain domain) {
        try {
            domain.validar();

            // Validar que no exista usuario con mismo email
            try {
                if (daoFactory.getUsuarioDAO().existsByEmail(domain.getCorreo())) {
                    throw new HumanSolutionException(
                            "Ya existe un usuario con ese correo electrónico",
                            "El correo ya está registrado"
                    );
                }
            } catch (HumanSolutionException e) {
                if (e.getUserMessage().contains("verificar")) {
                    throw new HumanSolutionException(
                            e.getTechnicalMessage(),
                            "Error de conexión a la base de datos. Verifique que PostgreSQL esté corriendo.",
                            e.getRootException()
                    );
                }
                throw e;
            }

            // Validar que no exista usuario con mismo documento
            try {
                if (daoFactory.getUsuarioDAO().existsByDocumento(domain.getDocumento())) {
                    throw new HumanSolutionException(
                            "Ya existe un usuario con ese documento",
                            "El documento ya está registrado"
                    );
                }
            } catch (HumanSolutionException e) {
                if (e.getUserMessage().contains("verificar")) {
                    throw new HumanSolutionException(
                            e.getTechnicalMessage(),
                            "Error de conexión a la base de datos. Verifique que PostgreSQL esté corriendo.",
                            e.getRootException()
                    );
                }
                throw e;
            }

            var id = generateId();
            var domainWithId = UsuarioDomain.create(
                    id,
                    domain.getDocumento(),
                    domain.getNombre(),
                    domain.getCorreo(),
                    domain.getContrasenia(),
                    domain.getIdRol()
            );

            var entity = UsuarioEntityAssembler.getUsuarioEntityAssembler().toEntity(domainWithId);
            daoFactory.getUsuarioDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico registrando usuario: " + exception.getMessage(),
                    "Error inesperado al registrar usuario",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioDomain> list() {
        try {
            UsuarioEntity filter = UsuarioEntity.create();
            List<UsuarioEntity> entities = daoFactory.getUsuarioDAO().read(filter);
            return UsuarioEntityAssembler.getUsuarioEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando usuarios: " + exception.getMessage(),
                    "Error inesperado al listar usuarios",
                    exception
            );
        }
    }

    @Override
    public UsuarioDomain findById(UUID id) {
        try {
            // ✅ CAMBIO 1: Último parámetro de UUID a String
            UsuarioEntity filter = UsuarioEntity.create(id, "", "", "", "", "");
            List<UsuarioEntity> entities = daoFactory.getUsuarioDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Usuario con ID " + id + " no existe",
                        "No se encontró el usuario"
                );
            }

            return UsuarioEntityAssembler.getUsuarioEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando usuario: " + exception.getMessage(),
                    "Error inesperado al buscar usuario",
                    exception
            );
        }
    }

    @Override
    public UsuarioDomain findByEmail(String email) {
        try {
            // ✅ CAMBIO 2: Ambos parámetros UUID a String
            UsuarioEntity filter = UsuarioEntity.create(UUIDHelper.getDefaultUUID(), "", "", email, "", "");
            List<UsuarioEntity> entities = daoFactory.getUsuarioDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Usuario con email " + email + " no existe",
                        "No se encontró el usuario"
                );
            }

            return UsuarioEntityAssembler.getUsuarioEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando usuario por email: " + exception.getMessage(),
                    "Error inesperado al buscar usuario",
                    exception
            );
        }
    }

    @Override
    public void update(UsuarioDomain domain) {
        try {
            domain.validar();

            var entity = UsuarioEntityAssembler.getUsuarioEntityAssembler().toEntity(domain);
            daoFactory.getUsuarioDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando usuario: " + exception.getMessage(),
                    "Error inesperado al actualizar usuario",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getUsuarioDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando usuario: " + exception.getMessage(),
                    "Error inesperado al eliminar usuario",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();

        var entity = UsuarioEntity.create(id, "", "", "", "", "");
        var existing = daoFactory.getUsuarioDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();

            entity = UsuarioEntity.create(id, "", "", "", "", "");
            existing = daoFactory.getUsuarioDAO().read(entity);
        }

        return id;
    }
}