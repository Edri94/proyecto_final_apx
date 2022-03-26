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
	 * Return value for input parameter CURP
	 */
	protected String getCurp(){
		return (String)this.getParameter("CURP");
	}

	/**
	 * Return value for input parameter NOMBRE
	 */
	protected String getNombre(){
		return (String)this.getParameter("NOMBRE");
	}

	/**
	 * Return value for input parameter APELLIDOPATERNO
	 */
	protected String getApellidopaterno(){
		return (String)this.getParameter("APELLIDOPATERNO");
	}

	/**
	 * Return value for input parameter APELLIDOMATERNO
	 */
	protected String getApellidomaterno(){
		return (String)this.getParameter("APELLIDOMATERNO");
	}

	/**
	 * Return value for input parameter TELEFONO
	 */
	protected String getTelefono(){
		return (String)this.getParameter("TELEFONO");
	}

	/**
	 * Set value for List<Cliente> output parameter EntityList
	 */
	protected void setEntitylist(final List<Cliente> field){
		this.addParameter("EntityList", field);
	}
}
