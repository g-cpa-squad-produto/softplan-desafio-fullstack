package br.com.danilopaixao.ws.core;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Collection;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreInfo {

	public void info() {
		final Logger logger = LoggerFactory.getLogger(getClass());
		final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		
		logger.info("###################################");
		logger.info("### JVM PARAMETERS & MORE INFOS ###");

		this.format(runtimeMxBean.getInputArguments(), "### ARG")
				.forEach(logger::info);

		logger.info("###################################");
	}
	
	private Stream<String> format(Collection<String> list, String term) {
		return list.stream().map(s-> term + " " + s);
	}
	
}