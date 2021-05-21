package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者関連機能の業務処理を行うサービス
 * @author keita.horikawa
 *
 */
@Service
@Transactional
public class AdministratorService {

	/**リポジトリ*/
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を挿入するメソッド.
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * ログイン処理を行うメソッド.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return メールアドレスとパスワードが一致した管理者の情報
	 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
}
