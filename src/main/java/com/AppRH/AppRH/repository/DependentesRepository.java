package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DependentesRepository extends CrudRepository<Dependentes, String> {

    Iterable<Dependentes> findByFuncionario(Funcionario funcionario);

    // thinking in delete method
    Dependentes findByCpf(String cpf);
    Dependentes findById(long id);

    // created for implement

    @Query(value = "select u from Dependentes u where u.nome like %?1%")
    List<Dependentes> findByNomesDependentes(String nome);
}
