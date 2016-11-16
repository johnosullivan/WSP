using Xamarin.Forms;
using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace WSP
{
	public class ContactsPageCS : ContentPage
	{
		async void OnButtonClicked(object sender, EventArgs e)
		{
			/*product data = await App.Manager.GetProduct("58236a9ae4b0e98526a7fad2");
			Debug.WriteLine(data.name);
			Debug.WriteLine(data.description);
			foreach(link x in data.link)
			{
				Debug.WriteLine(x.action);
				Debug.WriteLine(x.url);
			}*/
			//search data = await App.Manager.Search("iPhone");
			//Debug.WriteLine(data.searchterm);
		}


		protected async override void OnAppearing()
		{
			base.OnAppearing();

			search data = await App.Manager.Search("iPhone");
			Debug.WriteLine(data.searchterm);

			listView.ItemsSource = data.results;
		}

		public void OnClick(object sender, EventArgs e)
		{
			Debug.WriteLine("Item Clicked!");
		}

		ListView listView;
		public ContactsPageCS()
		{


			/*	Button button = new Button
				{
					Text = "Submit",
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
				};	*/



			ContentView search = new ContentView();
			search.Padding = 4;
			var searchBar = new SearchBar
			{
				Placeholder = "Search",
				BackgroundColor = Color.White,
				CancelButtonColor = Color.Black,
			};

			searchBar.HeightRequest = 30.0;
			searchBar.Focused += (sender, e) =>
			{
				var focusedSearchBar = (SearchBar)e.VisualElement;
				focusedSearchBar.HeightRequest = 50.0;
			};
			searchBar.Unfocused += (sender, e) =>
			{
				var focusedSearchBar = (SearchBar)e.VisualElement;
				focusedSearchBar.HeightRequest = 30.0;
			};
			search.Content = searchBar;

			listView = new ListView
			{
				HasUnevenRows = true,
				ItemTemplate = new DataTemplate(typeof(SearchViewCell)),
			};

			listView.ItemSelected += (sender, e) =>
			{

				if (e.SelectedItem == null) return; // don't do anything if we just de-selected the row


				((ListView)sender).SelectedItem = null; // de-select the row




			};

			var layout = new StackLayout
			{
				Children = {
					search,
					listView
				}
			};

			Content = layout;

		}



	}
}