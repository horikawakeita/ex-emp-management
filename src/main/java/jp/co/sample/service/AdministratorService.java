package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
}
