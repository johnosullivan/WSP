using System.Threading.Tasks;

namespace WSP
{
	public interface IRestService
	{
		// Customer API
		Task<customer> GetCustomer(string id);
		Task<customer> PostCustomer(customer payload);
		Task<customer> PutCustomer(customer payload);
		Task<bool> DeleteCustomer(string id);
		// Partner API
		Task<partner> GetPartner(string id);
		Task<partner> PostPartner(partner payload);
		Task<partner> PutPartner(partner payload);
		Task<bool> DeletePartner(string id);
		// Product API
		Task<product> GetProduct(string id);
		Task<search> Search(string term);
	}
}
