package hw05;
/**
 * 
 * This method hold values of node, and its connection 
 * 
 * @author dtnng, 402005276, 2013-07
 *
 * @param <E> 			 A Generic value
 */
public class Array2DNode<E> {
	/**This {@code E} is a generic value */
	protected E item;
	/**This {@code Array2DNode<E>} is a value of it next column connection */
	protected Array2DNode<E> nextCol;
	/**This {@code Array2DNode<E>} is a value of it next row connection */
	protected Array2DNode<E> nextRow;
	/** Initializes the Array2DNode Object using the given parameter values*/
	public Array2DNode(E item)
	{
		this.item = item;
		this.nextCol = null;
		this.nextRow = null;
	}
	
	public void setItem(E value)
	{
		this.item = value;
	}
}
