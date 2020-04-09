package com.teste.magnum.gerenciadorprocessosapi.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.teste.magnum.gerenciadorprocessosapi.model.StatusProcessoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ElementCollection
    @Column(name="usuarios")
    private Collection<String> usuariosVinculados;

    @Enumerated(EnumType.ORDINAL)
    private StatusProcessoEnum statusProcesso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parecer_id", referencedColumnName = "id")
    private Parecer parecer;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataRegistro;

}
