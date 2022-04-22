package com.Via1.facepe.entitys;

import java.util.ArrayList;
import java.util.List;

public enum Cargos {
	ASSISTENCIA_TECNICA("Assistencia Técnica"),
	AUDITORIA("Auditoria"),
	BACKOFFICE("BackOffice"),
	COMPRAS("Compras"),
	CONTABILIDADE("Contabilidade"),
	CONTABILIDADE_FISCAL("Contabilidade fiscal"),
	CONTROLADORIA("Controladoria"),
	CRM("CRM"),
	F_I("F&I"),
	FINANCEIRO_CONTAS_A_PAGAR("Financeiro - contas a pagar"),
	FINANCEIRO_CONTAS_A_RECEBER("Financeiro - contas a receber"),
	FUNILARIA("Funilaria"),
	GARANTIA("Garantia"),
	GESTÃO_DEPESSOAS_ADM_PESSOAL("Gestão de pessoas - ADM pessoal"),
	GESTÃO_DE_PESSOAS_DHO("Gestão de pessoas - DHO"),
	LOGISTICA("Logistica"),
	MANUTENÇÃO_PREDIAL("Manuntenção predial"),
	MARKETING("Marketing"),
	POSTO_DE_LAVAGEM("Posto de Lavagem"),
	TECNOLOGIA_E_INFORMAÇÃO("Tecnologia e informação"),
	VENDA_DE_CONSÓRCIO("Venda de consórcio"),
	VENDAS_DE_PEÇAS_E_ACESSÓRIOS("Vendas de peças e acessórios"),
	VENDAS_DE_SEGUROS("Vendas de seguros"),
	VENDAS_VEICULOS("Vendas veículos");
	
	private String descricao;

	 Cargos(String descricao) {
	        this.descricao = descricao;
	    }

	    public String getDescricao() {
	        return descricao;
	    }
	    public static List<String> descricoes() {
	    	List<String> descricoes = new ArrayList<>();
	    	Cargos[] cargos = Cargos.values();
	    	for (Cargos carg: cargos) {
	    		descricoes.add(carg.getDescricao());
	    	}
	    	return descricoes;
	    }
}
