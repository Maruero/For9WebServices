package br.com.diastecnologia.for9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.diastecnologia.for9.bean.ImagemBean;


public class ImagemDao extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public Integer getLastValue(){
		final String SQL = "select last_insert_id()";
		return getJdbcTemplate().queryForObject(SQL, Integer.class);
	}
	
	public Integer addImagem(ImagemBean imagem){
		final String SQL = "insert into imagem (Path) values (?)";
		getJdbcTemplate().update(SQL, imagem.getUrl());
		return getLastValue();
	}
	
	public ImagemBean getImagem( Integer imagemId ){
		final String SQL = "select * from imagem where ImagemId = ?";
		List<ImagemBean> imagens = getJdbcTemplate().query(SQL, imagemMapper, imagemId );
		return imagens.size() > 0 ? imagens.get( 0 ) : null;
	}
	
	public void deleteImagem(Integer imagemId ){
		final String SQL = "delete from imagem where ImagemId = ?";
		getJdbcTemplate().update(SQL, imagemId);
	}
	
	public List<ImagemBean> getAssociadoImagens(Integer associadoId ){
		final String SQL = "select * from imagem I join AssociadoImagem AI on I.ImagemId = AI.ImagemId where AI.AssociadoId = ?";
		return getJdbcTemplate().query(SQL, imagemMapper, associadoId);
	}
	
	public void addAssociadoImagem(Integer imagemId, Integer associadoId ){
		final String SQL = "insert into associadoimagem (AssociadoId, ImagemId) values (?,?)";
		getJdbcTemplate().update(SQL, associadoId, imagemId);
	}
	
	public void deleteAssociadoImagem(Integer imagemId, Integer associadoId ){
		final String SQL = "delete from AssociadoImagem where AssociadoId = ? and  ImagemId = ?";
		getJdbcTemplate().update(SQL, associadoId, imagemId);
	}
	
	public void deleteAssociadoImagens(Integer associadoId ){
		final String SQL = "delete from associadoimagem where AssociadoId = ?";
		getJdbcTemplate().update(SQL, associadoId);
	}
	
	public List<ImagemBean> getAgendaImagens(Integer agendaId ){
		final String SQL = "select * from imagem I join agendaimagem AI on I.ImagemId = AI.ImagemId where AI.AgendaId = ?";
		return getJdbcTemplate().query(SQL, imagemMapper, agendaId);
	}
	
	public void addAgendaImagem(Integer imagemId, Integer agendaId ){
		final String SQL = "insert into agendaimagem (AgendaId, ImagemId) values (?,?)";
		getJdbcTemplate().update(SQL, agendaId, imagemId);
	}
	
	public void deleteAgendaImagem(Integer imagemId, Integer agendaId ){
		final String SQL = "delete from agendaimagem where AgendaId = ? and  ImagemId = ?";
		getJdbcTemplate().update(SQL, agendaId, imagemId);
	}
	
	public void deleteAgendaImagens(Integer agendaId ){
		final String SQL = "delete from agendaimagem where AgendaId = ?";
		getJdbcTemplate().update(SQL, agendaId);
	}
	
	private RowMapper<ImagemBean> imagemMapper = new RowMapper<ImagemBean>() {
		public ImagemBean mapRow(ResultSet resultSet, int row) throws SQLException {
			ImagemBean imagem = new ImagemBean();
			imagem.setId(resultSet.getInt("ImagemId"));
			imagem.setUrl(resultSet.getString("Path"));
			return imagem;
		}
	};

}
