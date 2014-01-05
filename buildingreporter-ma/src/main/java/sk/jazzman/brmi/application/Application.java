/**
 * 
 */
package sk.jazzman.brmi.application;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.arduino.ArduinoThread;
import sk.jazzman.brmi.domain.measurement.MLog;
import sk.jazzman.brmi.domain.measurement.MLogNotSend;
import sk.jazzman.brmi.server.ws.WSServerActionHandler;
import sk.jazzman.brmi.server.ws.action.SendMLog;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;
import sk.jazzman.buildingreporter.domain.utils.ActionParamGetter;

/**
 * @author jano
 * 
 */
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	private static final Logger getLogger() {
		return log;
	}

	public static void main(String[] args) {
		new Application();
	}

	/**
	 * {@link Constructor}
	 */
	public Application() {
		Sandbox sandbox = new Sandbox();

		try {
			sandbox.init();
		} catch (Exception ex) {
			getLogger().error("Error during init application: ", ex);
			return;
		}

		handleMLogsNotSend(sandbox);

		ServerConnectionThread st = new ServerConnectionThread(sandbox);
		ArduinoThread at = new ArduinoThread(sandbox);

		st.start();
		at.start();
	}

	/**
	 * Handle not send logs
	 * 
	 * @param sandbox
	 */
	private void handleMLogsNotSend(SandboxInf sandbox) {
		if (((WSServerActionHandler) sandbox.getServerActionHandler()).isInitialized()) {
			sendMLogs(sandbox);
		}
	}

	/**
	 * Send not sent mlogs to web server
	 * 
	 * @param sandbox
	 */
	private void sendMLogs(SandboxInf sandbox) {
		List<MLogNotSend> notSend;
		try {
			Map<String, Object> params = sandbox.getJPAActionHandler().perform("GET-mlog_not_send", new ActionParamBuilder().build());

			notSend = ActionParamGetter.get("value", List.class, params);
		} catch (Exception e) {
			notSend = null;
			getLogger().error("Could not to perform GET-mlog_not_send!", e);
		}

		if (CollectionUtils.isNotEmpty(notSend)) {
			for (MLogNotSend ns : notSend) {
				Long mlogws = sendMLog(ns.getMlog(), sandbox);

				if (mlogws != null) {
					try {
						sandbox.getJPAActionHandler().perform("DELETE-mlog_not_send", new ActionParamBuilder().put("value", ns).build());
					} catch (Exception e) {
						getLogger().error("Could not to perform DELETE-mlog_not_send!", e);
					}
				}
			}
		}

	}

	/**
	 * Send mlog to web server
	 * 
	 * @param mlog
	 * @param sandbox
	 */
	private Long sendMLog(Long mlogId, SandboxInf sandbox) {

		Long retVal;
		MLog mlog;

		Map<String, Object> params;

		try {
			// load mlog
			params = sandbox.getJPAActionHandler().perform("GET-mlog", new ActionParamBuilder().put("mlog.id", mlogId).build());
		} catch (Exception e) {
			params = null;
			getLogger().error("Could not to perform GET-mlog! (id=" + mlogId + ")");
		}

		if (params != null) {
			mlog = ActionParamGetter.get("value", MLog.class, params);

			try {
				Map<String, Object> data = sandbox.getServerActionHandler().perform(SendMLog.getName(), new ActionParamBuilder().put("mlog", mlog).build());

				retVal = (Long) ActionParamGetter.get("mlog.id", data);
			} catch (Exception e) {
				retVal = null;
				getLogger().error("Could not to perform SEND-mlog! (id=" + mlogId + ")");
			}
		} else {
			retVal = null;
		}

		return retVal;
	}
}
