

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.access.method.P;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import co.edu.uniandes.dse.bookstore.podam.DateStrategy;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Entity

public class PacienteEntity extends BaseEntity{
    private String nombre;
    private String correo;
    private String telefono;

    @PodamExclude
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Lista<HistoriaClinicaEntity> historiasClinicas;

    @OneToOne
    private  PacienteEntity acudiente;
}