package ward.landa;

import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.Choreographer.FrameCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		FragmentCourses.OnCourseSelected, FragmentTeachers.callbackTeacher {

	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
	PagerTitleStrip strip;
	DrawerLayout drawerLayout;
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
		draweAdapter dAdapter = new draweAdapter(getApplicationContext());
		draweList.setAdapter(dAdapter);
		draweList.setOnItemClickListener(new drawerOnItemClick());
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		strip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	public class drawerOnItemClick implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if (arg2 < drawertitles.length)
				mViewPager.setCurrentItem(arg2);
			drawerLayout.closeDrawer(draweList);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

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
			Locale l = Locale.getDefault();
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
			// TODO Auto-generated method stub
			return POSITION_NONE;
		}

	}

	@Override
	public void onCourseClick(int position) {

	}

	@Override
	public void onCourseClick(Course c) {
		CourseFragment cf = new CourseFragment();
		Bundle extras = new Bundle();
		extras.putString("name", c.getName());
		extras.putInt("ImageID", c.getImgID());
		extras.putInt("courseID", c.getCourseID());
		cf.setArguments(extras);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.replace(R.id.fragment_container, cf);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.addToBackStack(null);
		transaction.commit();
		mSectionsPagerAdapter.notifyDataSetChanged();

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
		transaction.replace(R.id.fragment_container, tf);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.addToBackStack(null);
		transaction.commit();
		mSectionsPagerAdapter.notifyDataSetChanged();
	}

	class draweAdapter extends BaseAdapter {

		LayoutInflater inflater = null;

		public draweAdapter(Context cxt) {
			inflater = (LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return drawertitles.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return drawertitles[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View v = arg1;
			if (v == null) {
				v = inflater.inflate(R.layout.draweritem, arg2, false);
				v.setTag(R.id.drawer, v.findViewById(R.id.drawer));
			}
			TextView tv = (TextView) v.getTag(R.id.drawer);
			tv.setText(drawertitles[arg0]);

			return v;
		}

	}

}
