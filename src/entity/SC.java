package entity;

public class SC {
    private String sno;
    private String cno;
    private Integer grade;

    public SC() {
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSno() {
        return sno;
    }

    public String getCno() {
        return cno;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "SC{" +
                "sno='" + sno + '\'' +
                ", cno='" + cno + '\'' +
                ", grade=" + grade +
                '}';
    }
}
