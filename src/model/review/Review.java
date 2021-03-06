package model.review;

import java.net.UnknownHostException;

import com.mongodb.DBObject;

import dal.review.ReviewDAO;
import model.order.Order;
import model.order.Order.OrderStatusType;

public class Review {
	//attrs
	private String productid;
	private String review;
	private String orderid;
	private String name;
	private int stars;
	private String id;
	public String getId() { return this.id; }
	//Helper class for order code
	public int statuscode(OrderStatusType ord) {
		switch(ord) {
			case CREATED: 	return 1;
			case PROCESSED:		return 2;
			case SHIPPED:		return 3;
			case INROUTE:		return 4;
			case DELIVERED:		return 5;
			case CANCELED:		return 6;
			case PAYMENTFAILED:	return 7;
			default:
				break;
		}
		return 0;
	}
	
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	//Gets and sets product
	public void setProduct(String productid) { this.productid = productid; }
	public String getProduct() { return this.productid; }
	//Gets and sets review
	public void setReview(String review) { this.review = review; }
	public String getReview() { return this.review; }
	//Gets and sets the order
	public void setOrder(String orderid) { this.orderid = orderid; }
	public String getOrder() { return this.orderid; }
	//Gets and sets the stars
	public void setStars(int stars) { this.stars = stars; }
	public int getStars() { return this.stars; }
	//Submit the review in the database
	public String submit() throws UnknownHostException {
		/*System.out.println("T:" + this.orderid);
		Order order = new Order(this.orderid);
		System.out.println("Order Got" + order.getID());
		if (order.isProductInOrder(this.productid)) {
			int x = this.statuscode(order.getOrderStatus());
			if (x == 5) {
				ReviewDAO db = ReviewDAO.getInstance();
				return db.createReviews(this);
			} else {
				return "";
			}
		}*/
		ReviewDAO db = ReviewDAO.getInstance();
		return db.createReviews(this);
	}
	public Review() { }
	//Updates the review
	public boolean update() throws UnknownHostException {
		ReviewDAO db = ReviewDAO.getInstance();
		return db.updateReviewById(this);
	}
	//Creates the review from the database from id
	public Review(String id) throws UnknownHostException {
		ReviewDAO db = ReviewDAO.getInstance();
		DBObject object = db.findReviewById(id);
		this.productid = (String)object.get("product");
		this.orderid = (String)object.get("order");
		this.stars = (int)object.get("stars");
		this.review = (String)object.get("review");
		this.id = id;
	}	
}
