/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sk.jazzman.buildingreporter.domain.manager.SerializationManagerInf;
import sk.jazzman.buildingreporter.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.measurement.MLogInf;

/**
 * {@link MLog} controller
 * 
 * @author jkovalci
 * 
 */
@Controller
@RequestMapping("/mlog")
public class LogController {

	@Autowired
	private SerializationManagerInf serialization;

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@RequestMapping(value = "/put", method = RequestMethod.PUT)
	public @ResponseBody
	String getRegister(@RequestBody String mlog) {

		if (mlog != null) {
			logger.debug("Name " + mlog);

			Map<String, Object> params = serialization.toObject(mlog);

			MLogInf mlogObject = (MLogInf) params.get("mlog");
			String miName = (String) params.get("mi.name");

			logger.debug("Mlog Object: " + mlogObject.getValueMeasured());

		} else {
			logger.error("Null object!");
		}

		return "registered";
	}
}
