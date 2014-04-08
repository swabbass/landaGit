package ward.landa;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class FragmentCourses extends Fragment {

	OnCourseSelected callback;

	public interface OnCourseSelected {
		public void onCourseClick(int position);

		public void onCourseClick(Course c);
	}

	ListView l;
	GridView g;
	List<Course> courses;
	View root;

	@Override
	public void onAttach(Activity activity) {

		try {
			callback = (OnCourseSelected) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCourseSelected");
		}
		super.onAttach(activity);
	}

	@Override
	public void onStart() {

		// list already viewed

		super.onStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 root= inflater.inflate(R.layout.courses_frag_grid, container,
				false);
		 g=(GridView)root.findViewById(R.id.gridviewcourses);
		//l = (ListView) root.findViewById(R.id.courses_listView);
		courses = new ArrayList<Course>();
		Course c1 = new Course(100, "חדוא1", "Thursday 17:30", "Yoav",
				R.drawable.calculas2, 0);
		Course c2 = new Course(1110, "אלגברה", "Wednesday 14:30", "Yoav",
				R.drawable.akgebramatrix, 0);
		Course c3 = new Course(110, "מבוא למדעי מחשב", "Monday 19:30", "Yoav",
				R.drawable.c, 0);
		Course c4 = new Course(102, "כימיה כללית", "Sunday 13:00", "Yoav",
				R.drawable.chemestry, 0);
		courses.add(c1);
		courses.add(c2);
		courses.add(c3);
		courses.add(c4);
		coursesAdapter uAdapter = new coursesAdapter(courses, getActivity());
		g.setAdapter(uAdapter);
		g.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				callback.onCourseClick(courses.get(arg2));
				g.setItemChecked(arg2, true);
			}

		});
		return root;
	}

	class coursesAdapter extends BaseAdapter {

		List<Course> courses;
		LayoutInflater inflater = null;
		Context cxt = null;

		public coursesAdapter(List<Course> courses, Context cxt) {
			this.courses = courses;
			this.cxt = cxt;
			this.inflater = (LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {

			return courses.size();
		}

		@Override
		public Object getItem(int position) {

			return courses.get(position);
		}

		@Override
		public long getItemId(int position) {

			return courses.get(position).getCourseID();
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {

			View v = view;
			ImageView picture;
			TextView name;
			TextView date;
			RatingBar rb;

			if (v == null) {
				v = inflater.inflate(R.layout.course_item_grid, parent, false);
				v.setTag(R.id.list_image, v.findViewById(R.id.list_image));
				v.setTag(R.id.title, v.findViewById(R.id.title));
			}
			picture = (ImageView) v.getTag(R.id.list_image);
			name = (TextView) v.getTag(R.id.title);
			Course c = (Course) getItem(position);

			picture.setImageResource(c.getImgID());
			name.setText(c.getName());
			return v;
		}

	}

}
