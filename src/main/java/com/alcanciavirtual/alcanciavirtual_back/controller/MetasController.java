package com.alcanciavirtual.alcanciavirtual_back.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.alcanciavirtual.alcanciavirtual_back.model.EstadoMeta;
import com.alcanciavirtual.alcanciavirtual_back.model.Metas;
import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.MetasRepositorio;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@RestController
@RequestMapping("/${alcancia_dev.prefix}/metas")
public class MetasController {

    @Autowired
    private MetasRepositorio metasRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping(value = "/crearmeta")
    public ResponseEntity<Metas> crearMeta(
            @RequestParam int cantidad_meta,
            @RequestParam int cantidad_abonada,
            @RequestParam EstadoMeta estado,
            @RequestParam String descripcion_meta,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime fecha_esperada) {

        System.out.println("---------> Crear Meta controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        LocalDateTime fechaCreacion = LocalDateTime.now();

        Metas nuevaMeta = new Metas(cantidad_meta, cantidad_abonada, estado, descripcion_meta, usuarioAutenticado,
                fechaCreacion, fecha_esperada);

        Metas metaCreado = metasRepositorio.save(nuevaMeta);

        return ResponseEntity.ok(metaCreado);
    }

    @GetMapping("/listarmetas")
    public ResponseEntity<Page<Metas>> getAllMetas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam() int rowsPerPage) {

        System.out.println("---------> Listar Metas controller");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();

            System.out.println("Usuario autenticado: " + username);

            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            System.out.println(usuarioAutenticado);
            if (usuarioAutenticado != null) {
                Page<Metas> metasPage = metasRepositorio.findByIdUsuario(usuarioAutenticado.getId(),
                        PageRequest.of(page, rowsPerPage));
                return ResponseEntity.ok(metasPage);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/consultarmeta/{id}")
    public ResponseEntity<?> getMetaById(@PathVariable Integer id) {
        System.out.println("---------> Consultar Meta controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Metas meta = metasRepositorio.findById(id).orElse(null);

        if (meta != null && meta.getUsuario() == usuarioAutenticado) {
            return ResponseEntity.ok(meta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarmeta/{id}")
    public ResponseEntity<Metas> editarMeta(@PathVariable Integer id, @RequestBody Metas metaActualizada) {
        System.out.println("---------> Actualizar Meta controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Metas metaExistente = metasRepositorio.findById(id).orElse(null);

        if (metaExistente != null && metaExistente.getUsuario() == usuarioAutenticado) {
            metaExistente.setCantidad_meta(metaActualizada.getCantidad_meta());
            metaExistente.setCantidad_abonada(metaActualizada.getCantidad_abonada());
            metaExistente.setEstado(metaActualizada.getEstado());
            metaExistente.setDescripcion_meta(metaActualizada.getDescripcion_meta());
            metaExistente.setUsuario(usuarioAutenticado);
            metaExistente.setFecha_esperada(metaActualizada.getFecha_esperada());

            Metas metaActualizadaEnBD = metasRepositorio.save(metaExistente);
            return ResponseEntity.ok(metaActualizadaEnBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/retirarmeta/{id}")
    public ResponseEntity<?> deleteMeta(@PathVariable Integer id) {
        System.out.println("---------> Retirar Meta controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Metas metaOptional = metasRepositorio.findById(id).orElse(null);
        if (metaOptional != null && metaOptional.getUsuario() == usuarioAutenticado) {
            metasRepositorio.delete(metaOptional);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
