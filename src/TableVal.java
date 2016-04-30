// R7 code referred
public class TableVal extends KeyItem<String> {
	
	protected Boolean val;
	
	public TableVal(String id, Boolean val){
		super(id);
		this.val = val;
	}

	public String toString(){
		return "[" + getKey() + ": " + val+ "]"; 
	}
	
}

