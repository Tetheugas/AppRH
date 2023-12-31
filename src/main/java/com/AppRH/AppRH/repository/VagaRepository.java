package com.AppRH.AppRH.repository;

import java.util.List;

import com.AppRH.AppRH.models.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.AppRH.AppRH.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, String> {
    Vaga findByCodigo(long codigo);
    List<Vaga> findByNome(String nome);



    @Query(value = "select u from Vaga u where u.nome like %?1%")
    List<Vaga>findByNomesVaga(String nome);
}
