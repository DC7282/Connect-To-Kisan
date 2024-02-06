package com.dhiraj.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dhiraj.entity.Address;
import com.dhiraj.entity.BillingDetails;
import com.dhiraj.entity.Cart;
import com.dhiraj.entity.Contact;
import com.dhiraj.entity.OrderPlaced;
import com.dhiraj.entity.Product;
import com.dhiraj.entity.Setting;
import com.dhiraj.entity.UserRegistration;
import com.dhiraj.entity.Wishlist;
import com.dhiraj.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	String searchdata="All";
	int page=0;
	int produc_id;

	private final HttpServletRequest request;
	HttpSession session;

	public void UpdateBillingOrderStatus(int billingid) {
		BillingDetails bill=contactService.getBillingDetailsById(billingid);
			int status_count=0;
			List<OrderPlaced> lop=contactService.getAllOrderByBillingId(bill.getId());
			int count=0;
			for(OrderPlaced op : lop) {
				if(count==0) {
					count++;
					status_count=op.getDeliverystatus();
				}
				if(op.getDeliverystatus()<status_count) {
					status_count = op.getDeliverystatus();
				}
			}
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());
			contactService.UpdateBillingDeliveryStatus(bill.getId(), status_count, timeStamp);
	}

	public UserController(HttpServletRequest httpServletRequest, HttpSession session){
	      this.request = httpServletRequest;
	      this.session = session;
	}

	Model mod;
	public Model getDepartmentForHeader(Model model) {
		model.addAttribute("headerdepartments", contactService.getAllDepartmentList());
		return model;
	}

	int id;
	private float total=0, totalmrp=0, save=0, delivery=0, afterdescount=0;
	public Model getModdulForHeader(Model model) {
		total=0;
		totalmrp=0;
		save=0;
		delivery=0;
		afterdescount=0;

		if(session.getAttribute("uid")!=null) {
			int userid = (int)session.getAttribute("uid");
			List<Cart> cart = contactService.findProductByUserId(userid);
			List productid = new ArrayList();
			Setting setting = contactService.getAllSettingData();
			for(Cart c : cart) {
				productid.add(c.getProductid());
				Product pro = contactService.getProducts(c.getProductid());
				if(pro.getAvailability()==1) {
					totalmrp = totalmrp + (c.getQuantity() * pro.getMrp());
					total = total + (c.getQuantity() * pro.getSellingprice());
				}
			}
			save = totalmrp - total;
			afterdescount=total;
			if(total<setting.getMinshopingamount() && total>0){
				total += setting.getCharges();
				delivery=setting.getCharges();
			}
			model.addAttribute("headerwishlists", contactService.getWishlistCountForPerticularUser((int)session.getAttribute("uid")));
			model.addAttribute("headercarts", contactService.getCartCountForPerticularUser((int)session.getAttribute("uid")));
		}else {
			model.addAttribute("headerwishlists", 0);
			model.addAttribute("headercarts", 0);
		}
		model.addAttribute("headerusername", session.getAttribute("uname")==null ? null : (String) session.getAttribute("uname"));
		model.addAttribute("headertotals", total);
		model.addAttribute("totalmrp", totalmrp);
		model.addAttribute("totalsave", save);
		model.addAttribute("headertotalscharge", delivery >1 ? "₹"+delivery : "free");
		model.addAttribute("afterdescount", afterdescount);
		model.addAttribute("settingdata", contactService.getAllSettingData());
		return model;
	}

	@Autowired
	ContactService contactService;

	@GetMapping("/")
	public String home(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
//		System.out.println(contactService.getCartCountForPerticularUser((int)session.getAttribute("uid")));

		int bannerid=1;
		model.addAttribute("banner", contactService.getBannerById(bannerid));

		bannerid=2;
		model.addAttribute("firstsubbanner", contactService.getBannerById(bannerid));

		bannerid=3;
		model.addAttribute("secondsubbanner", contactService.getBannerById(bannerid));

		int sectionid=1;
		int limit=6;
		model.addAttribute("slider_products", contactService.getAllProductForIndexPage(sectionid,limit));

		sectionid=2;
		limit=8;
		model.addAttribute("featured_products", contactService.getAllProductForIndexPage(sectionid,limit));
//		System.out.println(session.getAttribute("uid"));

		limit=3;
		model.addAttribute("latest_products", contactService.getLatestProductForIndexPage(limit));
		
		sectionid=4;
		limit=3;
		model.addAttribute("rated_products", contactService.getAllProductForIndexPage(sectionid,limit));
		
		sectionid=5;
		limit=3;
		model.addAttribute("review_products", contactService.getAllProductForIndexPage(sectionid,limit));

		return "/index";
	}


	@GetMapping("/shop-grid")
	public String shopgrid(Model model) {
		Setting setting = contactService.getAllSettingData();
		if(searchdata.equals("All")){
			Page<Product> products = contactService.getAllProductsWithPagingnation(page, setting.getPagesize());
			model.addAttribute("products", products);
			model.addAttribute("currentPage", page);
			model.addAttribute("data", searchdata);
		}else{
			try {
		        int d = Integer.parseInt(searchdata);
		        Pageable pageable = PageRequest.of(page, setting.getPagesize(), Sort.by("id").descending());
		        Page<Product> products = contactService.getProductsByDepartment(d, pageable);
		        model.addAttribute("products", products);
				model.addAttribute("currentPage", page);
				model.addAttribute("data", d);
			} catch (Exception e) {
				Pageable pageable = PageRequest.of(page, setting.getPagesize(), Sort.by("id").descending());
		        Page<Product> products = contactService.getProductsBySearchList(searchdata, pageable);
		        model.addAttribute("products", products);
				model.addAttribute("currentPage", page);
				model.addAttribute("data", searchdata);
		    }
		}
		model.addAttribute("departments", contactService.getAllDepartmentList());
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		if(session.getAttribute("uid")!=null) {

		}else {
			model.addAttribute("headercarts", 0);
		}
		return "shop-grid";
	}

	@GetMapping("/shop-grid/{data}")
	public String shopgridDepartment(@PathVariable("data") String data, Model model, @RequestParam(defaultValue = "0") int page) {
			searchdata=data;
			this.page=page;
		return "redirect:/shop-grid";
	}

	@PostMapping("/shop-grid-data")
	public String shopgridSearch(HttpServletRequest request) {
		String search = request.getParameter("search");
		if(search!=null)
			searchdata=search;
		return "redirect:/shop-grid";
	}

	@GetMapping("/blog")
	public String blog(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		return "blog";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		return "contact";
	}

	@GetMapping("/shop-details")
	public String shopdetails(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);

		Product product = contactService.getProducts(produc_id);
		model.addAttribute("products", product);
		int sectionid=1;
		int limit=6;
		model.addAttribute("slider_products", contactService.getAllProductForIndexPage(sectionid,limit));
		model.addAttribute("related_products", contactService.getProductsByDepartmentLimit(product.getDepartment()));
		model.addAttribute("availabilitys", contactService.getAllProductAvailability(product.getAvailability()));
		return "shop-details";
	}

	@GetMapping("/product_details/{id}")
	public String productDetail(@PathVariable("id") int id) {
		produc_id=id;
		return "redirect:/shop-details";
	}

	@GetMapping("/shoping-cart")
	public String shopingCart(Model model) {
		model = getModdulForHeader(model);
		if(session.getAttribute("uid")!=null) {
			int userid = (int)session.getAttribute("uid");
			List<Cart> cart = contactService.findProductByUserId(userid);
			model.addAttribute("cartitem", cart);
			List productid = new ArrayList();
			for(Cart c : cart) {
				
				productid.add(c.getProductid());
			}
			model.addAttribute("products", contactService.findProductByProductId(productid));
			return "shoping-cart";
		}else {
			return "redirect:/userlogin";
		}
	}

	@PostMapping("/add_to_cart")
	public String addToCart(@ModelAttribute("cart") Cart cart, Model model) {
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int) session.getAttribute("uid");
		cart.setUserid(userid);
		Cart cartdata = contactService.getPerticularProductForUserFromCart(userid,cart.getProductid());
		if(cartdata==null) {
			contactService.saveCartProduct(cart);
		}else {
			int update = contactService.updateCartProductForUser(cart.getUserid(),cart.getProductid(),cart.getQuantity());
		}
		return "redirect:/shoping-cart";
	}

	@GetMapping("/shoping-wishlist")
	public String shopingWishlist(Model model) {
		model = getModdulForHeader(model);
		if(session.getAttribute("uid")!=null) {
			int userid = (int)session.getAttribute("uid");
			List<Wishlist> wishlist = contactService.findAllByUserid(userid);
			model.addAttribute("wishlistitem", wishlist);
			List productid = new ArrayList();
			for(Wishlist w : wishlist) {
				productid.add(w.getProductid());
			}
			model.addAttribute("products", contactService.findProductByProductId(productid));
			return "shoping-wishlist";
		}else {
			return "redirect:/userlogin";
		}
	}

	@PostMapping("/add_to_wishlist")
	public String addToWishlist(@ModelAttribute("wishlist") Wishlist Wishlist, Model model) {
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int) session.getAttribute("uid");
		Wishlist.setUserid(userid);
		Wishlist wishlistdata = contactService.getPerticularProductForUserFromWishlist(userid,Wishlist.getProductid());
		if(wishlistdata==null) {
			contactService.saveWishlistProduct(Wishlist);
		}
		return "redirect:/shoping-wishlist";
	}

	@PostMapping("/wishlist-to-cart")
	public String wishlistToCartAdd() {
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int) session.getAttribute("uid");
		List<Wishlist> wishlist = contactService.getAllWishlistByUserId(userid);

		for(Wishlist w : wishlist) {
			Cart cart=new Cart();
			cart.setProductid(w.getProductid());
			cart.setQuantity(1);
			cart.setUserid(userid);
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());
			cart.setTimeStamp(timeStamp);
			Cart cartdata = contactService.getPerticularProductForUserFromCart(userid,w.getProductid());
			if(cartdata==null) {
				contactService.saveCartProduct(cart);
			}else {
				int update = contactService.updateCartProductForUser(cart.getUserid(),cart.getProductid(),cart.getQuantity());
			}

//			contactService.saveCartProduct(cart);
		}

		return "redirect:/shoping-cart";
	}

	@GetMapping("/checkout")
	public String checkout(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int)session.getAttribute("uid");
		List<Cart> cart = contactService.findProductByUserId(userid);
		model.addAttribute("cartitem", cart);
		List productid = new ArrayList();
		for(Cart c : cart) {
			Product pro = contactService.getProducts(c.getProductid());
			if(pro.getAvailability()==1)
			productid.add(c.getProductid());
		}
		model.addAttribute("products", contactService.findProductByProductId(productid));

		model.addAttribute("user", contactService.getUserById(userid));
		model.addAttribute("addresses", contactService.getAddressByUserId(userid));
		
		return "checkout";
	}

	
	
	@GetMapping("/blog-details")
	public String blogdetails() {
		return "blog-details";
	}

	@PostMapping("/addcontact")
	public String saveStudent(@ModelAttribute("contact") Contact contact, Model model, RedirectAttributes redirAttrs) {
		Contact cont = contactService.saveStudent(contact);
		if (cont != null)
			redirAttrs.addFlashAttribute("success", "Thank You. Message Send succesfully!");
		else
			redirAttrs.addFlashAttribute("error", "Message Not Send some problem is there. Please try after some time");
		return "redirect:/contact";
	}

	@GetMapping("/delete-cart-item/{id}")
	public String deleteCartItem(@PathVariable("id") int id) {
		contactService.deleteCartProductById(id);
		return "redirect:/shoping-cart";
	}

	@GetMapping("/delete-wishlist-item/{id}")
	public String deleteWishlistItem(@PathVariable("id") int id) {
		contactService.deleteWishlistProductById(id);
		return "redirect:/shoping-wishlist";
	}

	@PostMapping("/billing")
	public String billing(@ModelAttribute("BillingDetails") BillingDetails billingdetails, Model model) {
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int)session.getAttribute("uid");

		billingdetails.setUserid(userid);
		billingdetails.setTotalbillingamount(total);
		BillingDetails bd=contactService.saveBillingDetails(billingdetails);

		List<Cart> cart = contactService.findProductByUserId(userid);
		for(Cart c : cart) {
			Product pro = contactService.getProducts(c.getProductid());
			if(pro.getAvailability()==1) {
				total = total + (c.getQuantity() * pro.getSellingprice());
	
				OrderPlaced op=new OrderPlaced();
				op.setBillingid(bd.getId());
				op.setUserid(userid);
				op.setProductid(pro.getId());
				op.setTitle(pro.getTitle());
				op.setMrp(pro.getMrp());
				op.setSellingprice(pro.getSellingprice());
				op.setQuantity(c.getQuantity());
				op.setAvailability(pro.getAvailability());
				op.setDepartment(pro.getDepartment());
				op.setCategory(pro.getCategory());
				op.setProductimage(pro.getProductimage());
				OrderPlaced orderplaced=contactService.saveOrderPlaced(op);
				if(orderplaced!=null)
					contactService.deleteCartProductById(c.getProductid());
			}
		}
		return "thankyou_page";
	}

	@GetMapping("/order-history")
	public String orderHistory(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int)session.getAttribute("uid");
		List<BillingDetails> billingdetails = contactService.getBillingDetailsByUserId(userid);
		model.addAttribute("billingdetails",billingdetails);
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		model.addAttribute("orders", contactService.getAllOrderByUserId(userid));
		return "user_orders";
	}

	@GetMapping("/order-history-details")
	public String orderHistoryDetails(Model model) {
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		if(session.getAttribute("uid")==null) {
			return "redirect:/userlogin";
		}
		int userid = (int)session.getAttribute("uid");
		BillingDetails billingdetails = contactService.getBillingDetailsById(id);
		model.addAttribute("billingdetails", billingdetails);
		List <OrderPlaced> ordplace = contactService.getAllOrderByBillingId(id);
		model.addAttribute("orders", ordplace);
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		Setting setting = contactService.getAllSettingData();
		float total=0, totalmrp=0, save=0, delivery=0, afterdescount=0;
		for(OrderPlaced op : ordplace) {
			if(op.getDeliverystatus()<5) {
			totalmrp =totalmrp + (op.getMrp() * op.getQuantity());
			afterdescount= afterdescount + (op.getSellingprice() * op.getQuantity());
			}
		}
		save=totalmrp-afterdescount;
		total=afterdescount;
		if(afterdescount<setting.getMinshopingamount() && afterdescount>0){
			total += setting.getCharges();
			delivery=setting.getCharges();
		}
		model.addAttribute("total", total);
		model.addAttribute("mrp", totalmrp);
		model.addAttribute("save", save);
		model.addAttribute("charge", delivery >1 ? delivery : "free");
		model.addAttribute("descount", afterdescount);

		if(billingdetails.getTotalbillingamount()!=total)
			contactService.updateBillingTotalAmount(total,id);

		return "user_order_details";
	}

	@GetMapping("/order-history/{id}")
	public String orderHistoryDetailsId(@PathVariable("id") int userid) {
		id=userid;
		return "redirect:/order-history-details";
	}

	@GetMapping("/delete-product-order/{orderid}/{billingid}")
	public String deleteProductOrders(@PathVariable("orderid") int orderid, @PathVariable("billingid") int billingid) {
		id=billingid;
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());
		contactService.UpdateDeliveryStatus(orderid,5,timeStamp);
		UpdateBillingOrderStatus(billingid);
		return "redirect:/order-history-details";
	}

//=================================================================================================================================================
//	User

	@GetMapping("/userlogin")
	public String userLogin() {
		if(session.getAttribute("uid")!=null) {
			return "redirect:/";
		}
		return "userlogin";
	}

	@PostMapping("/userlogined")
	public String userlogined(@ModelAttribute("UserRegistration") UserRegistration registration, Model model, RedirectAttributes redirAttrs) {
		UserRegistration user=contactService.checkUserAuth(registration.getContact(),registration.getPassword());
		
		if(user!=null) {
			if(user.getStatus()==1) {
				if(user.getContact().equals(registration.getContact()) && user.getPassword().equals(registration.getPassword())) {
					request.getSession().setAttribute("uid", user.getId());
					request.getSession().setAttribute("uname", user.getFirstname());
					session.setAttribute("name", user.getContact());
					return "redirect:/";
				}else {
					redirAttrs.addFlashAttribute("error", "Some Problem is there.....");
					return "redirect:/userlogin";
				}
			}else if(user.getStatus()==3) {
				redirAttrs.addFlashAttribute("error", "You are Deactivate your account, please Register again");
				return "redirect:/userregistration";
			}else {
				redirAttrs.addFlashAttribute("error", "You are blocked please try to contact with admin");
				return "redirect:/userlogin";
			}
			}else {
				redirAttrs.addFlashAttribute("error", "Username or Password does not matched");
				return "redirect:/userlogin";
			}

	}

	@GetMapping("/userregistration")
	public String userRegistration() {
		return "userregister";
	}

	@PostMapping("/userregistered")
	public String userRegistered(@ModelAttribute("UserRegistration") UserRegistration registration, Model model, RedirectAttributes redirAttrs) {
		String repassword = request.getParameter("repassword");
//		int user=contactService.checkUserRegistration(registration.getContact(), registration.getEmail());
		
//		UserRegistration user=contactService.checkUserAuth(registration.getContact(),registration.getPassword());
		UserRegistration usercontact=contactService.getUserContact(registration.getContact());
		if(usercontact!=null) {
			if(usercontact!=null && usercontact.getStatus()==3) {
				contactService.updateUserStatus(1,usercontact.getId());
				redirAttrs.addFlashAttribute("success", "Username activated please login your account");
				return "redirect:/userlogin";
			}else{
				redirAttrs.addFlashAttribute("error", "Contact Allready exist");
				return "redirect:/userlogin";
			}
		}else if(usercontact==null) {
			if(registration.getPassword().equals(repassword)) {
				UserRegistration ur=contactService.saveUserRegistration(registration);
				if(ur!=null) {
					redirAttrs.addFlashAttribute("success", "Register Succesfully!");
					return "redirect:/userlogin";
				}else {
					redirAttrs.addFlashAttribute("error", "Some Problem is there.....");
					return "redirect:/userregistration";
				}
			}else {
				redirAttrs.addFlashAttribute("error", "Password and Confirm Password deos not Matched");
				return "redirect:/userregistration";
			}
		}else {
			redirAttrs.addFlashAttribute("error", "User Already Exist.....");
			return "redirect:/userlogin";
		}
	}

	@GetMapping("/destroy-user-auth")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/userlogin";
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		
		if(session.getAttribute("uid")==null) {
			return "redirect:/";
		}
		model = getDepartmentForHeader(model);
		model = getModdulForHeader(model);
		int userid = (int)session.getAttribute("uid");
		model.addAttribute("user", contactService.getUserById(userid));
		model.addAttribute("addresses", contactService.getAddressByUserId(userid));
//		System.out.println(existingAddress.getLocation()+" "+existingAddress.getLandmark()+" "+existingAddress.getCity()+" "+existingAddress.getState()+" "+existingAddress.getPostcode());
		return "user_profile";
	}
	
	@PostMapping("/update-user-profile")
	public String updateUserProfile(@ModelAttribute("userregisteration") UserRegistration register, @ModelAttribute("address") Address address, Model model, RedirectAttributes redirAttrs) {
		if(session.getAttribute("uid")==null) {
			return "redirect:/";
		}
		UserRegistration ureg = null;
		int userid = (int)session.getAttribute("uid");
		UserRegistration existingUser = contactService.getUserById(userid);
		Address existingAddress = contactService.getAddressByUserId(userid);
		existingUser.setEmail(register.getEmail());
		existingUser.setFirstname(register.getFirstname());
		existingUser.setLastname(register.getLastname());
		ureg = contactService.saveUser(existingUser);
		
//		System.out.println(register.getEmail()+" "+register.getFirstname()+" "+register.getLastname()+" "+address.getLocation()+" "+address.getLandmark()+" "+address.getCity()+" "+address.getState()+" "+address.getPostcode());
		Address add=null;
		address.setUserid(existingUser.getId());
		if(existingAddress==null) {
			add=contactService.saveAddress(address);
		}else {
			existingAddress.setLocation(address.getLocation());
			existingAddress.setLandmark(address.getLandmark());
			existingAddress.setCity(address.getCity());
			existingAddress.setState(address.getState());
			existingAddress.setPostcode(address.getPostcode());
			existingAddress.setTimeStamp(new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date()));
			add=contactService.saveAddress(existingAddress);
		}
		if(add!=null && ureg!=null) 
			redirAttrs.addFlashAttribute("success", "Data Update Succesfully!");
		else 
			redirAttrs.addFlashAttribute("error", "Data not Updated, Some Problem is there.....");
		return "redirect:/profile";
	}
	
	@GetMapping("/deactive-user/{status}/{id}")
	public String deactiveUser(@PathVariable("status") int status, @PathVariable int id) {
		contactService.updateUserStatus(status,id);
		return "redirect:/destroy-user-auth";
	}
}