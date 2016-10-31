package service.review.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "reviews")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductReviewsRepresentation {

	private ArrayList<ProductReviewRepresentation> reviews;

	
	public ArrayList<ProductReviewRepresentation> getResults() {
		return this.reviews;
	}

	public void setResults(ArrayList<ProductReviewRepresentation> reviews) {
		this.reviews = reviews;
	}

}
