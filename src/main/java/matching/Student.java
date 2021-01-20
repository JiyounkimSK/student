package matching;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Student_table")
public class Student {

    @Id
    private String student;
    private Integer point;

    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }

    public Integer getPoint() {
        return point;
    }
    public void setPoint(Integer point) {
        this.point = point;
    }




}
