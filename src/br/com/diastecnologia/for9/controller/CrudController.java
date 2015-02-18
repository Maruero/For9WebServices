package br.com.diastecnologia.for9.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.diastecnologia.for9.bean.AgendaBean;
import br.com.diastecnologia.for9.bean.AssociadoBean;
import br.com.diastecnologia.for9.bean.CategoriaAssociadoBean;
import br.com.diastecnologia.for9.bean.ContatoBean;
import br.com.diastecnologia.for9.bean.ImagemBean;
import br.com.diastecnologia.for9.bean.LoginBean;
import br.com.diastecnologia.for9.bean.Session;
import br.com.diastecnologia.for9.business.For9Business;
import br.com.diastecnologia.for9.util.ImageUtil;

@Resource
public class CrudController{

	private Result result;
	private ServletRequest request;
	private For9Business business;
	
	public CrudController(Result _result, ServletRequest _request, For9Business _business, Session _session){
		this.result = _result;
		this.request = _request;
		this.business = _business;
		this.business.setSession(_session);
	}                       
	
	@Path("/")
	public void home(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
	}
	
	@Path("/login")
	public void login(){
		
	}
	
	@Path("/logon")
	@Post
	public void logon(LoginBean login){
		if( business.isValidLogin(login) ){
			result.redirectTo(CrudController.class).home();
			return;
		}else{
			result.include("message", "Usuário ou senha inválidos.");
			result.redirectTo(CrudController.class).login();
			return;
		}
	}
	
	@Path("/associados")
	public void associados(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		
	}
	
	@Path("/categorias")
	public void categorias(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		result.include("categorias", business.listCategorias());
	}
	
	@Path("/adicionar-categoria")
	public void addCategoria(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		result.forwardTo(CrudController.class).editCategoria(null);
	}
	
	@Path("/editar-categoria")
	public void editCategoria(Integer categoriaId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		CategoriaAssociadoBean categoria = null;
		if( categoriaId != null && categoriaId > 0 ){
			categoria = business.getCategoria(categoriaId);
		}else{
			categoria = new CategoriaAssociadoBean();
		}
		result.include("categoria", categoria);
		
	}
	
	@Path("/remover-categoria")
	public void deleteCategoria(Integer categoriaId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		business.deleteCategoria(categoriaId);
		result.redirectTo(CrudController.class).categorias();
	}
	
	@Path("/salvar-categoria")
	@Post
	public void saveCategoria(CategoriaAssociadoBean categoria){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		if( categoria.getId() > 0 ){
			business.updateCategoria(categoria);
		}else{
			business.addCategoria(categoria);
		}
		result.redirectTo(CrudController.class).categorias();
	}
	
	@Path("/listar-associados")
	public void listAssociados(Integer categoriaId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		CategoriaAssociadoBean categoria = business.getCategoria(categoriaId);
		List<AssociadoBean> associados = business.getAssociados(categoriaId);
		result.include("associados", associados);
		result.include("categoria", categoria);
	}
	
	@Path("/adicionar-associado")
	public void addAssociado(Integer categoriaId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		result.forwardTo(CrudController.class).editAssociado(categoriaId, null);
	}
	
	@Path("/editar-associado")
	public void editAssociado(Integer categoriaId, Integer associadoId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		AssociadoBean associado = null;
		if( associadoId != null && associadoId > 0 ){
			associado = business.getAssociado(associadoId);
		}else{
			associado = new AssociadoBean();
		}
		result.include("associado", associado);
		result.include("categoriaId", categoriaId);
	}
	
	@Path("/salvar-associado")
	public void saveAssociado(Integer categoriaId, AssociadoBean associado){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		if( associado.getId() > 0 ){
			business.updateAssociado(associado);
		}else{
			business.addAssociado(associado, categoriaId);
		}
		
		result.redirectTo(CrudController.class).listAssociados(categoriaId);
	}
	
	@Path("/remover-associado")
	public void deleteAssociado(Integer categoriaId, Integer associadoId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		business.deleteAssociado(associadoId);
		result.redirectTo(CrudController.class).listAssociados(categoriaId);
	}
	
	@Path("/agendas")
	public void listAgendas(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		
		List<AgendaBean> agendas = business.getAgendas();
		result.include("agendas", agendas);
	}
	
	@Path("/adicionar-agenda")
	public void addAgenda(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		result.forwardTo(CrudController.class).editAgenda(null);
	}
	
	@Path("/editar-agenda")
	public void editAgenda(Integer agendaId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		AgendaBean agenda = null;
		if( agendaId != null && agendaId > 0 ){
			agenda = business.getAgenda(agendaId);
		}else{
			agenda = new AgendaBean();
		}
		result.include("agenda", agenda);
	}
	
	@Path("/salvar-agenda")
	public void saveAgenda(AgendaBean agenda){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		if( agenda.getAgendaId() != null && agenda.getAgendaId() > 0 ){
			business.updateAgenda(agenda);
		}else{
			business.addAgenda(agenda);
		}
		
		result.redirectTo(CrudController.class).listAgendas();
	}
	
	@Path("/remover-agenda")
	public void deleteAgenda(Integer agendaId){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		business.deleteAgenda(agendaId);
		result.redirectTo(CrudController.class).listAgendas();
	}
	
	@Path("/carregar-imagem")
	@Post
	public void loadImage(UploadedFile image) throws IOException{
		String basePath = request.getServletContext().getRealPath("/");
		
		String path = ImageUtil.saveFile(basePath, image);
		ImagemBean imageBean = new ImagemBean();
		imageBean.setUrl(path);
		business.addImage(imageBean);
		
		result.include("image", imageBean);
	}
	
	
	@Path("/remover-imagem")
	@Post
	public void removeImage(Integer imagemId){
		business.removeImage(imagemId);
		result.use( Results.json()).from("OK").recursive().serialize();
	}
	
	@Path("/contatos")
	public void editContato(){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		ContatoBean contato = business.getContato();
		result.include("contato", contato);
	}
	
	@Path("/salvar-contatos")
	@Post
	public void saveContato(ContatoBean contato){
		if( !business.isLogged() ){
			result.redirectTo(CrudController.class).login();
			return;
		}
		
		business.updateContato(contato);
		result.include("message", "Contato salvo.");
		result.redirectTo(CrudController.class).editContato();
	}
}
