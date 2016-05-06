package crapPack;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class AbundantValueTest {

	@Test
	public void test(){
		BTree b1 = new BTree(1);
		//b1.insert("btree.txt");
		b1.insert(new Integer(5));
		b1.insert(new Integer(6));
		b1.insert(new Integer(4));
		
		b1.printLevelorder();
		System.out.println(b1.abundadntSearch());
	}

}
