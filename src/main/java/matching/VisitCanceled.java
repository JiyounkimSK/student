
package matching;

public class VisitCanceled extends AbstractEvent {

    private Long matchId;
    private String student;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
