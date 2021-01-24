package entity;

public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private Integer sage;
    private String sdept;

    public Student() {
    }

    public Student(String sno, String sname, String ssex, int sage, String sddept) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sage = sage;
        this.sdept = sddept;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sage=" + sage +
                ", sddept='" + sdept + '\'' +
                '}';
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public void setSdept(String sddept) {
        this.sdept = sddept;
    }

    public String getSno() {
        return sno;
    }

    public String getSname() {
        return sname;
    }

    public String getSsex() {
        return ssex;
    }

    public Integer getSage() {
        return sage;
    }

    public String getSdept() {
        return sdept;
    }
}
