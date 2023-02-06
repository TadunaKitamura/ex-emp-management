package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

/**
 * 管理者情報を操作するコントローラクラス.
 * 
 * @author tadunakitamura
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	/**
	 * 従業員登録画面表示.
	 * 
	 * * @param form 入力した管理者情報
	 * 
	 * @return 従業員登録画面
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form) {

		return "administrator/insert.html";
	}

	/**
	 * 管理者情報の登録.
	 * 
	 * @param form 管理者情報
	 * @return ログイン画面にリダイレクト
	 */
	@PostMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
//		administrator.setId(id);
//		administrator.setName(form.getName());
//		administrator.setMailAddress(form.getMailAddress());
//		administrator.setPassword(form.getPassword());

		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);

		return "redirect:/";
	}

	/**
	 * ログイン画面の表示.
	 * 
	 * @param form 管理者情報
	 * @return ログイン画面の表示
	 */
	@GetMapping("/")
	public String toLogin(LoginForm form) {
		return "administrator/login.html";
	}
}
