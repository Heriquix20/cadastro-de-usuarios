package br.com.criandoapi.projeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data // COM O LOMBOK ELE PEGA GET E SET E CONSTRUTORES

//@Getter // COM O LOMBOK ELE PEGA GETS
//@Setter // COM O LOMBOK ELE PEGA OS SETS

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    // NOT BLANK PARA VERIFICAR SE EXISTE UM ATRIBUTO
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, message = "O nome deve ter no minimo 3 caracteres!")  // MINIMO DE CARACTERES
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Email(message = "Insira um email válido!")
    @NotBlank(message = "O email é obrigatório")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatório")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @NotBlank(message = "O telefone é obrigatório")
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

}
