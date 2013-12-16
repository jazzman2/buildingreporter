/**
 * 
 */
package sk.jazzman.buildingreporter.domain.manager;

/**
 * Serialization Manager Interface
 * 
 * @author jano
 * 
 */
public interface SerializationManagerInf {

	/**
	 * Object to byte array
	 * 
	 * @param object
	 * @return
	 */
	public String toByteArray(Object object);

	/**
	 * Byte array to object
	 * 
	 * @param byteArray
	 * @return
	 */
	public <T> T toObject(String byteArray);

}
