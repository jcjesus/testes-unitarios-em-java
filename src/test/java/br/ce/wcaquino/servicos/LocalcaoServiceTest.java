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
//		//cen�rio: local onde as vari�veis ser�o inicializadas
//		Usuario u = new Usuario("Jesus");
//		Filme f = new Filme("Um tira da pesada", 2, 8.0);
//		
//		LocacaoService s = new LocacaoService();
//		
//		//acao: onde vamos invocar o m�todo de teste
//		Locacao r = s.alugarFilme(u, f);
//		
//		//validacao: onde coletar os resultados da a��o com o cen�rio especificado
//		// data da loca��o, pre�o da loca��o e data de retorno
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
	 * 		FALHA:	Acontece quando alguma condi��o esperada n�o foi atendida, pois uma valida��o l�gica apresentou falha, 
	 * 				representada pelos asserts. A execu��o do teste � completada com sucesso.
	 * 		ERRO:	Acontece quando a execu��o de um teste impede que o teste seja conclu�do.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testeUsandoErroCollector() throws Exception {
		
		//cen�rio: local onde as vari�veis ser�o inicializadas
		Usuario u = new Usuario("Julio");
		Filme f = new Filme("Robocop", 2, 8.0);
		
		LocacaoService s = new LocacaoService();
		
		//acao: onde vamos invocar o m�todo de teste
		Locacao locacao = s.alugarFilme(u, f);
		
		//validacao: onde coletar os resultados da a��o com o cen�rio especificado
		// data da loca��o, pre�o da loca��o e data de retorno
		
//		error.checkThat(locacao.getValor(), CoreMatchers.is(7.0));
		error.checkThat(locacao.getValor(), CoreMatchers.is(8.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
//		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(false));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
		
	}

	/**
	 * Instrutor chamou de solu��o ELEGANTE.
	 * 
	 * @throws Exception
	 */
	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		//cen�rio: local onde as vari�veis ser�o inicializadas
				Usuario u = new Usuario("Julio");
				Filme f = new Filme("Robocop", 0, 8.0);
				
				LocacaoService s = new LocacaoService();
				
				//acao: onde vamos invocar o m�todo de teste
				s.alugarFilme(u, f);
				
	}
	
//	/**
//	 * Aqui, por ter um alto n�vel de controle, o instrutor chama de forma ROBUSTA.
//	 */
//	@Test
//	public void testeLocacao_filmeSemEstoque_2() {
//		//cen�rio: local onde as vari�veis ser�o inicializadas
//		Usuario u = new Usuario("Julio");
//		Filme f = new Filme("Robocop", 0, 8.0);
//		
//		LocacaoService s = new LocacaoService();
//		
//		//acao: onde vamos invocar o m�todo de teste
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
//		//cen�rio: local onde as vari�veis ser�o inicializadas
//				Usuario u = new Usuario("Julio");
//				Filme f = new Filme("Robocop", 0, 8.0);
//				
//				LocacaoService s = new LocacaoService();
//				
//				// Deve ser declarada junto ao cen�rio, sen�o o junit p�ra a execu��o na linha:
//				// s.alugarFilme(u, f);
//				exception.expect(Exception.class);
//				exception.expectMessage(LocacaoService.FILME_SEM_ESTOQUE);
//
//				//acao: onde vamos invocar o m�todo de teste
//				s.alugarFilme(u, f);
//				
//				
//	}
	
	@Test
	public void testeLocacao_usuarioVazio( ) throws FilmeSemEstoqueException {
		// cen�rio
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("O poderoso chef�o", 1, 4.0);
		
		//a��o - usando forma ROBUSTA
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario vazio"));
		}
	}
	
	@Test
	public void testeLocacao_filmeVazio( ) throws FilmeSemEstoqueException, LocadoraException {
		// cen�rio
		LocacaoService service = new LocacaoService();
		Usuario u = new Usuario("El Loco");
		
		//a config para dizer que espera uma exce��o deve estar sempre antes da ececu��o do m�todo
		//que lan�ar� a exception.
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		//a��o - usando forma NOVA
		service.alugarFilme(u, null);
		
	}
}
