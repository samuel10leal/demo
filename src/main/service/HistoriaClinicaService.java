package co.edu.uniandes.dse.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.bookstore.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.bookstore.entities.PacienteEntity;
import co.edu.uniandes.dse.bookstore.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.bookstore.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.bookstore.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public HistoriaClinicaEntity crearHistoriaClinica(HistoriaClinicaEntity historiaClinica, Long idPaciente) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Creacion de una historia clinica con condiciones");

        // Verificar que el paciente existe
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        // Verificar que la historia clínica tiene un diagnóstico y un tratamiento
        if (historiaClinica.getDiagnostico() == null || historiaClinica.getTratamiento() == null) {
            throw new IllegalArgumentException("La historia clinica debe tener al menos un diagnostico y un tratamiento");
        }

        // Modificar el diagnóstico si el paciente tiene un acudiente
        if (paciente.getAcudiente() != null) {
            historiaClinica.setDiagnostico("HistoriaCompartida-" + historiaClinica.getDiagnostico());
        }

        // Asignar la historia clínica al paciente
        historiaClinica.setPaciente(paciente);

        // Guardar la historia clínica en el repositorio
        return historiaClinicaRepository.save(historiaClinica);
    }
}
