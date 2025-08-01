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
@Table(name = "paulinha")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paulinha {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

}
