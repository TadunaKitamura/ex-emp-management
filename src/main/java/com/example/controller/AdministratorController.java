package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

/**
 * Administratorのコントローラクラス
 * 
 * @author tadunakitamura
 *
 */
@Controller
@RequestMapping("/admin")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	/**
	 * 従業員登録画面表示.
	 * 
	 * @return 従業員登録画面
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form, Model model) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());

		model.addAttribute("administrator", administrator);

		return "administrator/insert.html";
	}
}
