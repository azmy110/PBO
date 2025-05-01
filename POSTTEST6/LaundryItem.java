package POSTTEST6;
public class LaundryItem {
    private String namaItem;
    private double berat;
    private static int totalItem = 0; // static variable

    public LaundryItem(String namaItem, double berat) {
        this.namaItem = namaItem;
        this.berat = berat;
        totalItem++;
    }

    public double hitungHarga() {
        return berat * 8000; // Rp 8000 per kg
    }

    public String getNamaItem() {
        return namaItem;
    }

    public double getBerat() {
        return berat;
    }

    public static int getTotalItem() {
        return totalItem;
    }
}
