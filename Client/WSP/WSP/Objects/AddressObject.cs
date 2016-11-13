using System;
using System.Xml.Serialization;
namespace WSP
{
	
	public class addresses
	{
		[XmlElement]
		public string address { get; set; }
		[XmlElement]
		public string city { get; set; }
		[XmlElement]
		public string state { get; set; }
		[XmlElement]
		public string zip { get; set; }
		[XmlElement]
		public string id { get; set; }
		[XmlElement]
		public string user { get; set; }
	}
}
