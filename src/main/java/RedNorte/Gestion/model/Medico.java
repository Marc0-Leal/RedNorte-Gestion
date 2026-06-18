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
import lombok.ToString; 
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medico")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, length = 40)
    private String apellido;

    @Column(nullable = false, length = 40)
    private String especialidad;

    @Column(nullable = false, length = 9)
    private Integer telefono;

    @Column(nullable = false, length = 40)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = true)
    @JsonIgnore     
    @ToString.Exclude
    private Hospital hospital;

}