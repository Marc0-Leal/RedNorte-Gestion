package RedNorte.Gestion.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pago")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 40)
    private Integer monto;

    @Column(nullable = false, length = 40)
    private Date fecha_pago;

    @Column(nullable = false, length = 40)
    private String metodo_pago;

    @Column(nullable = false, length = 40)
    private String estado;
}
