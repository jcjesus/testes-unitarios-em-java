/**
 * 
 */
package br.ce.wcaquino.servicos;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

/**
 * @author jjesus
 *
 */
public class LocalcaoServiceTest {
	@Test
	public void teste() {
		
		//cen�rio: local onde as vari�veis ser�o inicializadas
		Usuario u = new Usuario("Jesus");
		Filme f = new Filme("Um tira da pesada", 2, 8.0);
		
		LocacaoService s = new LocacaoService();
		
		//acao: onde vamos invocar o m�todo de teste
		Locacao r = s.alugarFilme(u, f);
		
		//validacao: onde coletar os resultados da a��o com o cen�rio especificado
		// data da loca��o, pre�o da loca��o e data de retorno
		final Date dtLoc = r.getDataLocacao();
		final Double precoLoc = r.getValor();
		final Date dtDevolucao = r.getDataRetorno();
		
		boolean foiLocadoHoje = DataUtils.isMesmaData(dtLoc, new Date());
		boolean precoLocalCorreto = precoLoc.equals(8.0);
		boolean dtDevolucaoAmanha = DataUtils.isMesmaData(dtDevolucao, DataUtils.obterDataComDiferencaDias(1));

		Assert.assertTrue(foiLocadoHoje);
		Assert.assertTrue(precoLocalCorreto);
		Assert.assertTrue(dtDevolucaoAmanha);
		
		
	}
}
