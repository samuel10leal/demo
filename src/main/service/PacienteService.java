package co.edu.uniandes.dse.bookstore.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.bookstore.entities.AuthorEntity;
import co.edu.uniandes.dse.bookstore.entities.BookEntity;
import co.edu.uniandes.dse.bookstore.entities.PrizeEntity;
import co.edu.uniandes.dse.bookstore.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.bookstore.exceptions.ErrorMessage;
import co.edu.uniandes.dse.bookstore.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.bookstore.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Author.
 *
 * @author ISIS2603
 */

@Slf4j
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;



    @Transactional
    public PacienteEntity crearPaciente(PacienteEntity paciente) {
        log.info("Creacion de un paciente");
        String telefono= paciente.getTelefono();
        if(telefono==null||telefono.length!=11|| telefono.startsWith("311")==false||telefono.startsWith("601")==false)
        {
            throw new IllegalArgumentException("El telefono no es valido");
        }

        return pacienteRepository.save(paciente);
        }
        
        @Transactional
        public PacienteEntity asociarAcudiente(Long idPaciente, Long idAcudiente) throws EntityNotFoundException, IllegalOperationException {
            log.info("Asociar acudiente");
    
            Optional<PacienteEntity> pacienteOpt = pacienteRepository.findById(idPaciente);
            Optional<PacienteEntity> acudienteOpt = pacienteRepository.findById(idAcudiente);
    
            if (!pacienteOpt.isPresent()) {
                throw new EntityNotFoundException("Paciente no encontrado");
            }
    
            if (!acudienteOpt.isPresent()) {
                throw new EntityNotFoundException("Acudiente no encontrado");
            }
    
            PacienteEntity paciente = pacienteOpt.get();
            PacienteEntity acudiente = acudienteOpt.get();
    
            if (paciente.getAcudiente() != null) {
                throw new IllegalOperationException("El paciente ya tiene un acudiente asignado");
            }
    
            if (acudiente.getAcudiente() != null) {
                throw new IllegalOperationException("El acudiente ya tiene un acudiente asignado");
            }
    
            if (acudiente.getHistoriasClinicas() == null || acudiente.getHistoriasClinicas().isEmpty()) {
                throw new IllegalOperationException("El acudiente debe tener al menos una historia clínica creada a su nombre");
            }
    
            paciente.setAcudiente(acudiente);
            return pacienteRepository.save(paciente);
        }
}