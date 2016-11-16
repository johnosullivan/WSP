using System;
using System.Collections.ObjectModel;
using Xamarin.Forms;
using System.Diagnostics;

namespace WSP
{
	public partial class Search : ContentPage
	{
		public Search()
		{
			InitializeComponent();
		}

		protected async override void OnAppearing()
		{
			base.OnAppearing();

			search data = await App.Manager.Search("iPhone");
			lstView.ItemsSource = data.results;
			lstView.ItemSelected += (sender, e) => {

			if (e.SelectedItem == null) return; 
				((ListView)sender).SelectedItem = null;
			};




		}


		void OnButtonClicked(object sender, EventArgs args)
		{
			Debug.WriteLine("Clicked!");
		}
	}
}
