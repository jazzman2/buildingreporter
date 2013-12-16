/**
 * 
 */
package sk.jazzman.brmi.common;

import sk.jazzman.buildingreporter.domain.common.DefaultRegisterAbt;

/**
 * Default Action Register
 * 
 * @author jano
 * 
 */
public abstract class DefaultActionRegisterAbt<A extends ActionInf> extends DefaultRegisterAbt<A> implements ActionRegisterInf<A> {
	// private final Map<String, A> register = new HashMap<String, A>();
	//
	// @Override
	// public void register(String name, A action) {
	// if (name == null || action == null) {
	// throw new IllegalArgumentException("Null argument!");
	// }
	//
	// if (register.containsKey(name)) {
	// throw new IllegalStateException("Action '" + name +
	// "' alredy registered!");
	// }
	//
	// register.put(name, action);
	// }
	//
	// @Override
	// public A get(String name) {
	// if (name == null) {
	// throw new IllegalArgumentException("Null argument!");
	// }
	//
	// A retVal = register.get(name);
	//
	// if (retVal == null) {
	// throw new IllegalStateException("Action '" + name + "' not registered");
	// }
	//
	// return retVal;
	// }
	//
	// /**
	// * Unregister object
	// *
	// * @param name
	// * @return
	// */
	// @Override
	// public A unregister(String name) {
	// if (name == null) {
	// throw new IllegalArgumentException("Null argument!");
	// }
	//
	// return register.remove(name);
	// }
	//
	// /**
	// * Unregister all objects
	// */
	// @Override
	// public void unregisterAll() {
	// register.clear();
	// }
}
