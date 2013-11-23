/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jano
 * 
 */
@Path("register")
public class RRegister {

	private static final Logger logger = LoggerFactory.getLogger(RRegister.class);

	public RRegister() {
		getLogger().debug("Init registering controller");
	}

	private Logger getLogger() {
		return logger;
	}

	// @RequestMapping(value = "/register")
	// public @ResponseBody
	// String register(@RequestParam(value = "name", required = false) String
	// name) {
	//
	// return "si registrovany " + name;
	// }

	@GET
	@Produces("text/plain")
	public String register() {
		return "Register";
	}

}
