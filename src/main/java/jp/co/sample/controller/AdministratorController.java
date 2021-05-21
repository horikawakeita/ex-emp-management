package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を行うコントローラ.
 * 
 * @author keita.horikawa
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	/**サービス*/
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * 管理者登録フォームオブジェクトをrequestスコープに格納.
	 * 
	 * @return InsertAdministratorFormのオブジェクト
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * ログインフォームオブジェクトをrequestスコープに格納.
	 * 
	 * @return LoginFormのオブジェクト
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * administrator/insert.htmlへフォワード.
	 * 
	 * @return administrator/insert.htmlへのパス
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * administrator/login.htmlへフォワード.
	 * 
	 * @return administrator/login.htmlへのパス
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * 管理者情報を登録するメソッド.
	 * 
	 * @param form リクエストパラメータの入ったフォームオブジェクト
	 * @return ログイン画面へのパス
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
	
	
}
