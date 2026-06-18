package RedNorte.Gestion.model;

import jakarta.persistence.CascadeType;
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

    @Column(nullable = true, length = 40)
    private LocalDate fecha;

    @Column(nullable = true, length = 40)
    private Integer hora;

    @Column(nullable = true, length = 40)
    private String estado;

    @Column(nullable = true)
    private String sintomas;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "medico", nullable = false)
    private Medico medico;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "pago", nullable = false)
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = true)
    private Cliente cliente;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "listaEspera", nullable = true)
    private ListaEspera listaEspera;
}