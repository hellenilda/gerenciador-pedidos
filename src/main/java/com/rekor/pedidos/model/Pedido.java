package com.rekor.pedidos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn
    private Cliente cliente;
}
