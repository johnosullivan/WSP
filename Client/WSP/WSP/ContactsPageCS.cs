using Xamarin.Forms;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Net;

namespace WSP
{
	public class ContactsPageCS : ContentPage
	{
		async void OnButtonClicked(object sender, EventArgs e)
		{
			//customer data = await App.Manager.GetCustomer("5816dd63e4b08fa7f4a1e258");
			customer cus = new customer();
			cus.firstName = "Branka";
			cus.middleName = "Emma";
			cus.lastName = "O'Sullivan";
			cus.email = "brankaosullivan@mac.com";
			cus.propic = "http://www.bing.com/";
			customer data = await App.Manager.PostCustomer(cus);
			Debug.WriteLine(data.id);
		}

		public ContactsPageCS()
		{


			Button button = new Button
			{
				Text = "Click Me!",
				Font = Font.SystemFontOfSize(NamedSize.Large),
				BorderWidth = 1,
				HorizontalOptions = LayoutOptions.Center,
				VerticalOptions = LayoutOptions.CenterAndExpand
			};
			button.Clicked += OnButtonClicked;

		
			this.Padding = new Thickness(10, Device.OnPlatform(20, 0, 0), 10, 5);

			this.Content = new StackLayout
			{
				Children =
				{
					
					button

				}
			};

		}



	}
}