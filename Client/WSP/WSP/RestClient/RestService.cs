using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net.Http;
using System.IO;
using System.Threading.Tasks;
using System.Net;
using System.Text;
using System.Xml.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Net.Http.Headers;

namespace WSP
{
	public class RestService : IRestService
	{
		HttpClient client;
		String contenttype;
		public RestService()
		{
			var cookies = new CookieContainer();
			var handler = new HttpClientHandler();
			handler.CookieContainer = cookies;
			client = new HttpClient(handler);
			contenttype = "application/json";
		}
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
		public async Task<customer> PutCustomer(customer payload)
		{
			JObject o = new JObject();
			o["firstName"] = payload.firstName;
			o["middleName"] = payload.middleName;
			o["lastName"] = payload.lastName;
			o["email"] = payload.email;
			o["propic"] = payload.propic;
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
		public async Task<customer> GetCustomer(String id)
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
	}
}
