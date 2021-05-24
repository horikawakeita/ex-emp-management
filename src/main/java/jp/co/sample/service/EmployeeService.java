package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員関連機能の業務処理を行うサービス.
 * 
 * @author keita.horikawa
 *
 */
@Service
@Transactional
public class EmployeeService {

	/** リポジトリ */
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得するメソッド.
	 * 
	 * @return 取得した従業員情報のリスト
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 従業員情報を取得するメソッド.
	 * 
	 * @param id 呼び出す従業員のID
	 * @return 取得できた従業員の情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員を更新するメソッド.
	 * 
	 * @param employee 更新する従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
}
