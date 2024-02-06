package com.dhiraj.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dhiraj.entity.AdminLogin;
import com.dhiraj.entity.Banner;
import com.dhiraj.entity.BillingDetails;
import com.dhiraj.entity.Category;
import com.dhiraj.entity.OrderPlaced;
import com.dhiraj.entity.Product;
import com.dhiraj.entity.ProductAndMainPageSectionJoin;
import com.dhiraj.entity.Setting;
import com.dhiraj.entity.UserRegistration;
import com.dhiraj.fileio.ProductIO;
import com.dhiraj.service.ContactService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@Controller
public class AdminController {


	private final HttpServletRequest request;
	HttpSession session;

	public AdminController(HttpServletRequest httpServletRequest, HttpSession session){
	      this.request = httpServletRequest;
	      this.session = session;
	}

	@Autowired
	ContactService contactService;

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


	@GetMapping("/adminlogin")
	public String adminlogin(Model model) {
			return "adminlogin";

	}

	@PostMapping("/admin-auth")
	public String adminlogin(@ModelAttribute("adminlogin") AdminLogin adminlogin, Model model, RedirectAttributes redirAttrs) {
		int count = contactService.checkAdminAuth(adminlogin.getUsername(),adminlogin.getPassword());
		if(count>0) {
			request.getSession().setAttribute("MY_ADMIN_SESSION", adminlogin.getUsername());
//			request.getSession().setAttribute("MY_ADMIN_SESSION", adminlogin.getUsername());
//			System.out.println(request.getSession().getAttribute("MY_ADMIN_SESSION"));
			return "redirect:/admin";
		}else {
			redirAttrs.addFlashAttribute("error", "Enter Correct Username & Password");
			return "redirect:/adminlogin";
		}
	}

	@GetMapping("/admin")
	public String admin(HttpServletRequest request, Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("totalusers", contactService.getUserCount());
		model.addAttribute("totalproduct", contactService.getProductCount());
		model.addAttribute("totalorders", contactService.getOrdersCount());
		model.addAttribute("totalmessages", contactService.getMessagesCount());
		model.addAttribute("totalcart", contactService.getCartCount());
		model.addAttribute("totalwish", contactService.getWishCount());

		return "admin";
	}

	@GetMapping("/destroy-admin-auth")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/adminlogin";
	}

	@GetMapping("/add-banner")
	public String addBanner(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("departments", contactService.getAllDepartmentList());
		return "add-banner";
	}

	@PostMapping("/banner-added")
	public String bannerAdded(@ModelAttribute("Banner") Banner banner, HttpServletRequest request, Model model, RedirectAttributes redirAttrs) throws Exception {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";

		ProductIO pio =new ProductIO();
		Part part=request.getPart("bannerimages");
//		String newfilename=pio.createFileName(part);

		  String image = part.getSubmittedFileName();
		  String newfilename=LocalDateTime.now().toString();
		  String[] split = newfilename.split(":");
		  newfilename = split[0]+split[1]+split[2]+"_"+image;

		  banner.setBannerimage(newfilename);
		  Banner ban = contactService.banneradded(banner);

		if (ban != null) {
			String res = pio.putInFile(newfilename, part);
			if(res.equals("saved"))
				redirAttrs.addFlashAttribute("success", "Banner Added succesfully!");
		}else {
			redirAttrs.addFlashAttribute("error", "Banner Not Added some problem is there");
		}
		return "redirect:/admin";
	}
	@GetMapping("/change-credential")
	public String changeCredential() {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		return "adminChangeCredential";
	}

	@PostMapping("/update-credential")
	public String updateCredential(HttpServletRequest request, Model model, RedirectAttributes redirAttrs) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		AdminLogin oldlogin = contactService.getAdminLoginCredential(1);
		String olduser = request.getParameter("olduser");
		String oldpass = request.getParameter("oldpass");
		String nuser = request.getParameter("nuser");
		String cuser = request.getParameter("cuser");
		String npass = request.getParameter("npass");
		String cpass = request.getParameter("cpass");

		if(olduser.equals(oldlogin.getUsername()) && oldpass.equals(oldlogin.getPassword())) {
			if(nuser.equals(cuser)) {
				if(nuser.equals(cpass)) {
					contactService.updateAdminCredential(nuser,npass);
					redirAttrs.addFlashAttribute("success", "Category Added succesfully!");
				}else {
					redirAttrs.addFlashAttribute("error", "New Password and Confirm New Password does not matched");
				}

			}else {
				redirAttrs.addFlashAttribute("error", "New Username and Confirm New Username does not matched");
			}

		}else {
			redirAttrs.addFlashAttribute("error", "Please enter correct old login credentials");
		}

		return "redirect:/change-credential";
	}


	@GetMapping("/messages")
	public String getAllMessages(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("contacts", contactService.getAllMessages());
		return "adminmessage";
	}

	@GetMapping("/contact/delete/{id}")
	public String deleteMessages(@PathVariable int id) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		contactService.deleteMessagesById(id);
		return "redirect:/messages";
	}

	@GetMapping("/addcat")
	public String addCat(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("products", contactService.getAllDepartmentList());
		return "addcategory";
	}

	@PostMapping("/addcategory")
	public String addCategory(@ModelAttribute Category category, Model model, RedirectAttributes redirAttrs) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		Category cat = contactService.saveCategory(category);
		if (cat != null)
			redirAttrs.addFlashAttribute("success", "Category Added succesfully!");
		else
			redirAttrs.addFlashAttribute("error", "Category Not Added some problem is there");
		return "redirect:/addcat";
	}

	 @GetMapping("/products/{did}")
	 @ResponseBody
	 public List<Optional> listcatagory(@PathVariable("did") int did){
		 return contactService.findByProductCategory(did);
	 }

	@GetMapping("/add-product")
	public String addproduct(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("departments", contactService.getAllDepartmentList());
		model.addAttribute("availabilitys", contactService.getAllProductAvailabilityList());
		return "addProduct";
	}

//	@RequestParam("productimage") MultipartFile file
	@PostMapping("/product-added")
	public String productadded(@ModelAttribute Product product, HttpServletRequest request, Model model, RedirectAttributes redirAttrs) throws Exception {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";

		ProductIO pio =new ProductIO();
		Part part=request.getPart("productimages");
//		String newfilename=pio.createFileName(part);

		  String image = part.getSubmittedFileName();
		  String newfilename=LocalDateTime.now().toString();
		  String[] split = newfilename.split(":");
		  newfilename = split[0]+split[1]+split[2]+"_"+image;

		product.setProductimage(newfilename);
		Product pro = contactService.productadded(product);

		if (pro != null) {
			String res = pio.putInFile(newfilename, part);
			if(res.equals("saved"))
				redirAttrs.addFlashAttribute("success", "Category Added succesfully!");
		}else {
			redirAttrs.addFlashAttribute("error", "Category Not Added some problem is there");
		}
		return "redirect:/add-product";
	}

	@GetMapping("/view-all-product")
	public String viewallproduct(Model model, @RequestParam(defaultValue = "0") int page)  {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		Setting setting = contactService.getAllSettingData();
		Page<Product> products = contactService.getAllProductsWithPagingnation(page, setting.getPagesize());
		model.addAttribute("products", products);
		model.addAttribute("currentPage", page);
		model.addAttribute("categorys", contactService.getAllCategoryList());
		model.addAttribute("departments", contactService.getAllDepartmentList());
		model.addAttribute("availabilitys", contactService.getAllProductAvailabilityList());
		return "allproducts";
	}

	@GetMapping("/orders_for_delivery")
	public String ordersForDelivery(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		model.addAttribute("BillingDetails", contactService.getProcessingDelivery());
		return "admin_orders_for_delivery";
	}

	@GetMapping("/orders_details/{id}")
	public String ordersDetails(@PathVariable("id") int id, Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		BillingDetails billingdetails = contactService.getBillingDetailsById(id);
		model.addAttribute("billingdetails", billingdetails);
		model.addAttribute("users", contactService.getUserById(billingdetails.getUserid()));
		model.addAttribute("orders", contactService.getAllOrderByBillingId(id));
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		model.addAttribute("categorys", contactService.getAllCategoryList());
		model.addAttribute("departments", contactService.getAllDepartmentList());
		return "admin_orders_details";
	}

	@PostMapping("/update-delivery-status")
	public String updateDeliveryStatus(@ModelAttribute OrderPlaced orderplaced, Model model, RedirectAttributes redirAttrs) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		int status=orderplaced.getDeliverystatus();
		int orderid = orderplaced.getId();
		int billingid= orderplaced.getBillingid();
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());
		int opcount= contactService.UpdateDeliveryStatus(orderid,status,timeStamp);
		UpdateBillingOrderStatus(billingid);
		if (opcount>0) {
				redirAttrs.addFlashAttribute("success", "Order Delivery Status Updated Successfully");
		}else {
			redirAttrs.addFlashAttribute("error", "Order Delivery Status not Updated, some problem is there");
		}

		BillingDetails billingdetails = contactService.getBillingDetailsById(billingid);
		List <OrderPlaced> ordplace = contactService.getAllOrderByBillingId(billingid);
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
		if(billingdetails.getTotalbillingamount()!=total)
			contactService.updateBillingTotalAmount(total,billingid);



		return "redirect:/orders_details/"+orderplaced.getBillingid();
	}

	@GetMapping("/delivered_orders")
	public String deliveredOrders(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		model.addAttribute("BillingDetails", contactService.getDeliveredOrders());
		return "admin_delivered_orders";
	}

	@GetMapping("/delivered_orders_details/{id}")
	public String DeliveredOrdersDetails(@PathVariable("id") int id, Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		BillingDetails billingdetails = contactService.getBillingDetailsById(id);
		model.addAttribute("billingdetails", billingdetails);
		model.addAttribute("users", contactService.getUserById(billingdetails.getUserid()));
		model.addAttribute("orders", contactService.getAllOrderByBillingId(id));
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		model.addAttribute("categorys", contactService.getAllCategoryList());
		model.addAttribute("departments", contactService.getAllDepartmentList());
		return "admin_delivered_order_details";
	}

	@GetMapping("/action-product/{id}")
	public String actionProduct(@PathVariable("id") int id, Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";

		Product pd = contactService.getProducts(id);
		model.addAttribute("products", pd);
		model.addAttribute("categorys", contactService.findBySpacificCategory(pd.getDepartment()));
		model.addAttribute("availabilitys", contactService.getAllProductAvailabilityList());
		model.addAttribute("departments", contactService.getAllDepartmentList());
		model.addAttribute("mainPageSections", contactService.getAllMainPageSectionList());
		ProductAndMainPageSectionJoin pmpsj = contactService.getSelectedProductAndMainPageSection(id);
		if(pmpsj!=null)
			model.addAttribute("selectedSections", contactService.getSelectedProductAndMainPageSection(id));
		return "actionOnProduct";
	}

	@PostMapping("/add-product-mainpagesection")
	public String addProductMainPageSection(@ModelAttribute("productandmainpagesectionjoin") ProductAndMainPageSectionJoin productandmainpagesectionjoin ,Model model){
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";

		ProductAndMainPageSectionJoin pmpsj=contactService.productAndMainPageSection(productandmainpagesectionjoin);
		return "redirect:/view-all-product";
	}

	@PostMapping("/update-product")
	public String updateProduct(@ModelAttribute("Product") Product product, HttpServletRequest request, Model model, RedirectAttributes redirAttrs) throws IOException, ServletException {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
//		System.out.println(product.getTitle()+" "+product.getId());
		Product existingProduct=contactService.getProducts(product.getId());
		existingProduct.setTitle(product.getTitle());
		existingProduct.setDiscription(product.getDiscription());
		existingProduct.setMrp(product.getMrp());
		existingProduct.setSellingprice(product.getSellingprice());
		existingProduct.setAvailability(product.getAvailability());
		existingProduct.setDepartment(product.getDepartment());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setTimeStamp(new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date()));
//		System.out.println("jjj"+request.getParameter("productimages"));
		if(request.getPart("productimages")==null) {
			existingProduct.setProductimage(product.getProductimage());
		}else {
			 try {
//				 File file = new File("/MyFiles/Products/"+ existingProduct.getProductimage());

				 File currentDirFile = new File("src\\main\\resources\\static\\MyFiles\\Products\\"+ existingProduct.getProductimage());
				 String realpath = currentDirFile.getAbsolutePath();
			     Path uploadPath = Path.of(realpath);
			     File file = new File(uploadPath.toString());
				 file.delete();

			} catch(Exception e) {
//				System.out.println("Failed to Delete image !!");
			}
//			String imagename=product.getProductimage();
//			String realpath= request.getServletContext().getRealPath("/MyFiles/Products")+File.separator.imagename;

			ProductIO pio =new ProductIO();
			Part part=request.getPart("productimages");
//			String newfilename=pio.createFileName(part);
			 String image = part.getSubmittedFileName();
			  String newfilename=LocalDateTime.now().toString();
			  String[] split = newfilename.split(":");
			  newfilename = split[0]+split[1]+split[2]+"_"+image;
			existingProduct.setProductimage(newfilename);
			Product pro = contactService.productadded(existingProduct);
			if (pro != null) {
				String res = pio.putInFile(newfilename, part);
				if(res.equals("saved"))
					redirAttrs.addFlashAttribute("success", "Product Updated succesfully!");
			}else {
				redirAttrs.addFlashAttribute("error", "Product Not Updated some problem is there");
			}
			return "redirect:/action-product/"+product.getId();
		}
		contactService.productadded(existingProduct);
//		return "redirect:/view-all-product";
		return "redirect:/action-product/"+product.getId();
	}

	@GetMapping("/all-user-list")
	public String allUserList(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("users",contactService.getAllUsers());
		return "all_user_list";
	}

	@GetMapping("/user-details/{id}")
	public String userDetailsById(@PathVariable("id") int id, Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		UserRegistration user = contactService.getUserById(id);
		model.addAttribute("user",user);
		model.addAttribute("orders", contactService.getAllOrderByUserId(id));
		model.addAttribute("orderstatus", contactService.getAllOrderStatus());
		model.addAttribute("BillingDetails",contactService.getBillingDetailsByUserId(id));

		return "admin_user_details";
	}

	@GetMapping("/change-user-status/{status}/{id}")
	public String changeUserStatus(@PathVariable("status") int status,@PathVariable("id") int id) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		contactService.updateUserStatus(status,id);
		return "redirect:/all-user-list";
	}

	@GetMapping("/disable-product/{status}/{id}")
	public String disableProduct(@PathVariable("status") int status, @PathVariable("id") int id, Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		contactService.disableProductById(status,id);
		return "redirect:/action-product/"+id;
	}

	@GetMapping("/setting")
	public String ProductSetting(Model model) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		model.addAttribute("data", contactService.getAllSettingData());
		return "adminsetting";
	}
	
	@PostMapping("/update-setting")
	public String updateProductSetting(@ModelAttribute("setting") Setting setting,RedirectAttributes redirAttrs) {
		if(session.getAttribute("MY_ADMIN_SESSION")==null)
			return "redirect:/adminlogin";
		Setting existingData = contactService.getAllSettingData();
		if(existingData!=null) {
		existingData.setEmail(setting.getEmail());
		existingData.setContact(setting.getContact());
		existingData.setAddress(setting.getAddress());
		existingData.setFacebooklink(setting.getFacebooklink());
		existingData.setInstagramlink(setting.getInstagramlink());
		existingData.setXlink(setting.getXlink());
		existingData.setLinkedinlink(setting.getLinkedinlink());
		existingData.setPinterestlink(setting.getPinterestlink());
		existingData.setPagesize(setting.getPagesize());
		existingData.setMinshopingamount(setting.getMinshopingamount());
		existingData.setCharges(setting.getCharges());
		existingData.setTimeStamp(new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date()));
		}else {
			existingData=setting;
		}
		Setting set = contactService.UpdateSettingData(existingData);
		if(set !=null) {
			redirAttrs.addFlashAttribute("success", "Data Updated succesfully!");
		}else {
			redirAttrs.addFlashAttribute("error", "Data Not Updated some problem is there");
		}
		return "redirect:/setting";
	}
}

