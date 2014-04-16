package ward.landa;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		FragmentCourses.OnCourseSelected, FragmentTeachers.callbackTeacher {

	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
	PagerTitleStrip strip;
	DrawerLayout drawerLayout;
	ActionBarDrawerToggle drawertoggle;
	ListView draweList;
	String[] drawertitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawertitles = new String[] { "Updates", "Events", "Teachers",
				"Courses", "Sign in" };

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		draweList = (ListView) findViewById(R.id.left_drawer);
		draweAdapter dAdapter = new draweAdapter(getApplicationContext(),drawertitles);
		draweList.setAdapter(dAdapter);
		draweList.setOnItemClickListener(new drawerOnItemClick(this));
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		drawertoggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerClosed(View drawerView) {

				super.onDrawerClosed(drawerView);

				getActionBar().setTitle("Landa");
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {

				super.onDrawerOpened(drawerView);
				getActionBar().setTitle("Navigate to ");
				invalidateOptionsMenu();
			}

		};

		getActionBar().setDisplayHomeAsUpEnabled(true);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		strip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {

				switch (pos) {

				case 0:
					strip.setBackgroundColor(Color.parseColor("#33b5e5"));
					break;
				case 1:
					// #8ec127
					strip.setBackgroundColor(Color.parseColor("#8ec127"));
					break;
				case 2:
					strip.setBackgroundColor(Color.parseColor("#f47835"));
					break;
				case 3:
					// #ffc425
					strip.setBackgroundColor(Color.parseColor("#ffc425"));
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {

		super.onPostCreate(savedInstanceState);
		drawertoggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		super.onConfigurationChanged(newConfig);
		drawertoggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO find the items and in menu and set it !drawerOpen

		boolean draweOpen = drawerLayout.isDrawerOpen(draweList);

		return super.onPrepareOptionsMenu(menu);
	}

	static   class drawerOnItemClick implements ListView.OnItemClickListener {

		MainActivity activityRef;
		public drawerOnItemClick(MainActivity activity) {
			// TODO Auto-generated constructor stub
		activityRef=activity;
		}
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			CourseFragment c = (CourseFragment) activityRef.getSupportFragmentManager()
					.findFragmentByTag("CourseFragment");
			teacherFragment t = (teacherFragment) activityRef.getSupportFragmentManager()
					.findFragmentByTag("TeacherFragment");

			if ((c != null && c.isVisible()) || (t != null && t.isVisible())) {
				Log.i("LANDA", "CourseFragment");

				activityRef.getSupportFragmentManager().popBackStack();
			}

			if (arg2 <activityRef. drawertitles.length)
				if (arg2 == activityRef.drawertitles.length - 1) {
					activityRef.openLoginFragment();
				} else {
					activityRef.mViewPager.setCurrentItem(arg2);
				}
			activityRef.drawerLayout.closeDrawer(activityRef.draweList);
		}

	}

	public void openLoginFragment() {
		LoginFragment login = new LoginFragment();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_container, login, "LoginFragment");
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.addToBackStack(null);
		transaction.commit();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (drawertoggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	static class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Log.i("FragmentPosition", "Fposition = " + position);
			switch (position) {
			case 0:
				return new FragmentUpdates();
			case 1:
				return new FragmentEvents();
			case 2:
				return new FragmentTeachers();
			case 3:
				FragmentCourses corses = new FragmentCourses();
				return corses;

			}

			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "Updates";
			case 1:
				return "Events";
			case 2:
				return "Teachers";
			case 3:
				return "Courses";

			}
			return null;
		}

		@Override
		public int getItemPosition(Object object) {

			return POSITION_NONE;
		}

	}

	@Override
	public void onCourseClick(int position) {

	}

	@Override
	public void onCourseClick(Course c) {
		CourseFragment cf;
		FragmentManager fm = getSupportFragmentManager();
		cf=(CourseFragment)fm.findFragmentByTag("CourseFragment");
		if(cf==null){
			cf= new CourseFragment();
		Bundle extras = new Bundle();
		extras.putString("name", c.getName());
		extras.putInt("ImageID", c.getImgID());
		extras.putInt("courseID", c.getCourseID());
		cf.setArguments(extras);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.replace(R.id.fragment_container, cf, "CourseFragment");
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.addToBackStack(null);
		transaction.commit();
		}
		else
		{
			Log.d("hehe","null");
		}
		

	}

	@Override
	public void OnTeacherItemClick(Teacher t) {
		teacherFragment tf = new teacherFragment();
		Bundle extras = new Bundle();
		extras.putInt("imgid", t.getImgId());
		extras.putString("name", t.getName());
		extras.putString("email", t.getEmail());
		extras.putString("faculty", t.getFaculty());
		extras.putString("position", t.getPosition());
		tf.setArguments(extras);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_container, tf, "TeacherFragment");
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.addToBackStack(null);
		transaction.commit();
		
	}

	static class draweAdapter extends BaseAdapter {

		LayoutInflater inflater = null;
		String[] drawertitles;
		public draweAdapter(Context cxt ,String[] drawertitles) {
			this.drawertitles=drawertitles;
			inflater = (LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {

			return drawertitles.length;
		}

		@Override
		public Object getItem(int arg0) {

			return drawertitles[arg0];
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			View v = arg1;
			if (v == null) {
				v = inflater.inflate(R.layout.draweritem, arg2, false);
				v.setTag(R.id.drawer, v.findViewById(R.id.drawer));
			}
			TextView tv = (TextView) v.getTag(R.id.drawer);
			tv.setText(drawertitles[position]);

			return v;

		}

	}

}
