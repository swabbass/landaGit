package ward.landa;




public class Update {
	private String text;
	private boolean active;
	 public Update(String str) {
		// TODO Auto-generated constructor stub
		 if(str!=null)
		 this.text=str;
		 else 
			 this.text="I have empty text hello world hey !";
		 setActive(true);
	}
	 
	public String getText() {
		return text;
	}

	public String getSmallText()
	{
		if (text.length()>200)
		{
			for(int i=200;i<text.length();i++)
			{
				if(text.charAt(i)==' ')
					return new String(text.substring(0, i)+"...");
			}
		}
		return text;
	}
	public boolean isToobig()
	{
		return text.length()>300;
	}
	public void setText(String text) {
		this.text = text;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
