package br.com.diastecnologia.for9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.diastecnologia.for9.bean.ContatoBean;


public class ContatoDao extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public void addContato(ContatoBean contato){
		final String SQL = "insert into contato (Horario, Endereco, Estado, Cidade, CEP, Telefone1, Telefone2, Latitude, Longitude) values (?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(SQL, contato.getHorarioFuncionamento(), contato.getEndereco(), contato.getEstado(), contato.getCidade(), contato.getCep(), contato.getTelefone1(), contato.getTelefone2(), contato.getLatitude(), contato.getLongitude());
	}
	
	public void updateContato(ContatoBean contato){
		final String SQL = "update contato set Horario = ?, Endereco = ?, Estado = ?, Cidade = ?, CEP = ?, Telefone1 = ?, Telefone2 = ?, Latitude = ?, Longitude = ? where ContatoId = ?";
		getJdbcTemplate().update(SQL, contato.getHorarioFuncionamento(), contato.getEndereco(), contato.getEstado(), contato.getCidade(), contato.getCep(), contato.getTelefone1(), contato.getTelefone2(), contato.getLatitude(), contato.getLongitude(), contato.getId());
	}
	
	public ContatoBean getContato(Integer contatoId){
		final String SQL = "select * from contato where contatoId = ?";
		List<ContatoBean> contatos = getJdbcTemplate().query(SQL, contatoMapper, contatoId);
		return contatos.size() > 0 ? contatos.get( 0 ) : null;
	}
	
	private RowMapper<ContatoBean> contatoMapper = new RowMapper<ContatoBean>() {
		public ContatoBean mapRow(ResultSet result, int row) throws SQLException {
			ContatoBean contato = new ContatoBean();
			
			contato.setId( result.getInt( "ContatoId" ));
			contato.setHorarioFuncionamento( result.getString( "Horario" ));
			contato.setEndereco( result.getString( "Endereco"));
			contato.setEstado( result.getString( "Estado" ));
			contato.setCidade( result.getString( "Cidade" ));
			contato.setCep( result.getString( "CEP" ));
			contato.setTelefone1( result.getString("Telefone1" ));
			contato.setTelefone2( result.getString( "Telefone2" ));
			contato.setLatitude( result.getDouble("Latitude"));
			contato.setLongitude( result.getDouble( "Longitude"));
			
			return contato;
		}
		
	};

}
