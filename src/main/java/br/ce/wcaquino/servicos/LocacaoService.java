package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) {
		
		//cen�rio: local onde as vari�veis ser�o inicializadas
		Usuario u = new Usuario("Jesus");
		final double valorLocacao = 8.0d;
		Filme f = new Filme("Um tira da pesada", 2, valorLocacao);
		
		LocacaoService s = new LocacaoService();
		
		//acao: onde vamos invocar o m�todo de teste
		Locacao r = s.alugarFilme(u, f);
		
		//validacao: onde coletar os resultados da a��o com o cen�rio especificado
		// data da loca��o, pre�o da loca��o e data de retorno
		final Date dtLoc = r.getDataLocacao();
		final Double precoLoc = r.getValor();
		final Date dtDevolucao = r.getDataRetorno();
		
		boolean foiLocadoHoje = DataUtils.isMesmaData(dtLoc, new Date());
		boolean precoLocalCorreto = precoLoc.equals(valorLocacao);
		boolean dtDevolucaoAmanha = DataUtils.isMesmaData(dtDevolucao, DataUtils.obterDataComDiferencaDias(1));

		System.out.println(foiLocadoHoje);
		System.out.println(precoLocalCorreto);
		System.out.println(dtDevolucaoAmanha);
		
		
	}
}