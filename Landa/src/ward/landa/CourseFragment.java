package ward.landa;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CourseFragment extends Fragment {

	List<Teacher> teachers;
	ListView l;
	int courseID;
	int imgId;
	String courseName;
	TextView nameView;
	ImageView courseImg;
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.fragment_course, null);		
		Teacher t1=new Teacher(0000, R.drawable.ward, "ward","mail@ward.com","0555","חברתי","CS");
		Teacher t2=new Teacher(111, R.drawable.mohammed, "Mohammed","mail@mohammed.com","015","","");
		Teacher t3=new Teacher(111, R.drawable.hamed, "Hammed","mail@hamed.com","0555","","");
		Teacher t4=new Teacher(333, R.drawable.stlzbale, "حسن","mail@stlzbale.com","0555","","");
		teachers=new ArrayList<Teacher>();
		teachers.add(t1);
		teachers.add(t2);
		teachers.add(t3);
		teachers.add(t4);
		Bundle ex=getArguments();
		if(ex!=null)
		{
			this.courseName=ex.getString("name");
			this.imgId=ex.getInt("ImageID");
			this.courseID=ex.getInt("courseID");
		}
		nameView=(TextView)root.findViewById(R.id.title);
		courseImg=(ImageView)root.findViewById(R.id.list_image);
		nameView.setText(courseID+" "+courseName+"");
		courseImg.setImageResource(imgId);
		l=(ListView)root.findViewById(R.id.courseTeachers);
		adapter ad=new adapter(getActivity());
		l.setAdapter(ad);
		return root;
	}

	class adapter extends BaseAdapter {
		public LayoutInflater inflater;

		public adapter(Context cxt) {
			// TODO Auto-generated constructor stub
			this.inflater=(LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return teachers.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return teachers.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return teachers.get(arg0).getID();
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = view;
			ImageView picture;
			TextView name;
			if (v == null) {
				v = inflater.inflate(R.layout.incourse_teacher_list_item, parent, false);
				v.setTag(R.id.incourse_teacher_pic, v.findViewById(R.id.incourse_teacher_pic));
				v.setTag(R.id.incourse_teacher_text, v.findViewById(R.id.incourse_teacher_text));
			}
			picture = (ImageView) v.getTag(R.id.incourse_teacher_pic);
			name = (TextView) v.getTag(R.id.incourse_teacher_text);
			Teacher t=(Teacher)getItem(pos);
			picture.setImageResource(t.getImgId());
			name.setText(t.toString());
			return v;
		}

	}

}
