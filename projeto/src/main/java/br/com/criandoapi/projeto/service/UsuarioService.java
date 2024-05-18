package br.com.criandoapi.projeto.service;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.repository.IUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    private IUsuario repository;
    private PasswordEncoder passwordEncoder;

    // CONSTRUTOR
    public UsuarioService(IUsuario repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // SERVICES

    // LISTAR USUARIOS GET
    public List<Usuario> listaUsuario() {
        List<Usuario> lista = repository.findAll();
        return lista;
    }

    // CRIAR USUARIO POST
    public Usuario criarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());  // ENCRIPITOGRAFAR A SENHA
        usuario.setSenha(encoder);  // SETAR A SENHA CRIPTOGRAFADA
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    // MUDAR USUARIO PUT
    public Usuario editarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());  // ENCRIPITOGRAFAR A SENHA
        usuario.setSenha(encoder);  // SETAR A SENHA CRIPTOGRAFADA
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    // DELETAR USUARIO DELETE
    public boolean excluirUsuario(Integer id) {
        repository.deleteById(id);
        return true;
    }

    // VALIDAR SENHA
    public Boolean validarSenha(Usuario usuario) {
        String senha = repository.getById(usuario.getId()).getSenha(); // PEGA A SENHA DO USUARIO PARA VALIDAR
        Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha); // VAI COMPARAR A SENHA
        return valid;
    }
}
