package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String login(@Validated @ModelAttribute("user") UserForm uform, BindingResult bindingResult,@ModelAttribute("product") ProductForm pform, Model model) {
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
			showMenu(u.getUserNo());
	
			return "menu";
		}
	}
	@RequestMapping(value="/signupMenu", method=RequestMethod.GET)
	public String signupMenu(@ModelAttribute("user") UserForm uform, Model model){
		return "signup";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@ModelAttribute("user") UserForm uform,@ModelAttribute("product") ProductForm pform, Model model){
		model.addAttribute("msg","ログアウトしました");
		return "login";
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public String menu(@ModelAttribute("user") UserForm uform, @ModelAttribute("product") ProductForm pform, Model model){
		Integer userNo = (Integer)session.getAttribute("userNo");
		showMenu(userNo);
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
	
	@PostMapping(value="/insert")
	public String insert (@Validated @ModelAttribute("product") ProductForm pform,  BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "insert";
	    }
		LocalDate [] l =setLocalDate(pform.getPurchaseDate(),pform.getStartingDate(),pform.getExpirationDate());
		
		Integer userNo = (Integer)session.getAttribute("userNo");
		Product product = new Product( pform.getProductName(), pform.getBrandName(), pform.getCategoryId(), l[0], l[1], l[2], pform.isFavorite(), pform.isFinished());
		String msg = pDao.insert(userNo, product);
		model.addAttribute("msg", msg);
		showMenu(userNo);
		return "menu";
	}
	
	@RequestMapping(value="back", method=RequestMethod.GET)
	public String back(@ModelAttribute("product") ProductForm pform, Model model) {
		Integer userNo = (Integer)session.getAttribute("userNo");
		showMenu(userNo);
		return "menu";
	}
	
	
	
	@RequestMapping(value="/updateMenu", method = RequestMethod.GET)
	public String updateMenu(@ModelAttribute("product") ProductForm pform, BindingResult bindingResult, Model model) {
		Integer id = pform.getProductId();
		Product p = pDao.findById(id);
		model.addAttribute("chosenProduct",p);
		List<Categories> category = cDao.select();
		model.addAttribute("category", category);
		return "update";
	}
	
	@PostMapping(value="/update")
	public String update (@Validated @ModelAttribute("product") ProductForm pform,  BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "update";
	    }
		LocalDate [] l =setLocalDate(pform.getPurchaseDate(),pform.getStartingDate(),pform.getExpirationDate());
		Integer userNo = (Integer)session.getAttribute("userNo");
		Product product = new Product(pform.getProductId(),pform.getProductName(), pform.getBrandName(), pform.getCategoryId(), l[0], l[1], l[2], pform.isFavorite(), pform.isFinished());
		String msg = pDao.update(product);
		model.addAttribute("msg", msg);
		showMenu(userNo);
		return "menu";
	}
	
	@GetMapping("/sort")
	public String sort(@ModelAttribute("user") UserForm uform, @ModelAttribute("product") ProductForm pform, BindingResult bindingResult, Model model) {
		Integer sortNo = pform.getSortCase();
		
		List<Product> list = (List<Product>) session.getAttribute("productList");
		switch (sortNo) {
		case 0:
			list.sort((p1, p2) -> p1.getProductId() <= p2.getProductId() ? -1 : 1);
			break;
		case 1:
			list.sort((p1,p2) -> p1.getCategoryId() <= p2.getCategoryId() ? -1 :1);
			break;
		case 2:
			list.sort((d1,d2) -> d1.getPurchaseDate().compareTo(d2.getPurchaseDate()));
			break;
		case 3:
			list.sort((d1,d2) -> d1.getExpirationDate().compareTo(d2.getExpirationDate()));
			break;
		}
		session.setAttribute("productList",list);
		return "menu";
	}
	@GetMapping("/sort2")
	public String sort2(@ModelAttribute("user") UserForm uform, @ModelAttribute("product") ProductForm pform, BindingResult bindingResult, Model model) {
		Integer sortNo = pform.getSortCase();
		
		List<Product> list = (List<Product>) session.getAttribute("productList");
		switch (sortNo) {
		case 0:
			list.sort((p1, p2) -> p1.getProductId() <= p2.getProductId() ? -1 : 1);
			break;
		case 1:
			list.sort((p1,p2) -> p1.getCategoryId() <= p2.getCategoryId() ? -1 :1);
			break;
		case 2:
			list.sort((d1,d2) -> d1.getPurchaseDate().compareTo(d2.getPurchaseDate()));
			break;
		case 3:
			list.sort((d1,d2) -> d1.getExpirationDate().compareTo(d2.getExpirationDate()));
			break;
		}
		session.setAttribute("productList",list);
		return "history";
	}
	
	public void showMenu(Integer userNo) {
		List<Product> list = pDao.notFinished(userNo);
		session.setAttribute("productList",list);
	}
	
	public LocalDate[] setLocalDate(String purchaseDate, String startingDate, String expirationDate) {
		LocalDate [] changed = new LocalDate[3];
		LocalDate purchase = LocalDate.parse(purchaseDate);
		changed[0]=purchase;
		LocalDate starting =null;
		if(!Util.isNullOrEmpty(startingDate)) {
			starting = LocalDate.parse(startingDate);
		}
		changed[1]=starting;
		
		LocalDate expiration = LocalDate.parse(expirationDate);
		changed[2] =expiration;
		return changed;
	}

}
