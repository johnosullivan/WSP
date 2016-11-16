using System.Threading.Tasks;

namespace WSP
{
	public class Manager
	{
		IRestService restService;


		public Manager(IRestService service)
		{
			restService = service;
		}

		public Task<customer> GetCustomer(string id)
		{
			return restService.GetCustomer(id);
		}

		public Task<customer> PostCustomer(customer payload)
		{
			return restService.PostCustomer(payload);
		}

		public Task<customer> PutCustomer(customer payload)
		{
			return restService.PutCustomer(payload);
		}

		public Task<bool> DeleteCustomer(string id)
		{
			return restService.DeleteCustomer(id);
		}

		public Task<partner> GetPartner(string id)
		{
			return restService.GetPartner(id);
		}

		public Task<partner> PostPartner(partner payload)
		{
			return restService.PostPartner(payload);
		}

		public Task<partner> PutPartner(partner payload)
		{
			return restService.PutPartner(payload);
		}

		public Task<bool> DeletePartner(string id)
		{
			return restService.DeletePartner(id);
		}

		public Task<product> GetProduct(string id)
		{
			return restService.GetProduct(id);
		}

		public Task<search> Search(string term)
		{
			return restService.Search(term);
		} 

	}
}
