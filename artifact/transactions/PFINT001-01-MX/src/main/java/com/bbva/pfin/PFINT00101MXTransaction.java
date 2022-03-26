package com.bbva.pfin;

import com.bbva.pfin.dto.clientes.Cliente;
import com.bbva.pfin.lib.r001.PFINR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * transaccion para proyecto final
 *
 */
public class PFINT00101MXTransaction extends AbstractPFINT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(PFINT00101MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute()
	{
		PFINR001 pfinR001 = this.getServiceLibrary(PFINR001.class);
		LOGGER.info("Execution of PFINT00101MXTransaction");

		List<Cliente> listClientes = pfinR001.execute();
		this.setEntitylist(listClientes);
	}

}
