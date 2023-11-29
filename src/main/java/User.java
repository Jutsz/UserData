import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String userName;
private Long processID;
private Date enterTime;
private Date expirationTime;


public User() {
	}

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public Long getProcessID() {
	return processID;
}
public void setProcessID(Long processID) {
	this.processID = processID;
}
public Date getEnterTime() {
	return enterTime;
}
public void setEnterTime(Date enterTime) {
	this.enterTime = enterTime;
}
public Date getExpirationTime() {
	return expirationTime;
}

public void setExpirationTime(Date expirationTime) {
	this.expirationTime = expirationTime;
}





}
