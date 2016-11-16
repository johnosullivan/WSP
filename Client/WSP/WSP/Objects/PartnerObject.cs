using System.Xml.Serialization;
namespace WSP
{
	[XmlRoot]
	public class partner
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
		[XmlElement]
		public string company { get; set; }
		[XmlElement]
		public string homepage { get; set; }

		[XmlElement("addresses")]
		public addresses[] addresses { get; set; }
	}
}
