package com.dhiraj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dhiraj.entity.Address;
import com.dhiraj.entity.AdminLogin;
import com.dhiraj.entity.Banner;
import com.dhiraj.entity.BillingDetails;
import com.dhiraj.entity.Cart;
import com.dhiraj.entity.Category;
import com.dhiraj.entity.Contact;
import com.dhiraj.entity.Department;
import com.dhiraj.entity.MainPageSections;
import com.dhiraj.entity.OrderPlaced;
import com.dhiraj.entity.OrderStatus;
import com.dhiraj.entity.Product;
import com.dhiraj.entity.ProductAndMainPageSectionJoin;
import com.dhiraj.entity.ProductAvailability;
import com.dhiraj.entity.Setting;
import com.dhiraj.entity.UserRegistration;
import com.dhiraj.entity.Wishlist;
import com.dhiraj.repository.AddressRepo;
import com.dhiraj.repository.AdminLoginRepo;
import com.dhiraj.repository.BannerRepo;
import com.dhiraj.repository.BillingDetailsRepo;
import com.dhiraj.repository.CartRepo;
import com.dhiraj.repository.CategoryRepo;
import com.dhiraj.repository.ContactRepo;
import com.dhiraj.repository.DepartmentRepo;
import com.dhiraj.repository.MainPageSectionRepo;
import com.dhiraj.repository.OrderPlacedRepo;
import com.dhiraj.repository.OrderStatusRepo;
import com.dhiraj.repository.ProductAndMainPageSectionJoinRepo;
import com.dhiraj.repository.ProductAvailabilityRepo;
import com.dhiraj.repository.ProductRepo;
import com.dhiraj.repository.SettingRepo;
import com.dhiraj.repository.UserRegistrationRepo;
import com.dhiraj.repository.WishlistRepo;

@Service("contactService")
public class ContactService {

	@Autowired
	ContactRepo contactRepo;

	public Contact saveStudent(Contact contact) {
		return contactRepo.save(contact);
	}

	public List<Contact> getAllMessages() {
		return contactRepo.findAll();
	}

	public void deleteMessagesById(int id) {
		contactRepo.deleteById(id);
	}

	public int getMessagesCount() {
		return contactRepo.getMessagesCount();
	}


//Category
	@Autowired
	CategoryRepo categoryRepo;

	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

	public List<Optional> findByProductCategory(int did) {
		return categoryRepo.findByProductCategory(did) ;
	}

	public List<Category> findBySpacificCategory(int department) {
		return categoryRepo.findByDepartmentid(department);
	}

	public List<Category> getAllCategoryList() {
		return categoryRepo.findAll();
	}

//Department
	@Autowired
		DepartmentRepo departmentRepo;

	public List<Department> getAllDepartmentList() {
		return departmentRepo.findAll();
	}

	public Department findByIdDepartment(int d) {
		return departmentRepo.findByIdDepartment(d);
	}

//Product
	@Autowired
	ProductRepo productRepo;
	public Product productadded(Product product) {
		return productRepo.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	
	
	public Page<Product> getAllProductsWithPagingnation(int page, int size) {
		Page<Product> products = productRepo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
		return products;
	}
	
	public Product getProducts(int id) {
		return productRepo.getProducts(id);
	}

	public List<Product> getProductsByDepartmentLimit(int d){
		return productRepo.findByDepartmentLimit(d);
	}

	public Page<Product> getProductsByDepartment(int d, Pageable pageable){
		Page<Product> products = productRepo.findDepartment(d, pageable);
		return products;
	}

	public Page<Product> getProductsBySearchList(String searchdata, Pageable pageable) {
		Page<Product> products = productRepo.getProductsBySearchList(searchdata, pageable);
		return products;
	}

	public List<Product> getAllProductForIndexPage(int sectionid, int limit) {
		return productRepo.getAllProductForIndexPage(sectionid, limit);
	}

	public List<Product> findProductByProductId(List productid) {
		return productRepo.findAllById(productid);
	}

	public int getProductCount() {
		return productRepo.getProductCount();
	}

	public void disableProductById(int status, int id) {
		productRepo.disableProductById(status, id);
	}

	public List<Product> getLatestProductForIndexPage(int limit) {
		return productRepo.getLatestProductForIndexPage(limit) ;
	}

//Main Page Sections
	@Autowired
	MainPageSectionRepo mainPageSectionRepo;
	public List<MainPageSections> getAllMainPageSectionList() {
		return mainPageSectionRepo.findAll();
	}

//Product And Main Page Section Join
	@Autowired
	ProductAndMainPageSectionJoinRepo productAndMainPageSectionJoinRepo;
	public ProductAndMainPageSectionJoin productAndMainPageSection(
			ProductAndMainPageSectionJoin productandmainpagesectionjoin) {
		return productAndMainPageSectionJoinRepo.save(productandmainpagesectionjoin);
	}


	public ProductAndMainPageSectionJoin getSelectedProductAndMainPageSection(int id) {
		return productAndMainPageSectionJoinRepo.getByProduct(id);
	}

	public List<ProductAndMainPageSectionJoin> getAllProductAndMainPageSectionJoinList() {
		return productAndMainPageSectionJoinRepo.findAll();
	}

//	public Optional findByIdSelectedProductAndMainPageSection(int id) {
//		return productAndMainPageSectionJoinRepo.findByProduct(id);
//	}

//Product Availability
	@Autowired
	ProductAvailabilityRepo productAvailabilityRepo;
	public List<ProductAvailability> getAllProductAvailabilityList() {
		return productAvailabilityRepo.findAll();
	}

	public ProductAvailability getAllProductAvailability(int availability) {
		return productAvailabilityRepo.findById(availability);
	}

// Admin Login
	@Autowired
	AdminLoginRepo adminLoginRepo;
	public int checkAdminAuth(String username, String password) {
		return adminLoginRepo.checkAdminAuth(username,password);
	}


	public AdminLogin getAdminLoginCredential(int id) {
		return adminLoginRepo.getAdminLoginCredential(id);
	}

	public void updateAdminCredential(String nuser, String npass) {
		adminLoginRepo.updateAdminCredential(nuser, npass);
	}

//User Registration

	@Autowired
	UserRegistrationRepo userRegistrationRepo;
	public int checkUserRegistration(String contact, String email) {
		return userRegistrationRepo.checkUserRegistration(contact, email) ;
	}
	

	public UserRegistration getUserContact(String contact) {
		return userRegistrationRepo.findByContact(contact);
	}

	public UserRegistration saveUserRegistration(UserRegistration registration) {
		return userRegistrationRepo.save(registration);
	}

	public UserRegistration checkUserAuth(String contact, String password) {
		return userRegistrationRepo.checkUserAuth(contact,password) ;
	}

	public UserRegistration getUserById(int userid) {
		return userRegistrationRepo.findById(userid);
	}

	public List<UserRegistration> getAllUsers() {
		return userRegistrationRepo.findAll();
	}

	public int getUserCount() {
		return userRegistrationRepo.getUserCount();
	}

	public void updateUserStatus(int status, int id) {
		userRegistrationRepo.updateUserStatus(status,id);
	}

	public UserRegistration saveUser(UserRegistration existingUser) {
		return userRegistrationRepo.save(existingUser);
	}

	
//Cart

	@Autowired
	CartRepo cartRepo;
	public Cart getPerticularProductForUserFromCart(int userid, int productid) {
		return cartRepo.getPerticularProductForUserFromCart(userid, productid);
	}

	public void saveCartProduct(Cart cart) {
		cartRepo.save(cart);
	}

	public int updateCartProductForUser(int userid, int productid, int quantity) {
		return cartRepo.updateCartProductForUser(userid, productid, quantity);
	}


	public int getCartCountForPerticularUser(int userid) {
		return cartRepo.getCartCountForPerticularUser(userid);
	}

	public List<Cart> findProductByUserId(int userid) {
		return cartRepo.findAllByUserid(userid);
	}


	public void deleteCartProductById(int id) {
		cartRepo.deleteByProductid(id);
	}

	public int getCartCount() {
		return cartRepo.getCartCount();
	}

//Wishlist

	@Autowired
	WishlistRepo wishlistRepo;

	public Wishlist getPerticularProductForUserFromWishlist(int userid, int productid) {
		return wishlistRepo.getPerticularProductForUserFromWishlist(userid, productid) ;
	}

	public void saveWishlistProduct(Wishlist wishlist) {
		wishlistRepo.save(wishlist);
	}

	public List<Wishlist> findAllByUserid(int userid) {
		return wishlistRepo.findAllByUserid(userid);
	}

	public int getWishlistCountForPerticularUser(int userid) {
		return wishlistRepo.getWishlistCountForPerticularUser(userid);
	}

	public void deleteWishlistProductById(int id) {
		wishlistRepo.deleteByProductid(id);
	}

	public List<Wishlist> getAllWishlistByUserId(int userid) {
		return wishlistRepo.getByUserid(userid);
	}

	public int getWishCount() {
		return wishlistRepo.getWishCount();
	}

//Billing Details

	@Autowired
	BillingDetailsRepo billingDetailsRepo;

	public BillingDetails saveBillingDetails(BillingDetails billingdetails) {
		return billingDetailsRepo.save(billingdetails);
	}

	public List<BillingDetails> getProcessingDelivery() {
		return billingDetailsRepo.getProcessingDelivery();
	}

	public BillingDetails getBillingDetailsById(int id) {
		return billingDetailsRepo.findById(id);
	}

	public void UpdateBillingDeliveryStatus(int id, int status_count, String timeStamp) {
		billingDetailsRepo.UpdateBillingDeliveryStatus(id, status_count, timeStamp);
	}

	public List<BillingDetails> getDeliveredOrders() {
		return billingDetailsRepo.getDeliveredOrders();
	}

	public List<BillingDetails> getBillingDetailsByUserId(int userid) {
		return billingDetailsRepo.getBillingDetailsByUserId(userid) ;
	}

	public void updateBillingTotalAmount(float total, int id) {
		billingDetailsRepo.updateBillingTotalAmount(total,id);
	}


//Order Placed

	@Autowired
	OrderPlacedRepo orderPlacedRepo;
	public OrderPlaced saveOrderPlaced(OrderPlaced op) {
		return orderPlacedRepo.save(op);
	}

	public void saveOrderPlaced(List<OrderPlaced> ord) {
		orderPlacedRepo.saveAll(ord);
	}

	public List<OrderPlaced> getAllOrderByBillingId(int id) {
		return orderPlacedRepo.findbyBillingId(id);
	}

	public int UpdateDeliveryStatus(int orderid, int status, String timeStamp) {
		return orderPlacedRepo.UpdateDeliveryStatus(orderid, status, timeStamp);
	}

	public List<OrderPlaced> getAllOrderByUserId(int userid) {
		return orderPlacedRepo.findAllByUserid(userid);
	}

	public void deleteOrderProductById(int orderid) {
		orderPlacedRepo.deleteById(orderid);
	}

	public int getOrdersCount() {
		return orderPlacedRepo.getOrdersCount();
	}

//Order Status

	@Autowired
	OrderStatusRepo orderStatusRepo;

	public List<OrderStatus> getAllOrderStatus() {
		return orderStatusRepo.findAll();
	}

//Banner

	@Autowired
	BannerRepo bannerRepo;
	public Banner banneradded(Banner banner) {
		return bannerRepo.save(banner);
	}

	public Banner getBannerById(int bannerid) {
		return bannerRepo.findBannerById(bannerid);
	}

//Setting
	
	@Autowired
	SettingRepo settingRepo;
	public Object getAddressByUserId;
	public Setting getAllSettingData() {
		return settingRepo.findById(1);
	}

	public Setting UpdateSettingData(Setting existingData) {
		return settingRepo.save(existingData);
	}

//Address
	
	@Autowired
	AddressRepo addressRepo;
	public Address getAddressByUserId(int userid) {
		return addressRepo.findByUserid(userid);
	}

	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}

}
