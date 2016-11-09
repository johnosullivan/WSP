using System.Collections.Generic;
using Xamarin.Forms;

namespace WSP
{
	public class MasterPage : ContentPage
	{
		public ListView ListView { get { return listView; } }

		ListView listView;

		public MasterPage ()
		{
			var masterPageItems = new List<MasterPageItem> ();
			masterPageItems.Add (new MasterPageItem {
				Title = "Contacts",
				IconSource = "contacts.png",
				TargetType = typeof(ContactsPageCS)
			});
			masterPageItems.Add (new MasterPageItem {
				Title = "TodoList",
				IconSource = "todo.png",
				TargetType = typeof(TodoListPageCS)
			});
			masterPageItems.Add (new MasterPageItem {
				Title = "Reminders",
				IconSource = "reminders.png",
				TargetType = typeof(ReminderPageCS)
			});

			listView = new ListView {
				ItemsSource = masterPageItems,
				ItemTemplate = new DataTemplate (() => {
					var imageCell = new ImageCell ();
					imageCell.SetBinding (TextCell.TextProperty, "Title");
					imageCell.SetBinding (ImageCell.ImageSourceProperty, "IconSource");
					return imageCell;
				}),
				VerticalOptions = LayoutOptions.FillAndExpand,
				SeparatorVisibility = SeparatorVisibility.None
			};
			BackgroundColor = Color.Gray;
			Padding = new Thickness (0, 40, 0, 0);
			Icon = "hamburger.png";
			Title = "Menu";
			Content = new StackLayout {
				VerticalOptions = LayoutOptions.FillAndExpand,
				Children = {
					listView
				}	
			};
		}
	}
}
