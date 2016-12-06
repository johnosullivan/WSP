package service.review.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "productreview")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductReviewRepresentation {

	private String review;
	private String name;
	private int stars;
	
	public void setReview(String review) { this.review = review; }
	public String getReview() { return this.review; }
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	public void setStars(int stars) { this.stars = stars; }
	public int getStars() { return this.stars; }
	
}
