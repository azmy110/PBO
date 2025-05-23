package POSTTEST5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

// Abstract superclass
abstract class User {
    protected String nama;
    protected String noTelp;

    public User(String nama, String noTelp) {
        this.nama = nama;
        this.noTelp = noTelp;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("No Telepon: " + noTelp);
    }

    public abstract void hakAkses(); // abstract method
}

// Final subclass Admin
final class Admin extends User {
    private String idAdmin;

    public Admin(String idAdmin, String nama, String noTelp) {
        super(nama, noTelp);
        this.idAdmin = idAdmin;
    }

    public final void kelolaData() {
        System.out.println("Admin " + nama + " mengelola data laundry.");
    }

    @Override
    public void hakAkses() {
        System.out.println("Admin memiliki akses penuh ke sistem.");
    }
}

// Subclass Pelanggan
class Pelanggan extends User {
    private String alamat;

    public Pelanggan(String nama, String noTelp, String alamat) {
        super(nama, noTelp);
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    @Override
    public void hakAkses() {
        System.out.println("Pelanggan hanya bisa melihat dan membuat pesanan.");
    }
}

// Superclass LaundryItem
abstract class LaundryItem {
    protected String name;
    protected double weight;
    protected BigDecimal price;
    protected Pelanggan pelanggan;

    public LaundryItem(String name, double weight, BigDecimal price, Pelanggan pelanggan) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.pelanggan = pelanggan;
    }

    public abstract double hitungBiaya();

    protected String formatHarga() {
        return String.format("Rp%,.2f", hitungBiaya()).replace(',', '.');
    }

    @Override
    public String toString() {
        return "Nama: " + name + ", Berat: " + weight + "kg, Total Biaya: " + formatHarga() +
               ", Pelanggan: " + pelanggan.nama + ", No Telp: " + pelanggan.noTelp + ", Alamat: " + pelanggan.getAlamat();
    }
}

// Subclass RegularLaundry
class RegularLaundry extends LaundryItem {
    public RegularLaundry(String name, double weight, BigDecimal price, Pelanggan pelanggan) {
        super(name, weight, price, pelanggan);
    }

    @Override
    public double hitungBiaya() {
        return weight * price.doubleValue();
    }
}

// Subclass ExpressLaundry
class ExpressLaundry extends LaundryItem {
    public ExpressLaundry(String name, double weight, BigDecimal price, Pelanggan pelanggan) {
        super(name, weight, price, pelanggan);
    }

    @Override
    public double hitungBiaya() {
        return (weight * price.doubleValue()) + 10000;
    }
}

// Main class
public class LaundryManagement {
    private static ArrayList<LaundryItem> laundryList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static Admin admin = new Admin("A001", "Admin Laundry", "081234567890");

    public static void main(String[] args) {
        admin.kelolaData();
        int choice;
        do {
            System.out.println("\n=== Sistem Manajemen Laundry Cepat Bersih ===");
            System.out.println("1. Tambah Data Laundry (Regular)");
            System.out.println("2. Tambah Data Laundry (Express)");
            System.out.println("3. Lihat Data Laundry");
            System.out.println("4. Ubah Data Laundry");
            System.out.println("5. Hapus Data Laundry");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addLaundryItem(false);
                case 2 -> addLaundryItem(true);
                case 3 -> viewLaundryItems();
                case 4 -> updateLaundryItem();
                case 5 -> deleteLaundryItem();
                case 6 -> System.out.println("Terima kasih telah menggunakan sistem!");
                default -> System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (choice != 6);
    }

    private static void addLaundryItem(boolean isExpress) {
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();
        System.out.print("Masukkan no telepon pelanggan: ");
        String noTelp = scanner.nextLine();
        System.out.print("Masukkan alamat pelanggan: ");
        String alamat = scanner.nextLine();

        Pelanggan pelanggan = new Pelanggan(namaPelanggan, noTelp, alamat);

        System.out.print("Masukkan nama laundry: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan berat (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Masukkan harga per kg: Rp");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();

        LaundryItem item = isExpress
                ? new ExpressLaundry(name, weight, price, pelanggan)
                : new RegularLaundry(name, weight, price, pelanggan);

        laundryList.add(item);
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
            LaundryItem itemLama = laundryList.get(index);

            System.out.print("Masukkan nama baru: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan berat baru (kg): ");
            double weight = scanner.nextDouble();
            System.out.print("Masukkan harga baru per kg: Rp");
            BigDecimal price = scanner.nextBigDecimal();
            scanner.nextLine();

            LaundryItem itemBaru = (itemLama instanceof ExpressLaundry)
                    ? new ExpressLaundry(name, weight, price, itemLama.pelanggan)
                    : new RegularLaundry(name, weight, price, itemLama.pelanggan);

            laundryList.set(index, itemBaru);
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
