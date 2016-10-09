package org.slf4j.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SPLoggerFactory implements ILoggerFactory{

	private Map<String, SPLogger> loggerMap;

    public SPLoggerFactory() {
        loggerMap = new HashMap<String, SPLogger>();
    }
	
	@Override
	public Logger getLogger(String name) {
		synchronized (loggerMap) {
            if (!loggerMap.containsKey(name)) {
                loggerMap.put(name, new SPLogger(name));
            }

            return loggerMap.get(name);
        }
	}

}
