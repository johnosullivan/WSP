using System.Diagnostics;
using Xamarin.Forms;
using System.Collections.Generic;
namespace WSP
{
	static public class ParseLink
	{

		static public string Parse(link link,string location)
		{
			Debug.WriteLine(link.url);

			if (link.action.Equals("GET"))
			{
				if (location.Equals("Search"))
				{
					return "View";
				}
				string[] data = link.url.Split('/');
				Debug.WriteLine(data.Length);
			}
			return "";
		}
	}
}
