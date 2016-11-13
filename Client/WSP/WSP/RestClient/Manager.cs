using System;
using System.Collections.Generic;
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

		public Task<customer> GetCustomer(String id)
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


	}
}
