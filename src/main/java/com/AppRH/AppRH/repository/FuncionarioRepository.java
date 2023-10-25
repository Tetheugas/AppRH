package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {
    Funcionario findById(long id);

    // busca?
    Funcionario findByNome(String nome);
}
