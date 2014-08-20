package us.zingalicio.cordstone.exceptions;

public class NoPlayerException extends Exception
{
	private static final long serialVersionUID = 5178702550370426088L;

	public final String s;
	
	public NoPlayerException(String s)
	{
		this.s = s;
		
	}
}
