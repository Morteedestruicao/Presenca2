package com.MundoSenai.Presenca.Repository;

import com.MundoSenai.Presenca.Model.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long> {
        @Query(value = "SELECT * FROM pessoa WHERE cpf = :usuario and senha = :senha limit 1", nativeQuery = true)
        M_Usuario findByUsuarioESenha(@Param("usuario") Long usuario, @Param("senha") String senha);
}

