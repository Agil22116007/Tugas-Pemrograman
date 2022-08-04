package com.tugas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private JPanel panelMain;
    private JTable jTableMahasiswa;
    private JTextField txtNIM;
    private JTextField txtNama;
    private JTextField txtIPK;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTextField txtFilter;
    private JButton filterButton;

    private DefaultTableModel defaultTableModel = new DefaultTableModel();


    public MainScreen() {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        refreshTable(getMahasiswa());


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String NIM = txtNIM.getText();
                double ipk = Double.parseDouble(txtIPK.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNIM(NIM);
                mahasiswa.setNama(nama);
                mahasiswa.setIPK(ipk);

                clearFrom();
                insertMahasiswa(mahasiswa);
                refreshTable(getMahasiswa());
            }
        });
        jTableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = jTableMahasiswa.getSelectedRow();

                String nim = jTableMahasiswa.getValueAt(row,0).toString();
                String nama = jTableMahasiswa.getValueAt(row,1).toString();
                String ipk = jTableMahasiswa.getValueAt(row,2).toString();

                txtNIM.setText(nim);
                txtNama.setText(nama);
                txtIPK.setText(ipk);

            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String nim = txtNIM.getText();
                double ipk = Double.parseDouble(txtIPK.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNIM(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIPK(ipk);

                clearFrom();
                UpdateMahasiswa(mahasiswa);
                refreshTable(getMahasiswa());

            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtNIM.getText();

                clearFrom();
                deleteMahasiswa(nim);
                refreshTable(getMahasiswa());
            }
        });


        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtFilter.getText();
                refreshTable(filterMahasiswa(nama));
            }

        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrom();
            }
        });
    }

    private static ResultSet executequery(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (Exception e) {
            return null;
        }
    }

    private static void executesql (String sql){
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (Exception e){

        }
    }

    public void refreshTable(List<Mahasiswa> arrayListMahasiswa) {
        Object data [][] = new Object[arrayListMahasiswa.size()][3];

        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            data[i] = new Object[]{
                    arrayListMahasiswa.get(i).getNIM(),
                    arrayListMahasiswa.get(i).getNama(),
                    arrayListMahasiswa.get(i).getIPK()
            };
        }
        defaultTableModel = new DefaultTableModel(data, new String[]{"NIM", "NAMA", "IPK"});
        jTableMahasiswa.setModel(defaultTableModel);
    }

    private static void insertMahasiswa(Mahasiswa mahasiswa) {
        String sql = "insert into mahasiswa values (" +
                "'" + mahasiswa.getNIM() + "'," +
                "'" + mahasiswa.getNama() + "'," +
                "'" + mahasiswa.getIPK() + "')";
        executesql(sql);
    }

    private static void UpdateMahasiswa(Mahasiswa mahasiswa) {
        String sql = "update mahasiswa set " +
                "nama = '" + mahasiswa.getNama() + "'," +
                "ipk = '" + mahasiswa.getIPK() + "'" +
                "where nim = '" + mahasiswa.getNIM() + "'";
        executesql(sql);
    }

    private static void deleteMahasiswa(String nim) {
        String sql = "delete from mahasiswa " +
                "where nim = '" + nim + "'";
        executesql(sql);
    }

    private static List<Mahasiswa> filterMahasiswa(String filternama) {
        List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();
        ResultSet resultSet = executequery("select * from mahasiswa where nama like '%" + filternama + "%'");

        try {
            while (resultSet.next()) {
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nama);
                System.out.println(nim);
                System.out.println(ipk);

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNIM(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIPK(ipk);

                arrayListMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arrayListMahasiswa;
    }

    private static List<Mahasiswa> getMahasiswa() {
        List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();
        ResultSet resultSet = executequery("select * from mahasiswa");

        try {
            while (resultSet.next()) {
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nama);
                System.out.println(nim);
                System.out.println(ipk);

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNIM(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIPK(ipk);

                arrayListMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arrayListMahasiswa;
    }


    private void clearFrom(){
        txtNama.setText("");
        txtIPK.setText("");
        txtNIM.setText("");
    }

    public static void main (String[] args){
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);

    }
}
