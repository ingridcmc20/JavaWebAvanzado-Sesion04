package com.tecsup.javawebavanzado.sesion04.beans;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.tecsup.javawebavanzado.sesion04.exception.DAOExcepcion;
import com.tecsup.javawebavanzado.sesion04.modelo.Categoria;
import com.tecsup.javawebavanzado.sesion04.negocio.GestionCategorias;


@ManagedBean
public class MenuVista {
	private MenuModel model;

	private Collection<Categoria> categorias;

	public MenuVista() {

		GestionCategorias gc = new GestionCategorias();

		try {
			categorias = gc.listar();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Total de categorías: " + categorias.size());

		model = new DefaultMenuModel();

		DefaultMenuItem item = null;

		// Submenu
		DefaultSubMenu submenu = new DefaultSubMenu("Categorias");

		for (Categoria categoria : categorias) {
			item = new DefaultMenuItem(categoria.getNombre());
			item.setIcon("ui-icon-disk");
			item.setOutcome("index.xhtml");
			item.setParam("categoriaId", categoria.getId());
			item.setAjax(true);
			item.setUpdate("messages");
			submenu.addElement(item);
		}

		model.addElement(submenu);
	}

	public MenuModel getModel() {
		return model;
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Collection<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void cambioCategoria(ActionEvent event) throws DAOExcepcion {
		MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
		int categoriaId = Integer.parseInt(menuItem.getParams().get("categoriaId").get(0));
		System.out.println("cat" + categoriaId);
		GestionCategorias gc = new GestionCategorias();
		addMessage("Categoria Selecccionada : ", gc.getCategoria(categoriaId).getNombre());

	}

}
