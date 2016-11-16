using Xamarin.Forms;
using System.Diagnostics;
using System;
 
namespace WSP
{
	
		public class SearchViewCell : ViewCell
		{
			
			public SearchViewCell()
			{
				
			}


			protected override void OnBindingContextChanged()
			{

				base.OnBindingContextChanged();

			results data = ((results)this.BindingContext);
				//Debug.WriteLine("isCalled" + ((results)this.BindingContext).name);


			var nameLabel = new Label()
			{
				FontFamily = "HelveticaNeue-Medium",
				FontSize = 18,
				TextColor = Color.Black
			};
			nameLabel.Text = data.name;

			var distanceLabel = new Label()
			{
				FontAttributes = FontAttributes.Bold,
				FontSize = 12,
				TextColor = Color.FromHex("#666")
			};
			distanceLabel.Text = data.cost + " " + data.curcode;

			// Online image and label
			var onlineImage = new Image()
			{
				Source = "online.png",
				HeightRequest = 8,
				WidthRequest = 8
			};


			//Debug.WriteLine(data.link.Length);

			//onlineImage.SetBinding(Image.IsVisibleProperty, "ShouldShowAsOnline");

			//onLineLabel.SetBinding(Label.IsVisibleProperty, "ShouldShowAsOnline");

			// Offline image and label
			var offlineImage = new Image()
			{
				Source = "offline.png",
				HeightRequest = 8,
				WidthRequest = 8
			};
			//offlineImage.SetBinding(Image.IsVisibleProperty, "ShouldShowAsOffline");
			var offLineLabel = new Label()
			{
				Text = "",
				TextColor = Color.FromHex("#ddd"),
				FontSize = 12,
			};
			//offLineLabel.SetBinding(Label.IsVisibleProperty, "ShouldShowAsOffline");

			// Vet rating label and star image
			var starLabel = new Label()
			{
				FontSize = 12,
				TextColor = Color.Gray
			};
			//starLabel.SetBinding(Label.TextProperty, "Stars");
			starLabel.Text = data.partnerid;
			var starImage = new Image()
			{
				Source = "star.png",
				HeightRequest = 12,
				WidthRequest = 12
			};

			var ratingStack = new StackLayout()
			{
				Spacing = 3,
				Orientation = StackOrientation.Horizontal,
				Children = { starImage, starLabel }
			};

			var statusLayout = new StackLayout
			{
				Orientation = StackOrientation.Horizontal,
				Children = {
								distanceLabel,
								onlineImage,
								offlineImage,
								offLineLabel
						}
			};

			var vetDetailsLayout = new StackLayout
			{
				Padding = new Thickness(10, 0, 0, 0),
				Spacing = 0,
				HorizontalOptions = LayoutOptions.FillAndExpand,
				Children = { nameLabel, statusLayout, ratingStack }
			};

			var tapImage = new Image()
			{
				Source = "tap.png",
				HorizontalOptions = LayoutOptions.End,
				HeightRequest = 12,
			};

			var cellLayout = new StackLayout
			{
				Spacing = 0,
				Padding = new Thickness(10, 5, 10, 5),
				Orientation = StackOrientation.Horizontal,
				HorizontalOptions = LayoutOptions.FillAndExpand,
				Children = { vetDetailsLayout, tapImage }

			};

			foreach(link s in data.link)
			{
				string title = ParseLink.Parse(s,"Search");

				Button button = new Button
				{
					Text = title,
					BackgroundColor = Color.Gray,
					TextColor = Color.White,
					Font = Font.SystemFontOfSize(NamedSize.Large),
					BorderWidth = 1,
					HorizontalOptions = LayoutOptions.Center,
					VerticalOptions = LayoutOptions.CenterAndExpand
				};


				cellLayout.Children.Add(button);
			}

			this.View = cellLayout;
			}

		public void ItemClicked(object sender, EventArgs e)
		{
			Debug.WriteLine("ItemClicked");
		}

		}
	}
	

