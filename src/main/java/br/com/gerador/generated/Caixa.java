package br.com.gerador.generated;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "caixa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caixa {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "dataabertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "datafechamento")
    private LocalDateTime dataFechamento;

    @Column(name = "valorabertura", nullable = false)
    private BigDecimal valorAbertura;

    @Column(name = "valorfechamento")
    private BigDecimal valorFechamento;

}
