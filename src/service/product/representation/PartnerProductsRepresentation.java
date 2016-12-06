package service.product.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerProductsRepresentation {


		private ArrayList<ProductRepresentation> products;
				
		public ArrayList<ProductRepresentation> getProducts() {
			return this.products;
		}
		
		public void setProducts(ArrayList<ProductRepresentation> products) {
			this.products = products;
		}
		
	
}
