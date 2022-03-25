package com.bbva.pfin.lib.r001.impl;

import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.pfin.dto.clientes.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.bbva.apx.exception.db.NoResultException;
import java.util.Map;
import java.util.Objects;

/**
 * The PFINR001Impl class...
 */
public class PFINR001Impl extends PFINR001Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PFINR001Impl.class);

	/**
	 * The execute method...
	 */
	@Override
	public List<Cliente> execute() {
		List<Cliente> listDTO = new ArrayList<>();

		Cliente cliente = new Cliente();

		listDTO.add(cliente);

		return listDTO;
	}

	@Override
	public List<Cliente> executeGetClienteByRfc(String rfc) {
		List<Cliente> listDTO = new ArrayList<>();

		if (StringUtils.isBlank(rfc))
		{
			this.addAdvice("PFIN00000001");
		}
		else
		{
			Cliente clienteDTO = null;

			String type = this.applicationConfigurationService.getDefaultProperty("type.find", "hardcode");
			LOGGER.error("type.find {}", type);

			if(Objects.equals(type, "jdbc"))
			{
				clienteDTO = this.getClienteDTOFromJDBC(rfc);
			}
			if(clienteDTO != null)
			{
				listDTO.add(clienteDTO);
			}

		}
		return listDTO;
	}

	private Cliente getClienteDTOFromJDBC(String rfc)
	{
		LOGGER.info("getClienteDTOFromJDBC({})", rfc);

		Cliente clienteDTO = null;
		Map<String, Object> mapClienteDTO;
		try {
			mapClienteDTO = this.jdbcUtils.queryForMap("select.cliente.by.rfc", rfc);
		} catch(IncorrectResultSizeException ex) {
			LOGGER.error(ex.toString());
			mapClienteDTO = null;
		} catch(NoResultException ex) {
			mapClienteDTO = null;
		}

		if (mapClienteDTO != null && !mapClienteDTO.isEmpty()) {
			clienteDTO = new Cliente();
			clienteDTO.setNombre((String) mapClienteDTO.get("nombre"));
			clienteDTO.setApellidopaterno((String) mapClienteDTO.get("apellidopaterno"));
			clienteDTO.setApellidomaterno((String) mapClienteDTO.get("apellidomaterno"));
			clienteDTO.setCurp((String) mapClienteDTO.get("curp"));
			clienteDTO.setRfc((String) mapClienteDTO.get("rfc"));
			clienteDTO.setTelefono((String) mapClienteDTO.get("telefono"));

		}
		return clienteDTO;
	}
}
