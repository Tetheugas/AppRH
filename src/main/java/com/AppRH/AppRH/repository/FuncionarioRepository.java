package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.models.Vaga;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {
    Funcionario findById(long id);

    // busca?
    Funcionario findByNome(String nome);

    // para busca
    @Query(value = "select u from Funcionario u where u.nome like %?1%")
    List<Funcionario>findByNomes(String nome);
}
