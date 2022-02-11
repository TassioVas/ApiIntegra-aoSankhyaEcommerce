package br.com.doalti.sankhya.apiIntegrador;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;

public class ParceiroComParametros  {

	private static final SimpleDateFormat ddMMyyy = new SimpleDateFormat("dd/MM/yyyy");
	private String url;
	private String chaveAPI = "7d8a0590682cfb16c1e9";
	private String chaveAplicacao = "b5e6f0dc-da3b-479e-ad8a-448e0576cd4d";
	private String aceita_newsletter;
	private ServiceContext sctx;
	private BigDecimal cgc_cnpj;
	private Timestamp data_criacao;
	private Timestamp data_modificacao;
	private String data_nascimento;
	private String email;
	private String ie;
	private String rg;
	private String bairro;
	private String cep;
	private String cidade;
	// "cliente": "/api/v1/cliente/103265/"
	private String complemento = "";
	private String endereco = "";
	private String estado = "";
	private String nomeParc = "";
	private String pais = "";
	private String razao_social = "";
	private String tipo = "";
	private String tipPessoa = "";
	private String inscricaoEstadual = "";
	private String telefone_celular = "";
	private String telefone_comercial = "";
	private String telefone_principal = "";
	private String sexo = "";
	private String nome = "";
	private String ativo = "";
	private String end = "";
	private String msg = "";
	private String cnpj = "";
	private String cpf = "";
	private String cpfcnpj = "";
	// resource_uri": "/api/v1/cliente/1/",
	private BigDecimal codParc;
	private BigDecimal count;
	private BigDecimal codVend;
	private java.math.BigDecimal codCid;
	private String numero;
	private BigDecimal id_site;
	private java.math.BigDecimal codUf;

	// incluirParceiro
	public void incluirParceiro(String cpfcnpj, int id) throws Exception {
		System.out.println("chave api " + chaveAPI);
		System.out.println("LINHA 72");
		// TODO Auto-generated method stub
		JdbcWrapper JDBC = JapeFactory.getEntityFacade().getJdbcWrapper();
		NativeSql nativeSql = new NativeSql(JDBC);
		SessionHandle hnd = JapeSession.open();
		System.out.println("LINHA 77");
		try {

			System.out.println("INICIOU O CÓDIGO");
			

			ConexaoLojaIntegrada api = new ConexaoLojaIntegrada();
			String metodo = "GET";
			url = "/cliente";
		String resposta = api.ChamaApi2(url, metodo, chaveAPI, chaveAplicacao);

	    //	System.out.println("sysout linha 87" + resposta);

		//	JSONObject jsonobject1 = new JSONObject(resposta); 
		//	JSONArray jsonArray = jsonobject1.getJSONArray("objects");
		//	System.out.println("LINHA 90"); 
			try {
				System.out.println("entrou no try do parceiro");
					System.out.println("LINHA 93");

		//			int idCliente = obj.getInt("id");0
					String url2 = "/cliente/" + id ;
					String response1 = api.ChamaApi2(url2, metodo, chaveAPI, chaveAplicacao);
					System.out.println("LINHA 101" + response1);
					JSONObject obj2 = new JSONObject(response1);

					if (!obj2.isNull("tipo")) {
						tipo = obj2.getString("tipo");
						System.out.println("tipo : " + tipo);
						System.out.println("LINHA 111 tipo ");
					}
					if (!obj2.isNull("razao_social")) {
						razao_social = obj2.getString("razao_social");
						razao_social = razao_social.substring(0, 20);
						System.out.println("LINHA 109 razao_social " + razao_social);
					}
					if (!obj2.isNull("sexo")) {
						sexo = obj2.getString("sexo");
						System.out.println("LINHA 113 sexo " + sexo);
					}
					if (!obj2.isNull("telefone_principal")) {
						System.out.println("LINHA 116");
						telefone_principal = obj2.getString("telefone_principal");

					}
					if (!obj2.isNull("telefone_celular")) {
						System.out.println("LINHA 120");
						telefone_celular = obj2.getString("telefone_celular");
						System.out.println("LINHA 113 telefone_celular " + telefone_celular);
					}
					if (!obj2.isNull("nome")) {
						System.out.println("LINHA 124");
						nome = obj2.getString("nome");
						System.out.println("LINHA 113 nome " + nome);
					}
					if (!obj2.isNull("ie")) {
						ie = obj2.getString("ie");
						System.out.println("LINHA 129 ie" + ie);
					}
					if (!obj2.isNull("rg")) {
						rg = obj2.getString("rg");
						System.out.println("LINHA 133 rg" + rg);
					}
					if (!obj2.isNull("data_nascimento")) {
						data_nascimento = obj2.getString("data_nascimento");
						System.out.println("LINHA 137 data_nascimento" + data_nascimento);
					}
					if (!obj2.isNull("cnpj")) {
						cnpj = obj2.getString("cnpj");
						cpfcnpj = cnpj;
						System.out.println("cpfcnpj" + cpfcnpj);
						System.out.println("LINHA 141");
					}
					if (!obj2.isNull("cpf")) {
						cpf = obj2.getString("cpf");
						cpfcnpj = cpf;
						System.out.println("cpfcnpj" + cpfcnpj);
						System.out.println("LINHA 145");
					}
					if (!obj2.isNull("email")) {
						email = obj2.getString("email");
						System.out.println("LINHA 149");
					}
					try {
						System.out.println("Entrou no try de endereços classe de parceiro");
						JSONArray jsonArrayEndereco = obj2.getJSONArray("enderecos");

						for (int j = 0; j < jsonArrayEndereco.length(); j++) {
							System.out.println("LINHA 154");
							JSONObject objend = jsonArrayEndereco.getJSONObject(j);

							if (!objend.isNull("cidade")) {
								cidade = objend.getString("cidade");
								System.out.println("cidade " + cidade);
							}
							if (!objend.isNull("end")) {
								end = objend.getString("endereco");
							}
							if (!objend.isNull("complemento")) {
								complemento = objend.getString("complemento");
							}
							if (!objend.isNull("bairro")) {
								bairro = objend.getString("bairro");
							}
							if (!objend.isNull("cep")) {
								cep = objend.getString("cep");
							}
							if (!objend.isNull("numero")) {
								numero = objend.getString("numero");
							}
							if (!objend.isNull("estado")) {
								estado = objend.getString("estado");
								System.out.println("estado " + estado);
							}
							System.out.println("LINHA 178");
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("entrou no do item");
						System.out.println("LINHA 183");
					}

					System.out.println("TIPO DE PARCEIRO : " + tipo);

					ResultSet query2 = nativeSql.executeQuery(
							"SELECT " + " COUNT(*) AS CONTADOR FROM " + " TGFPAR WHERE CGC_CPF = '" + cpfcnpj + "'");

					if (query2.next() && query2.getInt("CONTADOR") == 0) {
						count = query2.getBigDecimal("CONTADOR");
						System.out.println("sysout entrou no incluir parceiro com o parceiro cnpj ou cpf " + cpfcnpj);

						System.out.println("LINHA 226" + cnpj);
						System.out.println("LINHA 227" + estado);
						System.out.println("LINHA 227" + cidade);
						ResultSet rs = nativeSql.executeQuery(" SELECT" + "  NOMECID," + "  CODCID" + " FROM "
								+ " TSICID  " + " WHERE " + " NOMECID = upper('" + cidade + "') ");

						if (rs.next()) {
							codCid = rs.getBigDecimal("CODCID");
							System.out.println("cidade pegando o cod da cidade e o nome " + cidade);
						}
						query2.close();
						System.out.println("LINHA 241 antes do cpf");
						System.out.println("LINHA 234 linha " + cnpj);
						cpfcnpj = cpfcnpj.replace(".", "").replace("/", "").replace("-", "");
						if (cpfcnpj.length() == 11) {
							System.out.println("entrou no if do cnpj incluir parceiro");

						/*	Date dataNASC = null;
							String anoi = data_nascimento.substring(0, 4);
							String mesi = data_nascimento.substring(5, 7);
							String diai = data_nascimento.substring(8, 10);
							String dataFormat = diai + "/" + mesi + "/" + anoi;
							System.out.println("dataFormat : " + dataFormat);
							SimpleDateFormat dtF = new SimpleDateFormat("dd/MM/yyyy");
							try {
								dataNASC = dtF.parse(dataFormat);
							} catch (Exception e) {
								System.out.println("ERRO NA FORMATA��O DA DATA DE NASCIMENTO.");
								e.printStackTrace();
							} */

							// Registro Parceiro = contexto.novaLinha("TGFPAR");
							EntityFacade dwfFacade1 = EntityFacadeFactory.getDWFFacade();
							EntityVO Parceiro = dwfFacade1.getDefaultValueObjectInstance(DynamicEntityNames.PARCEIRO);

							Timestamp dataHoraAtual = new Timestamp(System.currentTimeMillis());

							JapeWrapper parDAO = JapeFactory.dao("Parceiro");
							DynamicVO savePar = parDAO.create().set("NOMEPARC", nome)
									.set("CODVEND", BigDecimal.valueOf(0))
									.set("CODPARC", this.codParc)
									.set("TIPPESSOA", "F")
									.set("CGC_CPF", cpfcnpj)
									.set("TELEFONE", telefone_principal)
									.set("FAX", telefone_celular)
									.set("SEXO", sexo).set("CEP", cep)
									.set("NUMEND", String.valueOf(numero))
									.set("COMPLEMENTO", complemento)
									.set("EMAIL", email)
									.set("CODCID",  BigDecimal.valueOf(1))
									.set("INSCESTADNAUF", rg)
									.set("CLASSIFICMS", "C")
									.set("CLIENTE", "S")
									.set("CODTIPPARC", BigDecimal.valueOf(2200000)).save();
							System.out.println(cpfcnpj);
							System.out.println(telefone_principal);
							System.out.println(telefone_celular);
							System.out.println(complemento);
							System.out.println(rg);
							System.out.println(numero);

						} else {
							System.out.println("Entrou no else do parceiro cnpj");

							EntityFacade dwfFacade1 = EntityFacadeFactory.getDWFFacade();
							EntityVO Parceiro = dwfFacade1.getDefaultValueObjectInstance(DynamicEntityNames.PARCEIRO);

							Timestamp dataHoraAtual = new Timestamp(System.currentTimeMillis());
							String inscEstadual;

							if (this.ie.equals("") || this.ie.equals(null)) {
								inscEstadual = "ISENTO";
							} else {
								inscEstadual = ie;
							}

							JapeWrapper parDAO = JapeFactory.dao("Parceiro");
							DynamicVO savePar = parDAO.create().set("NOMEPARC", nome)
									.set("CODVEND", BigDecimal.valueOf(0)).set("CODPARC", this.codParc)
									.set("TIPPESSOA", "J")
									.set("CGC_CPF", cpfcnpj)
									.set("TELEFONE", telefone_principal)
									.set("FAX", telefone_celular)
									.set("SEXO", sexo).set("CEP", cep)
									.set("NUMEND", String.valueOf(numero))
									.set("COMPLEMENTO", complemento)
									.set("EMAIL", email)
									.set("CODCID", BigDecimal.valueOf(1)).set("INSCESTADNAUF", rg)
									.set("CLASSIFICMS", "C").set("CODTIPPARC", BigDecimal.valueOf(2200000))
									.set("CLIENTE", "S")
									.set("INSCESTADNAUF", inscEstadual).save();

							System.out.println("LINHA 294");
						}
						System.out.println(" Sysout foi incluido o parceiro de cnpj : " + cpfcnpj);
					} else {
						System.out.println("Ja incluso : " + cpfcnpj);

					}
					JdbcWrapper.closeSession(JDBC);
					// query.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("entrou no do item");
				System.out.println("LINHA 302");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LINHA 307");
		} finally {
			JdbcWrapper.closeSession(JDBC);
			JapeSession.close(hnd);
			System.out.println("LINHA Final parceiro");
		}
	}

}
