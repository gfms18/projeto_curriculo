package com.Via1.facepe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Via1.facepe.entitys.Curriculo;

public interface CurriculoDAO extends JpaRepository<Curriculo, Integer>{

	List<Curriculo> findByAreaGeral(String cargo);

	List<Curriculo> findByAreaEspecifica(String subCargo);
	
	List<Curriculo> findByAreaGeralAndAreaEspecifica(String cargo, String subCargo);

}
