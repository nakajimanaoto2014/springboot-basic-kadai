package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    // フォーム表示
    @GetMapping("/form")
    public String form(Model model) {
       
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contactFormView";
    }
    // フォーム送信
    @PostMapping("/form")
    public String registerUser(
            @Validated @ModelAttribute("contactForm") ContactForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        // バリデーションエラー → リダイレクト
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("contactForm", form);
            redirectAttributes.addFlashAttribute(
                BindingResult.MODEL_KEY_PREFIX + "contactForm", result
            );
            return "redirect:/form";
        }

        // バリデーション成功 → 確認画面へ
       
        return "confirmView";
    }
}
