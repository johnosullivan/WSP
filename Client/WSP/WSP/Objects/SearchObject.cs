using System.Xml.Serialization;
namespace WSP
{
	[XmlRoot]
	public class search
	{
		[XmlElement]
		public string searchterm { get; set; }
		[XmlElement("results")]
		public results[] results { get; set; }
	}
}
