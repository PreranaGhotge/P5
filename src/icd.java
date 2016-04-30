import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class icd {
	
	private int size;
	private String filename;
	private ArrayList<String> queries;
	
	public icd(String filename,int size,ArrayList<String> queries) {
		this.filename = args[0];
		this.size = args[1];
		readFile(filename);
	}
	
	public void readFile(String filename) {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String query = line.substring(6, 13);
			long cksum = cksum(query);
			
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
