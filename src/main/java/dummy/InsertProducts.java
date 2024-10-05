package dummy;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import models.Product;
import utils.ConnectionDB;
import utils.ImageUtils;

public class InsertProducts {
	
	static List<Product> list = new ArrayList<>();
	
	public static void main(String[] args) {
		
		// THIS CLASS PURPOSE IS TO FILL THE DATABASE TABLE "products" WITH SOME EXAMPLE PRODUCTS IF IT IS EMPTY
		
		// fillList();
		// updateDatabase();
		
	}

	

	@SuppressWarnings("unused")
	private static void updateDatabase() {
		for(Product p : list) {
			insertProductIntoDatabase(p.getName(),p.getDescription(),p.getPrice(),p.getAmount(),p.getType(),p.getImage());
		}
	}



	private static void insertProductIntoDatabase(String name, String description, double price, int amount, String type,
			byte[] image) {
		try {
			Connection connection = ConnectionDB.connect();

			String sql = "INSERT INTO products (name, description, price, amount, type, image) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, amount);
			pstmt.setString(5, type);
			pstmt.setBytes(6, image);

			System.out.println(name);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static byte[] getImage(File file) {
		byte [] blobImage = null;
		
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			
			if(bufferedImage != null) {
				// Resize image to 230x230 (JLabel size)
				BufferedImage resizedImage = Scalr.resize(ImageIO.read(file), 230, 230);
				
				// Parse BufferedImage to byte[] (BLOB)
				blobImage = ImageUtils.bufferedImageToByteArray(resizedImage, "PNG");
				
				return blobImage;
			} else {
				System.out.println("Null Image!!: " + file.getAbsolutePath());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ImagingOpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return blobImage;
	}
	
	@SuppressWarnings("unused")
	private static void fillList() {
		list.add(new Product(0, "Razer Blackwidow V3", "The Razer BlackWidow V3 is a mechanical gaming keyboard with Razer Green or Yellow switches, customizable RGB Chroma lighting, a durable aluminum frame, and media controls. It's known for gaming performance and comfort, with a detachable wrist rest and Synapse 3 support for customization.", "Keyboard", getImage(new File("files/images/razer-blackwidow-v3.png")), 89.99, 20));
		list.add(new Product(1, "Razer Deathadder", "The Razer DeathAdder is a popular gaming mouse known for its ergonomic design, high-precision optical sensor, and durability. It features customizable buttons, RGB lighting, and is designed for comfortable, long-term use, making it ideal for both casual and competitive gamers.", "Mouse", getImage(new File("files/images/razer-deathadder-v2.png")), 45.99, 50));
		list.add(new Product(2, "Razer Huntsman Mini", "The Razer Huntsman Mini is a compact 60% mechanical gaming keyboard that offers Razer's Opto-Mechanical switches for fast and responsive keystrokes. It features customizable RGB Chroma lighting, a durable aluminum frame, and a detachable USB-C cable for portability. Its space-saving design makes it ideal for gamers looking to maximize desk space while maintaining performance.", "Keyboard", getImage(new File("files/images/razer-huntsman-mini.png")), 85.99, 50));
		list.add(new Product(3, "Razer Viper V3", "The Razer Viper V3 is a lightweight, high-performance gaming mouse designed for esports enthusiasts. It features Razer's Focus+ optical sensor for precise tracking, customizable RGB Chroma lighting, and a durable design with a comfortable grip. The mouse includes programmable buttons and is designed for fast, accurate movements, making it an excellent choice for competitive gaming.", "Mouse", getImage(new File("files/images/razer-viper-v3.png")), 79.99, 25));
		list.add(new Product(4, "Corsair K70", "The Corsair K70 is a mechanical gaming keyboard featuring Cherry MX switches for precise and responsive typing. It offers RGB lighting, dedicated media controls, and an aluminum frame for durability. With customizable macros and key remapping through iCUE software, it's a popular choice for gaming enthusiasts.", "Keyboard", getImage(new File("files/images/corsair-k70.png")), 85.99, 80));
		list.add(new Product(5, "Corsair K55", "The Corsair K55 is a budget-friendly gaming keyboard with membrane switches that provide a quieter typing experience. It features customizable RGB backlighting, dedicated media controls, and six programmable macro keys. The keyboard also includes a detachable wrist rest for added comfort during extended gaming sessions.", "Keyboard", getImage(new File("files/images/corsair-k55.png")), 75.99, 80));
		list.add(new Product(6, "Corsair A500", "The Corsair A500 is a premium air cooler designed for high-performance cooling in PC builds. It features dual 120mm fans for efficient airflow and an innovative design that allows for customizable fan speeds and RGB lighting through Corsair iCUE software.", "Cooler", getImage(new File("files/images/corsair-a500.png")), 85.99, 50));
		list.add(new Product(7, "Corsair Hydro H100I", "The Corsair Hydro H100i is a liquid CPU cooler designed for high-performance cooling in gaming PCs. It features a 240mm radiator with dual 120mm fans for efficient heat dissipation and customizable RGB lighting for aesthetic appeal. The cooler is compatible with various Intel and AMD sockets and includes an easy-to-install mounting system, providing excellent cooling performance while maintaining low noise levels.", "Cooler", getImage(new File("files/images/corsair-hydro-h100i.png")), 99.99, 100));
		list.add(new Product(8, "Corsair RM750", "The Corsair RM750 is a high-performance, fully modular power supply unit (PSU) with a 750W capacity. It features 80 PLUS Gold certification for energy efficiency, ensuring reliable power delivery with minimal waste. The PSU includes a zero RPM fan mode for silent operation at low loads and is equipped with multiple protection features for safety. Its fully modular design allows for easy cable management, making it a great choice for custom PC builds.", "Power Supply", getImage(new File("files/images/corsair-rm750.png")), 105.99, 80));
		list.add(new Product(9, "Corsair Vengeance 750M", "The Corsair Vengeance 750M is a semi-modular power supply unit (PSU) with a 750W capacity. It features 80 PLUS Bronze certification for improved energy efficiency and reliable power delivery. The PSU includes a 135mm fan for quiet operation and a range of protection features for safety. Its semi-modular design allows users to connect only the cables they need, making it easier to manage and organize cables in custom builds.", "Power Supply", getImage(new File("files/images/corsair-vengeance-750m.png")), 120.99, 100));
		list.add(new Product(10, "Corsair Vengeance DDR4 2x16GB", "The Corsair Vengeance DDR4 2x16GB is a high-performance memory kit designed for gaming and demanding applications. With a total of 32GB, it provides fast speeds and low latency, ensuring smooth multitasking and enhanced performance in memory-intensive tasks.", "Memory", getImage(new File("files/images/corsair-vengeance-ddr4.png")), 108.99, 80));
		list.add(new Product(11, "Asus Rog Strix Z790", "The Asus Rog Strix Z790 is a high-end motherboard designed for gaming and performance. It supports the latest Intel processors and features advanced connectivity options, robust power delivery, and customizable RGB lighting for an enhanced gaming experience.", "Motherboard", getImage(new File("files/images/asus-rog-strix-z790.png")), 135.99, 20));
		list.add(new Product(12, "Asus RX6900XT", "The Asus RX6900XT is a powerful graphics card designed for high-performance gaming. With cutting-edge RDNA 2 architecture, it delivers stunning visuals and exceptional frame rates at 4K resolution, making it ideal for gamers and content creators.", "Graphics Card", getImage(new File("files/images/asus-rx6900xt.png")), 659.99, 40));
		list.add(new Product(13, "Asus TUF Gaming B650 Plus", "The Asus TUF Gaming B650 Plus is a durable and reliable motherboard built for gamers. It supports AMD processors and features military-grade components, advanced cooling options, and customizable lighting for a stable and stylish gaming setup.", "Motherboard", getImage(new File("files/images/asus-tup-gaming-b650-plus.png")), 129.99, 15));
		list.add(new Product(14, "Asus VY279HGE", "The Asus VY279HGE is a versatile monitor designed for both gaming and productivity. It features a Full HD display with a fast refresh rate, providing smooth visuals and reduced motion blur for an immersive viewing experience.", "Monitor", getImage(new File("files/images/asus-VY279HGE.png")), 239.99, 10));
		list.add(new Product(15, "Deepcool Matrexx 55", "The Deepcool Matrexx 55 is a stylish tower case that offers excellent airflow and ample space for high-performance components. It features tempered glass panels for showcasing your build and supports multiple cooling options for optimal performance.", "Tower Case", getImage(new File("files/images/deepcool-matrexx-55.png")), 150.99, 8));
		list.add(new Product(16, "G.Skill Ripjaws V DDR4 2x8GB", "The G.Skill Ripjaws V DDR4 2x8GB is a reliable memory kit that provides great performance for gaming and multitasking. With a total of 16GB, it offers fast speeds and low latency, making it an excellent choice for budget-conscious gamers.", "Memory", getImage(new File("files/images/gskill-ripjaws-v-ddr4.png")), 59.99, 25));
		list.add(new Product(17, "Kingston Fury Beast 2x16GB", "The Kingston Fury Beast 2x16GB is a high-performance memory kit designed for gamers and content creators. With a total of 32GB, it delivers fast speeds and efficient multitasking capabilities, enhancing overall system performance.", "Memory", getImage(new File("files/images/kingston-fury-beast-2x16gb.png")), 120.99, 40));
		list.add(new Product(18, "Logitech G502", "The Logitech G502 is a high-performance gaming mouse known for its customizable buttons, precision, and ergonomic design. With adjustable DPI settings and RGB lighting, it is perfect for gamers seeking both functionality and style.", "Mouse", getImage(new File("files/images/logitech-g502.png")), 69.99, 20));
		list.add(new Product(19, "Logitech K120", "The Logitech K120 is a reliable and budget-friendly keyboard designed for everyday use. It features a comfortable typing experience, durable construction, and a spill-resistant design, making it an ideal choice for home or office environments.", "Keyboard", getImage(new File("files/images/logitech-k120.png")), 35.99, 15));
		list.add(new Product(20, "MSI Optix G24C6", "The MSI Optix G24C6 is a gaming monitor with a curved display that offers immersive visuals and a fast refresh rate. With features like FreeSync technology, it provides smooth gameplay with reduced screen tearing, making it perfect for gaming.", "Monitor", getImage(new File("files/images/msi-optix-G24C6.png")), 219.99, 10));
		list.add(new Product(21, "MSI Z270 Gaming M3", "The MSI Z270 Gaming M3 is a robust motherboard designed for Intel processors. It features advanced cooling solutions, customizable lighting, and support for high-speed memory, making it an excellent choice for gaming and performance-oriented builds.", "Motherboard", getImage(new File("files/images/msi-z270-gaming-m3.png")), 120.99, 15));
		list.add(new Product(22, "Nvidia RTX 2060 Super", "The Nvidia RTX 2060 Super is a powerful graphics card that offers excellent performance for gaming at 1080p and 1440p. With support for ray tracing and DLSS technology, it provides stunning graphics and immersive gameplay experiences.", "Graphics Card", getImage(new File("files/images/nvidia-rtx-2060-super.png")), 250.99, 10));
		list.add(new Product(23, "Nvidia RTX 4090", "The Nvidia RTX 4090 is a top-of-the-line graphics card designed for extreme gaming performance. With advanced ray tracing capabilities and exceptional power, it delivers stunning graphics and performance at 4K resolution and beyond.", "Graphics Card", getImage(new File("files/images/nvidia-rtx-4090.png")), 1239.99, 8));
		list.add(new Product(24, "PowerColor RX7900", "The PowerColor RX7900 is a high-performance graphics card that provides outstanding gaming performance. With advanced RDNA 3 architecture, it offers exceptional frame rates and stunning visuals for an immersive gaming experience.", "Graphics Card", getImage(new File("files/images/powercolor-rx7900.png")), 959.99, 10));
		list.add(new Product(25, "Samsung LS32BM700", "The Samsung LS32BM700 is a versatile monitor designed for both entertainment and productivity. With a stunning 4K resolution and vibrant colors, it delivers an immersive viewing experience for movies, games, and work tasks.", "Monitor", getImage(new File("files/images/samsung-LS32BM700.png")), 289.99, 5));
		list.add(new Product(26, "Xigmatek Overtake", "The Xigmatek Overtake is a stylish and functional tower case that provides excellent airflow and space for high-performance components. Its tempered glass panels showcase your build while supporting multiple cooling configurations.", "Tower Case", getImage(new File("files/images/xigmatek-overtake.png")), 129.99, 15));

	}
}
