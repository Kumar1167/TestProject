package collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetImp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set hashset = new HashSet();
		hashset.add("Raju");
		hashset.add(1);
		hashset.add(1.09);
		hashset.add(0.013);
		System.out.println(hashset);
		Iterator itr = hashset.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		//hashset.remove("Raju");
		//System.out.println(hashset);
		
		System.out.println("======LinkedHashset========");
		Set linkSet = new LinkedHashSet();
		linkSet.add(5);
		linkSet.add(1);
		linkSet.add(109);
		linkSet.add(0013);
		System.out.println(linkSet);
		System.out.println(Collections.max(linkSet));
		//linkSet.addAll(hashset);
		//System.out.println(linkSet);
		
		System.out.println("======TreeSet========");
		Set treeSet = new TreeSet(linkSet);
		System.out.println(treeSet);
		List lis = new LinkedList(treeSet);
		System.out.println(lis.get(treeSet.size() - 1));
		Collections.sort(lis, Collections.reverseOrder());
		System.out.println(lis);
		System.out.println(Collections.min(lis));
		Collections.reverse(lis);
		System.out.println(lis);
	}

}
