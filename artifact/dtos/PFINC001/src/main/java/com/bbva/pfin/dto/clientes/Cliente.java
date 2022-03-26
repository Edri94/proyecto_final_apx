package com.bbva.pfin.dto.clientes;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Cliente class...
 */
public class Cliente implements Serializable
{
	private static final long serialVersionUID = 2931699728946643245L;

	private int idcliente;
	private String nombre;
	private String apellidopaterno;
	private String apellidomaterno;
	private String rfc;
	private String curp;
	private String telefono;

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidopaterno() {
		return apellidopaterno;
	}

	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}

	public String getApellidomaterno() {
		return apellidomaterno;
	}

	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"idcliente=" + idcliente +
				", nombre='" + nombre + '\'' +
				", apellidopaterno='" + apellidopaterno + '\'' +
				", apellidomaterno='" + apellidomaterno + '\'' +
				", rfc='" + rfc + '\'' +
				", curp='" + curp + '\'' +
				", telefono='" + telefono + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return idcliente == cliente.idcliente && nombre.equals(cliente.nombre) && apellidopaterno.equals(cliente.apellidopaterno) && apellidomaterno.equals(cliente.apellidomaterno) && rfc.equals(cliente.rfc) && curp.equals(cliente.curp) && telefono.equals(cliente.telefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcliente, nombre, apellidopaterno, apellidomaterno, rfc, curp, telefono);
	}
}
