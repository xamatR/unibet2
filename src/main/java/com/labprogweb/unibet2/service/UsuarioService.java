package com.labprogweb.unibet2.service;

// import com.labprogweb.unibet2.exception.SenhaInvalidaException;
import com.labprogweb.unibet2.Model.entity.Usuario;
import com.labprogweb.unibet2.Model.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final PasswordEncoder encoder;
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(@Lazy PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }
        throw new UsernameNotFoundException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String[] roles = usuario.isAdmin()
                ? new String[]{"ADMIN", "USER"}
                : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public Optional<Usuario> findByLogin(String login) {
        return repository.findByLogin(login);

    }
}