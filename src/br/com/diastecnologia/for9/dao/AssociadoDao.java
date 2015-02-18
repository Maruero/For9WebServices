package br.com.diastecnologia.for9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.diastecnologia.for9.bean.AssociadoBean;
import br.com.diastecnologia.for9.bean.CategoriaAssociadoBean;
import br.com.diastecnologia.for9.bean.ImagemBean;
import br.com.diastecnologia.for9.bean.PaisBean;

public class AssociadoDao extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}

	public Integer getLastValue(){
		final String SQL = "select last_insert_id()";
		return getJdbcTemplate().queryForObject(SQL, Integer.class);
	}
	
	public void addCategoriaAssociado( CategoriaAssociadoBean categoria ){
		final String SQL = "insert into categoria (Nome, ImagemId) values (?,?)";
		getJdbcTemplate().update(SQL, categoria.getNome(), categoria.getImagem().getId());
	}
	
	public void updateCategoriaAssciado( CategoriaAssociadoBean categoria ){
		final String SQL = "update categoria set Nome = ?, ImagemId = ? where CategoriaId = ?";
		getJdbcTemplate().update(SQL, categoria.getNome(), categoria.getImagem().getId(), categoria.getId());
	}
	
	public void deleteCategoriaAssociado( Integer categoriaId){
		final String SQL = "delete from categoria where CategoriaId = ?";
		getJdbcTemplate().update(SQL, categoriaId);
	}
	
	public CategoriaAssociadoBean getCategoria( Integer categoriaId ){
		final String SQL = "select * from categoria where categoriaId = ?";
		List<CategoriaAssociadoBean> categorias = getJdbcTemplate().query(SQL, categoriaMapper, categoriaId);
		return categorias.size() > 0 ? categorias.get( 0 ) : null;
	}
	
	public List<CategoriaAssociadoBean> getCategorias( ){
		final String SQL = "select * from categoria";
		return getJdbcTemplate().query(SQL, categoriaMapper);
	}
	
	public void addAssociado(AssociadoBean associado, Integer categoriaId ){
		final String SQL = "insert into associado (Nome, Telefone, Email, PaisId, CategoriaId, Site) values (?,?,?,?,?,?)";
		Integer paisId = null;
		if( associado.getPais() != null ){
			paisId = associado.getPais().getId();
		}
		getJdbcTemplate().update(SQL, associado.getNome(), associado.getTelefone(), associado.getEmail(), paisId, categoriaId, associado.getSite() );
	}
	
	public void updateAssociado(AssociadoBean associado){
		final String SQL = "update associado set Nome = ?, Telefone = ?, Email = ?, PaisId = ?, Site = ? where CategoriaId = ?";
		Integer paisId = null;
		if( associado.getPais() != null ){
			paisId = associado.getPais().getId();
		}
		getJdbcTemplate().update(SQL, associado.getNome(), associado.getTelefone(), associado.getEmail(), paisId, associado.getSite(), associado.getId() );
	}
	
	public void deleteAssociado(Integer associadoId){
		final String SQL = "delete from associado where AssociadoId = ?";
		getJdbcTemplate().update(SQL, associadoId);
	}
	
	public List<AssociadoBean> getAssociados(Integer categoriaId){
		final String SQL = "select A.*, P.PaisId, P.Nome as PaisNome from associado A left outer join pais P on A.PaisId = P.PaisId where CategoriaId = ?";
		return getJdbcTemplate().query(SQL, associadoMapper, categoriaId);
	}
	
	public AssociadoBean getAssociado( Integer idAssociado ){
		final String SQL = "select A.*, P.PaisId, P.Nome as PaisNome from associado A left outer join pais P on A.PaisId = P.PaisId where AssociadoId = ?";
		List<AssociadoBean> associados = getJdbcTemplate().query(SQL, associadoMapper, idAssociado);
		return associados.size() > 0 ? associados.get( 0 ) : null;
	}
	
	private RowMapper<AssociadoBean> associadoMapper = new RowMapper<AssociadoBean>() {
		public AssociadoBean mapRow(ResultSet result, int row) throws SQLException {
			AssociadoBean associado = new AssociadoBean();
			
			associado.setId( result.getInt( "AssociadoId" ));
			associado.setNome( result.getString( "Nome" ));
			associado.setEmail( result.getString( "Email" ));
			associado.setSite( result.getString( "Site" ));
			associado.setTelefone( result.getString("Telefone"));
			
			Integer paisId = result.getInt( "PaisId" );
			if( paisId != null && paisId > 0 ){
				PaisBean pais = new PaisBean();
				pais.setId(paisId);
				pais.setNome( result.getString("PaisNome" ) );
				associado.setPais(pais);
			}
			
			
			return associado;
		}
	}; 
	
	private RowMapper<CategoriaAssociadoBean> categoriaMapper = new RowMapper<CategoriaAssociadoBean>() {
		public CategoriaAssociadoBean mapRow(ResultSet result, int row) throws SQLException {
			CategoriaAssociadoBean categoria = new CategoriaAssociadoBean();
			
			categoria.setId( result.getInt( "CategoriaId" ));
			categoria.setNome( result.getString( "Nome" ));
			
			ImagemBean imagem = new ImagemBean();
			imagem.setId( result.getInt( "ImagemId" ) );
			if( imagem.getId() != null || imagem.getId() > 0){
				categoria.setImagem(imagem);
			}
			
			return categoria;
		}
	};
}
