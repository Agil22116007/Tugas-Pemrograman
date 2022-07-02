package com.tugas;
//Muh. Said Agil
//22116007
import java.util.ArrayList;
import java.util.Scanner;

public class data {
    public static void main(String[] args) {

        ArrayList<String> nama = new ArrayList<>();
        ArrayList<String> NIM = new ArrayList<>();
        ArrayList<String> alamat = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int pilihan;

        do{
            System.out.println("Program Data Mahasiswa");
            System.out.println("======================");
            System.out.println("1. Tambahkan");
            System.out.println("2. Tampilkan");
            System.out.println("3. Cari");
            System.out.println("4. Hapus");
            System.out.println("5. Keluar");
            System.out.print("\nPilih Menu : ");

            pilihan = input.nextInt();

            if (pilihan == 1){
                System.out.print("Masukan Nama : ");
                String nm = input.next();
                nama.add(nm);

                System.out.print("Masukan NIM : ");
                String nim= input.next();
                NIM.add(nim);

                System.out.print("Masukan Alamat : ");
                String alm = input.next();
                alamat.add(alm);
            } else if (pilihan == 2) {
                System.out.println("Data Mahasiswa : ");
                System.out.println("=================");

                for (int i = 0; i <NIM.size(); i++){
                    System.out.println(i + 1 + "." + NIM.get(i));
                }
                System.out.println("Untuk melihat data lengkap, cari data(3)");

            } else if (pilihan == 3) {
                System.out.print("Masukan data nomor yang ingin di cari : ");
                int cari = input.nextInt();
                if (cari == 1){
                    System.out.println("Nama : " + nama.get(0));
                    System.out.println("NIM : " + NIM.get(0));
                    System.out.println("Alamat : " + alamat.get(0));
                    System.out.println("======================");
                } if (cari == 2){
                    System.out.println("Nama : " + nama.get(1));
                    System.out.println("NIM : " + NIM.get(1));
                    System.out.println("Alamat : " + alamat.get(1));
                    System.out.println("======================");
                }

            } else if (pilihan == 4) {
                System.out.println("Data Mahasiswa");
                System.out.println("==============");

                for (int i = 0; i <NIM.size(); i++){
                    System.out.println(i + 1 + "." + NIM.get(i));
                }
                System.out.print("Masukan nim yang ingin di hapus : ");
                String nim = input.next();
                NIM.remove(nim);

            } else if (pilihan == 5) {
                System.out.println("Selesai");
            } else {
                System.err.println("Menu tidak tersedia");
            }

        }while (pilihan != 5);
    }
}
