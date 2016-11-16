using System;
using System.Net.Http;
using System.IO;
using System.Threading.Tasks;
using System.Net;
using System.Text;
using System.Xml.Serialization;
using Newtonsoft.Json.Linq;

namespace WSP
{
	public class RestService : IRestService
	{
		HttpClient client;
		string contenttype;
		public RestService()
		{
			var cookies = new CookieContainer();
			var handler = new HttpClientHandler();
			handler.CookieContainer = cookies;
			client = new HttpClient(handler);
			contenttype = "application/json";
		}
		/// <summary>
		/// Creates the customer
		/// </summary>
		/// <returns>The customer.</returns>
		/// <param name="payload">Payload.</param>
		public async Task<customer> PostCustomer(customer payload)
		{
			JObject o = new JObject();
			o["firstName"] = payload.firstName;
			o["middleName"] = payload.middleName;
			o["lastName"] = payload.lastName;
			o["email"] = payload.email;
			o["propic"] = payload.propic;
			var contents = new StringContent(o.ToString(), Encoding.UTF8, contenttype);
			var uri = new Uri(Common.serverURL + Common.customerservice);
 			var response = await client.PostAsync(uri, contents);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(customer));
				using (TextReader reader = new StringReader(content))
				{
					return (customer)serializer.Deserialize(reader);
				}
			}
			return new customer();
		}
		/// <summary>
		/// Updates the customer
		/// </summary>
		/// <returns>The customer.</returns>
		/// <param name="payload">Payload.</param>
		public async Task<customer> PutCustomer(customer payload)
		{
			JObject o = new JObject();
			o["firstName"] = payload.firstName;
			o["middleName"] = payload.middleName;
			o["lastName"] = payload.lastName;
			o["email"] = payload.email;
			o["propic"] = payload.propic;
			o["id"] = payload.id;
			var contents = new StringContent(o.ToString(), Encoding.UTF8, contenttype);
			var uri = new Uri(Common.serverURL + Common.customerservice);
			var response = await client.PutAsync(uri, contents);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(customer));
				using (TextReader reader = new StringReader(content))
				{
					return (customer)serializer.Deserialize(reader);
				}
			}
			return new customer();
		}
		/// <summary>
		/// Get the customer with id
		/// </summary>
		/// <returns>The customer.</returns>
		/// <param name="id">Identifier.</param>
		public async Task<customer> GetCustomer(string id)
		{
			var uri = new Uri(Common.serverURL + Common.customerservice + "/" + id);
			var response = await client.GetAsync(uri);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(customer));
				using (TextReader reader = new StringReader(content))
				{
					return (customer)serializer.Deserialize(reader);
				}
			}
			return new customer();
		}
		/// <summary>
		/// Deletes the customer.
		/// </summary>
		/// <returns>The customer.</returns>
		/// <param name="id">Identifier.</param>
		public async Task<bool> DeleteCustomer(string id)
		{
			var uri = new Uri(Common.serverURL + Common.customerservice + "/" + id);
			var response = await client.DeleteAsync(uri);
			if (response.IsSuccessStatusCode) { return true; }
			return false;
		}
		/// <summary>
		/// Gets the partner from id
		/// </summary>
		/// <returns>The partner.</returns>
		/// <param name="id">Identifier.</param>
		public async Task<partner> GetPartner(string id)
		{
			var uri = new Uri(Common.serverURL + Common.partnerservice + "/" + id);
			var response = await client.GetAsync(uri);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(partner));
				using (TextReader reader = new StringReader(content))
				{
					return (partner)serializer.Deserialize(reader);
				}
			}
			return new partner();
		}
		/// <summary>
		/// Creates a new partner
		/// </summary>
		/// <returns>The partner.</returns>
		/// <param name="payload">Payload.</param>
		public async Task<partner> PostPartner(partner payload)
		{
			JObject o = new JObject();
			o["firstName"] = payload.firstName;
			o["middleName"] = payload.middleName;
			o["lastName"] = payload.lastName;
			o["email"] = payload.email;
			o["propic"] = payload.propic;
			o["homepage"] = payload.homepage;
			o["company"] = payload.company;
			var contents = new StringContent(o.ToString(), Encoding.UTF8, contenttype);
			var uri = new Uri(Common.serverURL + Common.partnerservice);
			var response = await client.PostAsync(uri, contents);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(partner));
				using (TextReader reader = new StringReader(content))
				{
					return (partner)serializer.Deserialize(reader);
				}
			}
			return new partner();
		}
		/// <summary>
		/// Updates the partner
		/// </summary>
		/// <returns>The partner.</returns>
		/// <param name="payload">Payload.</param>
		public async Task<partner> PutPartner(partner payload)
		{
			JObject o = new JObject();
			o["firstName"] = payload.firstName;
			o["middleName"] = payload.middleName;
			o["lastName"] = payload.lastName;
			o["email"] = payload.email;
			o["propic"] = payload.propic;
			o["homepage"] = payload.homepage;
			o["company"] = payload.company;
			o["id"] = payload.id;
			var contents = new StringContent(o.ToString(), Encoding.UTF8, contenttype);
			var uri = new Uri(Common.serverURL + Common.partnerservice);
			var response = await client.PostAsync(uri, contents);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(partner));
				using (TextReader reader = new StringReader(content))
				{
					return (partner)serializer.Deserialize(reader);
				}
			}
			return new partner();
		}
		/// <summary>
		/// Deletes the partner.
		/// </summary>
		/// <returns>The partner.</returns>
		/// <param name="id">Identifier.</param>
		public async Task<bool> DeletePartner(string id)
		{
			var uri = new Uri(Common.serverURL + Common.partnerservice + "/" + id);
			var response = await client.DeleteAsync(uri);
			if (response.IsSuccessStatusCode) { return true; }
			return false;
		}
		/// <summary>
		/// Gets the product.
		/// </summary>
		/// <returns>The product.</returns>
		/// <param name="id">Identifier.</param>
		public async Task<product> GetProduct(string id)
		{
			var uri = new Uri(Common.serverURL + Common.productservice + "/" + id);
			var response = await client.GetAsync(uri);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(product));
				using (TextReader reader = new StringReader(content))
				{
					return (product)serializer.Deserialize(reader);
				}
			}
			return new product();
		}

		public async Task<search> Search(string term)
		{
			JObject o = new JObject();
			o["searchterm"] = term;
			var contents = new StringContent(o.ToString(), Encoding.UTF8, contenttype);
			var uri = new Uri(Common.serverURL + Common.productsearch);
			var response = await client.PostAsync(uri, contents);
			if (response.IsSuccessStatusCode)
			{
				var content = response.Content.ReadAsStringAsync().Result;
				var serializer = new XmlSerializer(typeof(search));
				using (TextReader reader = new StringReader(content))
				{
					return (search)serializer.Deserialize(reader);
				}
			}
			return new search();
		}

	}
}
