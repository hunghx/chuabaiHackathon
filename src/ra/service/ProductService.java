package ra.service;

import ra.model.Catalog;
import ra.model.Product;
import ra.util.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product, String> {
	private IOFile<Product> ioFile;
	private List<Product> products ;

	public ProductService() {
		ioFile = new IOFile<>();
		this.products = ioFile.readFromFile(IOFile.PRODUCT_FILE);
	}

	@Override
	public List<Product> getAll() {
		return products;
	}
	
	@Override
	public void save(Product product) {
		if (findById(product.getProductId()) == null) {
			products.add(product);
		} else {
			products.set(products.indexOf(findById(product.getProductId())), product);
		}
		ioFile.writeToFile(products,IOFile.PRODUCT_FILE);
	}
	
	@Override
	public Product findById(String s) {
		for (Product p : products) {
			if (p.getProductId().equals(s)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public void delete(String s) {
		if (findById(s) != null) {
			products.remove(findById(s));
			ioFile.writeToFile(products,IOFile.PRODUCT_FILE);
		} else {
			System.err.println("Không có sản phẩm này");
		}
	}
	
	public String getNewId() {
		String id = "P";
		int idMax = 0;
		for (Product product : products) {
			if (product != null) {
				int productId = Integer.parseInt(product.getProductId().replace("P", "0"));
				if (idMax < productId) {
					idMax = productId;
				}
			}
		}
		idMax += 1;
		String newId = Integer.toString(idMax);
		if (newId.length() == 1) {
			id += "0" + 0 + 0 + newId;
		}
		if (newId.length() == 2) {
			id += "0" + 0 + newId;
		}
		if (newId.length() == 3) {
			id = "0" + newId;
		}
		if (newId.length() == 4) {
			id = newId;
		}
		return id;
	}
}
