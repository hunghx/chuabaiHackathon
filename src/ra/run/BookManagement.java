package ra.run;

import ra.config.Inputmethods;
import ra.controller.CatalogController;
import ra.controller.ProductController;

public class BookManagement {
	
	static ProductController productController = new ProductController();
	static CatalogController catalogController = new CatalogController();
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("**************************BASIC-MENU**************************");
			System.out.println("1. Quản lý danh mục \n" +
					  "2. Quản lý sản phẩm \n" +
					  "3. Thoát ");
			System.out.print("Mời bạn lựa chọn: ");
			int choose = Inputmethods.getInteger();
			switch (choose) {
				case 1:
					new CatalogManager(catalogController);
					break;
				case 2:
					new ProductManager(productController, catalogController);
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.err.println("Vui lòng nhập lại (1 -> 3)");
					break;
			}
		}
	}
}