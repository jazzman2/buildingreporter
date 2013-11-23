/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jano
 * 
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String getRegister(@PathVariable String name) {

		logger.debug("Name " + name);

		return "registered";
	}

}
