package ex.tajti.mining;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an equivalence class. Every equivalence class belongs to an attribute set.
 * The rows that are equal on that given attribute set are the members of that class. For example,
 * if we consider the attribute set <code>X</code> then rows <code>t</code> and <code>u</code>
 * are in the EC generated by <code>X</code> iff <code>t[X] = u[X]</code>.
 * <br/>
 * In an EC we only store the row IDs. For ECs made up from only one attribute we also store the
 * value that the rows contain on that attribute.
 * <br/>
 * This implementation is not thread-safe.
 *
 * @author Akos Tajti
 * @param <T> Type of the attribute (makes sense only for single element attribute sets)
 * @param <U> Type of the row ID.
 */
public class EquivalenceClass<T, U> {
	private String attribute;

	/**
	 * All rows has this value on the attribute set.
	 */
	private T classifier;

	/**
	 * The list of row IDs.
	 */
	List<U> rows;

	U randomRowId = null;

	public EquivalenceClass(){
		this(null);
	}

	/**
	 *
	 * @param classifier The value that the rows has on the attribute that generates
	 * this equivalence class.
	 */
	public EquivalenceClass(T classifier){
		this.classifier = classifier;
		rows = new ArrayList<U>();
	}

	/**
	 * Removes all rows but the first from the class. Used only for verification.
	 */
//    public void removeAllRowsExceptTheFirst(){
//        List<U> l = Collections.singletonList(rows.get(0));
//        rows.retainAll(l);
//    }

	/**
	 * Adds a row ID to the class.
	 *
	 * @param rowId
	 */
	public void addRow(U rowId){
		if(getRandomRowId() == null) {
			randomRowId = rowId;
		}
		rows.add(rowId);
	}

	/**
	 * Checks if a row ID belongs to this equivalence class.
	 *
	 * @param rowId
	 * @return
	 */
	public Boolean contains(U rowId){
		return getRows().contains(rowId);
	}

	/**
	 * Returns the value that the rows has on the attribute that generated this class.
	 *
	 * @return
	 */
	public T getClassifier(){
		return classifier;
	}

	/**
	 * Returns the attribute set that generated this equivalence class. If the set contains
	 * multiple attributes then they are separated by <code>:</code>.
	 *
	 * @return
	 */
	public String getAttribute(){
		return attribute;
	}

	/**
	 * Retruns the number of rows belonging to this class.
	 *
	 * @return
	 */
	public Integer getSize(){
		return rows.size();
	}

	@Override
	public String toString(){
		return "[" + classifier + ": " + getRows() + "]";
	}

	/**
	 * Returns the list of row IDs contained in the equivalence class.
	 *
	 * @return the rows
	 */
	public List<U> getRows() {
		return rows;
	}

	/**
	 * Returns a randomly chosen row ID from the equivalence class.
	 *
	 * @return
	 */
	U getRandomRowId() {
		return randomRowId;
	}

	/**
	 * Adds a list of row IDs to the equivalence class.
	 *
	 * @param ids
	 */
	public void addRows(List<U> ids){
		rows.addAll(ids);
	}

}
