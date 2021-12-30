package br.com.agenda.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.agenda.model.Contato;


public interface ContatoRepository extends CrudRepository<Contato, String>{

	Contato findByIdContato(long idContato);

}
