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


@Entity
@Table(name = "hospital")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, length = 40)
    private String direccion;

    @Column(nullable = false, length = 9)
    private Integer telefono;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "comuna", nullable = false)
    private Comuna comuna;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hospital", nullable = true)
    private Hospital hospital;
}
