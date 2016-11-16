using System.Xml.Serialization;
namespace WSP
{
	public class results
	{
		[XmlElement]
		public string partnerid { get; set; }
		[XmlElement]
		public string curcode { get; set; }
		[XmlElement]
		public string invein { get; set; }
		[XmlElement]
		public string cost { get; set; }
		[XmlElement]
		public string name { get; set; }
		[XmlElement("link")]
		public link[] link { get; set; }
		public bool hasLink { 
			get {

				if (link.Length == 1)
				{
					return true;
				}

				return false; 
			
			} 
		}
	}
}
