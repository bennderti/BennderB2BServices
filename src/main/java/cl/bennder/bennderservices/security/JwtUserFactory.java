package cl.bennder.bennderservices.security;

import cl.bennder.entitybennderwebrest.model.Perfil;
import cl.bennder.entitybennderwebrest.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Usuario usuario) {
        return new JwtUser(
                usuario.getIdUsuario(),
                usuario.getUsuario(),
                usuario.getNombres(),
                usuario.getApellidoP(),
                usuario.getApellidoM(),
                usuario.getUsuario(),
                usuario.getPassword(),
                mapToGrantedAuthorities(usuario.getPerfiles()),
                usuario.getHabilitado()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Perfil> perfiles) {
        return perfiles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getNombre()))
                .collect(Collectors.toList());
    }
}