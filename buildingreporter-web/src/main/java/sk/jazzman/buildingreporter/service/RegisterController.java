/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sk.jazzman.buildingreporter.domain.instrument.Instrument;
import sk.jazzman.buildingreporter.domain.manager.SerializationManagerInf;

/**
 * Register Measure Instrument Controller
 * 
 * @author jano
 * 
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	SerializationManagerInf serialization;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	String getRegister(@RequestBody String configuration) {

		if (configuration != null) {
			logger.debug("Name " + configuration);

			try {
				XMLConfiguration miConfig = serialization.toObject(configuration);

				miConfig.setExpressionEngine(new XPathExpressionEngine());

				Instrument.register(miConfig);
			} catch (Exception e) {
				logger.error("Could not to create masure instrment configuration!", e);
			}

		} else {
			logger.error("Null object!");
		}

		return "registered";
	}
}
