package RedNorte.Gestion.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

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
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, length = 40)
    private String apellido;

    @Column(nullable = false, length = 40, unique = true)
    private String rut;

    @Column(nullable = false, length = 9)
    private Integer telefono;

    @Column(nullable = false, length = 40)
    private String correo;

    @Column(nullable = false, length = 40)
    private String direccion;

    @OneToMany(mappedBy = "cliente",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<CitaMedica> citas;
}
