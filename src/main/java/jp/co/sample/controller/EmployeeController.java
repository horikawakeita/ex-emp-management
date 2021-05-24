package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員関連機能の処理の制御を行うコントローラ.
 * 
 * @author keita.horikawa
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	/** サービス */
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * UpdateEmployeeFormオブジェクトをrequestスコープに格納.
	 * 
	 * @return UpdateEmployeeFormオブジェクト
	 */
	@Autowired
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員を全員取得し従業員一覧へフォワード.
	 * 
	 * @param model requestスコープに格納するためのオブジェクト
	 * @return 従業員一覧
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		
		return "employee/list";
	}
	
	/**
	 * 検索した従業員情報をrequestスコープに格納し、従業員詳細情報へフォワード.
	 * 
	 * @param id 検索する従業員のID
	 * @param model requestスコープに格納するためのオブジェクト
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		
		return "employee/detail";
	}
	
	/**
	 * 従業員詳細情報を更新(ここでは扶養人数のみ)するメソッド.
	 * 
	 * @param form 更新する従業員情報
	 * @return employeeContorollerのshowListメソッドへリダイレクト
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		
		return "redirect:/employee/showList";
	}
}
