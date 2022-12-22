package hw05;

import java.util.ArrayList;
/** 
 * This class will be a linked list holding all items together with various of
 * methods to perform
 * 
 * @param<E>			A Generic values
 * 
 * @author dtnng, 402005276, 2013-07
 * */

public class Array2D<E> {
	private int rowSize;
	/**This {@code int} is a number of column*/
	private int colSize;
	/**This {@code Array2DNode<E>} will holding the head reference of a 
	 * linked list */
	protected Array2DNode<E> head;
	/**This {@code Array2DNode<E>} will holding the row tail reference of a 
	 * linked list */
	protected Array2DNode<E> rowTail;
	/**This {@code Array2DNode<E>} will holding the column tail reference of a 
	 * linked list */
	protected Array2DNode<E> colTail;
	
	/** Initializes the Array2D Linked List Object with default values*/
	public Array2D()
	{
		this.head = this.rowTail = this.colTail = null;
		this.colSize = this.rowSize = 0;
	}
	/** Initializes the Array2D Linked List Object with values derived from a 
	 * 2D Array */
	public Array2D(E[][] values)
	{
		/** Check 2D Array if null*/
		if (values == null || values.length == 0 || values[0].length == 0)
		{
			throw new IllegalArgumentException("Error");
		}
		
		/** These 2 for loop initiate the first row, first column and assign it
		 * reference including head, cotail, rowtail */
		for(int i = 0; i< values[0].length; i++) 
		{
			Array2DNode<E> temp = new Array2DNode<>(values[0][i]);
			if(i == 0)
			{
				this.head = this.colTail = temp;
				this.head.nextCol = this.colTail.nextCol = null;
			}
			else
			{
				this.colTail.nextCol = temp;
				this.colTail = temp;
				temp.nextCol = null;
			}
		}		
		for(int i = 0; i < values.length; i++) 
		{
			Array2DNode<E> temp = new Array2DNode<>(values[i][0]);
			if(i == 0)
			{
				this.rowTail = this.head;
				this.colTail.nextRow = null;
			}
			else
			{
				this.rowTail.nextRow = temp;
				this.rowTail = temp;
				temp.nextRow = null;
			}
		}
		
		/** These value store the reference of top, and left of the value will 
		 * be added so it will be connected */
		Array2DNode<E> previous_Col = this.head.nextCol;
		Array2DNode<E> previous_Row = this.head.nextRow;
		
		/** This for loop will added the remaining 2D array into linked list, 
		 * row by row order.*/
		for(int i=1; i< values.length; i++) 	
		{	
			/**  These two values will be be the move long the the values will 
			 * be added, and reset after done with a row, and move to next row*/
			Array2DNode<E> current_Col = previous_Col;
			Array2DNode<E> current_Row = previous_Row;
			
            for(int j=1; j< values[i].length; j++) 
            {
            	/** values reference of a 2D array to be added*/
            	Array2DNode<E> temp = new Array2DNode<>(values[i][j]);
            	/** Get to coltail, rowtail to be corrected assign*/
            	if(j == values[i].length - 1 && i == 1)
            	{
            		this.colTail.nextRow = temp;
            	}
            	else if(j == 1 && i == values.length - 2)
            	{
            		this.rowTail.nextCol = temp;
            	}
            	
            	current_Col.nextRow = temp;
            	current_Col = current_Col.nextCol;
            	
            	current_Row.nextCol = temp;
            	current_Row = temp;
            	temp.nextCol = null;
            	temp.nextRow = null;
            }
            
            /** Move to next row after done with a row*/
            previous_Col = previous_Col.nextRow;
            previous_Row = previous_Row.nextRow;

		}
		
		/** Assign the size of column and row */
		this.rowSize = values.length;
		this.colSize = values[0].length;
	}

	/**
	 * This method will add a first column to the linked list
	 * 
	 * @param values				the list of values to be added
	 */
	public void addFirstCol(E ... values)
	{
		/** Check if a column valid to be added*/
		if(values.length != this.rowSize && this.rowSize != 0)
		{
			throw new IllegalArgumentException("Out of Range: Row Size");
		}
		/** There will be 2 scenario, if empty and if not empty
		 * Empty: It will first assign head, colTail, rowTail to the first value,
		 * Then move the rowTail downward as we add more values in a column
		 * 
		 * Not Empty: tempCurrent_Col will hold the reference the current column,
		 * tempCurrent_Row will hold the reference for a column to be added*/
		if(this.isEmpty())
		{
			this.colSize++;
			for(int n = 0; n < values.length; n++)
			{
				Array2DNode<E> temp = new Array2DNode<>(values[n]);
				if(n == 0)
				{
					this.head = this.colTail = this.rowTail= temp;
					temp.nextCol = null;
					temp.nextRow = null;
					this.rowSize++;
				}
				else
				{	
					this.rowTail.nextRow = temp;
					this.rowTail = temp;
					this.rowTail.nextCol = null;
					this.rowTail.nextRow = null;
					this.rowSize++;
				}
			}
		}
		else
		{
			this.colSize++;
			Array2DNode<E> tempCurrent_Row = null;
			Array2DNode<E> tempCurrent_Col = null;
			for(int n = 0; n < values.length; n++)
			{
				Array2DNode<E> temp = new Array2DNode<>(values[n]);
				if(n == 0)
				{
					/** get the reference for a head */
					tempCurrent_Col = this.head;
					/** connect the nextCol value to a head */
					temp.nextCol = this.head;
					/** Let the head be the new value*/
					this.head = temp;
					/** Get another reference represent the head*/
					tempCurrent_Row = this.head;
					/** connect the nextRow value to null*/
					tempCurrent_Row.nextRow = null;
				}
				else if(n == values.length - 1)
				{
					/** Get a reference for a rowTail*/
					tempCurrent_Col = this.rowTail;	
					/** Connect the previous value to a new value*/
					tempCurrent_Row.nextRow = temp;
					/** Set a reference now equal to the new value as well*/
					tempCurrent_Row = temp;
					/** Connect new value to the the right column*/
					temp.nextCol = tempCurrent_Col;
					/** Set the rowTail to a new value*/
					this.rowTail = temp;
					/** Connect null on the nextRow*/
					temp.nextRow = null;
				}
				else
				{
					/** Get the reference move to the next row */
					tempCurrent_Col = tempCurrent_Col.nextRow;
					/** Connect the previous value to a new value*/
					tempCurrent_Row.nextRow = temp;
					/** Set a reference now equal to the new value as well*/
					tempCurrent_Row = temp;
					/** Connect new value to the the right column*/
					temp.nextCol = tempCurrent_Col;
					/** Connect the new value to null in the next row*/
					temp.nextRow = null;
				}
			}
		}		
	}
	/**
	 * This method will add a first row to the linked list
	 * 
	 * @param values				the list of values to be added
	 */
	public void addFirstRow(E ... values)
	{
		/** Check if a row valid to be added*/
		if(values.length != this.colSize && this.colSize != 0)
		{
			throw new IllegalArgumentException("Out of Range: Column Size");
		}
		/** There will be 2 scenario, if empty and if not empty
		 * Empty: It will first assign head, colTail, rowTail to the first value,
		 * Then move the rowTail downward as we add more values in a column
		 * 
		 * Not Empty: tempCurrent_Col will hold the reference the added row,
		 * tempCurrent_Row will hold the reference of a current row */
		if(this.isEmpty())
		{
			this.rowSize++;
			for(int n = 0; n < values.length; n++)
			{
				Array2DNode<E> temp = new Array2DNode<>(values[n]);
				if(n == 0)
				{
					this.head = this.colTail = this.rowTail= temp;
					temp.nextCol = null;
					temp.nextRow = null;
					this.colSize++;
				}
				else
				{	
					this.colTail.nextCol = temp;
					this.colTail = temp;
					this.colTail.nextCol = null;
					this.colTail.nextRow = null;
					this.colSize++;
				}
			}
		}
		else
		{
			this.rowSize++;
			Array2DNode<E> tempCurrent_Row = null;
			Array2DNode<E> tempCurrent_Col = null;
			for(int n = 0; n < values.length; n++)
			{
				Array2DNode<E> temp = new Array2DNode<>(values[n]);
				if(n == 0)
				{
					/** Get the reference of a head*/
					tempCurrent_Row = this.head;
					/** Connect a new value to a head*/
					temp.nextRow = this.head;
					/** Let the head equal to new value*/
					this.head = temp;
					/** Get another reference for a new head*/
					tempCurrent_Col = this.head;
					/** Connect the new value to null on the right*/
					tempCurrent_Col.nextCol = null;
				}
				else if(n == values.length - 1)
				{
					/** Set the reference for colTail*/
					tempCurrent_Row = this.colTail;
					/** Connect the previous reference to a new value */
					tempCurrent_Col.nextCol = temp;
					/** Assign that reference to a new value as well*/
					tempCurrent_Col = temp;
					/** Connect the new value to a bottom reference*/
					temp.nextRow = tempCurrent_Row;
					/** Set the colTail to a new value*/
					this.colTail = temp;
					/** Connect the value to null on the right*/
					temp.nextCol = null;
				}
				else
				{
					/** Proceed the reference to a next column*/
					tempCurrent_Row = tempCurrent_Row.nextCol;
					/** Connect the previous reference to a new value*/
					tempCurrent_Col.nextCol = temp;
					/** Assign that reference to a new value as well*/
					tempCurrent_Col = temp;
					/** Connect the new value to a bottom reference*/
					temp.nextRow = tempCurrent_Row;
					/** Connect the value to null on the right*/
					temp.nextCol = null;
				}
			}
		}
	}	
	
	/**
	 * This method will add a last column to the linked list
	 * 
	 * @param values				the list of values to be added	
	 */
	public void addLastCol(E ... values)
	{		
		/** Check if a column valid to be added*/
		if(values.length != this.rowSize && this.rowSize != 0)
		{
			throw new IllegalArgumentException("Out of Range: Row Size");
		}
		/** If Empty, it will be the same as added first column*/		
		if(this.isEmpty())
		{
			this.addFirstCol(values);
		}
		else
		{
			this.colSize++;
			Array2DNode<E> tempCurrent_Row = null; 
			Array2DNode<E> tempCurrent_Col = null;
			for(int n = 0; n < values.length; n++)
			{
				Array2DNode<E> temp = new Array2DNode<>(values[n]);
				if(n == 0)
				{
					/** Set a reference for a colTail*/
					tempCurrent_Col = this.colTail;
					/** Connect colTail to the new value to the right*/
					this.colTail.nextCol = temp;
					/** Set the colTail to a new value as well*/
					this.colTail = temp;				
					/** Set a reference for a new coTail */
					tempCurrent_Row = this.colTail;
					/** Connect this reference to null on both directions*/
					tempCurrent_Row.nextRow = null;
					tempCurrent_Row.nextCol = null;
				}
				else
				{
					/** Reference proceed to a next row*/
					tempCurrent_Col = tempCurrent_Col.nextRow;
					/** Connect previous reference to a new value*/
					tempCurrent_Row.nextRow = temp;
					/** Set the reference equal to the value*/
					tempCurrent_Row = temp;
					/** Connect the reference to the value on the previous
					 *  column */
					tempCurrent_Col.nextCol = temp;
					/** Connect this value to null on both directions */
					temp.nextRow = null;
					temp.nextCol = null;
				}
			}
		}		
	}
	/**
	 * This method will add a last row to the linked list
	 * 
	 * @param values					the list of values to be added
	 */
	
	public void addLastRow(E ... values)
	{
		/** Check if a row valid to be added*/
		if(values.length != this.colSize && this.colSize != 0)
		{
			throw new IllegalArgumentException("Out of Range: Column Size");
		}
		/** If Empty, it will be the same as added first row*/
		if(this.isEmpty())
		{
			this.addFirstRow(values);
		}
		else
		{
			this.rowSize++;
			Array2DNode<E> tempCurrent_Row = null;
			Array2DNode<E> tempCurrent_Col = null;
			for(int n = 0; n < values.length; n++)
			{
				Array2DNode<E> temp = new Array2DNode<>(values[n]);
				if(n == 0)
				{
					/** Get the reference for a rowTail*/
					tempCurrent_Row = this.rowTail;
					/** Connect a rowTail to a new value*/
					this.rowTail.nextRow = temp;
					/** Set a rowTail to a new value*/
					this.rowTail = temp;
					/** Get a reference for a new rowTail position*/
					tempCurrent_Col = this.rowTail;
					/** Connect this reference to null on both directions*/
					tempCurrent_Col.nextCol = null;
					tempCurrent_Col.nextRow = null;
				}
				else
				{
					/** Proceed the reference to a next column */
					tempCurrent_Row = tempCurrent_Row.nextCol;
					/** Connect the previous reference to a new value*/
					tempCurrent_Col.nextCol = temp;
					/** Set a reference to a new value*/
					tempCurrent_Col = temp;
					/** Connect the reference to a new value from the top*/
					tempCurrent_Row.nextRow = temp;
					/** Connect the new values to null on both directions*/
					temp.nextRow = null;
					temp.nextCol = null;
				}
			}
		}		
	}
	/**
	 * This method will insert a column to any column index
	 * 
	 * @param index					The index of column to be added
	 * @param values				The values to be added in the column
	 */
	public void insertCol(int index, E ... values)
	{
		/** Check if column is valid to be added*/
		if(values.length != this.rowSize && this.rowSize != 0)
		{
			throw new IllegalArgumentException("Out of Range: Row Size");
		}
		/** If Empty or index position is 0, it will be the same as addFirstCol*/
		if(this.isEmpty() || index == 0)
		{
			this.addFirstCol(values);
		}
		/** If the index position is at last column position, it will be same 
		 * as addLastCol*/
		else if(index == this.colSize - 1)
		{
			this.addLastCol(values);
		}
		else
		{
			this.colSize++;
			Array2DNode<E> tempCurrent_Row = null; 
			Array2DNode<E> tempCurrent_Col = this.head;
			Array2DNode<E> temp = new Array2DNode<>(values[0]);

			/** Move the reference to a first value of a column just one index 
			 * behind*/
			int currentIndex = 0;
			while(currentIndex != (index - 1)) {
				tempCurrent_Col = tempCurrent_Col.nextCol;
				currentIndex++;
			}
			/** Connect the first value to a node on that index position*/
			temp.nextCol = tempCurrent_Col.nextCol;
			/** Connect the previous reference to a new value*/
			tempCurrent_Col.nextCol = temp;
			/** Set a reference for this value*/
			tempCurrent_Row = temp;
			/** Set the new value connect to null on the bottom*/
			temp.nextRow = null;
			
			for(int n = 1; n < values.length; n++)
			{
				/** Get the value on the next node*/
				temp = new Array2DNode<>(values[n]);
				
				/** Connect the previous added value to a new value*/
				tempCurrent_Row.nextRow = temp;
				/** Set the reference to a new value*/
				tempCurrent_Row = temp;
				/** Proceed the left reference to a next row*/
				tempCurrent_Col = tempCurrent_Col.nextRow;	
				/** Connect the new value to a value of index position */
				temp.nextCol = tempCurrent_Col.nextCol;
				/** Connect the left reference to a new value*/
				tempCurrent_Col.nextCol = temp;
				/** Connect the new value to null on the bottom*/
				temp.nextRow = null;
			}				
		}		
	}
	
	/**
	 * This method will add a row to any row index
	 * 
	 * @param index					The index of row to be added
	 * @param values				The values to be added in a row
	 */
	public void insertRow(int index, E ... values)
	{
		/** Check if a row is valid to be added*/
		if(values.length != this.colSize && this.colSize != 0)
		{
			throw new IllegalArgumentException("Out of Range: Column Size");
		}
		
		/** If Empty or the index at 0, it will be the same as addFirstRow*/
		if(this.isEmpty() || index == 0)
		{
			this.addFirstRow(values);
		}
		/** If the index at last row, it will be the same as addLastRow*/
		else if(index == this.rowSize - 1)
		{
			this.addLastRow(values);
		}
		else
		{
			this.rowSize++;
			Array2DNode<E> tempCurrent_Row = this.head;
			Array2DNode<E> tempCurrent_Col = null;
			Array2DNode<E> temp = new Array2DNode<>(values[0]);

			int currentIndex = 0;
			/** Move the reference to a first value of a row just one index 
			 * behind*/
			while(currentIndex != (index - 1)) {
				tempCurrent_Row = tempCurrent_Row.nextRow;
				currentIndex++;
			}
			/** Connect the first value to a node on that index position*/
			temp.nextRow = tempCurrent_Row.nextRow;
			/** Connect the previous reference node to a new value */
			tempCurrent_Row.nextRow = temp;
			/** Set the reference to a new value as well*/
			tempCurrent_Col = temp;
			/** Connect a value to null on the right side*/
			temp.nextCol = null;
			
			for(int n = 1; n < values.length; n++)
			{
				/** Get the value on the next node*/
				temp = new Array2DNode<>(values[n]);
				
				/** Connect the previous added value to a new value*/
				tempCurrent_Col.nextCol = temp;
				/** Set the reference fo a new value*/
				tempCurrent_Col = temp;
				/** Proceed the up reference to a next column*/
				tempCurrent_Row = tempCurrent_Row.nextCol;	
				/** Connect the new value to a index position node*/
				temp.nextRow = tempCurrent_Row.nextRow;
				/** Connect the previous node to a new value*/
				tempCurrent_Row.nextRow = temp;
				/** Connect the value to Null on the right side*/
				temp.nextCol = null;
			}				
		}
	}
	
	/**
	 * This method will delete a first column in the linked list
	 */
	public void deleteFirstCol()
	{
		/** Check if it Empty*/
		if(this.isEmpty())
			throw new IllegalStateException("Empty!");
				
		Array2DNode<E> tempCurrent_Col = null;
		
		for(int n = 0; n < rowSize; n++)
		{
			/** If there is only one column, get the reference equal to next 
			 * column every step and set head,rowTail,coTail equal null*/
			if(this.colSize == 1)
			{				
				if(n == 0)
				{
					tempCurrent_Col = this.head.nextRow;
					this.head = this.colTail = null;
				}	
				else if(n == rowSize - 1)
				{
					tempCurrent_Col = tempCurrent_Col.nextRow;
					this.rowTail = null;
				}
				else
				{
					tempCurrent_Col = tempCurrent_Col.nextRow;
				}
			}
			/** Get the head,rowTail reference equal to the nextCol in line 
			 */
			else
			{
				if(n == 0)
				{
					tempCurrent_Col = this.head.nextRow;
					this.head = this.head.nextCol;
				}	
				else if(n == rowSize - 1)
				{
					this.rowTail = this.rowTail.nextCol;
					tempCurrent_Col = tempCurrent_Col.nextRow;
				}
				else
				{
					tempCurrent_Col = tempCurrent_Col.nextRow;
				}
			}			
		}
		this.colSize--;		
	}
	
	/**
	 * This method will delete a first row in the linked list
	 */
	public void deleteFirstRow()
	{
		/** Check if Empty*/
		if(this.isEmpty())
			throw new IllegalStateException("Empty!");
				
		Array2DNode<E> tempCurrent_Row = null;
		
		for(int n = 0; n < colSize; n++)
		{
			/** If there is only one row, get the reference equal to next 
			 * row every step and set head,rowTail,coTail equal null*/
			if(this.rowSize == 1)
			{				
				if(n == 0)
				{
					tempCurrent_Row = this.head.nextCol;
					this.head = this.rowTail = null;
				}	
				else if(n == colSize - 1)
				{
					tempCurrent_Row = tempCurrent_Row.nextCol;
					this.colTail = null;
				}
				else
				{
					tempCurrent_Row = tempCurrent_Row.nextCol;
				}
			}
			/** Get the head,colTail reference equal to the nextRow in line */
			else
			{
				if(n == 0)
				{
					tempCurrent_Row = this.head.nextCol;
					this.head = this.head.nextRow;
				}	
				else if(n == colSize - 1)
				{
					this.colTail = this.colTail.nextRow;
					tempCurrent_Row = tempCurrent_Row.nextCol;
				}
				else
				{
					tempCurrent_Row = tempCurrent_Row.nextCol;
				}
			}			
		}
		this.rowSize--;		
	}
	/**
	 * This method will delete a last column in the linked list
	 */
	public void deleteLastCol()
	{
		/** Check if Empty*/
		if(this.isEmpty())
			throw new IllegalStateException("Empty!");
				
		Array2DNode<E> tempCurrent_Col = this.head;
		Array2DNode<E> tempPrevios_Col = this.head;
		/** Proceed the reference just one node behind the last column*/
		while(tempCurrent_Col.nextCol != null) {
			tempPrevios_Col = tempCurrent_Col;
			tempCurrent_Col = tempCurrent_Col.nextCol;
		}
		/** Set the colTail one node behind, as well as connect all the node
		 * from that column to null */		
		for(int n = 0; n < rowSize; n++)
		{
			if(this.colSize == 1)
			{				
				this.deleteFirstCol();
			}
			else
			{
				if(n == 0)
				{					
					this.colTail = tempPrevios_Col;
					this.colTail.nextCol = null;
				}	
				else
				{
					tempPrevios_Col = tempPrevios_Col.nextRow;
					tempPrevios_Col.nextCol = null;
				}
			}			
		}
		this.colSize--;
		
	}
	/**
	 * This method will delete a last row in the linked list
	 */
	public void deleteLastRow()
	{
		/** Check if Empty*/
		if(this.isEmpty())
			throw new IllegalStateException("Empty!");
				
		Array2DNode<E> tempCurrent_Row = this.head;
		Array2DNode<E> tempPrevios_Row = this.head;
		/** Proceed the reference just one node behind the last row*/
		while(tempCurrent_Row.nextRow != null) {
			tempPrevios_Row = tempCurrent_Row;
			tempCurrent_Row = tempCurrent_Row.nextRow;
		}
		/** Set the rowTail one node top, as well as connect all the node
		 * from that row to null */				
		for(int n = 0; n < colSize; n++)
		{
			if(this.rowSize == 1)
			{				
				this.deleteFirstRow();
			}
			else
			{
				if(n == 0)
				{					
					this.rowTail = tempPrevios_Row;
					this.rowTail.nextRow = null;
				}	
				else
				{
					tempPrevios_Row = tempPrevios_Row.nextCol;
					tempPrevios_Row.nextRow = null;
				}
			}			
		}
		this.rowSize--;
		
	}
	/**
	 * This method will delete a whole column at a specific index
	 * 
	 * @param index						The index of column to be deleted
	 */

	public void deleteCol(int index)
	{
		/** Check if Empty*/
		if(this.isEmpty())
			throw new IllegalStateException("Empty!");
		/** Check if the index is out of bounds*/
		if(index < 0 || index >= this.colSize) 
			throw new IndexOutOfBoundsException("Index out of bounds");
		
		/** If the index is 0, it will be the same as deleteFirstCol*/
		if(index == 0) {
			this.deleteFirstCol();
		}
		/** if the index is at last column index, it will be the same as 
		 * deleteLastCol */
		else if (index == this.colSize - 1) {
			this.deleteLastCol();
		}
		else
		{
			Array2DNode<E> tempCurrent_Col = this.head;
			int currentIndex = 0;
			/** Proceed the reference just one node behind the index column*/
			while(currentIndex < index - 1) 
			{
				tempCurrent_Col = tempCurrent_Col.nextCol;
				currentIndex++;
			}
			/** Set the connection of that reference column equal to the 
			 * next column two times, it will skip the index position and 
			 * eventually remove it,then proceed to next row and do the same */
			for(int n = 0; n < this.rowSize(); n++)
			{
				tempCurrent_Col.nextCol = tempCurrent_Col.nextCol.nextCol;
				tempCurrent_Col = tempCurrent_Col.nextRow;
			}
			this.colSize--;
				
		}		
	}
	
	/**
	 * This method will delete a whole row of any specific index
	 * 
	 * @param index						The index of row to be deleted
	 */
	public void deleteRow(int index)
	{
		/** Check if Empty*/
		if(this.isEmpty())
			throw new IllegalStateException("Empty!");
		/** Check if the index is out of bounds*/
		if(index < 0 || index >= this.rowSize) 
			throw new IndexOutOfBoundsException("Index out of bounds");
		/** If the index is 0, it will be the same as deleteFirstRow*/
		if(index == 0) {
			this.deleteFirstRow();
		}
		/** if the index is at last row index, it will be the same as 
		 * deleteLastRow */
		else if (index == this.rowSize - 1) {
			this.deleteLastRow();
		}
		else
		{
			Array2DNode<E> tempCurrent_Row = this.head;
			int currentIndex = 0;
			/** Proceed the reference just one node behind the index row*/
			while(currentIndex < index - 1) 
			{
				tempCurrent_Row = tempCurrent_Row.nextRow;
				currentIndex++;
			}
			/** Set the connection of that reference row equal to the 
			 * next row two times, it will skip the index position and 
			 * eventually remove it,then proceed to next column and do the same 
			 * */
			for(int n = 0; n < this.colSize(); n++)
			{
				tempCurrent_Row.nextRow = tempCurrent_Row.nextRow.nextRow;
				tempCurrent_Row = tempCurrent_Row.nextCol;
			}
			this.rowSize--;		
		}
	}
	
	/**
	 * This method will return a value at specific location in linked list
	 * 
	 * @param rowIndex					The index of a row location
	 * @param colIndex					The index of a column location
	 * @return							Return the value at the position
	 */	
	public E get(int rowIndex, int colIndex)
	{	
		/** Check if Out of bound*/
		if(rowIndex >= this.rowSize() || colIndex >= this.colSize())
		{
			throw new IndexOutOfBoundsException("Out of Bound");
		}
		Array2DNode<E> current = this.head;
		int currentIndex = 0;
		/** It will move the reference step by step from column, then to each 
		 * row until it at the desired index, and return the value*/
		while(currentIndex != colIndex)
		{
			current = current.nextCol;
			currentIndex++;
		}
		currentIndex = 0;
		while(currentIndex != rowIndex)
		{
			current = current.nextRow;
			currentIndex++;
		}
		return current.item;
	}
	/**
	 * The method will return an arraylist of all values in a column
	 * 
	 * @param colIndex					The index location of a column
	 * @return							Return all values from that position
	 */
	public ArrayList<E> getCol(int colIndex)
	{
		/** Check if Out of Bound*/
		if(colIndex >= this.colSize)
			throw new IndexOutOfBoundsException("Out of Bound");
		/** Create a temporary arrayList*/
		ArrayList<E> linkedList = new ArrayList<E>();
		Array2DNode<E> current_Col = this.head;
		int currentIndex = 0;
		/** Proceed the reference to the colIndex position*/
		while(currentIndex != colIndex)
		{
			current_Col = current_Col.nextCol;
			currentIndex++;
		}
		/** Using for loop to add the values to array*/
		for(int n = 0; n < this.rowSize;n++)
		{
			linkedList.add(current_Col.item);
			current_Col = current_Col.nextRow;
		}
		/** return the arrayList*/
		return linkedList;
	}
	/**
	 * The method will return an arraylist of all values in a row
	 * 
	 * @param rowIndex 					The index location of a row
	 * @return							Return all values from that position
	 */
	public ArrayList<E> getRow(int rowIndex)
	{
		/** Check if Out of Bound*/
		if(rowIndex >= this.rowSize)
			throw new IndexOutOfBoundsException("Out of Bound");
		/** Create a temporary arrayList*/
		ArrayList<E> linkedList = new ArrayList<E>();
		Array2DNode<E> current_Row = this.head;
		int currentIndex = 0;
		/** Proceed the reference to the rowIndex position*/
		while(currentIndex != rowIndex)
		{
			current_Row = current_Row.nextRow;
			currentIndex++;
		}
		/** Using for loop to add the values to array*/
		for(int n = 0; n < this.colSize;n++)
		{
			linkedList.add(current_Row.item);
			current_Row = current_Row.nextCol;
		}	
		/** return the arrayList*/
		return linkedList;
	}
	/**
	 * This method will replace a value from a linked list with a specific value
	 * 
	 * @param rowIndex						The index location of a row
	 * @param colIndex						The index location of a column
	 * @param item							The value to be replace
	 */
	public void set(int rowIndex, int colIndex, E item)
	{
		/** Check if Out of Bound*/
		if(rowIndex >= this.rowSize || colIndex >= this.colSize)
			throw new IndexOutOfBoundsException("Out of Bound");
		
		Array2DNode<E> current_Row = this.head;
		Array2DNode<E> current_Col = this.head;
		
		Array2DNode<E> temp = new Array2DNode<>(item); 
		int currentIndex = 0;
		/** There will be 3 scenarios to get the references at correct positions
		 * 
		 * If at the first row */
		if(rowIndex == 0 && colIndex != 0)
		{
			/** Proceed current_Col reference just one node left behind the
			 *  intended position */
			while(currentIndex != colIndex - 1)
			{
				current_Col = current_Col.nextCol;
				currentIndex++;
			}
			currentIndex = 0;
			/** Proceed current_Row reference to one row after the intended 
			 * position*/
			while(currentIndex != rowIndex + 1)
			{
				current_Row = current_Row.nextRow;
				currentIndex++;
			}
			currentIndex = 0;
			/** Continue proceed current_Row reference to one column at the 
			 * intended position*/
			while(currentIndex != colIndex)
			{
				current_Row = current_Row.nextCol;
				currentIndex++;
			}
			/** After this, we have one reference on the left, one reference on
			 * the bottom at the intended position*/

		}
		/** If at the first column */
		else if(colIndex == 0 && rowIndex != 0)
		{
			/** Proceed current_Col reference just one node on top of the
			 *  intended position */
			while(currentIndex != rowIndex - 1)
			{
				current_Col = current_Col.nextRow;
				currentIndex++;
			}
			currentIndex = 0;
			/** Proceed current_Row reference to one row at the intended 
			 * position*/
			while(currentIndex != rowIndex)
			{
				current_Row = current_Row.nextRow;
				currentIndex++;
			}
			currentIndex = 0;
			/** Continue proceed current_Row reference to one column to the 
			 * right of the intended position*/
			while(currentIndex != colIndex + 1)
			{
				current_Row = current_Row.nextCol;
				currentIndex++;
			}
			/** After this, we have one reference at the top, one reference on
			 * the right of intended position*/
		}
		/** Others*/
		else if(colIndex != 0 && rowIndex != 0)
		{
			/** Proceed current_Col reference to the column of the
			 *  intended position */
			while(currentIndex != colIndex)
			{
				current_Col = current_Col.nextCol;
				currentIndex++;
			}
			currentIndex = 0;
			/** Continue proceed current_Col reference to one row on top of the
			 *  intended position */
			while(currentIndex != rowIndex - 1)
			{			
				current_Col = current_Col.nextRow;
				currentIndex++;
			}
			currentIndex = 0;
			/** Proceed current_Row reference to a row at the intended 
			 * position*/
			while(currentIndex != rowIndex)
			{
				current_Row = current_Row.nextRow;
				currentIndex++;
			}
			currentIndex = 0;
			/** Continue proceed current_Row reference to one column to the 
			 * left of the intended position*/
			while(currentIndex != colIndex - 1)
			{
				current_Row = current_Row.nextCol;
				currentIndex++;
			}
			/** After this, we have one reference on top, one reference on the
			 * left of the intended position */
		}
		
		/** Replace part
		 * If the intended position at column index 0, row index 0 */
		if(rowIndex == 0 && colIndex == 0)
		{
			temp.nextCol = this.head.nextCol;
			temp.nextRow = this.head.nextRow;
			this.head = temp;
		}
		/** If the intended position at first row*/
		else if(rowIndex == 0)
		{
			/** Replace the intended position at colTail position*/
			if(colIndex == this.colSize - 1)
			{
				temp.nextRow = this.colTail.nextRow;
				temp.nextCol = current_Col.nextCol.nextCol;
				current_Col.nextCol = temp;
				this.colTail = temp;
				this.colTail.nextRow = temp.nextRow;
			}
			/** Replace intended position at other position on the first row*/
			else
			{
				temp.nextRow = current_Row;
				temp.nextCol = current_Col.nextCol.nextCol;
				current_Col.nextCol = temp;
			}
		}
		/** If the intended position at first column*/
		else if(colIndex == 0)
		{
			/** Replace the intended position at rowTail position*/
			if(rowIndex == this.rowSize - 1)
			{
				temp.nextCol = this.rowTail.nextCol;
				temp.nextRow = current_Col.nextRow.nextRow;
				current_Col.nextRow = temp;
				this.rowTail = temp;
				this.rowTail.nextCol = temp.nextCol;
			}
			/** Replace intended position at other position on the first column*/
			else
			{
				temp.nextCol = current_Row;
				temp.nextRow = current_Col.nextRow.nextRow;
				current_Col.nextRow = temp;
			}
		}
		/** Other positions */
		else
		{
			temp.nextCol = current_Row.nextCol.nextCol;
			temp.nextRow = current_Col.nextRow.nextRow;
			current_Row.nextCol = temp;
			current_Col.nextRow = temp;
		}		
	}
	
	/**
	 * This method return column size
	 * @return				Return column size
	 */
	public int colSize()
	{
		return this.colSize;
	}
	
	/**
	 * This method return row size
	 * 
	 * @return				Return row size
	 */	
	public int rowSize()
	{
		return this.rowSize;
	}
	/**
	 * This method display a values of linked list row by row
	 * 
	 * @Override			Override a toString Method
	 * @return				This method return a string display a values of 
	 * 						linked list row by row
	 */
	@Override
	public String toString()
	{
		String rowByRow = "";

		Array2DNode<E> current_Col = this.head;		
		for(int n = 0; n < this.rowSize;n++)
		{
			Array2DNode<E> temp = current_Col;
			for(int i = 0; i < this.colSize;i++)
			{
				rowByRow += temp.item + "\t";
				temp = temp.nextCol;
			}
			rowByRow += "\n";
			current_Col = current_Col.nextRow;
		}
		return rowByRow;
	}
	/**
	 * This method display a linked list column by column
	 * @return				Return a String dispaly values of linked list column
	 * 						by column
	 */
	public String toStringColByCol()
	{
		String colByCol = "";

		Array2DNode<E> current_Row = this.head;
		for(int n = 0; n < this.colSize;n++)
		{
			Array2DNode<E> temp = current_Row;
			for(int i = 0; i < this.rowSize;i++)
			{
				colByCol += temp.item + "\t";
				temp = temp.nextRow;
			}
			colByCol += "\n";
			current_Row = current_Row.nextCol;
		}
		return colByCol;
	}
	
	/**
	 * This helper method help to determined if the linked list is empty
	 * 
	 * @return						Return true if empty, false if not empty
	 */
	private boolean isEmpty()
	{
		return this.head == null && (this.colTail == null || this.rowTail == null) && (this.colSize == 0 || this.rowSize == 0);
	}
	
	/**
	 * This helper method to check if the head,coltail,rowtail reference is 
	 * correctly place in the linked list
	 */
	private void checkNode()
	{
		System.out.println("Head: " + this.head.item);
		System.out.println("Head Next Col: " + this.head.nextCol.item);
		System.out.println("Head Next Row: " + this.head.nextRow.item);
		
		System.out.println("ColTail: " + this.colTail.item);
		System.out.println("ColTail NextRow: " + this.colTail.nextRow.item);
		
		System.out.println("RowTail: " + this.rowTail.item);
		System.out.println("RowTail Next Col: " + this.rowTail.nextCol.item);
	}

}
