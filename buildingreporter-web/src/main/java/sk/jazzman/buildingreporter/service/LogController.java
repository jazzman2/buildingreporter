/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sk.jazzman.buildingreporter.domain.measurement.MLog;

/**
 * {@link MLog} controller
 * 
 * @author jkovalci
 * 
 */
@Controller
@RequestMapping("/mlog")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@RequestMapping(value = "/put", method = RequestMethod.PUT)
	public @ResponseBody
	String getRegister(@RequestBody String mlog) {

		if (mlog != null) {
			logger.debug("Name " + mlog);
		} else {
			logger.error("Null object!");
		}

		return "registered";
	}
}
