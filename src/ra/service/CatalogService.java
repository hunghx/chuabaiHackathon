package ra.service;

import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService<Catalog, Integer> {
	
	private List<Catalog> listCatalog = new ArrayList<>();
	
	@Override
	public List<Catalog> getAll() {
		return listCatalog;
	}
	
	@Override
	public void save(Catalog catalog) {
		if (findById(catalog.getCatalogId()) == null) {
			listCatalog.add(catalog);
		} else {
			listCatalog.set(listCatalog.indexOf(findById(catalog.getCatalogId())), catalog);
		}
	}
	
	@Override
	public Catalog findById(Integer integer) {
		for (Catalog c : listCatalog) {
			if (c.getCatalogId() == integer) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public void delete(Integer integer) {
		if (findById(integer) != null) {
			listCatalog.remove(findById(integer));
		} else {
			System.err.println("Không có mục lục này");
		}
	}
	
	public int getNewId() {
		int idMax = 0;
		for (Catalog c : listCatalog) {
			if (c.getCatalogId() > idMax) {
				idMax = c.getCatalogId();
			}
		}
		return idMax + 1;
	}
}
