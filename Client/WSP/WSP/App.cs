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
	public class App : Application
	{
		public static Manager Manager { get; private set; }
		public App () 
		{
			MainPage = new WSP.RootPage();
			Manager = new Manager(new RestService());
		}

	    protected override void OnStart () {
			
		}

		protected override void OnSleep ()
		{
			// Handle when your app sleeps
		}

		protected override void OnResume ()
		{
			// Handle when your app resumes
		}
	}
}

