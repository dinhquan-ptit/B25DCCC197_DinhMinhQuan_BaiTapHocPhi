package baithuchanh.baitrenlop;
import java.util.ArrayList;
import java.util.Scanner;
class SinhVien{
    String ma;
    String ten;
    String ngaySinh;
    SinhVien(String ma , String ten , String ngaySinh){
        this.ma = ma.trim();
        this. ten = chuanHoaTen(ten);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }
    String chuanHoaTen(String s){
        String[] tu = s.trim().split("\\s+");
        String kq = "";
        for (int i = 0 ; i < tu.length; i++){
            String t = tu[i].toLowerCase();
            kq += Character.toUpperCase(t.charAt(0)) + t.substring(1);
            if (i < tu.length - 1){
                kq += " ";
            }
        }
    return kq;
    }
    String chuanHoaNgay(String s) {
            String[] p = s.trim().split("/");
            String d = p[0];
            String m = p[1];
            String y = p[2];
            if (d.length() == 1) {
                d = "0" + d;
            }
            if (m.length() == 1) {
                m = "0" + m;
            }
            return d + "/" + m + "/" + y;
        }

        public String toString() {
            return ma + " " + ten + " " + ngaySinh;
        }
    }
public class bai1 {
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        ArrayList<SinhVien> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ns = sc.nextLine();
            ds.add(new SinhVien(ma, ten, ns));
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
    }
    
}
