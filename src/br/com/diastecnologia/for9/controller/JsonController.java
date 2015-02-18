package br.com.diastecnologia.for9.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.diastecnologia.for9.bean.AgendaBean;
import br.com.diastecnologia.for9.bean.AssociadoBean;
import br.com.diastecnologia.for9.bean.CategoriaAssociadoBean;
import br.com.diastecnologia.for9.bean.ContatoBean;
import br.com.diastecnologia.for9.bean.DefaultBean;
import br.com.diastecnologia.for9.bean.LoginBean;
import br.com.diastecnologia.for9.bean.Session;
import br.com.diastecnologia.for9.business.For9Business;

@Resource
public class JsonController {

	private Result result;
	private For9Business business;
	
	public JsonController(Result _result, For9Business _business, Session _session){
		this.result = _result;
		this.business = _business;
		this.business.setSession(_session);
	}
	
	@Path("/login-json")
	@Post
	public void doLogon( String usuario, String password ){
		try{
			LoginBean login = new LoginBean();
			login.setUsuario(usuario);
			login.setPassword(password);
			boolean valid = business.isValidLogin(login);
			
			DefaultBean resp = new DefaultBean();
			if( !valid ){
				resp.setErrorCode(1);
				resp.setErrorMessage("Invalid Username or/and password!");
			}else{
				resp.setErrorCode(0);
				resp.setErrorMessage("");
			}
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
			
		}catch(Throwable th){
			th.printStackTrace();
			DefaultBean resp = new DefaultBean();
			resp.setErrorCode(-1);
			resp.setErrorMessage( th.getMessage());
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
		}
	}
	
	@Path("/listar-categorias-de-associados-json")
	public void getCategoriaAssociado(){
		try{
			if( !business.isLogged() ){
				DefaultBean resp = new DefaultBean();
				resp.setErrorCode(0);
				resp.setErrorMessage( "Invalid session!" );
				result.use( Results.json() ).from( resp ).recursive().serialize();
				return;
			}
			
			List<CategoriaAssociadoBean> categorias = business.listCategorias();
			result.use( Results.json() ).from( categorias ).recursive().serialize();
			return;
			
		}catch(Throwable th){
			th.printStackTrace();
			DefaultBean resp = new DefaultBean();
			resp.setErrorCode(-1);
			resp.setErrorMessage( th.getMessage());
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
		}
	}
	
	@Path("/listar-associados-json")
	public void getAssociados(Integer categoriaId){
		try{
			if( !business.isLogged() ){
				DefaultBean resp = new DefaultBean();
				resp.setErrorCode(0);
				resp.setErrorMessage( "Invalid session!" );
				result.use( Results.json() ).from( resp ).recursive().serialize();
				return;
			}
			
			List<AssociadoBean> associados = business.getAssociados(categoriaId);
			result.use( Results.json() ).from( associados ).recursive().serialize();
			return;
			
		}catch(Throwable th){
			th.printStackTrace();
			DefaultBean resp = new DefaultBean();
			resp.setErrorCode(-1);
			resp.setErrorMessage( th.getMessage());
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
		}
	}
	
	@Path("/obter-associado-json")
	public void getAssociado(Integer associadoId){
		try{
			if( !business.isLogged() ){
				DefaultBean resp = new DefaultBean();
				resp.setErrorCode(0);
				resp.setErrorMessage( "Invalid session!" );
				result.use( Results.json() ).from( resp ).recursive().serialize();
				return;
			}
			
			AssociadoBean associado = business.getAssociado(associadoId);
			result.use( Results.json() ).from( associado ).recursive().serialize();
			return;
			
		}catch(Throwable th){
			th.printStackTrace();
			DefaultBean resp = new DefaultBean();
			resp.setErrorCode(-1);
			resp.setErrorMessage( th.getMessage());
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
		}
	}
	
	@Path("/listar-agendas-json")
	public void getAgendas(){
		try{
			if( !business.isLogged() ){
				DefaultBean resp = new DefaultBean();
				resp.setErrorCode(0);
				resp.setErrorMessage( "Invalid session!" );
				result.use( Results.json() ).from( resp ).recursive().serialize();
				return;
			}
			
			List<AgendaBean> agendas = business.getAgendas();
			result.use( Results.json() ).from( agendas ).recursive().serialize();
			return;
			
		}catch(Throwable th){
			th.printStackTrace();
			DefaultBean resp = new DefaultBean();
			resp.setErrorCode(-1);
			resp.setErrorMessage( th.getMessage());
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
		}
	}
	
	@Path("/obter-contato-json")
	public void getContatos(){
		try{
			if( !business.isLogged() ){
				DefaultBean resp = new DefaultBean();
				resp.setErrorCode(0);
				resp.setErrorMessage( "Invalid session!" );
				result.use( Results.json() ).from( resp ).recursive().serialize();
				return;
			}
			
			ContatoBean contato = business.getContato();
			result.use( Results.json() ).from( contato ).recursive().serialize();
			return;
			
		}catch(Throwable th){
			th.printStackTrace();
			DefaultBean resp = new DefaultBean();
			resp.setErrorCode(-1);
			resp.setErrorMessage( th.getMessage());
			result.use( Results.json() ).from( resp ).recursive().serialize();
			return;
		}
	}
	
}
