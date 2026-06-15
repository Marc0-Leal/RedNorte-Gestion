package RedNorte.Gestion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "citaMedica")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private LocalDate fecha;

    @Column(nullable = false, length = 40)
    private Integer hora;

    @Column(nullable = false, length = 40)
    private String estado;

    @Column(nullable = false, length = 250)
    private String sintomas;

    @ManyToOne
    @JoinColumn(name = "medico", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "pago", nullable = false)
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "listaEspera", nullable = false)
    private ListaEspera listaEspera;
}