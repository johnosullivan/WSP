using System.Xml.Serialization;
namespace WSP
{
	[XmlRoot]
	public class product
	{
		[XmlElement]
		public string id { get; set; }
		[XmlElement]
		public string partnerid { get; set; }
		[XmlElement]
		public string curcode { get; set; }
		[XmlElement]
		public string invein { get; set; }
		[XmlElement]
		public string cost { get; set; }
		[XmlElement]
		public string description { get; set; }
		[XmlElement]
		public string name { get; set; }
		[XmlElement("link")]
		public link[] link { get; set; }
	}
}
