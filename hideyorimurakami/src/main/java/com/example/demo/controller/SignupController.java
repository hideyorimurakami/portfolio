package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.models.BjUser;
import com.example.demo.models.BjUserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class SignupController {
	@Autowired
    PasswordEncoder passwordEncoder;
private final BjUserRepository rep;

    /**
     * ユーザー登録画面のGETメソッド用処理.
     */
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {


        // signup.htmlに画面遷移
        return "login/signup";
    }

    /**
     * ユーザー登録画面のPOSTメソッド用処理.
     */
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
            BindingResult bindingResult,
            Model model, @ModelAttribute BjUser bjUser) {

        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if (bindingResult.hasErrors()) {

            // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻ります
            return getSignUp(form, model);

        }

     // formの中身をコンソールに出して確認します
        System.out.println(form);

        //ユーザ名がすでに使われていた場合
        if(rep.findByUsername(form.getUserName()) != null) {
        	model.addAttribute("emsg","※The username is already in use.");
        	return getSignUp(form, model);
        }else {
        	bjUser.setUsername(form.getUserName());
            bjUser.setPassword(passwordEncoder.encode(form.getPassword()));
            rep.save(bjUser);

            // login.htmlにリダイレクト
            return "redirect:/login";
        }



    }
}