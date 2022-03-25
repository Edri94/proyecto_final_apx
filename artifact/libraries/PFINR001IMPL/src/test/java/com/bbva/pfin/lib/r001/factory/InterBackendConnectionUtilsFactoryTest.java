package com.bbva.pfin.lib.r001.factory;

import org.mockito.Mockito;
import org.osgi.framework.BundleContext;

import com.bbva.elara.utility.interbackend.InterBackendConnectionUtils;
import com.bbva.elara.utility.interbackend.factory.InterBackendConnectionUtilsFactory;

public class InterBackendConnectionUtilsFactoryTest implements InterBackendConnectionUtilsFactory{

	@Override
	public InterBackendConnectionUtils getInterBackendUtility(BundleContext arg0) {		
		return Mockito.mock(InterBackendConnectionUtils.class);
	}

}
