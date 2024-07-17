package com.proyectoDeAlura.FOROHUB.controller;


import com.proyectoDeAlura.FOROHUB.Usuarios.DatosAutenticacionUsuario;
import com.proyectoDeAlura.FOROHUB.Usuarios.Usuario;
import com.proyectoDeAlura.FOROHUB.infra.security.DatosJWTtoken;
import com.proyectoDeAlura.FOROHUB.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody@Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken
                (datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var JWTtoken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));


    }
}
