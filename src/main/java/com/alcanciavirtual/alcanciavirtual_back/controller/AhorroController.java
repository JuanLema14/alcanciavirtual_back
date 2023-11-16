package com.alcanciavirtual.alcanciavirtual_back.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alcanciavirtual.alcanciavirtual_back.model.Ahorro;
import com.alcanciavirtual.alcanciavirtual_back.model.TipoAhorro;
import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.AhorroRepositorio;
import com.alcanciavirtual.alcanciavirtual_back.repository.MetasRepositorio;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@RestController
@RequestMapping("/${alcancia_dev.prefix}/ahorro")
public class AhorroController {

    @Autowired
    private MetasRepositorio metasRepositorio;

    @Autowired
    private AhorroRepositorio ahorroRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/ahorrototal")
    public ResponseEntity<Integer> getAhorroTotal(
            @RequestParam() Boolean valorMetas) {

        System.out.println("---------> Sumar ahorro total Controller");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();

            System.out.println("Usuario autenticado: " + username);

            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            if (usuarioAutenticado != null) {
                int valorTotalAhorro = ahorroRepositorio.sumValorAhorroByIdUsuario(usuarioAutenticado.getId());
                if (valorMetas) {
                    int valorTotal = metasRepositorio.sumValorMetasByIdUsuario(usuarioAutenticado.getId())
                            + valorTotalAhorro;
                    return ResponseEntity.ok(valorTotal);
                } else {
                    return ResponseEntity.ok(valorTotalAhorro);
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/listarahorro")
    public ResponseEntity<List<Ahorro>> listarAhorros() {
        System.out.println("---------> Listar ahorro Controller");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();

            System.out.println("Usuario autenticado: " + username);

            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            if (usuarioAutenticado != null) {
                List<Ahorro> ahorros = ahorroRepositorio.findByIdUsuario(usuarioAutenticado.getId());
                return ResponseEntity.ok(ahorros);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/agregarahorro")
    public ResponseEntity<Ahorro> crearAhorro(@RequestParam int valor_ahorro,
            @RequestParam TipoAhorro tipo_ahorro) {
        System.out.println("---------> Crear Ahorro controller");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();

            System.out.println("Usuario autenticado: " + username);

            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            LocalDateTime fechaIngreso = LocalDateTime.now();

            if (usuarioAutenticado != null) {
                Ahorro nuevoAhorro = new Ahorro(valor_ahorro, tipo_ahorro, fechaIngreso, usuarioAutenticado);
                Ahorro ahorroGuardado = ahorroRepositorio.save(nuevoAhorro);
                return ResponseEntity.ok(ahorroGuardado);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/consultarahorro/{id}")
    public ResponseEntity<?> getAhorroById(@PathVariable Integer id) {
        System.out.println("---------> Consultar Ahorro controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Ahorro ahorro = ahorroRepositorio.findById(id).orElse(null);

        if (ahorro != null && ahorro.getUsuario() == usuarioAutenticado) {
            return ResponseEntity.ok(ahorro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/actualizarahorro/{id}")
    public ResponseEntity<Ahorro> editarAhorro(@PathVariable Integer id, @RequestBody Ahorro ahorroActualizado) {
        System.out.println("---------> Actualizar Ahorro controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Ahorro ahorroExistente = ahorroRepositorio.findById(id).orElse(null);

        if (ahorroExistente != null && ahorroExistente.getUsuario() == usuarioAutenticado) {
            ahorroExistente.setValorAhorro(ahorroActualizado.getValorAhorro());
            ahorroExistente.setTipoAhorro(ahorroActualizado.getTipoAhorro());
            ahorroExistente.setFechaIngreso(ahorroExistente.getFechaIngreso());
            ahorroExistente.setUsuario(usuarioAutenticado);

            Ahorro ahorroActualizadoEnBD = ahorroRepositorio.save(ahorroExistente);
            return ResponseEntity.ok(ahorroActualizadoEnBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/retirarahorro/{id}")
    public ResponseEntity<?> deleteAhorro(@PathVariable Integer id) {
        System.out.println("---------> Retirar ahorro controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String username = authentication.getName();

        System.out.println("Usuario autenticado: " + username);

        Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

        Ahorro ahorroOpcional = ahorroRepositorio.findById(id).orElse(null);
        if (ahorroOpcional != null && ahorroOpcional.getUsuario() == usuarioAutenticado) {
            ahorroRepositorio.delete(ahorroOpcional);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
