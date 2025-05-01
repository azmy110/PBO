package POSTTEST6;
public class Customer implements Promo {
    private String nama;
    private LaundryItem item;
    private double totalBayar;

    public Customer(String nama, LaundryItem item) {
        this.nama = nama;
        this.item = item;
        this.totalBayar = item.hitungHarga();
    }

    public void tampilkanDetail() {
        System.out.println("=== Detail Pesanan ===");
        System.out.println("Nama Customer : " + nama);
        System.out.println("Item Laundry  : " + item.getNamaItem());
        System.out.println("Berat         : " + item.getBerat() + " kg");
        System.out.println("Harga         : Rp " + totalBayar);
    }

    @Override
    public void cekPromo(double totalBayar) {
        if (totalBayar >= 100000) {
            double diskon = totalBayar * 0.1;
            this.totalBayar -= diskon;
            System.out.println("Promo: Diskon 10% diterapkan!");
            System.out.println("Total setelah diskon: Rp " + this.totalBayar);
        } else {
            System.out.println("Belum memenuhi syarat promo.");
        }
    }

    @Override
    public void tampilkanPromo() {
        System.out.println("Promo aktif: Diskon 10% untuk total > Rp100.000");
    }

    public double getTotalBayar() {
        return totalBayar;
    }
}
