package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.business.facade.UsuarioFacade;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private static final String KEY_MENSAJE = "mensaje";
    private static final String KEY_ERROR = "error";
    private static final String MSG_REGISTRO_EXITOSO = "Usuario registrado exitosamente";
    private static final String MSG_ACTUALIZACION_EXITOSA = "Usuario actualizado exitosamente";
    private static final String MSG_ELIMINACION_EXITOSA = "Usuario eliminado exitosamente";

    private final UsuarioFacade usuarioFacade;

    public UsuarioController(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            // LOGS DE DEBUG
            System.out.println("======= DEBUG CONTROLLER =======");
            System.out.println("DTO Recibido: " + usuarioDTO);
            System.out.println("ID: " + usuarioDTO.getId());
            System.out.println("Documento: " + usuarioDTO.getDocumento());
            System.out.println("Nombre: " + usuarioDTO.getNombre());
            System.out.println("Correo: " + usuarioDTO.getCorreo());
            System.out.println("Contrasena: " + usuarioDTO.getContrasena());
            System.out.println("Rol: " + usuarioDTO.getRol());
            if (usuarioDTO.getRol() != null) {
                System.out.println("Rol ID: " + usuarioDTO.getRol().getId());
            }
            System.out.println("================================");

            usuarioFacade.register(usuarioDTO);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Usuario registrado exitosamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            System.err.println("ERROR EN CONTROLLER: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> list() {
        try {
            return ResponseEntity.ok(usuarioFacade.list());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return ResponseEntity.ok(usuarioFacade.findById(uuid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable String id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioDTO.setId(id);
            usuarioFacade.update(usuarioDTO);

            Map<String, String> response = new HashMap<>();
            response.put(KEY_MENSAJE, MSG_ACTUALIZACION_EXITOSA);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            usuarioFacade.delete(uuid);

            Map<String, String> response = new HashMap<>();
            response.put(KEY_MENSAJE, MSG_ELIMINACION_EXITOSA);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}