import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.IOException;

public class icd {
	
	private int size;
	private String filename;
	private ArrayList<String> queries;
	private BST[] hashTable;
	
	public icd(String filename,int size) throws Exception {
		this.filename = filename;
		this.size = size;
		createHashTable(filename);
	}
	
	public void findQuery(String query) throws Exception {
		long cksum = cksum(query);
		int index = Math.abs((int) cksum%size);
		TableVal soln = hashTable[index].retrieveItem(cksum);
		if(soln==null) {
			System.out.println(query+": "+"code not found");
		}
		else {
		System.out.println(query+": "+soln);
		}
	}
	
	public void createHashTable(String filename) throws Exception {
		hashTable = new BST[size];
		for(int i=0;i<size;i++) {
			hashTable[i] = null;
		}
		File file = new File(filename);
		try{
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String code = line.substring(6, 13).trim();
			long cksum = cksum(code);
			int index = Math.abs((int) cksum%size);
			String desc = line.substring(77);
			TableVal item = new TableVal(cksum,desc);
			if(hashTable[index]==null) {
				BST tree = new BST();
				tree.insertItem(item);
				hashTable[index]=tree;
			}
			else {
				hashTable[index].insertItem(item);
			}
			
		}
		sc.close();
		}
		catch(IOException e) {
			System.out.println("IOException caught");
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
			int size = Integer.parseInt(args[1]);
			icd icd = new icd(args[0],size);
			int i=2;
			while(i<args.length) {
				icd.findQuery(args[i]);
				i++;
			}


		}

	}

	

}
