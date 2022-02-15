package br.com.doalti.sankhya.apiIntegrador;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.jexl2.UnifiedJEXL.Exception;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.util.JapeSessionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.PrePersistEntityState;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;

public class IncluirPedidoAction implements AcaoRotinaJava {

	Timestamp agora = new Timestamp(System.currentTimeMillis());
	private String chaveAPI = "7d8a0590682cfb16c1e9";
	private String chaveAplicacao = "b5e6f0dc-da3b-479e-ad8a-448e0576cd4d";
	String nome = "";
	String cpfcnpj = "";
	String cpf = "";
	String cnpj = "";
	String IE = "";
	String rua = "";
	String numero = "";
	String complemento = "";
	String bairro = "";
	String cidade = "";
	String uf = "";
	String cep = "";
	String material = "";
	String tipoProd = "";
	String sequencia = "";
	String codchip = "";
	// dados produçao
	String url;
	String url3;
	String id_anymarket = "";
	String email = "";
	String nomeCliente = "";
	String endereco = "";
	String estado = "";
	String pais = "";
	String referencia = "";
	String tipoFrete = "";
	String msg = "";
	String nomeProd = "";
	String bandeira = "";
	String nomeFormaPagamento;
	String mensagem_gateway;
	String tipoEndEntr;
	String codProd;
	int id_prod;
	String sku_prod;
	String bloquear = "";

	String resposta = "";
	String nunota = "";
	String recebido = "";
	String codemp = "";
	int codprod;
	String adnrosa = "";
	String sku = "";
	String cliente = "";
	String idCliente = "";
	String codigo = "";
	String resource_uri = "";
	String razao_social = "";
	String sexo = "";
	String telefone_celular = "";
	String telefone_comercial = "";
	String telefone_principal = "";
	String rg = "";
	String tipo = "";
	String code = "";
	String data_criacao = "";
	String data_expiracao = "";
	String data_modificacao = "";
	String data_nascimento = "";
	String nomeEnvio = "";
	String produto = "";
	String banco = "";
	String codigoPagamento = "";
	String pagamento_tipo = "";
	JSONObject parcelamento;
	String campoAtivo = "";
	String ie;
	String cliente_obs = "";
	String status;

	Collection dynamicVOs;

	public AuthenticationInfo authInfo;
	private ServiceContext sctx;

	private java.math.BigDecimal codCid;
	BigDecimal nuNotaNovo = null;
	BigDecimal codParc;
	BigDecimal nuNota = BigDecimal.ZERO;
	BigDecimal tolerancia = BigDecimal.ZERO;
	BigDecimal atraso;
	BigDecimal count;

	String transacao_id;
	String identificador_id;
	int id;
	int pedido;
	int prazo;

	int id_externo;
	int quantidade_usada;
	int idEnvio;
	int authorization_code;
	int idFormaPagamento;
	int linha;
	int numero_parcelas = 0;
	int sun;
	int id_cliente;
	BigDecimal numeroPed;

	Double valorParc = 0D;
	String valorParcString = "";
	double valor_pago;
	double peso;
	String preco_cheio;
	double preco_custo;
	double preco_promocional;
	double preco_subtotal;
	double valor_minimo;
	double valor;
	double quantidade;
	String preco_venda = "";
	double valor_desconto;
	double valor_subtotal;
	double valor_total;
	boolean aprovado;
	boolean cancelado;
	boolean final1;
	boolean notificar_comprador;
	boolean padrao;
	boolean aplicar_no_total;
	boolean ativo;
	boolean disponivel;
	double valor_envio;

	// private Object sctx;

	BigDecimal codbco;
	BigDecimal codTipTit;
	BigDecimal numNOTA;
	Timestamp dtNEG;
	BigDecimal vlrDesDob;
	BigDecimal codTipoper;
	Timestamp dhTipoPer;
	BigDecimal codNat;
	BigDecimal codUSU;
	BigDecimal recDesp;
	Timestamp DtPRESENTE;
	Timestamp dataVencimento;
	BigDecimal codCencus;
	BigDecimal contaSankhya;
	String numParcela;
	BigDecimal tipVend;
	int pedido_id;
	String data_criado;
	BigDecimal pagamento_id;
	String codigo_cupDesc;
	String condicao_cliente;
	String condicao_produto;
	Boolean cumulativo;
	String descricao;
	String id_endEntrega;
	double vlrDesc;

	@Override
	public void doAction(ContextoAcao contexto) {
		// TODO Auto-generated method stub

		// for (int cont = 0; cont <= 2; cont++) {
		System.out.println("INICIOU O CÓDIGO");
		EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

		Timestamp sysdate = new Timestamp(System.currentTimeMillis());
		String atualizado = "";
		JdbcWrapper JDBC = JapeFactory.getEntityFacade().getJdbcWrapper();
		NativeSql nativeSql = new NativeSql(JDBC);
		SessionHandle hnd = JapeSession.open();
		ConexaoLojaIntegrada api = new ConexaoLojaIntegrada();
		System.out.println("chave api " + chaveAPI);
		try {
			// pedido_id = 20;
			
			ResultSet query5 = nativeSql.executeQuery("SELECT MAX(pedidoOrga) AS CONTADOR FROM AD_LOG ");
			
			if (query5.next()) {
				pedido_id = query5.getInt("CONTADOR");
				//data_criado = query5.getString("NOW");
				System.out.println(query5);
				System.out.println("pedido_id " + pedido_id);

				System.out.println("INICIOU O CÓDIGO");
				// url = "/pedido";
				//url = "/pedido/search/?since_criado="+data_criado+"&limit=20";
				url = "/pedido/search/?since_numero=" + (pedido_id + 1) + "&limit=1";
				System.out.println("Url começo do cidgo " + url);

				String metodo = "GET";
				// String resposta = api.ChamaApi2(urlTeste, metodo, chaveAPI, chaveAplicacao);
				String resposta = api.ChamaApi2(url, metodo, chaveAPI, chaveAplicacao);
				System.out.println("Passou na linha 213");
				// System.out.println("sysout linha 87" + resposta);
				System.out.println("Passou na linha 215");
				JSONObject jsonobjPedect1 = new JSONObject(resposta);
				JSONArray jsonArray = jsonobjPedect1.getJSONArray("objects");
				System.out.println("LINHA 90");
				System.out.println("Passou na linha 219");
				try {
					System.out.println("Entrou no try da linha221");
					for (int i = 0; i < jsonArray.length(); i++) {

						System.out.println("LINHA 126");
						JSONObject obj = jsonArray.getJSONObject(i);

						// ResultSet query5 = nativeSql.executeQuery("SELECT CAST(GETDATE() AS DATE) AS
						// DAT FROM DUAL");
						// while (query5.next()) {
						// data_criado = query5.getString("DAT");

						// String pedido_idS = obj.getString("numero");

						// String url2 = "/pedido/search/?since_numero="+ pedido_id +"&limit=20";
						// "&pagamento_id="+pagamento_id+
						// situacao_id="+situacao_pedido;
						// String url2 = "/pedido/" + pedido_id;
						// String response1 = api.ChamaApi2(url2, metodo, chaveAPI, chaveAplicacao);

						// System.out.println("Sysout url2 " + url2);

						// ResultSet queryMax = (" ");

						System.out.println("numero pedido = " + pedido_id);

						if (!obj.isNull("numero")) {
							numero = obj.getString("numero");
							System.out.println("LINHA 232 : " + numero + " numero do pedido");
							numeroPed = new BigDecimal(numero);
						}
						if (!obj.isNull("resource_uri")) {
							resource_uri = obj.getString("resource_uri");
							resource_uri = resource_uri.substring(7);
							System.out.println("sysout resource_uri começo pedico" + resource_uri);
						}

						ResultSet query4 = nativeSql.executeQuery(" SELECT " + " COUNT(*) AS CONTADOR " + " FROM "
								+ "TGFCAB " + " WHERE " + " AD_PEDIDOECOM = '" + numeroPed + "'");

						System.out.println("LINHA 241 : " + numeroPed + " numero do pedido");

						String url4 = "/pedido/" + numeroPed;
						String response2 = api.ChamaApi2(url4, metodo, chaveAPI, chaveAplicacao);
						JSONObject objPed = new JSONObject(response2);

						System.out.println("sysout 282 " + url4);
						System.out.println(response2);

						JSONArray jsonArrayItens = objPed.getJSONArray("itens");
						JSONArray jsonArrayEnvios = objPed.getJSONArray("envios");
						JSONArray jsonArrayPagametos = objPed.getJSONArray("pagamentos");

						if (!objPed.isNull("cliente")) {
							JSONObject objCli = objPed.getJSONObject("cliente");

							if (!objCli.isNull("cnpj")) {
								System.out.println("Entrou no cnpj ");
								cnpj = objCli.getString("cnpj");
								cpfcnpj = cnpj;
								System.out.println("sysout cpfcnpj" + cpfcnpj);
							}
							if (!objCli.isNull("cpf")) {
								cpf = objCli.getString("cpf");
								cpfcnpj = cpf;
								System.out.println("sysout cpfcnpj" + cpfcnpj);
							}

							if (!objCli.isNull("id")) {
								id_cliente = objCli.getInt("id");
								System.out.println("LINHA 209  email : " + email);
							}
							if (!objCli.isNull("nome")) {
								nome = objCli.getString("nome");
								System.out.println("LINHA 263 nome : " + nome);
							}
							if (!objCli.isNull("razao_social")) {
								razao_social = objCli.getString("razao_social");
								System.out.println("LINHA 213 razao_social : " + razao_social);
							}
							if (!objCli.isNull("telefone_celular")) {
								telefone_celular = objCli.getString("telefone_celular");
								System.out.println("LINHA 217 telefone_celular : " + telefone_celular);
							}
							if (!objCli.isNull("telefone_principal")) {
								telefone_principal = objCli.getString("telefone_principal");
								System.out.println("LINHA 221 telefone_principal : " + telefone_principal);
							}
							if (!objCli.isNull("cliente_obs")) {
								cliente_obs = objCli.getString("cliente_obs");
								System.out.println("LINHA 221 cliente_obs : " + cliente_obs);
							}
						}

						if (!objPed.isNull("endereco_entrega")) {
							JSONObject objEndEntrega = objPed.getJSONObject("endereco_entrega");

							if (!objEndEntrega.isNull("cidade")) {
								cidade = objEndEntrega.getString("cidade");
								cidade = cidade.replaceAll("[-+.^:,~]", "");
								System.out.println("sysout  cidade " + cidade);
							}
							if (!objEndEntrega.isNull("bairro")) {
								bairro = objEndEntrega.getString("bairro");
								System.out.println("sysout  bairro " + bairro);
							}
							if (!objEndEntrega.isNull("cep")) {
								cep = objEndEntrega.getString("cep");
								System.out.println("sysout  cep " + cep);
							}
							if (!objEndEntrega.isNull("complemento")) {
								complemento = objEndEntrega.getString("complemento");
								System.out.println("sysout  complemento " + complemento);
							}
							if (!objEndEntrega.isNull("endereco")) {
								endereco = objEndEntrega.getString("endereco");
								System.out.println("sysout  endereco " + endereco);
							}
							if (!objEndEntrega.isNull("estado")) {
								estado = objEndEntrega.getString("estado");
								System.out.println("sysout  estado " + estado);
							}
							if (!objEndEntrega.isNull("id")) {
								id_endEntrega = objEndEntrega.getString("id");
								System.out.println("sysout  id_endEntrega " + id_endEntrega);
							}
							if (!objEndEntrega.isNull("numero")) {
								numero = objEndEntrega.getString("numero");
								System.out.println("sysout  numero " + numero);
							}
							if (!objEndEntrega.isNull("pais")) {
								pais = objEndEntrega.getString("pais");
								System.out.println("sysout  pais " + pais);
							}
							if (!objEndEntrega.isNull("referencia")) {
								referencia = objEndEntrega.getString("referencia");
								System.out.println("sysout  referencia " + referencia);
							}
							if (!objEndEntrega.isNull("tipo")) {
								tipo = objEndEntrega.getString("tipo");
								System.out.println("sysout  tipo " + tipo);
							}
						}
						for (int entrega = 0; entrega < jsonArrayEnvios.length(); entrega++) {
							JSONObject objEndentr = jsonArrayEnvios.getJSONObject(entrega);

							if (!objEndentr.isNull("forma_envio")) {
								JSONObject objformEnv = objEndentr.getJSONObject("forma_envio");
								if (!objformEnv.isNull("code")) {
									code = objformEnv.getString("code");
									System.out.println("code " + code);
								}
								if (!objformEnv.isNull("id")) {
									String id_formEnvio = objformEnv.getString("id");
									System.out.println("codigoPagamento " + id_formEnvio);
								}
								if (!objformEnv.isNull("nome")) {
									String nomeFormPag = objformEnv.getString("nome");
									System.out.println("nomeFormPag " + nomeFormPag);
								}
								if (!objformEnv.isNull("tipo")) {
									String tipoFormEnv = objformEnv.getString("tipo");
									System.out.println("tipoFormEnv " + tipoFormEnv);
								}
							}
							if (!objEndentr.isNull("id")) {
								int idEntrega = objEndentr.getInt("id");
								System.out.println("sysout  idEntrega " + idEntrega);
							}
							if (!objEndentr.isNull("objeto")) {
								String objeto = objEndentr.getString("objeto");
								System.out.println("sysout  objeto " + objeto);
							}
							if (!objEndentr.isNull("prazo")) {
								prazo = objEndentr.getInt("prazo");
								System.out.println("sysout  prazo " + prazo);
							}
							if (!objEndentr.isNull("valor")) {
								double valorEntrega = objEndentr.getDouble("valor");
								System.out.println("sysout  valor " + valorEntrega);
							}
						}

						for (int pag = 0; pag < jsonArrayPagametos.length(); pag++) {
							System.out.println("LINHA 154");
							JSONObject objPag = jsonArrayPagametos.getJSONObject(pag);

							if (!objPag.isNull("authorization_code")) {
								authorization_code = objPag.getInt("authorization_code");
								System.out.println("codigo de autorização  " + nomeProd);
							}
							if (!objPag.isNull("banco")) {
								banco = objPag.getString("banco");
								System.out.println("banco " + banco);
							}
							if (!objPag.isNull("bandeira")) {
								bandeira = objPag.getString("bandeira");
								System.out.println("bandeira " + bandeira);
							}
							if (!objPag.isNull("identificador_id")) {
								identificador_id = objPag.getString("identificador_id");
								System.out.println("numero identificador " + identificador_id);
							}
							if (!objPag.isNull("forma_pagamento")) {
								JSONObject objPedformPag = objPag.getJSONObject("forma_pagamento");

								if (!objPedformPag.isNull("codigo")) {
									codigoPagamento = objPedformPag.getString("codigo");
									System.out.println("FORMA DE PAGAMENTO " + codigoPagamento);
								}
								if (!objPedformPag.isNull("configuracoes")) {
									JSONObject objPedConf = objPedformPag.getJSONObject("configuracoes");

									if (!objPedConf.isNull("ativo")) {
										ativo = objPedConf.getBoolean("ativo");
										System.out.println("ativo : " + ativo);
									}
									if (!objPedConf.isNull("disponivel")) {
										disponivel = objPedConf.getBoolean("disponivel");
										System.out.println("disponivel  " + disponivel);
									}
								}
								if (!objPedformPag.isNull("id")) {
									idFormaPagamento = objPedformPag.getInt("id");
									System.out.println("ID " + ativo);
								}
								if (!objPedformPag.isNull("nome")) {
									nomeFormaPagamento = objPedformPag.getString("nome");
									System.out.println("NOME " + ativo);
								}
							}
							if (!objPag.isNull("identificador_id")) {
								identificador_id = objPag.getString("identificador_id");
								System.out.println("ID " + identificador_id);
							}
							if (!objPag.isNull("mensagem_gateway")) {
								mensagem_gateway = objPag.getString("mensagem_gateway");
								System.out.println("mensagem_gateway " + mensagem_gateway);
							}
							if (!objPag.isNull("pagamento_tipo")) {
								pagamento_tipo = objPag.getString("pagamento_tipo");
								System.out.println("TIPO DE PAGAMENTO " + pagamento_tipo);
							}
							if (!objPag.isNull("transacao_id")) {
								transacao_id = objPag.getString("transacao_id");
								System.out.println("TRANSIÇÃO ID " + transacao_id);
							}
							if (!objPag.isNull("valor")) {
								valor = objPag.getDouble("valor");
								System.out.println("VALOR " + valor);
							}
							if (!objPag.isNull("valor_pago")) {
								valor_pago = objPag.getDouble("valor_pago");
								System.out.println("VALOR PAGO " + valor_pago);
							}
						}
						if (!objPed.isNull("situacao")) {
							JSONObject situacaoObj = objPed.getJSONObject("situacao");

							if (!situacaoObj.isNull("aprovado")) {
								aprovado = situacaoObj.getBoolean("aprovado");
								System.out.println("sysout LINHA 183 aprovado " + aprovado);
							}
							if (!situacaoObj.isNull("final")) {
								final1 = situacaoObj.getBoolean("final");
								System.out.println("sysout LINHA 587 final " + final1);
							}
							if (!situacaoObj.isNull("cancelado")) {
								cancelado = situacaoObj.getBoolean("cancelado");
								System.out.println("sysout LINHA 183 final " + cancelado);
							}
							if (!situacaoObj.isNull("codigo")) {
								codigo = situacaoObj.getString("codigo");
								System.out.println("sysout LINHA 595 codigo " + codigo);
							}
							if (!situacaoObj.isNull("id")) {
								int idSituacao = situacaoObj.getInt("id");
								System.out.println("sysout LINHA 599 idSituacao " + idSituacao);
							}
							if (!situacaoObj.isNull("id")) {
								int idSituacao = situacaoObj.getInt("id");
								System.out.println("sysout LINHA 603 idSituacao " + idSituacao);
							}
							if (!situacaoObj.isNull("nome")) {
								nome = situacaoObj.getString("nome");
								System.out.println("sysout LINHA 607 nome " + nome);
							}
							if (!situacaoObj.isNull("notificar_comprador")) {
								notificar_comprador = situacaoObj.getBoolean("notificar_comprador");
								System.out.println("sysout LINHA 6177 notificar_comprador " + notificar_comprador);
							}
							if (!situacaoObj.isNull("padrao")) {
								padrao = situacaoObj.getBoolean("padrao");
								System.out.println("sysout LINHA 183 padrao " + padrao);
							}
							if (!situacaoObj.isNull("resource_uri")) {
								resource_uri = situacaoObj.getString("resource_uri");
								System.out.println("sysout LINHA 619 resource_uri " + resource_uri);
							}
						}

						if (!objPed.isNull("valor_desconto")) {
							vlrDesc = objPed.getDouble("valor_desconto");
							System.out.println("LINHA 627 valor_desconto : " + valor_desconto);
						}
						if (!objPed.isNull("valor_envio")) {
							valor_envio = objPed.getDouble("valor_envio");
							System.out.println("LINHA 633 valor_envio : " + valor_envio);
						}
						if (!objPed.isNull("valor_subtotal")) {
						   valor_subtotal = objPed.getDouble("valor_subtotal");
							System.out.println("LINHA 639 valor_subtotal : " + valor_subtotal);
						}
						if (!objPed.isNull("valor_total")) {
							 valor_total = objPed.getDouble("valor_total");
							System.out.println("LINHA 645 CLIENTE : " + valor_total);
						}

						cpfcnpj = cpfcnpj.replace(".", "").replace("/", "").replace("-", "");
						System.out.println("Antes de validar pedido ja existente : ");

						System.out.println("Sysout count linha anmtes da validação 327 : " + cpfcnpj);

						ResultSet query2 = nativeSql.executeQuery("SELECT " + " COUNT(*) AS CONTADOR FROM "
								+ " TGFPAR WHERE CGC_CPF = '" + cpfcnpj + "'");

						if (query2.next() && query2.getInt("CONTADOR") == 0) {
							System.out.println("Entrou para na validação do contador para chamar incluir parceiro ");
							count = query2.getBigDecimal("CONTADOR");
							System.out
									.println("sysout entrou no incluir parceiro com o parceiro cnpj ou cpf " + cpfcnpj);
							ParceiroComParametros parceiroComParametros = new ParceiroComParametros();
							parceiroComParametros.incluirParceiro(cpfcnpj, id_cliente);
						}

						System.out.println("ante de validar forma de pagamento ");
						System.out.println(" cliente_obs " + cliente_obs);

						if (aprovado == true) {
							status = "aprovado";
						} else {
							status = "Reprovado";
						}

						if (aprovado == true && cancelado == false) {
							if (cliente_obs.equals("Boleto Ã")) {
								tipVend = new BigDecimal(671);
							} else if (cliente_obs.equals("Boleto 21")) {
								tipVend = new BigDecimal(679);
							} else if (cliente_obs.equals("Boleto 21/42")) {
								tipVend = new BigDecimal(680);
							} else {
								tipVend = new BigDecimal(681);
							}

							System.out.println("sysout linha 510 ");
							System.out.println("depois de validar forma de pagamento");
							System.out.println("antes do result set cpfcnpj : " + cpfcnpj);

							ResultSet result = nativeSql.executeQuery(" SELECT " + "    CODPARC,  " + "    CGC_CPF "
									+ " FROM  " + " TGFPAR " + " WHERE  " + "CGC_CPF ='" + cpfcnpj + "'");

							System.out.println("depois do result set cpfcnpj : " + cpfcnpj);

							if (result.next()) {
								cpfcnpj = result.getString("CGC_CPF");
								System.out.println("sysout linha " + cpfcnpj);
								codParc = result.getBigDecimal("CODPARC");
								System.out.println("sysout linha 605 " + codParc);

								JapeSession.SessionHandle hnd1 = null;

								ResultSet rs = nativeSql.executeQuery("SELECT "
										+ "    ( CASE WHEN FIN.DTVENC IS NULL THEN 0 "
										+ "    ELSE (CASE WHEN FIN.DHBAIXA IS NOT NULL AND "
										+ "    (FIN.VLRBAIXA <> 0 OR FIN.VLRDESC >= FIN.VLRDESDOB) "
										+ "    THEN CAST(FIN.DHBAIXA AS DATETIME)-CAST(FIN.DTVENC AS DATETIME) "
										+ "        ELSE CONVERT(DATETIME, CONVERT(DATE,GETDATE()))-CAST(FIN.DTVENC AS DATETIME) "
										+ "        END) " + "	END) AS ATRASO " + "    , PAR.NOMEPARC "
										+ "    , PAR.CGC_CPF " + "    , BLOQUEAR " + "    , TOLERINADIMP "
										+ "    , PAR.CODPARC " + "FROM TGFCAB CAB "
										+ "JOIN TGFPAR PAR ON CAB.CODPARC = PAR.CODPARC "
										+ "JOIN TGFFIN FIN ON CAB.NUNOTA = FIN.NUNOTA "
										+ "LEFT JOIN TGFTPV TPV ON CAB.NUNOTA = TPV.NUNOTA " + "WHERE "
										+ "PAR.CGC_CPF = '" + cpfcnpj + "'");

								if (rs.next() && tipVend.equals(679) || tipVend.equals(680)) {
									System.out.println("Entrou na validação do tipo de negociaçao");
									bloquear = rs.getString("BLOQUEAR");
									tolerancia = rs.getBigDecimal("TOLERINADIMP");
									atraso = rs.getBigDecimal("ATRASO");
									codParc = rs.getBigDecimal("CODPARC");
									if (bloquear.equals("S")) {
										// contexto.setMensagemRetorno("Cliente Bloqueado para venda aprazo, favor
										// verificar Cliente : "+ codParc);
										return;
									}
									if (atraso.compareTo(tolerancia) > 0) {
										JapeWrapper parDAO = JapeFactory.dao(DynamicEntityNames.PARCEIRO);
										parDAO.prepareToUpdateByPK(codParc).set("BLOQUEAR", "S").update();
										// contexto.setMensagemRetorno("Cliente " + codParc + "Com atraso maior "
										// + "que a tolerancia, foi bloqueado para vendas a prazo ");
									}
								}

								JdbcWrapper JDBC1 = JapeFactory.getEntityFacade().getJdbcWrapper();

								String msg = "";
								String codVol = "";
								System.out.println("sysout  antes de incluir CAB");

								DynamicVO cabVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("CabecalhoNota");
								cabVO.setProperty("CODTIPOPER", new BigDecimal(54));
								cabVO.setProperty("TIPMOV", "P"); // topVO.asString("TIPMOV")
								cabVO.setProperty("CODPARC", this.codParc);
								cabVO.setProperty("CODEMP", new BigDecimal(3));
								cabVO.setProperty("DTNEG", agora);
								cabVO.setProperty("CODNAT", new BigDecimal(0));
								cabVO.setProperty("CODCENCUS", new BigDecimal(0));
								cabVO.setProperty("CODTIPVENDA", tipVend);
								cabVO.setProperty("SERIENOTA", "1");
								System.out.println("AD_PEDIDOECOM " + numero);
								//cabVO.setProperty("AD_PEDIDOORG", this.numeroPed);
								System.out.println("no meio de incluir TGFCAB ");
								cabVO.setProperty("OBSERVACAO",
										" Pedido Oriundo do Ecommerce, atraves integração : " + this.numero);
								cabVO.setProperty("CODEMPNEGOC", "3");
								cabVO.setProperty("DTALTER", agora);
								cabVO.setProperty("CODPARCTRANSP", BigDecimal.valueOf(4129L));
								cabVO.setProperty("VLRDESCTOT", new BigDecimal(vlrDesc));
								cabVO.setProperty("VLRFRETE", new BigDecimal(valor_envio));
								System.out.println("passou do cabVO");
								CACHelper cacHelper = new CACHelper();

								this.sctx = new ServiceContext(null);
								// authInfo = AuthenticationInfo.getCurrent();
								authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO,
										new Integer(2147483647));
								this.sctx.setAutentication(authInfo);
								this.sctx.putHttpSessionAttribute("usuario_logado", authInfo.getUserID());
								JapeSessionContext.putProperty("usuario_logado", authInfo.getUserID());
								this.sctx.setAttribute("usuario_logado", authInfo.getUserID());
								this.sctx.makeCurrent();

								System.out.println("passou do Helper");
								JapeSessionContext.putProperty("br.com.sankhya.com.CentralCompraVenda", Boolean.TRUE);
								System.out.println("passou do JapeSessionContext");
								PrePersistEntityState cabPreState = PrePersistEntityState.build(dwfFacade,
										"CabecalhoNota", cabVO);
								System.out.println("passou do cabPreState");
								BarramentoRegra bRegrasCab = null;
								System.out.println("auth : " + authInfo);
								System.out.println("cabPReState : " + cabPreState);
								if (authInfo != null && cabPreState != null) {
									System.out.println("entrou aqui no if da linha 658 sysout : ");
									bRegrasCab = cacHelper.incluirAlterarCabecalho(this.sctx, cabPreState);
									System.out.println("entrou aqui no if da linha 660 sysout : ");
								} else {
									System.out.println("algum dos dois está nulo.");
								}

								System.out.println("passou do bRegrascab");
								DynamicVO newCabVO = bRegrasCab.getState().getNewVO();
								System.out.println("passou do newCabVo");
								nuNota = newCabVO.asBigDecimal("NUNOTA");
								System.out.println("passou do nuNota");
								msg = "Cabecaincluido: " + nuNota;
								System.out.println("passou do msg ? " + msg);
								System.out.println(msg);
								try {
									for (int item = 0; item < jsonArrayItens.length(); item++) {

										System.out.println("LINHA 154");
										JSONObject objItem = jsonArrayItens.getJSONObject(item);

										if (!objItem.isNull("nome")) {
											nomeProd = objItem.getString("nome");
											System.out.println("nome do produto  " + nomeProd);
										}
										if (!objItem.isNull("linha")) {
											linha = objItem.getInt("linha");
											System.out.println("sysout linha  " + linha);
										}
										if (!objItem.isNull("preco_cheio")) {
											preco_cheio = objItem.getString("preco_cheio");
											System.out.println("preco_cheio " + preco_cheio);
										}
										if (!objItem.isNull("preco_custo")) {
											preco_custo = objItem.getDouble("preco_custo");
											System.out.println("preço de custo  " + preco_custo);
										}
										if (!objItem.isNull("preco_promocional")) {
											preco_promocional = objItem.getDouble("preco_promocional");
											System.out.println("preco_promocional " + preco_promocional);
										}
										if (!objItem.isNull("preco_subtotal")) {
											preco_subtotal = objItem.getDouble("preco_subtotal");
											System.out.println("preco_subtotal  " + preco_subtotal);
										}
										if (!objItem.isNull("preco_venda")) {
											preco_venda = objItem.getString("preco_venda");
											System.out.println("preço de venda " + preco_venda);
										}
										if (!objItem.isNull("produto")) {
											produto = objItem.getString("produto");
											produto = produto.substring(16);
											System.out.println("produto  " + produto);
										}
										if (!objItem.isNull("quantidade")) {
											quantidade = objItem.getDouble("quantidade");
											System.out.println("quantidade " + quantidade);
										}
										if (!objItem.isNull("sku")) {
											sku = objItem.getString("sku");
											System.out.println("codigo Sku " + sku);
										}
										if (!objItem.isNull("tipo")) {
											tipoProd = objItem.getString("tipo");
											System.out.println("tipo de produto  " + tipo);
										}

										System.out.println("LINHA 599");

										/*
										 * BuscaProduto prod = new BuscaProduto(); String[] retorno =
										 * prod.buscaProdutoSite(produto, chaveAPI);
										 * 
										 * String idProd2 = retorno[1]; String skuProd2 = retorno[0];
										 * 
										 * System.out.println("sysout id classe incluir pedido linha527: " + idProd2);
										 * System.out.println("sysout sku classe incluir pedido linha 528 : " +
										 * skuProd2);
										 * 
										 * if (!StringUtils.isNumeric(skuProd2)) { return; } else { ResultSet query3 =
										 * nativeSql.executeQuery("SELECT * " + "FROM " + "	TGFPRO " + "WHERE " +
										 * " CODPROD = " + new BigDecimal(skuProd2)); if (!query3.next()) { //
										 * contexto.setMensagemRetorno("SKU " + skuProd2 + " nao Encontrado // na tabela
										 * de produtos"); return; } else { System.out.println("sysout sku : " +
										 * skuProd2); System.out.println("sysout sku codprod 537: " + skuProd2);
										 * 
										 * System.out.println("sysout sku codprod 547: " + skuProd2);
										 */

										BigDecimal codLocal = BigDecimal.ZERO;
										System.out.println("SYSOUIT LINHA 694 ANTES DO FOR E TRY");
										try {
											System.out.println("eNTROU NO TRY DA INSERÇÃO DO ITEM ");
											Collection<PrePersistEntityState> itensNota = new ArrayList<>();
											DynamicVO itemVO = (DynamicVO) dwfFacade
													.getDefaultValueObjectInstance("ItemNota");
											itemVO.setProperty("SEQUENCIA", new BigDecimal(item + 1));
											System.out.println("Depois do sequenc " + linha);
											itemVO.setProperty("CODPROD", new BigDecimal(sku));
											System.out.println("depois do codprod " + sku);
											itemVO.setProperty("CODVOL", itemVO.asString("CODVOL"));
											System.out.println("depois do codvol ");
											// itemVO.setProperty("CODLOCALORIG",
											itemVO.setProperty("CODLOCALORIG", new BigDecimal(2800));
											System.out.println("depois do qtdneg ");
											itemVO.setProperty("QTDNEG", new BigDecimal(this.quantidade));
											System.out.println("Antes do qtdneg " + quantidade);
											itemVO.setProperty("VLRUNIT", new BigDecimal(this.preco_venda));
											System.out.println("Antes do c " + valor_total);
											// itemVO.setProperty("CONTROLE", item.asString("CONTROLE"));
											// itemVO.setProperty("CODLOCAL", 2400);

											System.out.println("Antes do Persist ");
											PrePersistEntityState itePreState = PrePersistEntityState.build(dwfFacade,
													"ItemNota", itemVO);
											System.out.println("Antes do Collection ITE ");
											itensNota.add(itePreState);

											cacHelper.incluirAlterarItem(nuNota, authInfo, itensNota, true);

											
										} catch (Exception e) {
											e.printStackTrace();
											msg = "Erro na inclusao do item " + e.getMessage();
											System.out.println(msg);
											boolean insert = nativeSql.executeUpdate(
													"INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)"
															+ "VALUES (" + null + ", " + null + ", '" + status + "', '"
															+ e.getMessage() + "')");
										}
									}
								} catch (Exception e) {
									msg = "Erro na inclusao dos Itens " + e.getMessage();
									e.printStackTrace();
									System.out.println(msg);
									boolean insert = nativeSql.executeUpdate(
											"INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)"
													+ "VALUES (" + null + ", " + null + ", '" + status + "', '"
													+ e.getMessage() + "')");
								}
								System.out.println(msg);
								nativeSql.executeUpdate(
										"INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)"
												+ "VALUES (" + numeroPed + ", " + null
												+ ", ' Aprovado', 'Pedidos Com status APROVADO')");

							}
						} else {
							System.out.println("numero pedido" + numeroPed);
							boolean insert = nativeSql.executeUpdate(
									"INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)" + "VALUES (" + numeroPed
											+ ", " + null + ", ' Reprovado', 'Pedidos Com status Reprovado')");
							 contexto.setMensagemRetorno("Nenhum Pedido para ser integrado agora, ou Nao aprovados");
						}
					}
					 contexto.setMensagemRetorno("Todos Pedidos Integrados");
					// contexto.setMensagemRetorno(" finalizado! notas incluidas com sucesso");

					// contexto.setMensagemRetorno(" Pedido ja incluso no pedido, ou Com status de
					// reprovado : ");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("entrou no do item");
					System.out.println("LINHA 302");
					boolean insert = nativeSql
							.executeUpdate("INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)" + "VALUES ("
									+ null + ", " + null + ", '" + status + "', '" + e.getMessage() + "')");
					contexto.setMensagemRetorno(e.getMessage());

				} catch (java.lang.Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					boolean insert = nativeSql
							.executeUpdate("INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)" + "VALUES ("
									+ null + ", " + null + ", '" + status + "', '" + e2.getMessage() + "')");
					contexto.setMensagemRetorno(e2.getMessage());
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			 contexto.setMensagemRetorno("Erro PP : " + e.getMessage());
			System.out.println("LINHA 307");
			try {
				boolean insert = nativeSql
						.executeUpdate("INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)" + "VALUES ("
								+ null + ", " + null + ", '" + status + "', '" + e.getMessage() + "')");
			} catch (java.lang.Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			contexto.setMensagemRetorno(e.getMessage());
		} catch (java.lang.Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			try {
				boolean insert = nativeSql
						.executeUpdate("INSERT INTO AD_LOG (pedidoOrga, pedidoProd, Status, descricao)" + "VALUES ("
								+ null + ", " + null + ", '" + status + "', '" + e3.getMessage() + "')");
			} catch (java.lang.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 contexto.setMensagemRetorno(e3.getMessage());
		} finally {
			JdbcWrapper.closeSession(JDBC);
			JapeSession.close(hnd);

			System.out.println("LINHA 3 final do incluir nota");

		}
	}
}
