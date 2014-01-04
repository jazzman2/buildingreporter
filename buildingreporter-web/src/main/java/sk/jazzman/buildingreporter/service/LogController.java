/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import java.util.Map;

import org.hibernate.criterion.Restrictions;
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

			if (mlogObject == null || miName == null) {
				throw new IllegalStateException("Null value!");
			}

			Instrument mi = (Instrument) Instrument.createCriteria().add(Restrictions.eq("name", miName)).uniqueResult();

			logger.debug("Mlog Object: " + mlogObject.getValueMeasured());

			putLog((MLog) mlogObject, mi);

		} else {
			logger.error("Null object!");
		}

		return "registered";
	}

	/**
	 * Put Log
	 * 
	 * @param log
	 * @param instrument
	 */
	private void putLog(MLog log, Instrument instrument) {
		if (log == null || instrument == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		log.setInstrument(instrument);
		log.persist();
	}
}
