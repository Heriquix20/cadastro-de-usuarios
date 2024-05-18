package br.com.criandoapi.projeto.controller;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")  // LIBERAR TODAS PORTAS
@RequestMapping("/usuarios")
public class UsuarioController  {

    private UsuarioService service;

    // CONSTRUTOR
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

/////////// ENDPOINTS

    @GetMapping
    public ResponseEntity<List<Usuario>> listaDeUsuarios() {
        return ResponseEntity.status(200).body(service.listaUsuario());    // DAR O RETORNO PADRAO DO GET 200
    }

    @PostMapping  // ADICIONAR UM USUARIO COM METODO POST
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(service.criarUsuario(usuario));   // DAR O RETORNO PADRAO DO POST 201
    }

    @PutMapping  // EDITAR UM USUARIO
    public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(200).body(service.editarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {  // ? PARA PASSA QUALQUER TIPO
        service.excluirUsuario(id);
        return ResponseEntity.status(204).build();  // DAR O RETORNO PADRAO DO DELETE 204
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> validarSenha(@Valid @RequestBody Usuario usuario) {
        Boolean valid = service.validarSenha(usuario);
        if (!valid) { // SE FOR FALSO
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }

    // INTERCEPTAR O ERRO
    @ResponseStatus(HttpStatus.BAD_REQUEST) // SEMPRE QUE HOUVER UMA BAD REQUEST ELE VAI CAIR NESTE METODO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidadionException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



}
