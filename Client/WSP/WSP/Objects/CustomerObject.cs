using System;
using System.Collections.Generic;
using System.Xml.Serialization;

namespace WSP
{
	[XmlRoot]
	public class customer
	{
		[XmlElement]
		public string firstName { get; set; }
		[XmlElement]
		public string middleName { get; set; }
		[XmlElement]
		public string lastName { get; set; }
		[XmlElement]
		public string email { get; set; }
		[XmlElement]
		public string id { get; set; }
		[XmlElement]
		public string propic { get; set; }
		[XmlElement("addresses")]
		public addresses[] addresses { get; set; }
	}
}
