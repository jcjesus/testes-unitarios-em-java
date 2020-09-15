/**
 * 
 */
package br.ce.wcaquino.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

/**
 * @author jjesus
 *
 */
public class LocalcaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void teste() {
		
		//cenário: local onde as variáveis serão inicializadas
		Usuario u = new Usuario("Jesus");
		Filme f = new Filme("Um tira da pesada", 2, 8.0);
		
		LocacaoService s = new LocacaoService();
		
		//acao: onde vamos invocar o método de teste
		Locacao r = s.alugarFilme(u, f);
		
		//validacao: onde coletar os resultados da ação com o cenário especificado
		// data da locação, preço da locação e data de retorno
		final Date dtLoc = r.getDataLocacao();
		final Double precoLoc = r.getValor();
		final Date dtDevolucao = r.getDataRetorno();
		
		boolean foiLocadoHoje = DataUtils.isMesmaData(dtLoc, new Date());
		boolean precoLocalCorreto = precoLoc.equals(8.0);
		boolean dtDevolucaoAmanha = DataUtils.isMesmaData(dtDevolucao, DataUtils.obterDataComDiferencaDias(1));

		Assert.assertThat(r.getValor(), CoreMatchers.is(8.0));
		Assert.assertThat(r.getValor(), CoreMatchers.is(CoreMatchers.equalTo(8.0)));
		Assert.assertThat(r.getValor(), CoreMatchers.is(CoreMatchers.not(9.0)));
		Assert.assertEquals(8.0, r.getValor(), 0.01);
		Assert.assertTrue(foiLocadoHoje);
		Assert.assertTrue(precoLocalCorreto);
		Assert.assertTrue(dtDevolucaoAmanha);
		
		
	}
	
	@Test
	public void testeUsandoErroCollector() {
		
		//cenário: local onde as variáveis serão inicializadas
		Usuario u = new Usuario("Julio");
		Filme f = new Filme("Robocop", 2, 8.0);
		
		LocacaoService s = new LocacaoService();
		
		//acao: onde vamos invocar o método de teste
		Locacao locacao = s.alugarFilme(u, f);
		
		//validacao: onde coletar os resultados da ação com o cenário especificado
		// data da locação, preço da locação e data de retorno
		
//		error.checkThat(locacao.getValor(), CoreMatchers.is(7.0));
		error.checkThat(locacao.getValor(), CoreMatchers.is(8.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
//		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(false));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
		
	}
	
	
}
