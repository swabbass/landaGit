package ward.landa;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentUpdates extends Fragment {

	ListView l;
	List<update> ups;
	boolean isExpanded = false;

	public interface updateCallback {
		public void onUpdateClick(int pos);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.updates_frag_list, container,
				false);
		l = (ListView) root.findViewById(R.id.updates_listView);
		String lo = "A proven pattern to handle this undo option is to offer a selection at the end of the screen. This selection vanishes after a predefined time or once the user continues to interact with the applicationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn.";
		ups = new Vector<update>();
		ups.add(new update("hey hey "));
		ups.add(new update(
				"veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy looooooooooooooooooooooooooooooooooooooonggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg texxxxxxxtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"));
		ups.add(new update(lo));
		ups.add(new update(lo.substring(20)));
		ups.add(new update(lo.substring(10, lo.length() - 1)));

		updatesAdapter uAdapter = new updatesAdapter(ups, getActivity());
		l.setAdapter(uAdapter);
		l.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				int first = l.getFirstVisiblePosition();
				View v = l.getChildAt(arg2 - first);
				ExpandableTextView tv = (ExpandableTextView) v
						.findViewById(R.id.contentText);
				if (!tv.isExpanded()) {

					Fx.slideDown(getActivity(), v);
					tv.setEllipsize(null);
					tv.setMaxLines(20);
					tv.setExpanded(true);

				} else {
					Fx.slideUp(getActivity(), v);
					tv.setEllipsize(TruncateAt.END);
					tv.setMaxLines(2);
					tv.setExpanded(false);
				}

			}

		});
		return root;
	}

	static class updatesAdapter extends BaseAdapter {

		List<update> updates;
		LayoutInflater inflater = null;
		Context cxt = null;

		public updatesAdapter(List<update> updates, Context cxt) {
			this.updates = updates;
			this.cxt = cxt;
			this.inflater = (LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return updates.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return updates.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = convertView;
			TextView title;
			TextView time;
			ExpandableTextView subject;
			if (v == null) {
				v = inflater.inflate(R.layout.updates_item, parent, false);
				v.setTag(R.id.title_update, v.findViewById(R.id.title_update));
				v.setTag(R.id.contentText, v.findViewById(R.id.contentText));
				v.setTag(R.id.update_time, v.findViewById(R.id.update_time));

			}
			subject = (ExpandableTextView) v.getTag(R.id.contentText);
			title = (TextView) v.getTag(R.id.title_update);
			subject.setText(updates.get(position).getText());
			time = (TextView) v.getTag(R.id.update_time);

			return v;
		}


	}
}
