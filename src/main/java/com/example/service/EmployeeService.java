package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員のサービスクラス.
 * 
 * @author tadunakitamura
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全件取得する.
	 * 
	 * @return 従業員情報のリスト
	 */
	public List<Employee> showList() {

		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;

	}

	/**
	 * IDを元に従業員情報を取得.
	 * 
	 * @param id 従業員ID
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {

		Employee employee = employeeRepository.load(id);
		return employee;

	}

	/**
	 * 従業員情報の更新.
	 * 
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);

	}

}
