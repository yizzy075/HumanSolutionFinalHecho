package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.EvaluacionDesempenoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.EvaluacionDesempenoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.EvaluacionDesempenoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.EvaluacionDesempenoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.ContratoDTO;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import co.edu.uco.HumanSolution.entity.ContratoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public final class EvaluacionDesempenoFacadeImpl implements EvaluacionDesempenoFacade {

    private DAOFactory daoFactory;

    public EvaluacionDesempenoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(EvaluacionDesempenoDTO dto) {
        try {
            // ✅ PATRÓN CORRECTO: Iniciar transacción ANTES de cualquier operación
            daoFactory.initTransaction();

            System.out.println("======= DEBUG FACADE CREATE =======");
            System.out.println("DTO recibido: " + dto);

            // ✅ Auto-asignar evaluador si viene null
            if (dto.getEvaluador() == null || dto.getEvaluador().getId() == null || dto.getEvaluador().getId().isBlank()) {
                System.out.println("⚠️ Evaluador null, asignando usuario como evaluador");
                UsuarioDTO evaluador = new UsuarioDTO();
                evaluador.setId(dto.getUsuario().getId());
                dto.setEvaluador(evaluador);
            }

            // ✅ Buscar contrato activo DENTRO de la misma transacción
            if (dto.getContrato() == null || dto.getContrato().getId() == null || dto.getContrato().getId().isBlank()) {
                System.out.println("⚠️ Contrato null, buscando contrato activo...");

                UUID usuarioId = UUID.fromString(dto.getUsuario().getId());
                List<ContratoEntity> contratos = daoFactory.getContratoDAO().findByUsuario(usuarioId);

                if (contratos.isEmpty()) {
                    throw new HumanSolutionException(
                            "El usuario no tiene contratos registrados",
                            "No se encontraron contratos para el usuario"
                    );
                }

                // Buscar contrato vigente
                LocalDate hoy = LocalDate.now();
                ContratoEntity contratoActivo = contratos.stream()
                        .filter(c -> {
                            boolean despuesInicio = !c.getFechaInicio().isAfter(hoy);
                            boolean antesFin = c.getFechaFin() == null || !c.getFechaFin().isBefore(hoy);
                            return despuesInicio && antesFin;
                        })
                        .findFirst()
                        .orElse(null);

                if (contratoActivo == null) {
                    throw new HumanSolutionException(
                            "El usuario no tiene un contrato vigente",
                            "No se encontró contrato activo"
                    );
                }

                System.out.println("✅ Contrato activo encontrado: " + contratoActivo.getId());
                ContratoDTO contratoDTO = new ContratoDTO();
                contratoDTO.setId(contratoActivo.getId().toString());
                dto.setContrato(contratoDTO);
            }

            // ✅ Convertir DTO a Domain y crear
            var domain = EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDomain(dto);
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            business.create(domain);

            // ✅ Commit si todo salió bien
            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando evaluación: " + exception.getMessage(),
                    "Error al crear evaluación",
                    exception
            );
        } finally {
            // ✅ Cerrar conexión al final
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EvaluacionDesempenoDTO> list() {
        try {
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            var domains = business.list();
            return EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando evaluaciones: " + exception.getMessage(),
                    "Error al listar evaluaciones",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EvaluacionDesempenoDTO> findByUsuario(UUID idUsuario) {
        try {
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando evaluaciones por usuario: " + exception.getMessage(),
                    "Error al buscar evaluaciones",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public EvaluacionDesempenoDTO findById(UUID id) {
        try {
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando evaluación: " + exception.getMessage(),
                    "Error al buscar evaluación",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(EvaluacionDesempenoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDomain(dto);
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando evaluación: " + exception.getMessage(),
                    "Error al actualizar evaluación",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.initTransaction();

            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando evaluación: " + exception.getMessage(),
                    "Error al eliminar evaluación",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}