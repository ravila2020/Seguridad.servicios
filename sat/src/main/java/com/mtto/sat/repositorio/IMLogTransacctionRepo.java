package com.mtto.sat.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtto.sat.modelo.LogTransacction;

public interface IMLogTransacctionRepo extends JpaRepository<LogTransacction, Integer> {

}
