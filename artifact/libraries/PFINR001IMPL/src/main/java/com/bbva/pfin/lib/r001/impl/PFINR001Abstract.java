package com.bbva.pfin.lib.r001.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.elara.utility.api.connector.APIConnectorBuilder;
import com.bbva.elara.utility.interbackend.InterBackendConnectionUtils;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pfin.lib.r001.PFINR001;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class PFINR001Abstract extends AbstractLibrary implements PFINR001 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected JdbcUtils jdbcUtils;

	protected InterBackendConnectionUtils interBackendConnectionUtils;

	protected APIConnector internalApiConnector;

	protected APIConnectorBuilder apiConnectorBuilder;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param jdbcUtils the this.jdbcUtils to set
	*/
	public void setJdbcUtils(JdbcUtils jdbcUtils) {
		this.jdbcUtils = jdbcUtils;
	}

	/**
	* @param interBackendConnectionUtils the this.interBackendConnectionUtils to set
	*/
	public void setInterBackendConnectionUtils(InterBackendConnectionUtils interBackendConnectionUtils) {
		this.interBackendConnectionUtils = interBackendConnectionUtils;
	}

	/**
	* @param internalApiConnector the this.internalApiConnector to set
	*/
	public void setInternalApiConnector(APIConnector internalApiConnector) {
		this.internalApiConnector = internalApiConnector;
	}

	/**
	* @param apiConnectorBuilder the this.apiConnectorBuilder to set
	*/
	public void setApiConnectorBuilder(APIConnectorBuilder apiConnectorBuilder) {
		this.apiConnectorBuilder = apiConnectorBuilder;
	}

}