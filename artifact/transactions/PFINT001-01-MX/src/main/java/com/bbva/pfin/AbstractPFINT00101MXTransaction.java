package com.bbva.pfin;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.pfin.dto.clientes.Cliente;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractPFINT00101MXTransaction extends AbstractTransaction {

	public AbstractPFINT00101MXTransaction(){
	}


	/**
	 * Return value for input parameter rfc
	 */
	protected String getRfc(){
		return (String)this.getParameter("rfc");
	}

	/**
	 * Set value for List<Cliente> output parameter EntityList
	 */
	protected void setEntitylist(final List<Cliente> field){
		this.addParameter("EntityList", field);
	}
}
