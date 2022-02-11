package br.com.doalti.sankhya.apiIntegrador;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.jexl2.UnifiedJEXL.Exception;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;

public class BuscaProduto implements AcaoRotinaJava{
	
	private String chaveAPI = "7d8a0590682cfb16c1e9";
	private String chaveAplicacao = "b5e6f0dc-da3b-479e-ad8a-448e0576cd4d";
	String url;
	String descricao = "";
	String descricao_completa = "";
	String skuProd;
	
	int id1Prod;
	String idProd1;

	String[] retornoProd = new String[2];

	@Override
	public void doAction(ContextoAcao ctx) throws java.lang.Exception {
		
	}

	public String[] buscaProdutoSite(String produto_id, String chaveAPI ) throws java.lang.Exception {
		
		try {
				ConexaoLojaIntegrada api = new ConexaoLojaIntegrada();
				String metodo = "GET";
					//int produto_id = obj.getInt("id");
					String url2 = "/produto/" + produto_id;
					String response1 = api.ChamaApi2(url2, metodo, chaveAPI, chaveAplicacao);
					System.out.println("LINHA 101" + response1);
					JSONObject obj2 = new JSONObject(response1);
					
					if (!obj2.isNull("id")) {
						id1Prod = obj2.getInt("id");
						System.out.println("id classe prod: " + id1Prod);
						idProd1 = Integer.toString(id1Prod);
						System.out.println("LINHA 55 busca produto");
					}
					if (!obj2.isNull("sku")) {
						skuProd = obj2.getString("sku");
						System.out.println("sku classe prod: " + skuProd);
						System.out.println("LINHA 60 busca produto");
					}

					retornoProd[0] = skuProd;
					retornoProd[1] = idProd1;

		}catch (Exception e) {
				e.printStackTrace();
				System.out.println("entrou no do item");
				System.out.println("LINHA 302");
		}
		return retornoProd;
	}
}
