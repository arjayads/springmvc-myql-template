package tsi.map.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date attemptDateTime;

    @Column
    private String ip;


    @Column
    private Boolean successful;

    @Column
    private Boolean isMaster = false;

    public Login() {}

    public Login(String username, Date attemptDateTime, String ip, Boolean successful, Boolean isMaster) {
        this.username = username;
        this.attemptDateTime = attemptDateTime;
        this.ip = ip;
        this.successful = successful;
        this.isMaster = isMaster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAttemptDateTime() {
        return attemptDateTime;
    }

    public void setAttemptDateTime(Date attemptDateTime) {
        this.attemptDateTime = attemptDateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Boolean getMaster() {
        return isMaster;
    }

    public void setMaster(Boolean master) {
        isMaster = master;
    }
}
