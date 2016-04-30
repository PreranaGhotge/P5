// R7 code referred
public class TableVal extends KeyItem<Integer> {
	
	protected int index;
	protected String desc;
	
	public TableVal(int index,String desc){
		super(index);
		this.desc = desc;
	}

	public String toString(){
		return "[" + getKey() + ": " + val+ "]"; 
	}
	
}

