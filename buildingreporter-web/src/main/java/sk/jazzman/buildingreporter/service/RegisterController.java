/**
 *
 */
package sk.jazzman.buildingreporter.service;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.jazzman.buildingreporter.domain.instrument.Instrument;
import sk.jazzman.buildingreporter.domain.instrument.InstrumentInf;
import sk.jazzman.buildingreporter.domain.manager.SerializationManagerInf;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Register Measure Instrument Controller
 *
 * @author jano
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	SerializationManagerInf serialization;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public
	@ResponseBody
	ResponseEntity<String> getRegister(@RequestBody String configuration) {
		String retVal;
		
		if(configuration != null) {
			logger.debug("Name " + configuration);
			
			try {
				
				XMLConfiguration cfg = new XMLConfiguration();
				
				cfg.setExpressionEngine(new XPathExpressionEngine());
				
				cfg.read(new StringReader(configuration));
				
				InstrumentInf i = Instrument.register(cfg);
				
				if(i == null) {
					throw new IllegalStateException("Instrument not registered!");
				}
				
				Map<String, Object> response = new HashMap<String, Object>();
				response.put("instrument.id", i.getId());
				
				retVal = serialization.toByteArray(response);
			}
			catch(Exception e) {
				logger.error("Could not to create masure instrment configuration!", e);
				retVal = null;
			}
			
		}
		else {
			logger.error("Null object!");
			retVal = null;
		}
		
		return new ResponseEntity<String>(retVal, HttpStatus.CREATED);
	}
}
