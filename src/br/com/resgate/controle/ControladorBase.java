package br.com.resgate.controle;

import org.primefaces.component.datatable.DataTable;

public abstract class ControladorBase {
	private DataTable datatable;

	public DataTable getDatatable() {
		return datatable;
	}

	public void setDatatable(DataTable datatable) {
		this.datatable = datatable;
	}
}
