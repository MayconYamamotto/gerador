package br.com.gerador.generated;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "pessoaid", nullable = false)
    private UUID pessoaId;

}
