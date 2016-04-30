// R7 code referred
public class TableVal extends KeyItem<Long> {
	
	protected long cksum;
	protected String desc;
	
	public TableVal(long cksum,String desc){
		super(cksum);
		this.desc = desc;
	}

	public String toString(){
		return "[" + getKey() + ": " + val+ "]"; 
	}
	
}

