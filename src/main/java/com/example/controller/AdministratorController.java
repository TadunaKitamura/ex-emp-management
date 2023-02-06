package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

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

	@Autowired
	private HttpSession session;

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

	/**
	 * ログイン処理.
	 * 
	 * @param form 入力した管理者情報
	 * @return 従業員一覧ページへリダイレクト
	 * 
	 */
	@PostMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if (administrator == null) {
			model.addAttribute("messeage", "メールアドレスまたはパスワードが不正です。");
			return toLogin(form);

		}
		session.setAttribute("administratorName", administrator.getName());
		return "redirect:/employee/showList";
	}

}
