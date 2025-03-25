package POSTTEST2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

// Class LaundryItem dengan konsep Encapsulation
class LaundryItem {
    private String name;       // Hanya bisa diakses dalam class ini
    private double weight;     
    private BigDecimal price;  

    // Constructor
    public LaundryItem(String name, double weight, BigDecimal price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    // Getter dan Setter (Encapsulation)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        } else {
            System.out.println("Berat tidak valid!");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            this.price = price;
        } else {
            System.out.println("Harga tidak valid!");
        }
    }

    protected String formatHarga() {
        return String.format("Rp%,.2f", getPrice()).replace(',', '.');
    }
    
    

    @Override
    public String toString() {
        return "Nama: " + name + ", Berat: " + weight + "kg, Harga: " + formatHarga();
    }
}

// Default access modifier: hanya bisa diakses dalam package posttest2
class LaundryManagement {
    private static ArrayList<LaundryItem> laundryList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Sistem Manajemen Laundry Cepat Bersih ===");
            System.out.println("1. Tambah Data Laundry");
            System.out.println("2. Lihat Data Laundry");
            System.out.println("3. Ubah Data Laundry");
            System.out.println("4. Hapus Data Laundry");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addLaundryItem();
                    break;
                case 2:
                    viewLaundryItems();
                    break;
                case 3:
                    updateLaundryItem();
                    break;
                case 4:
                    deleteLaundryItem();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (choice != 5);
    }

    private static void addLaundryItem() {
        System.out.print("Masukkan nama laundry: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan berat (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Masukkan harga: Rp");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();
        
        laundryList.add(new LaundryItem(name, weight, price));
        System.out.println("Data berhasil ditambahkan!");
    }

    private static void viewLaundryItems() {
        if (laundryList.isEmpty()) {
            System.out.println("Tidak ada data laundry.");
        } else {
            System.out.println("\nData Laundry:");
            for (int i = 0; i < laundryList.size(); i++) {
                System.out.println((i + 1) + ". " + laundryList.get(i));
            }
        }
    }

    private static void updateLaundryItem() {
        viewLaundryItems();
        if (laundryList.isEmpty()) return;
        
        System.out.print("Masukkan nomor item yang ingin diubah: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index >= 0 && index < laundryList.size()) {
            System.out.print("Masukkan nama baru: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan berat baru (kg): ");
            double weight = scanner.nextDouble();
            System.out.print("Masukkan harga baru: Rp");
            BigDecimal price = scanner.nextBigDecimal();
            scanner.nextLine();
            
            laundryList.get(index).setName(name);
            laundryList.get(index).setWeight(weight);
            laundryList.get(index).setPrice(price);
            System.out.println("Data berhasil diperbarui!");
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }

    private static void deleteLaundryItem() {
        viewLaundryItems();
        if (laundryList.isEmpty()) return;
        
        System.out.print("Masukkan nomor item yang ingin dihapus: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index >= 0 && index < laundryList.size()) {
            laundryList.remove(index);
            System.out.println("Data berhasil dihapus!");
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }
}
