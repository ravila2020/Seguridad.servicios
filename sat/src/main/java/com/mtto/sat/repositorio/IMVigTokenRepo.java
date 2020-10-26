package com.mtto.sat.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtto.sat.modelo.VigenciaToken;

public interface IMVigTokenRepo extends JpaRepository<VigenciaToken, Integer> {

}
