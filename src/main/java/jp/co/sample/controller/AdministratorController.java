package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	/** サービス */
	@Autowired
	private AdministratorService administratorService;

	/** sessionスコープを使用するための変数 */
	@Autowired
	private HttpSession session;

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
	 * 登録画面へフォワード.
	 * 
	 * @return 登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * ログイン画面へフォワード.
	 * 
	 * @return ログイン画面
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
	
	/**
	 * ログイン処理を行うメソッド.
	 * 
	 * @param form ログインフォームに入力された情報
	 * @param model requestスコープに格納するための変数
	 * @return employeeControllerのshowListメソッドへフォワード
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			model.addAttribute("error","メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		}
		
		session.setAttribute("administratorName", administrator.getName());
		
		return "forward:/employee/showList";
	}
	
	/**
	 * ログアウト処理を行うメソッド.
	 * 
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		
		return "redirect:/";
	}

}
