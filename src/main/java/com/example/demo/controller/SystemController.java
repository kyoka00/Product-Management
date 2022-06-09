package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Categories;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.form.ProductForm;
import com.example.demo.form.UserForm;

@Controller
public class SystemController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProductDao pDao;
	
	@Autowired
	CategoryDao cDao;
	
	@Autowired
	UserDao uDao;
	
	
	@RequestMapping(value={"/","/top"})
	public String top(@ModelAttribute("user") UserForm uform, Model model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("user") UserForm uform, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "login";
	    }
		
		User u = uDao.login(uform.getLoginId(), uform.getPassword());
		
		if(u==null) {
			model.addAttribute("msg", "IDかパスワードが一致しません");
			return "login";
		}else {
			session.setAttribute("userNo", u.getUserNo());
			session.setAttribute("userName", u.getUserName());
			List<Product> list = pDao.notFinished(u.getUserNo());
			session.setAttribute("productList",list);
	
			return "menu";
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@ModelAttribute("user") UserForm uform,@ModelAttribute("product") ProductForm pform, Model model){
		model.addAttribute("msg","ログアウトしました");
		return "login";
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public String menu(@ModelAttribute("user") UserForm uform, @ModelAttribute("product") ProductForm pform, Model model){
		Integer userNo = (Integer)session.getAttribute("userNo");
		List<Product> list = pDao.notFinished(userNo);
		session.setAttribute("productList",list);
		return "menu";
	}
	
	@RequestMapping(value="/allMenu", method=RequestMethod.GET)
	public String allMenuShow (@ModelAttribute("product") ProductForm pform,Model model) {
		Integer userNo = (Integer)session.getAttribute("userNo");
		List<Product> list = pDao.search(userNo,"");
		session.setAttribute("productList",list);
		return "history";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search (@ModelAttribute("product") ProductForm pform, BindingResult bindingResult, Model model) {
		String searchKey = pform.getSearchKey();
		if (searchKey.equals(null) || searchKey==null) {
			searchKey ="";
		}
		Integer userNo = (Integer)session.getAttribute("userNo");
		List<Product> list = pDao.search(userNo, searchKey);
		session.setAttribute("productList",list);
		return "history";
	}
	
	@RequestMapping(value="/insertMenu", method=RequestMethod.GET)
	public String insertMenu (@ModelAttribute("product") ProductForm pform, BindingResult bindingResult, Model model) {
		List<Categories> category = cDao.select();
		model.addAttribute("category", category);
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert (@Validated @ModelAttribute("product") ProductForm pform,  BindingResult bindingResult, Model model) {
		LocalDate purchase = LocalDate.parse(pform.getPurchaseDate());
		LocalDate starting = LocalDate.parse(pform.getStartingDate());
		LocalDate expiration = LocalDate.parse(pform.getExpirationDate());
		
		Integer userNo = (Integer)session.getAttribute("userNo");
		Product product = new Product(pform.getProductName(), pform.getBrandName(), pform.getCategoryId(), purchase, starting, expiration, pform.isFavorite(), pform.isFinished());
		String msg = pDao.insert(userNo, product);
		session.setAttribute("msg", msg);
		List<Product> list = pDao.notFinished(userNo);
		session.setAttribute("productList",list);
		return "menu";
	}
	
	
}
