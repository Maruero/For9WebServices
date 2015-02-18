package br.com.diastecnologia.for9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.diastecnologia.for9.bean.AgendaBean;
import br.com.diastecnologia.for9.bean.ImagemBean;

public class AgendaDao extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public Integer getLastValue(){
		final String SQL = "select last_insert_id()";
		return getJdbcTemplate().queryForObject(SQL, Integer.class);
	}
	
	public void addAgenda(AgendaBean agenda){
		final String SQL = "insert into agenda (Nome, Inicio, Fim, Local, Site, Descricao, ImagemId) values (?,?,?,?,?,?,?)";
		getJdbcTemplate().update(SQL, agenda.getNome(), agenda.getDateInicioEvento(), agenda.getDateFimEvento(), agenda.getLocal(), agenda.getSite(), agenda.getDescricao(), agenda.getImagemPrincipal().getId());
	}
	
	public void updateAgenda(AgendaBean agenda){
		final String SQL = "update agenda set Nome = ?, Inicio = ?, Fim = ?, Local = ?, Site = ?, Descricao = ?, ImagemId = ?, AgendaId = ?";
		getJdbcTemplate().update(SQL, agenda.getNome(), agenda.getDateInicioEvento(), agenda.getDateFimEvento(), agenda.getLocal(), agenda.getSite(), agenda.getDescricao(), agenda.getImagemPrincipal().getId(), agenda.getAgendaId());
	}
	
	public void deleteAgenda(Integer agendaId){
		final String SQL = "delete from agenda where AgendaId = ?";
		getJdbcTemplate().update(SQL, agendaId);
	}
	
	public List<AgendaBean> getAgendas(){
		final String SQL = "select A.*, I.Path from agenda A left outer join imagem I on I.ImagemId = A.ImagemId";
		return getJdbcTemplate().query(SQL, agendaMapper);
	}
	
	public AgendaBean getAgenda(Integer agendaId){
		final String SQL = "select A.*, I.Path from agenda A left outer join imagem I on I.ImagemId = A.ImagemId where A.AgendaId = ?";
		List<AgendaBean> agendas = getJdbcTemplate().query(SQL, agendaMapper, agendaId);
		return agendas.size() > 0 ? agendas.get( 0 ): null;
	}
	
	
	private RowMapper<AgendaBean> agendaMapper = new RowMapper<AgendaBean>() {
		@Override
		public AgendaBean mapRow(ResultSet result, int row) throws SQLException {
			AgendaBean agenda = new AgendaBean();
			
			agenda.setAgendaId( result.getInt( "AgendaId" ));
			agenda.setNome( result.getString( "Nome" ));
			agenda.setDateInicioEvento( result.getString( "Inicio" ));
			agenda.setDateFimEvento( result.getString( "Fim" ));
			agenda.setLocal( result.getString( "Local" ));
			agenda.setDescricao( result.getString( "Descricao" ));
			agenda.setSite( result.getString( "Site" ));
			Integer imagemId = result.getInt( "ImagemId" );
			
			ImagemBean imagem = new ImagemBean();
			if( imagemId != null){
				imagem.setId(imagemId);
				imagem.setUrl( result.getString( "Path" ));
			}
			agenda.setImagemPrincipal(imagem);
			
			return agenda;
		}
	};

}
