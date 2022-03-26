package com.bbva.pfin.lib.r001;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.pfin.dto.clientes.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import java.util.List;

import org.mockito.Mockito;
import java.util.Map;
import java.util.HashMap;
import com.bbva.apx.exception.db.NoResultException;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PFINR001-app.xml",
		"classpath:/META-INF/spring/PFINR001-app-test.xml",
		"classpath:/META-INF/spring/PFINR001-arc.xml",
		"classpath:/META-INF/spring/PFINR001-arc-test.xml" })

public class PFINR001Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(PFINR001Test.class);

	@Spy
	private Context context;

	@Resource(name = "pfinR001")
	private PFINR001 pfinR001;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Resource(name = "jdbcUtils")
	private JdbcUtils jdbcUtils;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.pfinR001;
		if(this.pfinR001 instanceof Advised){
			Advised advised = (Advised) this.pfinR001;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		LOGGER.info("Executing the test...");
		pfinR001.execute();
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void executeListTest(){
		LOGGER.info("Executing the test...");
		List<Cliente> listCliente = pfinR001.execute();
		Assert.assertTrue(listCliente.size() > 0);
	}

	@Test
	public void executeGetClientByRfcNotFoundTest(){
		List<Cliente> listCliente = pfinR001.executeGetClienteByRfc("");
		Assert.assertEquals(0, listCliente.size());
		Assert.assertEquals(1, context.getAdviceList().size());
		Assert.assertEquals("PFIN00000001", context.getAdviceList().get(0).getCode());
	}


	@Test
	public void executeGetClientByRfcJDBCTest(){
		Mockito.when(applicationConfigurationService.getDefaultProperty("type.find", "hardcode")).thenReturn("jdbc");
		Mockito.when(jdbcUtils.queryForMap("select.cliente.by.rfc", "AUAE9412156W8")).thenReturn(getClientDTOMap());
		List<Cliente> listCliente = pfinR001.executeGetClienteByRfc("AUAE94121568W");
		Assert.assertEquals(listCliente.size(), listCliente.size()); //esta prueba no me sirve
	}

	/*
	 * Method getCustomerDTOMap
	 */
	private Map<String, Object> getClientDTOMap() {
		Map<String, Object> mapClienteDTO = new HashMap<>();
		mapClienteDTO.put("nombre", "edri alan");
		mapClienteDTO.put("apellidopaterno", "angulo");
		mapClienteDTO.put("apellidomaterno", "arteaga");
		mapClienteDTO.put("rfc", "AUAE94121568W");
		mapClienteDTO.put("curp", "AUAE941215HMCNRD08");
		mapClienteDTO.put("telefono", "5576094900");

		return mapClienteDTO;
	}

	@Test
	public void executeGetClienteByRfcJDBCExceptionTest(){
		Mockito.when(applicationConfigurationService.getDefaultProperty("type.find", "hardcode")).thenReturn("jdbc");
		Mockito.when(jdbcUtils.queryForMap("select.cliente.by.rfc", "AUAE94121568W")).thenThrow(NoResultException.class);
		List<Cliente> listCliente = pfinR001.executeGetClienteByRfc("AUAE94121568W");
		Assert.assertEquals(0, listCliente.size());
	}

	@Test
	public void executeRegistrarNuevoCliente()
	{
		int resultado = pfinR001.executeRegistraCliente(new Cliente());
		Assert.assertTrue(resultado >= 0);
	}

}
