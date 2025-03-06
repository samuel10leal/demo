package co.edu.uniandes.dse.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    
    List<HistoriaClinica> findAll();

    List<HistoriaClinica> findById(Long id);

}