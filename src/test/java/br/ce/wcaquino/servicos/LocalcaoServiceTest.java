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
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

/**
 * @author jjesus
 *
 */
public class LocalcaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
//	@Test
//	public void teste() {
//		
//		//cenário: local onde as variáveis serão inicializadas
//		Usuario u = new Usuario("Jesus");
//		Filme f = new Filme("Um tira da pesada", 2, 8.0);
//		
//		LocacaoService s = new LocacaoService();
//		
//		//acao: onde vamos invocar o método de teste
//		Locacao r = s.alugarFilme(u, f);
//		
//		//validacao: onde coletar os resultados da ação com o cenário especificado
//		// data da locação, preço da locação e data de retorno
//		final Date dtLoc = r.getDataLocacao();
//		final Double precoLoc = r.getValor();
//		final Date dtDevolucao = r.getDataRetorno();
//		
//		boolean foiLocadoHoje = DataUtils.isMesmaData(dtLoc, new Date());
//		boolean precoLocalCorreto = precoLoc.equals(8.0);
//		boolean dtDevolucaoAmanha = DataUtils.isMesmaData(dtDevolucao, DataUtils.obterDataComDiferencaDias(1));
//
//		Assert.assertThat(r.getValor(), CoreMatchers.is(8.0));
//		Assert.assertThat(r.getValor(), CoreMatchers.is(CoreMatchers.equalTo(8.0)));
//		Assert.assertThat(r.getValor(), CoreMatchers.is(CoreMatchers.not(9.0)));
//		Assert.assertEquals(8.0, r.getValor(), 0.01);
//		Assert.assertTrue(foiLocadoHoje);
//		Assert.assertTrue(precoLocalCorreto);
//		Assert.assertTrue(dtDevolucaoAmanha);
//		
//		
//	}

	/**
	 * Diff entre ERRO e FALHA: 
	 * 
	 * 		FALHA:	Acontece quando alguma condição esperada não foi atendida, pois uma validação lógica apresentou falha, 
	 * 				representada pelos asserts. A execução do teste é completada com sucesso.
	 * 		ERRO:	Acontece quando a execução de um teste impede que o teste seja concluído.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testeUsandoErroCollector() throws Exception {
		
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

	/**
	 * Instrutor chamou de solução ELEGANTE.
	 * 
	 * @throws Exception
	 */
	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		//cenário: local onde as variáveis serão inicializadas
				Usuario u = new Usuario("Julio");
				Filme f = new Filme("Robocop", 0, 8.0);
				
				LocacaoService s = new LocacaoService();
				
				//acao: onde vamos invocar o método de teste
				s.alugarFilme(u, f);
				
	}
	
//	/**
//	 * Aqui, por ter um alto nível de controle, o instrutor chama de forma ROBUSTA.
//	 */
//	@Test
//	public void testeLocacao_filmeSemEstoque_2() {
//		//cenário: local onde as variáveis serão inicializadas
//		Usuario u = new Usuario("Julio");
//		Filme f = new Filme("Robocop", 0, 8.0);
//		
//		LocacaoService s = new LocacaoService();
//		
//		//acao: onde vamos invocar o método de teste
//		try {
//			s.alugarFilme(u, f);
//			Assert.fail("Deveria ter lancado uma ececao");
//		} catch (Exception e) {
//			Assert.assertThat(e.getMessage(), CoreMatchers.is(LocacaoService.FILME_SEM_ESTOQUE));
//		}
//		
//	}
//	
//	@Test
//	public void testeLocacao_filmeSemEstoque_3() throws Exception {
//		//cenário: local onde as variáveis serão inicializadas
//				Usuario u = new Usuario("Julio");
//				Filme f = new Filme("Robocop", 0, 8.0);
//				
//				LocacaoService s = new LocacaoService();
//				
//				// Deve ser declarada junto ao cenário, senão o junit pára a execução na linha:
//				// s.alugarFilme(u, f);
//				exception.expect(Exception.class);
//				exception.expectMessage(LocacaoService.FILME_SEM_ESTOQUE);
//
//				//acao: onde vamos invocar o método de teste
//				s.alugarFilme(u, f);
//				
//				
//	}
	
	@Test
	public void testeLocacao_usuarioVazio( ) throws FilmeSemEstoqueException {
		// cenário
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("O poderoso chefão", 1, 4.0);
		
		//ação - usando forma ROBUSTA
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario vazio"));
		}
	}
	
	@Test
	public void testeLocacao_filmeVazio( ) throws FilmeSemEstoqueException, LocadoraException {
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario u = new Usuario("El Loco");
		
		//a config para dizer que espera uma exceção deve estar sempre antes da ececução do método
		//que lançará a exception.
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		//ação - usando forma NOVA
		service.alugarFilme(u, null);
		
	}
}
