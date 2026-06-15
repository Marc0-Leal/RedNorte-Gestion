package RedNorte.Gestion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private Integer monto;

    @Column(nullable = false, length = 40)
    private LocalDate fecha_pago;

    @Column(nullable = false, length = 40)
    private String metodo_pago;

    @Column(nullable = false, length = 40)
    private String estado;
}
