package com.alcanciavirtual.alcanciavirtual_back.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.alcanciavirtual.alcanciavirtual_back.model.EstadoServicio;
import com.alcanciavirtual.alcanciavirtual_back.model.Servicios;
import com.alcanciavirtual.alcanciavirtual_back.model.TipoServicio;
import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.ServiciosRepositorio;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@RestController
@RequestMapping("/${alcancia_dev.prefix}/servicios")
public class ServiciosController {

    @Autowired
    private ServiciosRepositorio serviciosRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping(value = "/crearservicio")
    public ResponseEntity<Servicios> crearServicio(
            @RequestParam String nombre_servicio,
            @RequestParam Integer valor_servicio,
            @RequestParam EstadoServicio estado,
            @RequestParam Integer cantidad_meses,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime fecha_pago,
            @RequestParam TipoServicio tipo_servicio) {

        System.out.println("---------> Crear Servicio controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Servicios nuevoServicio = new Servicios(nombre_servicio, valor_servicio, estado, cantidad_meses, fecha_pago,
                tipo_servicio, usuarioAutenticado);

        Servicios servicioCreado = serviciosRepositorio.save(nuevoServicio);

        return ResponseEntity.ok(servicioCreado);
    }

    @GetMapping("/listarservicios")
    public ResponseEntity<Page<Servicios>> getAllServicios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam() int rowsPerPage) {

        System.out.println("---------> Listar Servicios controller");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();

            System.out.println("Usuario autenticado: " + username);

            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            System.out.println(usuarioAutenticado);
            if (usuarioAutenticado != null) {
                Page<Servicios> serviciosPage = serviciosRepositorio.findByIdUsuario(usuarioAutenticado.getId(),
                        PageRequest.of(page, rowsPerPage));
                return ResponseEntity.ok(serviciosPage);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/consultarservicio/{id}")
    public ResponseEntity<?> getServicioById(@PathVariable Integer id) {
        System.out.println("---------> Consultar Servicio controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Servicios servicio = serviciosRepositorio.findById(id).orElse(null);

        if (servicio != null && usuarioAutenticado == servicio.getUsuario()) {
            return ResponseEntity.ok(servicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarservicio/{id}")
    public ResponseEntity<Servicios> editarServicio(@PathVariable Integer id,
            @RequestBody Servicios servicioActualizada) {
        System.out.println("---------> Actualizar Servicio controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Servicios servicioExistente = serviciosRepositorio.findById(id).orElse(null);

        if (servicioExistente != null && usuarioAutenticado == servicioExistente.getUsuario()) {
            servicioExistente.setNombre_servicio(servicioActualizada.getNombre_servicio());
            servicioExistente.setValor_servicio(servicioActualizada.getValor_servicio());
            servicioExistente.setEstado(servicioActualizada.getEstado());
            servicioExistente.setCantidad_meses(servicioActualizada.getCantidad_meses());
            servicioExistente.setFecha_pago(servicioActualizada.getFecha_pago());
            servicioExistente.setTipo_servicio(servicioActualizada.getTipo_servicio());
            servicioExistente.setUsuario(servicioActualizada.getUsuario());

            Servicios servicioActualizadoEnBD = serviciosRepositorio.save(servicioExistente);
            return ResponseEntity.ok(servicioActualizadoEnBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/retirarservicio/{id}")
    public ResponseEntity<?> deleteServicio(@PathVariable Integer id) {
        System.out.println("---------> Retirar Servicio controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Servicios servicio = serviciosRepositorio.findById(id).orElse(null);

        if (servicio != null && usuarioAutenticado == servicio.getUsuario()) {
            serviciosRepositorio.delete(servicio);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
