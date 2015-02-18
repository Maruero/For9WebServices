package br.com.diastecnologia.for9.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.diastecnologia.for9.bean.LoginBean;


public class LoginDao extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public boolean loginIsValid( LoginBean login ){
		final String SQL = "select count(*) from login where Username = ? and Password = ?";
		return getJdbcTemplate().queryForObject(SQL, Integer.class, login.getUsuario(), login.getPassword()) > 0;
	}
	
	
}
