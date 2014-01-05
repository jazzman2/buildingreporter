/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import java.util.Map;

import org.hibernate.criterion.Restrictions;
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
import sk.jazzman.buildingreporter.domain.manager.SerializationManagerInf;
import sk.jazzman.buildingreporter.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.measurement.MLogInf;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;

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
	ResponseEntity<String> getRegister(@RequestBody String mlog) {

		Long retVal;

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

			retVal = putLog((MLog) mlogObject, mi);

		} else {
			retVal = null;
			logger.error("Null object!");
		}

		return new ResponseEntity<String>(//
				serialization.toByteArray(//
						new ActionParamBuilder().put("mlog.id", retVal).build()), //
				HttpStatus.CREATED);
	}

	/**
	 * Put Log
	 * 
	 * @param log
	 * @param instrument
	 * 
	 * @return id
	 */
	private Long putLog(MLog log, Instrument instrument) {
		if (log == null || instrument == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		Long id = log.getId();

		if (id == null) {
			id = MLog.getNextValSeq();
			id = id * (-1);
		} else {
			id = (id * 1000) + instrument.getId();
		}

		log.setId(id);

		log.setInstrument(instrument);
		log.persist();

		logger.info("Log: " + log.toString());

		return log.getId();
	}
}
