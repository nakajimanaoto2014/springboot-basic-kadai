package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController{

	//ファーム表示
	@GetMapping("/form")
public String form(Model model) {
	model.addAttribute("contactForm",new ContactForm());
	return "contactFormView";
}
	//ファーム送信
	@PostMapping("/register")
	public String registerUser (@Validated @ModelAttribute("contactForm")ContactForm form,
								BindingResult result,
								Model model) {
		
	//バリデーションエラーがあったら終了
	if(result.hasErrors()) {
		//エラーがある場合、入力画面に戻す
		return "contactFormView";
	}
	//バリデーション成功 → 確認画面へ
	model.addAttribute("contactForm",form);
	return "confirmView";
	}
}