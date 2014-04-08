package ward.landa;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class expandableTextView extends TextView {

	private boolean isExpanded;
	public expandableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setExpanded(false);
		
		// TODO Auto-generated constructor stub
	}
	public boolean isExpanded() {
		return isExpanded;
	}
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

}
