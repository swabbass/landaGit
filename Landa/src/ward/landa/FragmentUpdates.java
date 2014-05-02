package ward.landa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentUpdates extends Fragment {

	ListView l;
	List<Update> ups;
	List<Update> hidden;
	// maybe more effiecent to deal with indexes of the active ones but first
	// solve adapter issues
	List<Update> active;
	boolean isExpanded = false;
	updateCallback callBack;
	boolean showAll;
	updatesAdapter uAdapter;

	public interface updateCallback {
		public void onUpdateClick(int pos);

		public void onUpdateButtonClick(ImageButton btn, int pos,
				List<Update> updates, boolean show);
	}
	
	
	@Override
	public void onResume() {
		Log.d("Fragment","on resume updates");
		super.onResume();
	}
	@Override
	public void onStop() {
		Log.d("Fragment","on stop updates");
		super.onStop();
	}
	@Override
	public void onStart() {
		Log.d("Fragment","on start updates");
		super.onStart();
	}
	@Override
	public void onPause() {
		Log.d("Fragment","on pause updates");
		super.onPause();
	}
	@Override
	public void onAttach(Activity activity) {
		Log.d("Fragment","on attach updates");
		try {
			callBack = (updateCallback) activity;
			setHasOptionsMenu(true);
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement callbackTeacher");
		}

		super.onAttach(activity);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.showall:
			if (!showAll) {
				this.showAll = true;
				if (uAdapter.getCount() == 0) {

					uAdapter = new updatesAdapter(active, ups, getActivity(),
							callBack);
					l.setAdapter(uAdapter);

				}

				uAdapter.setUpdates(ups);

				uAdapter.notifyDataSetChanged();
				item.setIcon(R.drawable.hide_icpn);
				Fx.slideDown(getActivity(), item.getActionView());

			} else {
				this.showAll = false;
				active = filterActive(ups);
				uAdapter.setUpdates(active);
				uAdapter.notifyDataSetChanged();
				item.setIcon(R.drawable.show_icpn);
				Fx.slideUp(getActivity(), item.getActionView());
			}
			uAdapter.setShowall(this.showAll);
			uAdapter.notifyDataSetInvalidated();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		getActivity().getMenuInflater().inflate(R.menu.listmenu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("Fragment","on create updates");
		View root = inflater.inflate(R.layout.updates_frag_list, container,
				false);
		l = (ListView) root.findViewById(R.id.updates_listView);
		showAll = false;
		String lo = "A proven pattern to handle this undo option is to offer a selection at the end of the screen. This selection vanishes after a predefined time or once the user continues to interact with the applicationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn.";
		ups = new ArrayList<Update>();
		ups.add(new Update("hey hey "));
		ups.add(new Update(
				"veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy looooooooooooooooooooooooooooooooooooooonggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg texxxxxxxtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"));
		ups.add(new Update(lo));
		ups.add(new Update(lo.substring(20)));
		ups.add(new Update(lo.substring(10, lo.length() - 1)));

		active = filterActive(ups);
		uAdapter = new updatesAdapter(active, ups, getActivity(), callBack);
		uAdapter.setShowall(false);
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

		private List<Update> updates;
		LayoutInflater inflater = null;
		Context cxt = null;
		updateCallback callback;
		private boolean showall;
		List<Update> source;

		public List<Update> getUpdates() {
			return updates;
		}

		public void setUpdates(List<Update> updates) {
			this.updates = updates;
		}

		public void setShowall(boolean showall) {
			this.showall = showall;
		}

		public boolean isShowall() {
			return showall;
		}

		public updatesAdapter(List<Update> updates, List<Update> source,
				Context cxt, updateCallback callback) {
			showall = false;
			this.updates = updates;
			this.cxt = cxt;
			this.inflater = (LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.callback = callback;
			this.source = source;
		}

		@Override
		public int getCount() {
	
			return updates.size();
		}

		@Override
		public Object getItem(int position) {
	
			return updates.get(position);
		}

		@Override
		public long getItemId(int position) {
	
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
	
			View v = convertView;
			TextView title;
			TextView time;
			ExpandableTextView subject;

			if (v == null) {
				v = inflater.inflate(R.layout.updates_item, parent, false);
				v.setTag(R.id.title_update, v.findViewById(R.id.title_update));
				v.setTag(R.id.contentText, v.findViewById(R.id.contentText));
				v.setTag(R.id.update_time, v.findViewById(R.id.update_time));
				v.setTag(R.id.imgattention, v.findViewById(R.id.imgattention));
			}
			subject = (ExpandableTextView) v.getTag(R.id.contentText);
			title = (TextView) v.getTag(R.id.title_update);
			subject.setText(updates.get(position).getText());
			time = (TextView) v.getTag(R.id.update_time);
			final ImageButton btn = (ImageButton) v.getTag(R.id.imgattention);
			if (updates.get(position).isActive()) {
				btn.setImageResource(R.drawable.update_icon_new);
			} else {
				btn.setImageResource(R.drawable.megaphone_after);
			}
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					callback.onUpdateButtonClick(btn, position, updates,
							showall);
					if (!showall) {
						updateSource(updates);
					}

				}
			});

			return v;
		}

		private void updateSource(List<Update> updates) {
			ArrayList<Integer> toRemove = new ArrayList<Integer>();
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			for (Update u : updates) {
				if (source.contains(u)) {
					int index = source.indexOf(u);
					source.get(index).setActive(u.isActive());

					if (!u.isActive()) {
						toRemove.add(Integer.valueOf(updates.indexOf(u)));
					} else {
						if (!updates.contains(source.get(index))) {
							toRemove.add(Integer.valueOf(index));
						}
					}

				}
			}
			for (Integer a : toRemove) {
				updates.remove(a.intValue());
			}
			for (Integer a : toAdd) {
				updates.add(source.get(a.intValue()));
			}
			if (updates.isEmpty()) {
				notifyDataSetInvalidated();
			} else {
				notifyDataSetChanged();
			}

		}
	}

	private static List<Update> filterActive(List<Update> updates) {
		List<Update> result = new ArrayList<Update>();
		for (Update u : updates) {
			if (u.isActive()) {
				result.add(u);
			}
		}
		return result;
	}

}
