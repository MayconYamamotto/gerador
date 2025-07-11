package br.com.gerador.generated;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "idade", nullable = false)
    @Min(18)
    private Integer idade;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "datanascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "criadoem", nullable = false)
    private LocalDateTime criadoEm;

}
