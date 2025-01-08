package projek.akhir;

import java.io.*;
import java.util.*;

public class Lising {
    static ArrayList<String> noPelanggan = new ArrayList<>();
    static ArrayList<String> namaPelanggan = new ArrayList<>();
    static ArrayList<Integer> kodeMotor = new ArrayList<>();
    static ArrayList<Double> hargaPokok = new ArrayList<>();
    static ArrayList<Integer> angsuran = new ArrayList<>();
    static ArrayList<Double> totalBunga = new ArrayList<>();
    static ArrayList<Integer> jumlahDp = new ArrayList<>();
    static boolean isRunning = true;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("+----------------------------------------+");
        System.out.println("|[ AKROM FAKHRUDIN | TEKNIK INFORMATIKA ]|");
        System.out.println("|                [1P41]                  |");
        System.out.println("+----------------------------------------+");
        while (isRunning) {
            showMenu();
        }
    }

    static void showMenu() throws IOException {
        System.out.println("\n======== MENU ========");
        System.out.println("=> 1. Lihat Data Customer");
        System.out.println("=> 2. Tambah Data Customer");
        System.out.println("=> 3. Edit Data Customer");
        System.out.println("=> 4. Hapus Data Customer");
        System.out.println("=> 5. Keluar");
        System.out.print("Pilih menu: ");

        try {
            int selectedMenu = Integer.parseInt(br.readLine());

            switch (selectedMenu) {
                case 1:
                    showAllPelanggan();
                    break; // Tambahkan break untuk menghindari eksekusi ke case lain
                case 2:
                    inputPelanggan();
                    break;
                case 3:
                    editPelanggan();
                    break;
                case 4:
                    deletePelanggan();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Program dihentikan. Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Masukkan angka.");
        }
    }

    static void showAllPelanggan() {
         Scanner scanner = new Scanner(System.in);
    int page = 1;
    int recordsPerPage = 3;
    int totalPages = (int) Math.ceil((double) noPelanggan.size() / recordsPerPage);

    while (true) {
        if (noPelanggan.isEmpty()) {
            System.out.println("Data Customer Tidak Tersedia!");
            return;
        }

        System.out.println("   LAPORAN LEASING MOTOR ADIRA FINANCE PEMALANG                                                                                                                                   Hal "+page+"\n");
        System.out.printf("+-----+-----------------+---------------------+-------------------------+-----------------+----------------+---------------+--------------------+---------------------+-----------------------+\n");
        System.out.printf("| No  |    Nomor KTP    |    Nama Pelanggan   |       Type  Motor       |    Harga(Rp)    |  Angsuran(bln) | Bunga(Persen) |     Uang Muka      |   Angsuran/bulan    |      Total Bayar      |\n");
        System.out.printf("+-----+-----------------+---------------------+-------------------------+-----------------+----------------+---------------+--------------------+---------------------+-----------------------+\n");
         int start = (page - 1) * recordsPerPage;
            int end = Math.min(start + recordsPerPage, noPelanggan.size());
        for (int i = start; i < end; i++) {
            double bunga = totalBunga.get(i) * hargaPokok.get(i);
            double totalBayar = hargaPokok.get(i) + bunga - jumlahDp.get(i);
            double perBulan = totalBayar / angsuran.get(i);

            System.out.printf(
                "|%-5d|%-17s|%-21s|%-25s|%-17.0f|%-16d|%-15.0f|%-20d|%-21.0f|%-23.0f|\n",
                i + 1,
                noPelanggan.get(i),
                namaPelanggan.get(i),
                getMotor(kodeMotor.get(i)),
                hargaPokok.get(i),
                angsuran.get(i),
                totalBunga.get(i) * 100,
                jumlahDp.get(i),
                perBulan,
                hargaPokok.get(i) + bunga
            );
        }

        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
    
     if (page < totalPages) {
            System.out.print("\nTekan 'n' untuk halaman berikutnya atau 'k' untuk keluar: ");
        } else if (page > 1) {
            System.out.print("\nTekan 'b' untuk halaman sebelumnya atau 'k' untuk keluar: ");
        } else {
            System.out.print("\nTekan 'n' untuk halaman berikutnya atau 'k' untuk keluar: ");
        }

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("n") && page < totalPages) {
            page++;
        } else if (input.equalsIgnoreCase("b") && page > 1) {
            page--;
        } else if (input.equalsIgnoreCase("k")) {
            break;
        } else {
            System.out.println("Input tidak valid. Silakan coba lagi.");
        }
    
}
    }
    static void inputPelanggan() throws IOException {
        boolean berjalan = true;
        while (berjalan) {
        System.out.println("\n======== TAMBAH DATA ========");
        System.out.print("Nomor KTP\t\t: ");
        String nomor = br.readLine();

        System.out.print("Nama Customer\t\t: ");
        String nama = br.readLine();

        System.out.println("List Type Motor:");
        System.out.println("=> 1. BeATStreet = Rp. 19.400.000");
        System.out.println("=> 2. BeATDeluxs = Rp. 18.530.000");
        System.out.println("=> 3. Scoopy     = Rp. 22.525.000");
        System.out.println("=> 4. PCX160     = Rp. 33.750.000");
        System.out.println("=> 5. Vario160   = Rp. 27.600.000");

        System.out.print("Masukkan Kode Type Motor\t\t: ");
        int kode = Integer.parseInt(br.readLine());
        double harga = getHarga(kode);

        System.out.print("Masukkan Uang Muka\t\t: ");
        int dp = Integer.parseInt(br.readLine());

        System.out.print("Angsuran Perbulan [12-24-36]\t\t: ");
        int angsur = Integer.parseInt(br.readLine());

        double bunga = (angsur / 12) * 0.10;

        noPelanggan.add(nomor);
        namaPelanggan.add(nama);
        kodeMotor.add(kode);
        hargaPokok.add(harga);
        angsuran.add(angsur);
        jumlahDp.add(dp);
        totalBunga.add(bunga);

        System.out.println("Data berhasil ditambahkan!");
        System.out.print("Mau nambah data lagi ndak? (y/t): ");
            String choice = br.readLine().toLowerCase();
            if (!choice.equals("y")) {
                berjalan = false;
            }
        }
    }

    static void editPelanggan() throws IOException {
        System.out.println("\n======== EDIT DATA ========");
        showAllPelanggan();
        System.out.print("Pilih nomor Customer yang ingin di-edit: ");
        int index = Integer.parseInt(br.readLine()) - 1;

        if (index >= 0 && index < noPelanggan.size()) {
            System.out.print("Nomor KTP baru\t\t: ");
            noPelanggan.set(index, br.readLine());

            System.out.print("Nama Customer baru\t\t: ");
            namaPelanggan.set(index, br.readLine());
           System.out.println("List Type Motor:");
        System.out.println("=> 1. BeATStreet = Rp. 19.400.000");
        System.out.println("=> 2. BeATDeluxs = Rp. 18.530.000");
        System.out.println("=> 3. Scoopy     = Rp. 22.525.000");
        System.out.println("=> 4. PCX160     = Rp. 33.750.000");
        System.out.println("=> 5. Vario160   = Rp. 27.600.000");
            System.out.print("Masukkan Kode Type Motor baru\t\t: ");
            int kode = Integer.parseInt(br.readLine());
            kodeMotor.set(index, kode);
            hargaPokok.set(index, getHarga(kode));

            System.out.print("Masukkan Angsuran baru [12-24-36]\t\t: ");
            int angsur = Integer.parseInt(br.readLine());
            angsuran.set(index, angsur);
            totalBunga.set(index, (angsur / 12) * 0.10);

            System.out.print("Masukkan Uang Muka baru\t\t: ");
            jumlahDp.set(index, Integer.parseInt(br.readLine()));

            System.out.println("Data berhasil diubah!");
        } else {
            System.out.println("Data tidak ditemukan!");
        }
    }

    static void deletePelanggan() throws IOException {
        System.out.println("\n======== HAPUS DATA ========");
        showAllPelanggan();
        System.out.print("Pilih nomor Customer yang ingin dihapus: ");
        int index = Integer.parseInt(br.readLine()) - 1;

        if (index >= 0 && index < noPelanggan.size()) {
            noPelanggan.remove(index);
            namaPelanggan.remove(index);
            kodeMotor.remove(index);
            hargaPokok.remove(index);
            angsuran.remove(index);
            totalBunga.remove(index);
            jumlahDp.remove(index);

            System.out.println("Data berhasil dihapus!");
        } else {
            System.out.println("Data tidak ditemukan!");
        }
    }

    static double getHarga(int kode) {
        switch (kode) {
            case 1:
                return 19400000;
            case 2:
                return 18530000;
            case 3:
                return 22525000;
            case 4:
                return 33750000;
            case 5:
                return 27600000;
            default:
                return 0;
        }
    }

    static String getMotor(int kode) {
        switch (kode) {
            case 1:
                return "BeATStreet";
            case 2:
                return "BeATDeluxs";
            case 3:
                return "Scoopy";
            case 4:
                return "PCX160";
            case 5:
                return "Vario160";
            default:
                return "Tidak Diketahui";
        }
    }
}
