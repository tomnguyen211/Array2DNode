package hw05;

import java.util.ArrayList;
/**
 * This main will be a tester class to test all methods of using a empty 
 * linked list, and a linked list derived from a 2D array;
 * 
 * @author dtnng, 402005276, 2013-07
 *
 */
public class Array2DTester {
	public static void main(String[] args)
	{
		/** Empty LinkedList */
		System.out.println("EMPTY LINKEDLIST");
		Array2D<Integer> linkInt = new Array2D<Integer>();
		
		/** Add First Column */
		linkInt.addFirstCol(0,1,2,3,4,5,6,7,8,9);
		System.out.println("Add First Column\n");
		System.out.println(linkInt.toString());
		
		System.out.println("Add Another First Column\n");
		linkInt.addFirstCol(10,11,12,13,14,15,16,17,18,19);
		System.out.println(linkInt.toString());		
		/** Add Last Column */
		System.out.println("Add Last Column\n");
		linkInt.addLastCol(20,21,22,23,24,25,25,27,28,29);
		System.out.println(linkInt.toString());		
		
		System.out.println("Add Another Last Column\n");
		linkInt.addLastCol(30,31,32,33,34,35,36,37,38,39);
		System.out.println(linkInt.toString());	
		
		/** Add First Row */
		System.out.println("Add First Row\n");
		linkInt.addFirstRow(40,41,42,43);
		System.out.println(linkInt.toString());	
		
		System.out.println("Add Another First Row\n");
		linkInt.addFirstRow(44,45,46,47);
		System.out.println(linkInt.toString());	
				
		/** Add Last Row */
		System.out.println("Add Last Row\n");
		linkInt.addLastRow(48,49,50,51);
		System.out.println(linkInt.toString());	
		
		System.out.println("Add Another Last Row\n");
		linkInt.addLastRow(52,53,54,55);
		System.out.println(linkInt.toString());	
		/** Insert Column */
		System.out.println("Insert A Column: Index 1");
		linkInt.insertCol(1,56,57,58,59,60,61,62,63,64,65,66,67,68,69);
		System.out.println(linkInt.toString());	
		/** Insert Row */
		System.out.println("Insert A Row: Index 5");
		linkInt.insertRow(5,70,71,72,73,74);
		System.out.println(linkInt.toString());	
		/** Delete First Column */
		System.out.println("Delete First Column");
		linkInt.deleteFirstCol();
		System.out.println(linkInt.toString());	
		/** Delete First Row */
		System.out.println("Delete First Row");
		linkInt.deleteFirstRow();
		System.out.println(linkInt.toString());	
		/** Delete Last Column */
		System.out.println("Delete Last Column");
		linkInt.deleteLastCol();
		System.out.println(linkInt.toString());	
		/** Delete Last Row */
		System.out.println("Delete Last Row");
		linkInt.deleteLastRow();
		System.out.println(linkInt.toString());	
		/** Delete Column */
		System.out.println("Delete Column: Index 1");
		linkInt.deleteCol(1);
		System.out.println(linkInt.toString());	
		/** Delete Row */
		System.out.println("Delete Row: Index 4");
		linkInt.deleteRow(4);
		System.out.println(linkInt.toString());	
		/** Get */
		System.out.println("Get: Row Index 4, Col index 1");
		System.out.println(linkInt.get(4, 1));
		/** Get Column*/
		System.out.println("\nGet Column: Index 1");
		ArrayList<Integer> arrayInt_Col = linkInt.getCol(1);
		for(int n = 0; n < arrayInt_Col.size();n++)
		{
			System.out.print(arrayInt_Col.get(n) + "\t");
		}
		/** Get Row */
		System.out.println("\n\nGet Row: Index 8");
		ArrayList<Integer> arrayInt_Row = linkInt.getRow(8);
		for(int n = 0; n < arrayInt_Row.size();n++)
		{
			System.out.print(arrayInt_Row.get(n) + "\t");
		}
		/** Set */
		System.out.println("\n\nSet Col Index 0,Row Index 0: 5");
		linkInt.set(0, 0, 5);
		System.out.println(linkInt.toString());	
		
		System.out.println("\n\nSet Col Index 0, Row Index 11: 5");
		linkInt.set(11, 0, 5);
		System.out.println(linkInt.toString());	
		
		System.out.println("\n\nSet Col Index 1,Row Index 0: 5");
		linkInt.set(0, 1, 5);
		System.out.println(linkInt.toString());	
		
		System.out.println("\n\nSet Col Index 1,Row Index 6: 5");
		linkInt.set(6, 1, 5);
		System.out.println(linkInt.toString());	
		/** Col Size */
		System.out.println("Col Size: " + linkInt.colSize());
		/** Row Size */
		System.out.println("Row Size: " + linkInt.rowSize());
		/** To String */
		System.out.println("Print Row by Row:");
		System.out.println(linkInt.toString());
		/** ToStringColByCol*/
		System.out.println("Print Col by Col:");
		System.out.println(linkInt.toStringColByCol());
		
		
		
		
			
		/** Array LinkedList */

		Integer[][] arrayInt = {{  4,  3, 8, -1 },
                				{  2, 6,  9, 4 },
            					{ 10, -2,  1, 9 }
		};
		Array2D<Integer> linkedArray = new Array2D<Integer>(arrayInt);
		/** Print */
		System.out.println("PRINT 2D ARRAY LINKEDLIST");
		System.out.println(linkedArray.toString());
		
		/** Add First Column */
		linkedArray.addFirstCol(0,1,2);
		System.out.println("Add First Column\n");
		System.out.println(linkedArray.toString());
		
		System.out.println("Add Another First Column\n");
		linkedArray.addFirstCol(10,11,12);
		System.out.println(linkedArray.toString());		
		/** Add Last Column */
		System.out.println("Add Last Column\n");
		linkedArray.addLastCol(20,21,22);
		System.out.println(linkedArray.toString());		
		
		System.out.println("Add Another Last Column\n");
		linkedArray.addLastCol(30,31,32);
		System.out.println(linkedArray.toString());	
		
		/** Add First Row */
		System.out.println("Add First Row\n");
		linkedArray.addFirstRow(40,41,42,43,44,45,46,47);
		System.out.println(linkedArray.toString());	
		
		System.out.println("Add Another First Row\n");
		linkedArray.addFirstRow(44,45,46,47,48,49,50,51);
		System.out.println(linkedArray.toString());	
				
		/** Add Last Row */
		System.out.println("Add Last Row\n");
		linkedArray.addLastRow(48,49,50,51,52,53,54,55);
		System.out.println(linkedArray.toString());	
		
		System.out.println("Add Another Last Row\n");
		linkedArray.addLastRow(52,53,54,55,56,57,58,59);
		System.out.println(linkedArray.toString());	
		/** Insert Column */
		System.out.println("Insert A Column: Index 5");
		linkedArray.insertCol(5,56,57,58,59,60,61,62);
		System.out.println(linkedArray.toString());	
		/** Insert Row */
		System.out.println("Insert A Row: Index 4");
		linkedArray.insertRow(4,70,71,72,73,74,75,76,77,78);
		System.out.println(linkedArray.toString());	
		/** Delete First Column */
		System.out.println("Delete First Column");
		linkedArray.deleteFirstCol();
		System.out.println(linkedArray.toString());	
		/** Delete First Row */
		System.out.println("Delete First Row");
		linkedArray.deleteFirstRow();
		System.out.println(linkedArray.toString());	
		/** Delete Last Column */
		System.out.println("Delete Last Column");
		linkedArray.deleteLastCol();
		System.out.println(linkedArray.toString());	
		/** Delete Last Row */
		System.out.println("Delete Last Row");
		linkedArray.deleteLastRow();
		System.out.println(linkedArray.toString());	
		/** Delete Column */
		System.out.println("Delete Column: Index 5");
		linkedArray.deleteCol(5);
		System.out.println(linkedArray.toString());	
		/** Delete Row */
		System.out.println("Delete Row: Index 4");
		linkedArray.deleteRow(4);
		System.out.println(linkedArray.toString());	
		/** Get */
		System.out.println("Get: Row Index 4, Col index 1");
		System.out.println(linkedArray.get(4, 1));
		/** Get Column*/
		System.out.println("\nGet Column: Index 3");
		arrayInt_Col = linkedArray.getCol(3);
		for(int n = 0; n < arrayInt_Col.size();n++)
		{
			System.out.print(arrayInt_Col.get(n) + "\t");
		}
		/** Get Row */
		System.out.println("\n\nGet Row: Index 2");
		arrayInt_Row = linkedArray.getRow(2);
		for(int n = 0; n < arrayInt_Row.size();n++)
		{
			System.out.print(arrayInt_Row.get(n) + "\t");
		}
		/** Set */
		System.out.println("\n\nSet Col Index 0,Row Index 0: 5");
		linkedArray.set(0, 0, 5);
		System.out.println(linkedArray.toString());	
		
		System.out.println("\n\nSet Col Index 0, Row Index 4: 5");
		linkedArray.set(4, 0, 5);
		System.out.println(linkedArray.toString());	
		
		System.out.println("\n\nSet Col Index 5,Row Index 0: 5");
		linkedArray.set(0, 5, 5);
		System.out.println(linkedArray.toString());	
		
		System.out.println("\n\nSet Col Index 1,Row Index 3 : 5");
		linkedArray.set(3, 1, 5);
		System.out.println(linkedArray.toString());	
		/** Col Size */
		System.out.println("Col Size: " + linkedArray.colSize());
		/** Row Size */
		System.out.println("Row Size: " + linkedArray.rowSize());
		/** To String */
		System.out.println("Print Row by Row:");
		System.out.println(linkedArray.toString());
		/** ToStringColByCol*/
		System.out.println("Print Col by Col:");
		System.out.println(linkedArray.toStringColByCol());
	}	
}
