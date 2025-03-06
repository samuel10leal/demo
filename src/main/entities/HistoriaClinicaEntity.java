import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.access.method.P;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import co.edu.uniandes.dse.bookstore.podam.DateStrategy;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Entity
public class HistoriaClinicaEntity extends BaseEntity{
    private String diagnostico;
    private String tratamiento;
    
    @Temporal(TemporalType.DATE)
	private Date fechaDeCreacionDate;

    @PodamExclude
    @ManyToOne
    private List<HistoriaClinicaEntity> historiasClinicas;
}