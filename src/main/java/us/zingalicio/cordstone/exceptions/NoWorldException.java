package us.zingalicio.cordstone.exceptions;

public class NoWorldException extends Exception
{
	private static final long serialVersionUID = -5280862479873542780L;
	
	public final String s;
	
	public NoWorldException(String s)
	{
		this.s = s;
		
	}

}
