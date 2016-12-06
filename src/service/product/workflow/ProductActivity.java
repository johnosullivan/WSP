package service.product.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.core.UriInfo;

import service.Link;

import dal.main.MainDatabaseDAO;
import dal.product.ProductDAO;
import model.partner.Partner;
import model.product.Product;
import service.product.representation.PartnerProductsRepresentation;
import service.product.representation.ProductRepresentation;
import service.product.representation.ProductRequest;
import service.product.representation.SearchRepresentation;
import service.product.representation.SearchRequest;

public class ProductActivity {

	static String productServiceURL = "productservice/product";
	static String partnerServiceURL = "partnerservice/partner";
	static String reviewServiceURL = "reviewservice/reviews/product";

	
	public ProductRepresentation getProduct(String id,UriInfo url) throws UnknownHostException {
		Product product = new Product(id);
		Partner part = new Partner(product.getSeller());
		ProductRepresentation cusRep = new ProductRepresentation();
		cusRep.setName(product.getName());
		cusRep.setDescription(product.getDescription());
		cusRep.setCost(product.getCost());
		cusRep.setCurcode(product.getCostCode());
		cusRep.setInvein(product.getInventory());
		cusRep.setID(id);
		cusRep.setPartnerid(part.getCompany());
				
		Link view = new Link("View Partner",url.getBaseUri() + partnerServiceURL  + "/" + product.getSeller(),"GET","application/json");
		Link review = new Link("Reviews",url.getBaseUri() + "reviewservice/reviews/product/" + id,"GET","application/json");

		cusRep.setLinks(view,review);
		
		return cusRep;
	}
	
	
	public boolean deleteProduct(String id) throws UnknownHostException {
		ProductDAO db = ProductDAO.getInstance();
		return db.deleteProductById(id);	
	}
	
	public PartnerProductsRepresentation getPartnerProducts(String id,UriInfo url)  throws UnknownHostException {
		
		Partner partner = new Partner(id);
		PartnerProductsRepresentation p =  new PartnerProductsRepresentation();
		ArrayList<ProductRepresentation> data = new ArrayList<ProductRepresentation>();
		
		ArrayList<Product> results = partner.getMyProduct();
		for (Product x : results) {
			ProductRepresentation temp = new ProductRepresentation();
			temp.setName(x.getName());
			temp.setCost(x.getCost());
			temp.setCurcode(x.getCostCode());
			temp.setID(x.getID());
			temp.setDescription(x.getDescription());
			Partner a = new Partner(x.getSeller());
			temp.setPartnerid(a.getCompany());
			temp.setInvein(x.getInventory());
			Link edit = new Link("Edit",url.getBaseUri() + productServiceURL,"PUT","application/json");
			temp.setLinks(edit);
			
			data.add(temp);
		}
		p.setProducts(data);
		return p;
	}
	
	public ProductRepresentation updateProduct(ProductRepresentation request) throws UnknownHostException {
		Product product = new Product(request.getID());
		product.setDescription(request.getDescription());
		product.setName(request.getName());
		product.setCost(request.getCost());
		product.setCostCode(request.getCurcode());
		product.setInventory(request.getInvein());
		
		if(product.update()) {
			return request;
		} 
		return new ProductRepresentation();
	}
	
	
	public SearchRepresentation searchProduct(SearchRequest request,UriInfo url) throws UnknownHostException {		
		MainDatabaseDAO db = MainDatabaseDAO.getInstance();
		ArrayList<ProductRepresentation> data = new ArrayList<ProductRepresentation>();
		
		ArrayList<Product> results = db.searchService(request.getSearchterm());
		for (Product x : results) {
			ProductRepresentation temp = new ProductRepresentation();
			temp.setName(x.getName());
			temp.setCost(x.getCost());
			temp.setCurcode(x.getCostCode());
			Partner a = new Partner(x.getSeller());
			temp.setPartnerid(a.getCompany());
			Link view = new Link("View",url.getBaseUri() + productServiceURL  + "/" + x.getID(),"GET","application/json");	
			temp.setLinks(view);
			data.add(temp);
		}
		
		
		
		SearchRepresentation s = new SearchRepresentation();
		s.setSearch(request.getSearchterm());
		s.setResults(data);
		return s;
	}
	
	public ProductRepresentation createProduct(ProductRequest request) throws UnknownHostException {
		
		// Catches Bad Requests
		if (request.getName().equals("")) {
			 throw new UnknownHostException();
		}
		if (request.getDescription().equals("")) {
			 throw new UnknownHostException();
		}
	
		Product newproduct = new Product();
		newproduct.setName(request.getName());
		newproduct.setDescription(request.getDescription());
		newproduct.setCost(request.getCost());
		newproduct.setCostCode(request.getCurcode());
		newproduct.setSeller(request.getPartnerid());
		newproduct.setInventory(request.getInvein());
		String newid = newproduct.create();
		Partner part = new Partner(request.getPartnerid());
		ProductRepresentation cusRep = new ProductRepresentation();
		cusRep.setName(newproduct.getName());
		cusRep.setDescription(newproduct.getDescription());
		cusRep.setCost(newproduct.getCost());
		cusRep.setCurcode(newproduct.getCostCode());
		cusRep.setPartnerid(part.getCompany());
		cusRep.setInvein(newproduct.getInventory());
		cusRep.setID(newid);
		return cusRep;
	}
	
}
