package RedNorte.Gestion.model;

import java.time.LocalDate;

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

@Entity
@Table(name = "listaEspera")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListaEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 40)
    private LocalDate fecha_solitud;

    @Column(nullable = false, length = 40)
    private String prioridad;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hospital", nullable = false)
    private Hospital hospital;
}
