package com.tecsup.javawebavanzado.sesion04.beans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tecsup.javawebavanzado.sesion04.exception.DAOExcepcion;
import com.tecsup.javawebavanzado.sesion04.modelo.Categoria;
import com.tecsup.javawebavanzado.sesion04.modelo.Producto;
import com.tecsup.javawebavanzado.sesion04.negocio.GestionCategorias;
import com.tecsup.javawebavanzado.sesion04.negocio.GestionProductos;

@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean {

	// private int idCategoria;
	private int categoriaId = 1;
	private String nombre;
	private String descripcion;
	private Collection<Categoria> categorias;
	private Collection<Producto> productos;

	public CategoriaBean() {
		GestionCategorias gc = new GestionCategorias();

		try {
			categorias = gc.listar();
			System.out.println("Total de categoríasooo: " + categorias.size());

		} catch (DAOExcepcion e) {
			e.printStackTrace();
		}
	}



	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Collection<Producto> getProductos() {
		this.cargarProductos();
		return productos;
	}

	public void setProductos(Collection<Producto> productos) {
		this.productos = productos;
	}

	
	public void cargarProductos() {
		System.out.println("Dentro de cargarProductos()");
		GestionProductos negocio = new GestionProductos();
		try {
			productos = negocio.listarPorCategoria(getCategoriaId());
			System.out.println("Total de productos: " + productos.size());
		} catch (DAOExcepcion e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}

