package Practise.Querys;


public class Ders {


    private int id;
    private String ders_isim;
    private int egitim_suresi;

    public Ders(int id, String ders_isim, int egitim_suresi) {
        this.id = id;
        this.ders_isim = ders_isim;
        this.egitim_suresi = egitim_suresi;
    }

    public String getDers_isim() {
        return ders_isim;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    @Override
    public String toString() {
        return "Ders{" +
                "id=" + id +
                ", ders_isim='" + ders_isim + '\'' +
                ", egitim_suresi=" + egitim_suresi +
                '}';
    }

    public int getEgitim_suresi() {
        return egitim_suresi;
    }

    public void setEgitim_suresi(int egitim_suresi) {
        this.egitim_suresi = egitim_suresi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }
}