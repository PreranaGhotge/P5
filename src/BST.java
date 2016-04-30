
public class BST{ 
	private BSTNode<String> root;

	//empty tree
	public BST(){
		this.root = null;
	}

	public boolean isEmpty(){
		return root==null;
	}

	public void insertItem(String item) throws BSTException{
		root = insertItem(root, item);
	}

	private BSTNode<String> insertItem(BSTNode<String> node, String item) throws BSTException{
		BSTNode<String> newSubTree;
		if(node==null){
			newSubTree = new BSTNode<String>(item);
			return newSubTree;
		}
		
		String tok = node.getItem();
		if(item.compareTo(tok)<0){
			newSubTree = insertItem(node.getLeft(), item);
			node.setLeft(newSubTree);
			return node;
		}
		if(item.compareTo(tok)<0){
			newSubTree = insertItem(node.getRight(), item);
			node.setRight(newSubTree);
			return node;
		}
		// ERROR: inserting existing item
		else 
			throw new BSTException("Inserting item with existing key!");
	}

	public String retrieveItem(String key){
		return retrieveItem(root,key);
	}
	
	private String retrieveItem(BSTNode<String> node, String key){
		String treeItem;
		
		if(node==null)
			treeItem = null;
		else{
			String nodeItem = node.getItem();
			if(key.compareTo(nodeItem) == 0)
				//found
				treeItem = nodeItem;
			else if(key.compareTo(nodeItem) < 0)
				//search left
				treeItem = retrieveItem(node.getLeft(), key);
			else
				// search right
				treeItem = retrieveItem(node.getRight(), key);
		}
		return treeItem;

	}
	

	public void preorderTraverse(){
		if (!isEmpty())
			preorderTraverse(root,"");
		else
			System.out.println("root is null");
	}

	public void preorderTraverse(BSTNode<String> node, String indent){
		System.out.println(indent+node.getItem());		
		if(node.getLeft()!=null) {
			System.out.println(indent+"left");
			preorderTraverse(node.getLeft(),indent+" ");
		}

		if(node.getRight()!=null) {
			System.out.println(indent+"right");
			preorderTraverse(node.getRight(),indent+" ");
		}
	}

}

