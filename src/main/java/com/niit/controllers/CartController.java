package com.niit.controllers;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.myGreatSale.dao.CartDAO;
import com.niit.myGreatSale.dao.ProductDAO;
import com.niit.myGreatSale.model.MyCart;
import com.niit.myGreatSale.model.Product;

@Controller
	public class CartController {
	
		Logger log = LoggerFactory.getLogger(CartController.class);
		
		@Autowired
		private CartDAO cartDAO;

		@Autowired
		private MyCart myCart;

		@Autowired
		private ProductDAO productDAO;
		
		@Autowired
		private HttpSession session;

		@RequestMapping(value = "/myCart", method = RequestMethod.GET)
		public String myCart(Model model) {
			log.debug("Starting of the method myCart");
			
			// get the logged-in user id
			String loggedInUserid = (String) session.getAttribute("loggedInUserID");

			if (loggedInUserid == null) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				loggedInUserid = auth.getName();
				Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)   auth.getAuthorities();
				authorities.contains("ROLE_USER");
				
			}

			int cartSize = cartDAO.list(loggedInUserid).size();

			if (cartSize == 0) {
				model.addAttribute("errorMessage", "You do not have any products in your Cart :Plzs LOGin to Keep Shopping");
			} else {
				
				model.addAttribute("cartList", cartDAO.list(loggedInUserid));
				model.addAttribute("myCart", "myCart");
				model.addAttribute("totalAmount", cartDAO.getTotalAmount(loggedInUserid));
				
				model.addAttribute("displayCart", "true");
				

			}
			log.debug("Ending of the method myCart");
			return "/home";
		}

		// For add and update myCart both
		@RequestMapping(value = "/myCart/add/{id}", method = RequestMethod.GET)
		public ModelAndView addToCart(@PathVariable("id") String id) {
			
			log.debug("Starting of the method addToCart");
			// get the product based on product id
			
			Product product = productDAO.getProductByID(id);
			
			myCart.setPrice((int) product.getPrice());
			
			myCart.setName(product.getName());
			
			String loggedInUserid = (String) session.getAttribute("loggedInUserID");
			
			if (loggedInUserid == null) {
			
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				loggedInUserid = auth.getName();
				
			}
			myCart.setUserId(loggedInUserid);
			
			//It is not required if you given default value while creating the table
			
			myCart.setStatus('N'); // Status is New. Once it is dispatched, we can
									// changed to 'D'
			myCart.setAddedDate(new Date());
			
			if(myCart.getQuantity()== 0){
				myCart.setQuantity(1);
			}
			
			
			log.info("DATE IS "+myCart);
			//To get sequence number, you can do programmatically in DAOImpl
			//myCart.setId(ThreadLocalRandom.current().nextLong(100, 1000000 + 1));

			
			cartDAO.save(myCart);
			// return "redirect:/views/home.jsp";

			/*ModelAndView mv = new ModelAndView("forward:/myCart");*/
			ModelAndView mv = new ModelAndView("redirect:/myCart");
			/*ModelAndView mv = new ModelAndView("/home");*/
			mv.addObject("successMessage", " Successfuly add the product to myCart");
			log.debug("Ending of the method addToCart");
			return mv;

		}
		
		@RequestMapping("/remove/{id}")
		public ModelAndView RemoveFromCart(@PathVariable("id") Long id ){
			
			log.debug("Staring of remove Method");
			myCart.setId(id);
			cartDAO.delete(myCart);
			
			ModelAndView mv = new ModelAndView("redirect:/myCart");
			/*mv.addObject("successMessage", " Successfuly delete one product from myCart");*/
			log.debug("Staring of remove Method");
			
			return mv;
			
			
		}
		/*
		@RequestMapping("/remove/all/{id}")
		public ModelAndView Removeall(@PathVariable("id") String id ){
			
			log.debug("Staring of removeall method	");
			
			cartDAO.list(userId);
			
			ModelAndView mv = new ModelAndView("redirect:/myCart");
			mv.addObject("successMessage", " Successfuly delete one product from myCart");
			log.debug("Staring of remove Method");
			
			return mv;
			
			
		}
		
		*/
		
		
		
		@RequestMapping(value = "/details_get/myCart/add/{id}", method = RequestMethod.GET)
		public ModelAndView fromProductCart(@PathVariable("id") String id) {
			
			log.debug("Starting of the method addToCart");
			// get the product based on product id
			
			Product product = productDAO.getProductByID(id);
			
			myCart.setPrice((int) product.getPrice());
			
			myCart.setName(product.getName());
			
			String loggedInUserid = (String) session.getAttribute("loggedInUserID");
			
			if (loggedInUserid == null) {
			
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				loggedInUserid = auth.getName();
				
			}
			myCart.setUserId(loggedInUserid);
			
			//It is not required if you given default value while creating the table
			
			myCart.setStatus('N'); // Status is New. Once it is dispatched, we can
									// changed to 'D'
			myCart.setAddedDate(new Date());
			
			if(myCart.getQuantity()== 0){
				myCart.setQuantity(1);
			}
			
			
			log.info("DATE IS "+myCart);
			//To get sequence number, you can do programmatically in DAOImpl
			//myCart.setId(ThreadLocalRandom.current().nextLong(100, 1000000 + 1));

			
			cartDAO.save(myCart);
			// return "redirect:/views/home.jsp";

			/*ModelAndView mv = new ModelAndView("forward:/myCart");*/
			ModelAndView mv = new ModelAndView("redirect:/myCart");
			mv.addObject("successMessage", " Successfuly add the product to myCart");
			log.debug("Ending of the method addToCart");
			return mv;

		}
		

		
	}
	

