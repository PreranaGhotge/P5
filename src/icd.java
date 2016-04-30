import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class icd {
	
	private int size;
	private String filename;
	private ArrayList<String> queries;
	private BST[] hashTable;
	
	public icd(String filename,int size,ArrayList<String> queries) throws Exception {
		this.filename = filename;
		this.size = size;
		createHashTable(filename);
	}
	
	public void createHashTable(String filename) throws Exception {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String code = line.substring(6, 13);
			long cksum = cksum(code);
			int index = Math.abs((int) cksum%size);
			
			String desc = line.substring(77);
		}
		
	}
	
	public static long cksum(String s) throws Exception{
        Checksum engine = new CRC32();
        byte[] bytes = s.getBytes();
        engine.update(bytes, 0, bytes.length);
        return engine.getValue();
    }
	
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
		    throw new Exception("specify filename,hash table size,one or more code queries");
	}
		else {
			ArrayList<String> queries = new ArrayList<String>();
			int i=2;
			while(i<args.length) {
				queries.add(args[i]);
			}
			int size = Integer.parseInt(args[1]);
			icd icd = new icd(args[0],size,queries);
		}
			
		}

	

}
