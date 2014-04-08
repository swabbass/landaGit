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
import android.widget.GridView;
import android.widget.TextView;

public class FragmentEvents extends Fragment {
	List<Event> l;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View root= inflater.inflate(R.layout.events_frag_list,container,false);
	l = new ArrayList<Event>();
	Event e1=new Event("students of sami full info", "students of sami ", "16/12/2020 13:30", "Sego 4", 112);
	Event e2=new Event("students of ward full info", "students of ward ", "25/11/2010 14:30", "taub 1", 113);
	Event e3=new Event("students of mohammed full info", "students of mohammed  ", "19/1/2000 11:30", "Ulman 305", 114);
	Event e4=new Event("students of almothana full info", "students of almothana ", "13/18/2050 15:30", "Sefrya ", 115);
	Event e5=new Event("students of Yoav full info", "students of Yoav", "15/7/2120 19:30", "taub 7", 116);
	
	l.add(e1);
	l.add(e2);
	l.add(e3);
	l.add(e4);
	l.add(e5);
	GridView gridView = (GridView)root.findViewById(R.id.EventGridView);
    gridView.setAdapter(new gridAdabter(root.getContext()));
	return root;
}


class gridAdabter extends BaseAdapter {

	LayoutInflater inflater;

	public gridAdabter(Context context) {
		this.inflater = LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
	
		return 	l.size();
	}

	@Override
	public Object getItem(int arg0) {

		return l.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return l.get(arg0).getId();
	}

	@Override
	public View getView(int pos, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		View v = view;
		TextView info;
		
		if (v == null) {
			v = inflater.inflate(R.layout.event_item, viewGroup, false);
			v.setTag(R.id.eventDetails, v.findViewById(R.id.eventDetails));
			//add buttons actions later 
			//v.setTag(R.id.text, v.findViewById(R.id.text));
		}
		//picture = (ImageView) v.getTag(R.id.picture);
		info = (TextView) v.getTag(R.id.eventDetails);

		Event event = (Event) getItem(pos);

		
		info.setText(event.toString());

		return v;
	}
}
}
