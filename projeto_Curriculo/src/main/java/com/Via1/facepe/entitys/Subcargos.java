package com.Via1.facepe.entitys;

import java.util.ArrayList;
import java.util.List;

public enum Subcargos {
	Alinhador("Alinhador"),
	Analista_Junior("Analista Junior"),
	Analista_Pleno("Analista Pleno"),
	Analista_Senior("Analista Senior"),
	Aprediz("Aprediz"),
	Assistente("Assistente"),
	Auditor("Auditor"),
	Auxiliar("Auxiliar"),
	Avaliador("Avaliador"),
	Consultor_de_Consórcio("Consultor de Consórcio"),
	Consultor_de_Leads("Consultor de Leads"),
	Consultor_de_Seguros("Consultor de Seguros"),
	Consultor_de_Vendas("Consultor de Vendas"),
	Consultor_Tecnico("Consultor Tecnico"),
	CONTEUDISTA("Conteudista"),
	COORDENADOR("Coordenador"),
	DIRETOR_DE_ARTE("Diretor de Arte"),
	ELETRICISTA("Eletricista"),
	ENTREGADOR_TECNICO("Entregador Tecnico"),
	ESTAGIÁRIO("Estagiário"),
	FUNILEIRO("Funileiro"),
	GARANTISTA("Garantista"),
	GERENTE("Gerente"),
	LAVADOR("Lavador"),
	MECÂNICO("Mecânico"),
	MIDIA("Midia"),
	MOTORISTA("Motorista"),
	PINTOR_POLIDOR("Pintor/Polidor"),
	RECEPCIONISTA("Recepcionista"),
	REDATOR("Redator"),
	SUPERVISOR("Supervisor"),
	TEC_SEGURANÇA_DE_TRABALHO("Tec Segurança de Trabalho");
	
	
	private String descricao;

	 Subcargos(String descricao) {
	        this.descricao = descricao;
	    }

	    public String getDescricao() {
	        return descricao;
	    }
	    public static List<String> descricoes() {
	    	List<String> descricoes = new ArrayList<>();
	    	Subcargos[] cargos = Subcargos.values();
	    	for (Subcargos carg: cargos) {
	    		descricoes.add(carg.getDescricao());
	    	}
	    	return descricoes;
	    }

	
}
