package br.com.diastecnologia.for9.business;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.diastecnologia.for9.bean.AgendaBean;
import br.com.diastecnologia.for9.bean.AssociadoBean;
import br.com.diastecnologia.for9.bean.CategoriaAssociadoBean;
import br.com.diastecnologia.for9.bean.ContatoBean;
import br.com.diastecnologia.for9.bean.ImagemBean;
import br.com.diastecnologia.for9.bean.LoginBean;
import br.com.diastecnologia.for9.bean.Session;
import br.com.diastecnologia.for9.dao.AgendaDao;
import br.com.diastecnologia.for9.dao.AssociadoDao;
import br.com.diastecnologia.for9.dao.ContatoDao;
import br.com.diastecnologia.for9.dao.ImagemDao;
import br.com.diastecnologia.for9.dao.LoginDao;

@Component
public class For9Business {

	private AssociadoDao associadoDao;
	private AgendaDao agendaDao;
	private ContatoDao contatoDao;
	private ImagemDao imagemDao;
	private LoginDao loginDao;
	
	private Session session;
	
	public For9Business(AssociadoDao _associadoDao, ContatoDao _contatoDao, ImagemDao _imagemDao, LoginDao _loginDao, AgendaDao _agendaDao){
		this.associadoDao = _associadoDao;
		this.contatoDao = _contatoDao;
		this.imagemDao = _imagemDao;
		this.loginDao = _loginDao;
		this.agendaDao = _agendaDao;
	}
	
	public boolean isLogged(){
		return session.isLogged();
	}
	
	public boolean isValidLogin(LoginBean login ){
		if( loginDao.loginIsValid(login)){
			session.setUserLogged(login);
			return true;
		}
		return false;
	}

	public List<CategoriaAssociadoBean> listCategorias(){
		return associadoDao.getCategorias();
	}
	
	public void addCategoria(CategoriaAssociadoBean categoria){
		associadoDao.addCategoriaAssociado(categoria);
	}
	
	public CategoriaAssociadoBean getCategoria(Integer categoriaId){
		CategoriaAssociadoBean categoria = associadoDao.getCategoria(categoriaId);
		if( categoria.getImagem() != null && categoria.getImagem().getId() != null && categoria.getImagem().getId() > 0 ){
			categoria.setImagem( imagemDao.getImagem(categoria.getImagem().getId() ) );
		}
		return categoria;
	}
	
	public void updateCategoria(CategoriaAssociadoBean categoria){
		associadoDao.updateCategoriaAssciado(categoria);
	}
	
	public void deleteCategoria(Integer categoriaId){
		associadoDao.deleteCategoriaAssociado(categoriaId);
	}
	
	
	public List<AssociadoBean> getAssociados(Integer categoriaId){
		return associadoDao.getAssociados(categoriaId);
	}
	
	public void addAssociado(AssociadoBean associado, Integer categoriaId){
		associadoDao.addAssociado(associado, categoriaId);
		Integer associadoId = associadoDao.getLastValue();
		if( associado.getListImagem() != null ){
			for( ImagemBean imagem : associado.getListImagem()){
				if( imagem != null ){
					imagemDao.addAssociadoImagem(imagem.getId(), associadoId);
				}
			}
		}
		associado.setId(associadoId);
	}
	
	public void updateAssociado(AssociadoBean associado){
		imagemDao.deleteAssociadoImagens(associado.getId());
		if( associado.getListImagem() != null ){
			for( ImagemBean imagem : associado.getListImagem()){
				if( imagem != null ){
					imagemDao.addAssociadoImagem(imagem.getId(), associado.getId());
				}
			}
		}
		associadoDao.updateAssociado(associado);
	}
	
	public void deleteAssociado(Integer associadoId){
		imagemDao.deleteAssociadoImagens(associadoId);
		associadoDao.deleteAssociado(associadoId);
	}
	
	public List<AgendaBean> getAgendas(){
		return agendaDao.getAgendas();
	}
	
	public AgendaBean getAgenda(Integer agendaId){
		AgendaBean agenda = agendaDao.getAgenda(agendaId);
		agenda.setListImagem( imagemDao.getAgendaImagens(agenda.getAgendaId()));
		return agenda;
	}
	
	public void addAgenda(AgendaBean agenda){
		agendaDao.addAgenda(agenda);
		Integer agendaId = agendaDao.getLastValue();
		if( agenda.getListImagem() != null ){
			for( ImagemBean imagem : agenda.getListImagem()){
				if( imagem != null ){
					imagemDao.addAgendaImagem(imagem.getId(), agendaId);
				}
			}
		}
		agenda.setAgendaId(agendaId);
	}
	
	public void updateAgenda(AgendaBean agenda){
		imagemDao.deleteAgendaImagens(agenda.getAgendaId());
		if( agenda.getListImagem() != null ){
			for( ImagemBean imagem : agenda.getListImagem()){
				if( imagem != null ){
					imagemDao.addAgendaImagem(imagem.getId(), agenda.getAgendaId());
				}
			}
		}
		agendaDao.updateAgenda(agenda);
	}
	
	public void deleteAgenda(Integer agendaId){
		imagemDao.deleteAgendaImagens(agendaId);
		agendaDao.deleteAgenda(agendaId);
	}
	
	public void addImage(ImagemBean imagem){
		imagemDao.addImagem(imagem);
		imagem.setId( imagemDao.getLastValue());
	}
	
	public void removeImage(Integer imageId){
		imagemDao.deleteImagem(imageId);
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public AssociadoBean getAssociado(Integer associadoId) {
		AssociadoBean associado = associadoDao.getAssociado(associadoId);
		associado.setListImagem(imagemDao.getAssociadoImagens(associado.getId()));
		return associado;
	}
	
	public ContatoBean getContato(){
		return contatoDao.getContato(1);
	}
	
	public void updateContato(ContatoBean contato){
		contatoDao.updateContato(contato);
	}
	
	
	
}
