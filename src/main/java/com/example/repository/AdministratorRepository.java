package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author tadunakitamura
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Administratortオブジェクトを生成するローマッパー.
	 */

	private static final RowMapper<Administrator> Administrator_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getNString("mail_address"));
		administrator.setPassword(rs.getString("password"));

		return administrator;
	};

	/**
	 * 管理者情報の挿入.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String insertSql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name,:mailAddress,:password) ;";
		template.update(insertSql, param);

	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得.
	 * 
	 * @param mailAddress 管理者のメールアドレス
	 * @param password 管理者のパスワード
	 * @return 管理者情報
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address:mailAddress AND password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);

		List<Administrator> administratorList = template.query(sql, param, Administrator_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		}

		return administratorList.get(0);
	}
}
