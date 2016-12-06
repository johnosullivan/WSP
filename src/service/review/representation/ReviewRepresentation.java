package service.review.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "review")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ReviewRepresentation {

		private String id;
		private String order;
		private String name;
		private String review;
		private int stars;
		private String product;
		
		public void setId(String id) { this.id = id; }
		public String getId() { return this.id; }
		
		public void setName(String name) { this.name = name; }
		public String getName() { return this.name; }
		
		public void setOrder(String order) { this.order = order; }
		public String getOrder() { return this.order; }
		
		public void setReview(String review) { this.review = review; }
		public String getReview() { return this.review; }
		
		public void setStars(int stars) { this.stars = stars; }
		public int getStars() { return this.stars; }
		
		public void setProduct(String product) { this.product = product; }
		public String getProduct() { return this.product; }
	
}
