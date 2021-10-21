package smarter.common.util;

public class NotSet {

	private static final NotSet notSet = new NotSet();
	
	public static NotSet get(){
		return notSet;
	}
}
