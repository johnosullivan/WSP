using System.Xml.Serialization;
namespace WSP
{
	public class link
	{
		[XmlElement]
		public string action { get; set; }
		[XmlElement]
		public string url { get; set; }
	}
}
