package com.Via1.facepe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Via1.facepe.entitys.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer >{

	Usuario findByCpfAndSenha(String cpf, String senha);

	Usuario findByCpf(String usuarioLogadoCpf);

}
