package Himafor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;
import java.util.Calendar;

public class Main extends JFrame {

    public Main() {
        super("Sistem Informasi Organisasi HIMAFOR");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("1. Anggota", new PanelAnggota());
        tabbedPane.addTab("2. Pengurus", new PanelPengurus());
        tabbedPane.addTab("3. Kegiatan", new PanelKegiatan());
        tabbedPane.addTab("4. Divisi", new PanelDivisi());
        tabbedPane.addTab("5. Keanggotaan Divisi", new PanelKeanggotaanDivisi());
        tabbedPane.addTab("6. Presensi Kegiatan", new PanelPresensiKegiatan());

        // Listener: Saat pindah tab, refresh isi combobox agar data terbaru muncul
        tabbedPane.addChangeListener(e -> {
            Component c = tabbedPane.getSelectedComponent();
            if (c instanceof Refreshable) {
                ((Refreshable) c).refreshCombo();
            }
        });

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        new Main();
    }

    // Interface agar Tab bisa direfresh otomatis
    interface Refreshable {
        void refreshCombo();
    }

    // Helper untuk mengambil ID dari String "101 - Nama"
    // Logika: Kita split berdasarkan " - " dan ambil bagian depan
    private String ambilID(Object item) {
        if (item == null) return "";
        String s = item.toString();
        if (s.contains(" - ")) {
            return s.split(" - ")[0];
        }
        return s;
    }

    // Helper UI Standard
    private void addInput(JPanel p, String label, JComponent field) {
        p.add(new JLabel(label));
        p.add(field);
    }

    
    // 1. PANEL ANGGOTA 
   
    class PanelAnggota extends JPanel implements Refreshable {
        JTextField txtID, txtNama, txtNIM, txtJurusan, txtAngkatan, txtEmail;
        JComboBox<String> cmbStatus; // Status dibuat pilihan
        JTable tabel;
        DefaultTableModel model;
        AnggotaCRUD dao = new AnggotaCRUD();

        public PanelAnggota() {
            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(8, 2, 5, 5));
            form.setBorder(BorderFactory.createTitledBorder("Input Data Anggota"));

            txtID = new JTextField(); txtNama = new JTextField(); txtNIM = new JTextField();
            txtJurusan = new JTextField(); txtAngkatan = new JTextField(); txtEmail = new JTextField();
            
            // Status jadi Pilihan
            cmbStatus = new JComboBox<>(new String[]{"Aktif", "Tidak Aktif", "Alumni"});

            addInput(form, "ID Anggota:", txtID);
            addInput(form, "Nama:", txtNama);
            addInput(form, "NIM:", txtNIM);
            addInput(form, "Jurusan:", txtJurusan);
            addInput(form, "Angkatan:", txtAngkatan);
            addInput(form, "Email:", txtEmail);
            addInput(form, "Status Keaktifan:", cmbStatus);

            JPanel btnPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan"), btnEdit = new JButton("Edit"), btnHapus = new JButton("Hapus"), btnReset = new JButton("Reset");
            btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus); btnPanel.add(btnReset);
            form.add(new JLabel("")); form.add(btnPanel);

            add(form, BorderLayout.NORTH);

            model = new DefaultTableModel(new String[]{"ID", "Nama", "NIM", "Jurusan", "Angkatan", "Email", "Status"}, 0);
            tabel = new JTable(model);
            add(new JScrollPane(tabel), BorderLayout.CENTER);

            tabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int r = tabel.getSelectedRow();
                    txtID.setText(model.getValueAt(r, 0).toString());
                    txtNama.setText(model.getValueAt(r, 1).toString());
                    txtNIM.setText(model.getValueAt(r, 2).toString());
                    txtJurusan.setText(model.getValueAt(r, 3).toString());
                    txtAngkatan.setText(model.getValueAt(r, 4).toString());
                    txtEmail.setText(model.getValueAt(r, 5).toString());
                    cmbStatus.setSelectedItem(model.getValueAt(r, 6).toString());
                }
            });

            btnSimpan.addActionListener(e -> {
                dao.simpanAnggota(new Anggota(txtID.getText(), txtNama.getText(), txtNIM.getText(), txtJurusan.getText(), txtAngkatan.getText(), txtEmail.getText(), cmbStatus.getSelectedItem().toString()));
                refreshData(); bersihkan();
            });
            btnEdit.addActionListener(e -> {
                dao.updateAnggota(new Anggota(txtID.getText(), txtNama.getText(), txtNIM.getText(), txtJurusan.getText(), txtAngkatan.getText(), txtEmail.getText(), cmbStatus.getSelectedItem().toString()));
                refreshData(); bersihkan();
            });
            btnHapus.addActionListener(e -> { dao.hapusAnggota(txtID.getText()); refreshData(); bersihkan(); });
            btnReset.addActionListener(e -> bersihkan());
            refreshData();
        }

        void refreshData() { model.setRowCount(0); for (Anggota a : dao.getAllAnggota()) model.addRow(new Object[]{a.getIdAnggota(), a.getNama(), a.getNim(), a.getJurusan(), a.getAngkatan(), a.getEmail(), a.getStatusKeaktifan()}); }
        void bersihkan() { txtID.setText(""); txtNama.setText(""); txtNIM.setText(""); txtJurusan.setText(""); txtAngkatan.setText(""); txtEmail.setText(""); }
        public void refreshCombo() {} // Tidak ada combo foreign key disini
    }

    // =======================================================================
    // 2. PANEL PENGURUS (Pilih Anggota dari List)
    // =======================================================================
    class PanelPengurus extends JPanel implements Refreshable {
        JTextField txtID, txtJabatan, txtPeriode;
        JComboBox<String> cmbAnggota; // Ganti Textfield jadi ComboBox
        DefaultTableModel model;
        JTable tabel;
        PengurusCRUD dao = new PengurusCRUD();
        AnggotaCRUD anggotaDAO = new AnggotaCRUD();

        public PanelPengurus() {
            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
            form.setBorder(BorderFactory.createTitledBorder("Input Pengurus"));
            
            txtID = new JTextField(); 
            cmbAnggota = new JComboBox<>(); // Dropdown
            txtJabatan = new JTextField(); txtPeriode = new JTextField();

            addInput(form, "ID Pengurus:", txtID);
            addInput(form, "Pilih Anggota:", cmbAnggota); // User pilih disini
            addInput(form, "Jabatan:", txtJabatan);
            addInput(form, "Periode:", txtPeriode);

            JPanel btnPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan"), btnEdit = new JButton("Edit"), btnHapus = new JButton("Hapus");
            btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus);
            form.add(new JLabel("")); form.add(btnPanel);

            add(form, BorderLayout.NORTH);

            model = new DefaultTableModel(new String[]{"ID Pengurus", "ID Anggota", "Jabatan", "Periode"}, 0);
            tabel = new JTable(model);
            add(new JScrollPane(tabel), BorderLayout.CENTER);

            tabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int r = tabel.getSelectedRow();
                    txtID.setText(model.getValueAt(r, 0).toString());
                    // Set Combo Box sesuai ID
                    String idTabel = model.getValueAt(r, 1).toString();
                    for (int i = 0; i < cmbAnggota.getItemCount(); i++) {
                        if (cmbAnggota.getItemAt(i).startsWith(idTabel)) {
                            cmbAnggota.setSelectedIndex(i);
                            break;
                        }
                    }
                    txtJabatan.setText(model.getValueAt(r, 2).toString());
                    txtPeriode.setText(model.getValueAt(r, 3).toString());
                }
            });

            btnSimpan.addActionListener(e -> { 
                dao.simpan(new Pengurus(txtID.getText(), ambilID(cmbAnggota.getSelectedItem()), txtJabatan.getText(), txtPeriode.getText())); 
                refreshData(); 
            });
            btnEdit.addActionListener(e -> { 
                dao.update(new Pengurus(txtID.getText(), ambilID(cmbAnggota.getSelectedItem()), txtJabatan.getText(), txtPeriode.getText())); 
                refreshData(); 
            });
            btnHapus.addActionListener(e -> { dao.hapus(txtID.getText()); refreshData(); });
            
            refreshCombo(); // Load data combo awal
            refreshData();
        }
        
        
        public void refreshCombo() {
            cmbAnggota.removeAllItems();
            for (String s : anggotaDAO.getComboAnggota()) {
                cmbAnggota.addItem(s);
            }
        }
        void refreshData() { model.setRowCount(0); for (Pengurus p : dao.getAll()) model.addRow(new Object[]{p.getIdPengurus(), p.getIdAnggota(), p.getJabatan(), p.getPeriodeJabatan()}); }
    }

    // =======================================================================
    // 3. PANEL KEGIATAN (Pilih Tanggal Otomatis)
    // =======================================================================
    class PanelKegiatan extends JPanel implements Refreshable {
        JTextField txtID, txtNama, txtDeskripsi, txtPJ;
        JSpinner dateSpinner; // Ganti TextField Tanggal jadi Spinner
        DefaultTableModel model;
        JTable tabel;
        KegiatanCRUD dao = new KegiatanCRUD();

        public PanelKegiatan() {
            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
            form.setBorder(BorderFactory.createTitledBorder("Input Kegiatan"));

            txtID = new JTextField(); txtNama = new JTextField(); 
            txtDeskripsi = new JTextField(); txtPJ = new JTextField();
            
            // Setup Date Picker
            SpinnerDateModel dateModel = new SpinnerDateModel(new java.util.Date(), null, null, Calendar.DAY_OF_MONTH);
            dateSpinner = new JSpinner(dateModel);
            JSpinner.DateEditor de = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
            dateSpinner.setEditor(de);

            addInput(form, "ID Kegiatan:", txtID);
            addInput(form, "Nama Kegiatan:", txtNama);
            addInput(form, "Tanggal:", dateSpinner); // Tampilan User Friendly
            addInput(form, "Deskripsi:", txtDeskripsi);
            addInput(form, "Penanggung Jawab:", txtPJ);

            JPanel btnPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan"), btnEdit = new JButton("Edit"), btnHapus = new JButton("Hapus");
            btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus);
            form.add(new JLabel("")); form.add(btnPanel);
            add(form, BorderLayout.NORTH);

            model = new DefaultTableModel(new String[]{"ID", "Nama", "Tanggal", "Deskripsi", "PJ"}, 0);
            tabel = new JTable(model);
            add(new JScrollPane(tabel), BorderLayout.CENTER);

            tabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int r = tabel.getSelectedRow();
                    txtID.setText(model.getValueAt(r, 0).toString());
                    txtNama.setText(model.getValueAt(r, 1).toString());
                    try {
                        dateSpinner.setValue(model.getValueAt(r, 2)); // Set tanggal spinner
                    } catch(Exception ex) {}
                    txtDeskripsi.setText(model.getValueAt(r, 3).toString());
                    txtPJ.setText(model.getValueAt(r, 4).toString());
                }
            });

            btnSimpan.addActionListener(e -> { 
                java.util.Date utilDate = (java.util.Date) dateSpinner.getValue();
                Date sqlDate = new Date(utilDate.getTime());
                dao.simpan(new Kegiatan(txtID.getText(), txtNama.getText(), sqlDate, txtDeskripsi.getText(), txtPJ.getText())); 
                refreshData();
            });
            btnEdit.addActionListener(e -> { 
                java.util.Date utilDate = (java.util.Date) dateSpinner.getValue();
                Date sqlDate = new Date(utilDate.getTime());
                dao.update(new Kegiatan(txtID.getText(), txtNama.getText(), sqlDate, txtDeskripsi.getText(), txtPJ.getText())); 
                refreshData();
            });
            btnHapus.addActionListener(e -> { dao.hapus(txtID.getText()); refreshData(); });
            refreshData();
        }
        void refreshData() { model.setRowCount(0); for (Kegiatan k : dao.getAll()) model.addRow(new Object[]{k.getIdKegiatan(), k.getNamaKegiatan(), k.getTanggal(), k.getDeskripsi(), k.getPenanggungJawab()}); }
        public void refreshCombo() {}
    }

    // =======================================================================
    // 4. PANEL DIVISI
    // =======================================================================
    class PanelDivisi extends JPanel implements Refreshable {
        JTextField txtID, txtNama, txtDeskripsi;
        DefaultTableModel model;
        JTable tabel;
        DivisiCRUD dao = new DivisiCRUD();

        public PanelDivisi() {
            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
            form.setBorder(BorderFactory.createTitledBorder("Input Divisi"));
            
            txtID = new JTextField(); txtNama = new JTextField(); txtDeskripsi = new JTextField();
            addInput(form, "ID Divisi:", txtID);
            addInput(form, "Nama Divisi:", txtNama);
            addInput(form, "Deskripsi:", txtDeskripsi);

            JPanel btnPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan"), btnEdit = new JButton("Edit"), btnHapus = new JButton("Hapus");
            btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus);
            form.add(new JLabel("")); form.add(btnPanel);
            add(form, BorderLayout.NORTH);

            model = new DefaultTableModel(new String[]{"ID", "Nama", "Deskripsi"}, 0);
            tabel = new JTable(model);
            add(new JScrollPane(tabel), BorderLayout.CENTER);

            tabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int r = tabel.getSelectedRow();
                    txtID.setText(model.getValueAt(r, 0).toString());
                    txtNama.setText(model.getValueAt(r, 1).toString());
                    txtDeskripsi.setText(model.getValueAt(r, 2).toString());
                }
            });

            btnSimpan.addActionListener(e -> { dao.simpan(new Divisi(txtID.getText(), txtNama.getText(), txtDeskripsi.getText())); refreshData(); });
            btnEdit.addActionListener(e -> { dao.update(new Divisi(txtID.getText(), txtNama.getText(), txtDeskripsi.getText())); refreshData(); });
            btnHapus.addActionListener(e -> { dao.hapus(txtID.getText()); refreshData(); });
            refreshData();
        }
        void refreshData() { model.setRowCount(0); for (Divisi d : dao.getAll()) model.addRow(new Object[]{d.getIdDivisi(), d.getNamaDivisi(), d.getDeskripsi()}); }
        public void refreshCombo() {}
    }

    // =======================================================================
    // 5. PANEL KEANGGOTAAN DIVISI (Pilih Divisi dari List)
    // =======================================================================
    class PanelKeanggotaanDivisi extends JPanel implements Refreshable {
        JTextField txtID, txtPeriode;
        JComboBox<String> cmbDivisi; // Pilih Divisi
        DefaultTableModel model;
        JTable tabel;
        KeanggotaanDivisiCRUD dao = new KeanggotaanDivisiCRUD();
        DivisiCRUD divisiDAO = new DivisiCRUD();

        public PanelKeanggotaanDivisi() {
            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
            form.setBorder(BorderFactory.createTitledBorder("Keanggotaan Divisi"));
            
            txtID = new JTextField(); cmbDivisi = new JComboBox<>(); txtPeriode = new JTextField();
            addInput(form, "ID Keanggotaan:", txtID);
            addInput(form, "Pilih Divisi:", cmbDivisi); // User Pilih disini
            addInput(form, "Periode:", txtPeriode);

            JPanel btnPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan"), btnEdit = new JButton("Edit"), btnHapus = new JButton("Hapus");
            btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus);
            form.add(new JLabel("")); form.add(btnPanel);
            add(form, BorderLayout.NORTH);

            model = new DefaultTableModel(new String[]{"ID Keanggotaan", "ID Divisi", "Periode"}, 0);
            tabel = new JTable(model);
            add(new JScrollPane(tabel), BorderLayout.CENTER);

            tabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int r = tabel.getSelectedRow();
                    txtID.setText(model.getValueAt(r, 0).toString());
                    String idDiv = model.getValueAt(r, 1).toString();
                    for(int i=0; i<cmbDivisi.getItemCount(); i++) {
                        if(cmbDivisi.getItemAt(i).startsWith(idDiv)) { cmbDivisi.setSelectedIndex(i); break; }
                    }
                    txtPeriode.setText(model.getValueAt(r, 2).toString());
                }
            });

            btnSimpan.addActionListener(e -> { dao.simpan(new KeanggotaanDivisi(txtID.getText(), ambilID(cmbDivisi.getSelectedItem()), txtPeriode.getText())); refreshData(); });
            btnEdit.addActionListener(e -> { dao.update(new KeanggotaanDivisi(txtID.getText(), ambilID(cmbDivisi.getSelectedItem()), txtPeriode.getText())); refreshData(); });
            btnHapus.addActionListener(e -> { dao.hapus(txtID.getText()); refreshData(); });
            refreshCombo();
            refreshData();
        }
        public void refreshCombo() {
            cmbDivisi.removeAllItems();
            for(String s : divisiDAO.getComboDivisi()) cmbDivisi.addItem(s);
        }
        void refreshData() { model.setRowCount(0); for (KeanggotaanDivisi k : dao.getAll()) model.addRow(new Object[]{k.getIdKeanggotaan(), k.getIdDivisi(), k.getPeriode()}); }
    }

    // =======================================================================
    // 6. PANEL PRESENSI (Full Otomatis)
    // =======================================================================
    class PanelPresensiKegiatan extends JPanel implements Refreshable {
        JTextField txtID;
        JComboBox<String> cmbAnggota, cmbKegiatan, cmbStatus;
        DefaultTableModel model;
        JTable tabel;
        PresensiKegiatanCRUD dao = new PresensiKegiatanCRUD();
        AnggotaCRUD anggotaDAO = new AnggotaCRUD();
        KegiatanCRUD kegiatanDAO = new KegiatanCRUD();

        public PanelPresensiKegiatan() {
            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
            form.setBorder(BorderFactory.createTitledBorder("Input Presensi"));
            
            txtID = new JTextField();
            cmbAnggota = new JComboBox<>(); // Pilih Orang
            cmbKegiatan = new JComboBox<>(); // Pilih Acara
            cmbStatus = new JComboBox<>(new String[]{"Hadir", "Izin", "Sakit", "Alpha"}); // Pilih Status

            addInput(form, "ID Presensi:", txtID);
            addInput(form, "Pilih Anggota:", cmbAnggota);
            addInput(form, "Pilih Kegiatan:", cmbKegiatan);
            addInput(form, "Status Kehadiran:", cmbStatus);

            JPanel btnPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan"), btnEdit = new JButton("Edit"), btnHapus = new JButton("Hapus");
            btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus);
            form.add(new JLabel("")); form.add(btnPanel);
            add(form, BorderLayout.NORTH);

            model = new DefaultTableModel(new String[]{"ID Presensi", "ID Anggota", "ID Kegiatan", "Status"}, 0);
            tabel = new JTable(model);
            add(new JScrollPane(tabel), BorderLayout.CENTER);

            tabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int r = tabel.getSelectedRow();
                    txtID.setText(model.getValueAt(r, 0).toString());
                    
                    String idAng = model.getValueAt(r, 1).toString();
                    for(int i=0; i<cmbAnggota.getItemCount(); i++) {
                        if(cmbAnggota.getItemAt(i).startsWith(idAng)) { cmbAnggota.setSelectedIndex(i); break; }
                    }

                    String idKeg = model.getValueAt(r, 2).toString();
                    for(int i=0; i<cmbKegiatan.getItemCount(); i++) {
                        if(cmbKegiatan.getItemAt(i).startsWith(idKeg)) { cmbKegiatan.setSelectedIndex(i); break; }
                    }

                    cmbStatus.setSelectedItem(model.getValueAt(r, 3).toString());
                }
            });

            btnSimpan.addActionListener(e -> { 
                dao.simpan(new PresensiKegiatan(txtID.getText(), ambilID(cmbAnggota.getSelectedItem()), ambilID(cmbKegiatan.getSelectedItem()), cmbStatus.getSelectedItem().toString())); 
                refreshData(); 
            });
            btnEdit.addActionListener(e -> { 
                dao.update(new PresensiKegiatan(txtID.getText(), ambilID(cmbAnggota.getSelectedItem()), ambilID(cmbKegiatan.getSelectedItem()), cmbStatus.getSelectedItem().toString())); 
                refreshData(); 
            });
            btnHapus.addActionListener(e -> { dao.hapus(txtID.getText()); refreshData(); });
            
            refreshCombo();
            refreshData();
        }

        public void refreshCombo() {
            cmbAnggota.removeAllItems();
            for(String s : anggotaDAO.getComboAnggota()) cmbAnggota.addItem(s);
            
            cmbKegiatan.removeAllItems();
            for(String s : kegiatanDAO.getComboKegiatan()) cmbKegiatan.addItem(s);
        }

        void refreshData() { model.setRowCount(0); for (PresensiKegiatan p : dao.getAll()) model.addRow(new Object[]{p.getIdPresensi(), p.getIdAnggota(), p.getIdKegiatan(), p.getStatusHadir()}); }
    }
}