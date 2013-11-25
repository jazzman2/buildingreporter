/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	String getRegister(@RequestBody String configuration) {

		if (configuration != null) {
			logger.debug("Name " + configuration);
		} else {
			logger.error("Null object!");
		}

		return "registered";
	}
}
