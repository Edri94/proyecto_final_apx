package com.bbva.pfin.lib.r001;

import com.bbva.pfin.dto.clientes.Cliente;
import java.util.List;

/**
 * The  interface PFINR001 class...
 */
public interface PFINR001 {

	/**
	 * The execute method...
	 */
	List<Cliente> execute();
	List<Cliente> executeGetClienteByRfc(String rfc);

}
