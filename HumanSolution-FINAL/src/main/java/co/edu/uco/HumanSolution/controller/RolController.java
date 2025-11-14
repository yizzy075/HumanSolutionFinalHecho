package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.business.facade.RolFacade;
import co.edu.uco.HumanSolution.business.facade.impl.RolFacadeImpl;
import co.edu.uco.HumanSolution.controller.dto.response.ResponseDTO;
import co.edu.uco.HumanSolution.crosscutting.exception.ControllerException;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.dto.RolDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolFacade facade;

    public RolController() {
        System.out.println("=== RolController CONSTRUIDO ===");
        try {
            this.facade = new RolFacadeImpl();
            System.out.println("=== RolController INICIALIZADO - Endpoint /api/v1/roles DISPONIBLE ===");
        } catch (Exception e) {
            System.err.println("ERROR al crear RolController: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<RolDTO>> create(@RequestBody RolDTO dto) {
        try {
            facade.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseDTO.<RolDTO>builder()
                            .success(true)
                            .message("Rol creado exitosamente")
                            .data(null)
                            .build());
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado creando rol: " + exception.getMessage(),
                    "Error al crear rol",
                    exception
            );
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<RolDTO>>> list() {
        try {
            List<RolDTO> roles = facade.list();
            return ResponseEntity.ok(ResponseDTO.<List<RolDTO>>builder()
                    .success(true)
                    .message("Roles consultados exitosamente")
                    .data(roles)
                    .build());
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado listando roles: " + exception.getMessage(),
                    "Error al listar roles",
                    exception
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<RolDTO>> findById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            RolDTO rol = facade.findById(uuid);
            return ResponseEntity.ok(ResponseDTO.<RolDTO>builder()
                    .success(true)
                    .message("Rol consultado exitosamente")
                    .data(rol)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
                    "ID de rol inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado buscando rol: " + exception.getMessage(),
                    "Error al buscar rol",
                    exception
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<RolDTO>> update(@PathVariable String id, @RequestBody RolDTO dto) {
        try {
            dto.setId(id);
            facade.update(dto);
            return ResponseEntity.ok(ResponseDTO.<RolDTO>builder()
                    .success(true)
                    .message("Rol actualizado exitosamente")
                    .data(null)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
                    "ID de rol inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado actualizando rol: " + exception.getMessage(),
                    "Error al actualizar rol",
                    exception
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            facade.delete(uuid);
            return ResponseEntity.ok(ResponseDTO.<Void>builder()
                    .success(true)
                    .message("Rol eliminado exitosamente")
                    .data(null)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
                    "ID de rol inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado eliminando rol: " + exception.getMessage(),
                    "Error al eliminar rol",
                    exception
            );
        }
    }
}
