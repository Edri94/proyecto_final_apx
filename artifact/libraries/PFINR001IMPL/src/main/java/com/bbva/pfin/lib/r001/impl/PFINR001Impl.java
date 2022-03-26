package com.bbva.pfin.lib.r001.impl;

import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.pfin.dto.clientes.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

import com.bbva.apx.exception.db.NoResultException;
import org.springframework.jdbc.core.SqlParameterValue;

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
		try
		{
			mapClienteDTO = this.jdbcUtils.queryForMap("select.cliente.by.rfc", rfc);
		}
		catch(IncorrectResultSizeException ex)
		{
			LOGGER.error(ex.toString());
			mapClienteDTO = null;
		}
		catch(NoResultException ex)
		{
			mapClienteDTO = null;
		}

		if (mapClienteDTO != null && !mapClienteDTO.isEmpty()) {
			clienteDTO = new Cliente();
			clienteDTO.setIdcliente((Integer) mapClienteDTO.get("IDCLIENTE"));
			clienteDTO.setNombre((String) mapClienteDTO.get("NOMBRE"));
			clienteDTO.setApellidopaterno((String) mapClienteDTO.get("APELLIDOPATERNO"));
			clienteDTO.setApellidomaterno((String) mapClienteDTO.get("APELLIDOMATERNO"));
			clienteDTO.setCurp((String) mapClienteDTO.get("CURP"));
			clienteDTO.setRfc((String) mapClienteDTO.get("RFC"));
			clienteDTO.setTelefono((String) mapClienteDTO.get("TELEFONO"));

		}
		return clienteDTO;
	}

	@Override
	public int executeActualizaCliente(Cliente cliente)
	{
		int afectados;

		Map<String, Object> args;
		args = createMapToClienteDTO(cliente);

		try
		{
			afectados = this.jdbcUtils.update("update.cliente.by.idcliente", args);
		}
		catch(IncorrectResultSizeException ex)
		{
			LOGGER.error(ex.toString());
			afectados = 0;
		}
		catch(NoResultException ex)
		{
			afectados = 0;
		}

		return afectados;
	}

	@Override
	public int executeBorraCliente(Cliente cliente)
	{
		int afectados;

		Map<String, Object> args;
		args = createMapToClienteDTO(cliente);

		try
		{
			afectados = this.jdbcUtils.update("delete.cliente.by.idcliente", args);
		}
		catch(IncorrectResultSizeException ex)
		{
			LOGGER.error(ex.toString());
			afectados = 0;
		}
		catch(NoResultException ex)
		{
			afectados = 0;
		}

		return afectados;
	}

	@Override
	public int executeRegistraCliente(Cliente cliente)
	{
		int afectados = 0;

		Cliente cliente_buscar = getClienteDTOFromJDBC(cliente.getRfc());

		if(cliente_buscar != null)
		{
			Map<String, Object> args;
			args = createMapToClienteDTO(cliente);

			try
			{
				afectados = this.jdbcUtils.update("insert.cliente", args);
			}
			catch(IncorrectResultSizeException ex)
			{
				LOGGER.error(ex.toString());
				afectados = 0;
			}
			catch(NoResultException ex)
			{
				afectados = 0;
			}
		}
		else
		{
			afectados = 0;
		}
		return afectados;
	}

	private Map createMapToClienteDTO(Cliente cliente)
	{
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("NOMBRE", new SqlParameterValue(java.sql.Types.CHAR, cliente.getNombre()));
		args.put("APELLIDOPATERNO",new SqlParameterValue(java.sql.Types.CHAR, cliente.getApellidopaterno()));
		args.put("APELLIDOMATERNO",new SqlParameterValue(java.sql.Types.CHAR, cliente.getApellidomaterno()));
		args.put("CURP",new SqlParameterValue(java.sql.Types.CHAR, cliente.getCurp()));
		args.put("RFC",new SqlParameterValue(java.sql.Types.CHAR, cliente.getRfc()));
		args.put("TELEFONO",new SqlParameterValue(java.sql.Types.CHAR, cliente.getTelefono()));

		return args;
	}
}
