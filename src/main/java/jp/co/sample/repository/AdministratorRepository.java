package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author keita.horikawa
 *
 */
@Repository
public class AdministratorRepository {

	/** Administratorオブジェクトを生成するRowMapper */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(
			Administrator.class);

	/** テンプレート */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 管理者情報を挿入するメソッド.
	 * 
	 * @param administrator 登録する管理者の情報
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String sql = "INSERT INTO administrators(name,mail_address,password) "
				+ "VALUES(:name,:mailAddress,:password)";
		template.update(sql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得するメソッド. 
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 取得した管理者情報(１件も存在しない場合はnullを返す)
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password "
				+ "FROM administrators "
				+ "WHERE mail_address = :mailAddress AND password = :password";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
				.addValue("password", password);

		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		}

		return administratorList.get(0);
	}
}
