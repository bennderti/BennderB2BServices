package cl.bennder.bennderservices.security;

import cl.bennder.bennderservices.mapper.UsuarioMapper;
import cl.bennder.entitybennderwebrest.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioMapper.getUsuarioByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("No existe usuario con username '%s'.", username));
        } else {
            return JwtUserFactory.create(usuario);
        }
    }
}