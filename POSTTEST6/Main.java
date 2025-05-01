package POSTTEST6;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean lanjut = true;

        System.out.println("===== Laundry Cepat Bersih =====");

        while (lanjut) {
            try {
                System.out.print("\nMasukkan Nama Customer: ");
                String nama = input.nextLine();

                System.out.print("Masukkan Nama Item Laundry: ");
                String namaItem = input.nextLine();

                System.out.print("Masukkan Berat Cucian (kg): ");
                double berat = input.nextDouble();
                input.nextLine(); // Bersihkan newline

                LaundryItem item = new LaundryItem(namaItem, berat);
                Customer customer = new Customer(nama, item);

                customer.tampilkanDetail();
                customer.tampilkanPromo();
                customer.cekPromo(customer.getTotalBayar());

                System.out.println("Total item diproses sejauh ini: " + LaundryItem.getTotalItem());

                System.out.print("\nInput data lagi? (y/n): ");
                String jawab = input.nextLine();
                if (!jawab.equalsIgnoreCase("y")) {
                    lanjut = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Input berat harus berupa angka (gunakan titik, bukan koma)!");
                input.nextLine(); // Reset input
            }
        }

        System.out.println("\nTerima kasih telah menggunakan Laundry Cepat Bersih.");
        input.close();
    }
}
