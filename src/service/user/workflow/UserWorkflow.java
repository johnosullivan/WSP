package service.user.workflow;

import java.net.UnknownHostException;

import dal.main.MainDatabaseDAO;
import model.customer.Customer;
import model.partner.Partner;
import service.user.representation.Account;
import service.user.representation.LoginRepresentation;
import service.user.representation.RegistrationRepresentation;

public class UserWorkflow {

	public Account register(RegistrationRepresentation req) throws UnknownHostException {
		
		MainDatabaseDAO db = MainDatabaseDAO.getInstance();
		
		String userid = "";
		if (req.getType() == 0) {
			Customer cus = new Customer();
			cus.setFirst(req.getFirst());
			cus.setMiddle(req.getMiddle());
			cus.setLast(req.getLast());
			cus.setEmail(req.getEmail());
			userid = cus.create();
		}
		
		if (req.getType() == 1) {
			Partner cus = new Partner();
			cus.setFirst(req.getFirst());
			cus.setMiddle(req.getMiddle());
			cus.setLast(req.getLast());
			cus.setEmail(req.getEmail());
			cus.setCompany(req.getCompany());
			cus.setHomepage(req.getHomepage());
			userid = cus.create();
		}
		
		db.register(req.getUsername(), req.getPassword(), req.getEmail(), req.getType(), userid);
		
		Account account = new Account();
		account.setFirst(req.getFirst());
		account.setMiddle(req.getMiddle());
		account.setLast(req.getLast());
		account.setEmail(req.getEmail());
		account.setCompany(req.getCompany());
		account.setHomepage(req.getHomepage());
		account.setId(userid);
		return account;
	}

	public Account login(LoginRepresentation req) throws UnknownHostException {
		
		MainDatabaseDAO db = MainDatabaseDAO.getInstance();
		Account pwd = db.login(req.getUsername(), req.getPassword());
		return pwd;
	}
	
}
