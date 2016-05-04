import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class icd {
	
	private int size;
	private BST[] hashTable;
	
	public icd(String filename,int size) throws Exception {
		this.size = size;
		createHashTable(filename);
	}
	
	public void findQuery(String query) {
		long cksum = cksum(query);
		int index = Math.abs((int) cksum%size);
		TableVal soln;
		if(hashTable[index]!=null) {
			soln = hashTable[index].retrieveItem(cksum);
		}
		else {
			soln = null;
		}
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
		Scanner sc = null;
		try{
			File file = new File(filename);
			sc = new Scanner(file);
			if(!(sc.hasNext())) {
				System.err.println("File empty!");
			}
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
		}
		catch(IOException e) {
			throw new IOException("Bad filename/File not found!");
		}
		/*catch(IOException e) {
			throw new IOException("Bad filename");
		}*/
		finally {
			if(sc!=null) {
				sc.close();
			}
		}

	}
	
	public static long cksum(String s) {
        Checksum engine = new CRC32();
        byte[] bytes = s.getBytes();
        engine.update(bytes, 0, bytes.length);
        return engine.getValue();
    }
	
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.err.println("Bad arguments: specify filename,hash table size,one or more code queries");
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
