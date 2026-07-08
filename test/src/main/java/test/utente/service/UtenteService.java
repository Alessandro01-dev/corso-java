package test.utente.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.utente.entity.UtenteEntity;
import test.utente.model.Utente;
import test.utente.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository repository;
	
	public Utente findById(Long id) {
		
		Optional<UtenteEntity> entity = repository.findById(id);
		
		UtenteEntity ent = entity.isPresent() ? entity.get() : new UtenteEntity();
		Utente utente = new Utente();
		BeanUtils.copyProperties(ent, utente);
		
		return utente;
	}
	
	public void saveUser(Utente utente) {
		
		UtenteEntity entity = new UtenteEntity();
		BeanUtils.copyProperties(utente, entity);
		
		repository.save(entity);

	}
}
