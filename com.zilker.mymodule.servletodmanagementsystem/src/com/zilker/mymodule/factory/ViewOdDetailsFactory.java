package com.zilker.mymodule.factory;

import java.util.logging.Logger;

import com.zilker.mymodule.interfaces.ViewOdInterface;

public class ViewOdDetailsFactory {

	public Logger logger = Logger.getLogger(ViewOdDetailsFactory.class.getName());
	
	public ViewOdInterface selectTheView (String type) {
		try {
			logger.info("com.zilker.mymodule.concreteclass."+type);
			return (ViewOdInterface)Class.forName("com.zilker.mymodule.concreteclass."+type).getDeclaredConstructor().newInstance();
		} catch(Exception e) {
			//logger.info("unable to get concrete class");
			e.printStackTrace();
		}
		return null;
	}
	
}
