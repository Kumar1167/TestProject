package collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListImp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("==========ArrayList==========");
			ArrayList li = new ArrayList();
			li.add(12);
			li.add(34);
			li.add(342);
			li.add(0);
			li.add(12);
			
			System.out.println(li);
			Collections.reverse(li);
			System.out.println("reverse Order " +li +"\n");
			 for(Object e : li) {
			int we = Collections.frequency(li, e);
			System.out.println("Frequency of " +e +" is " +we);
	}
			 
		 Collections.sort(li,Collections.reverseOrder());
		 System.out.println("\n" +li);
		 int qw = li.lastIndexOf(12);
		 li.remove(qw); 
		 System.out.println(li);
		 Collections.reverse(li);
		 System.out.println("Min Val is " +li.get(0));
		 System.out.println("Max Val is " +li.get(li.size()-1));
		 
		 System.out.println("==========LinkedList==========");
		 List linkLi = new LinkedList();
		 linkLi.addAll(li);
		 System.out.println(linkLi);
		 linkLi.set(0,100);
		 System.out.println(linkLi);
		 Collections.sort(linkLi);
		 System.out.println(linkLi);
		 linkLi.add(0, 87);
		 linkLi.add(linkLi.size(), 97);
		 System.out.println(linkLi);
		 System.out.println(linkLi.get(linkLi.size()-1));
		 //Collections.sort(linkLi);
		 //System.out.println(linkLi);
		 System.out.println(Collections.min(linkLi));
 }

}
