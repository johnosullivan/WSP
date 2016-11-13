using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace WSP
{
	public interface IRestService
	{
		// Customer Calls
		Task<customer> GetCustomer(String id);
		Task<customer> PostCustomer(customer payload);
		Task<customer> PutCustomer(customer payload);
		//Task<Boolean> DeleteCustomer(String id);

	}
}
