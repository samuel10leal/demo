package co.edu.uniandes.dse.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
	
    
    List<PacienteEntity> findAll();


    List<PacienteEntity> findById(Long id);

   
    List<PacienteEntity> findByName(String name);

    
}
