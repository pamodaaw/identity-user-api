package org.wso2.carbon.identity.rest.api.user.session.v1.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.rest.api.user.session.v1.dto.ApplicationDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class SessionDTO  {
  
  
  
  private List<ApplicationDTO> applications = new ArrayList<ApplicationDTO>();
  
  
  private String userAgent = null;
  
  
  private String ip = null;
  
  
  private String loginTime = null;
  
  
  private String lastAccessTime = null;
  
  
  private String sessionId = null;

  
  /**
   * List of active applications in the session
   **/
  @ApiModelProperty(value = "List of active applications in the session")
  @JsonProperty("applications")
  public List<ApplicationDTO> getApplications() {
    return applications;
  }
  public void setApplications(List<ApplicationDTO> applications) {
    this.applications = applications;
  }

  
  /**
   * User agent of the session
   **/
  @ApiModelProperty(value = "User agent of the session")
  @JsonProperty("userAgent")
  public String getUserAgent() {
    return userAgent;
  }
  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  
  /**
   * IP address of the session
   **/
  @ApiModelProperty(value = "IP address of the session")
  @JsonProperty("ip")
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }

  
  /**
   * Login time of the session
   **/
  @ApiModelProperty(value = "Login time of the session")
  @JsonProperty("loginTime")
  public String getLoginTime() {
    return loginTime;
  }
  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }

  
  /**
   * Last access time of the session
   **/
  @ApiModelProperty(value = "Last access time of the session")
  @JsonProperty("lastAccessTime")
  public String getLastAccessTime() {
    return lastAccessTime;
  }
  public void setLastAccessTime(String lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }

  
  /**
   * ID of the session
   **/
  @ApiModelProperty(value = "ID of the session")
  @JsonProperty("sessionId")
  public String getSessionId() {
    return sessionId;
  }
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionDTO {\n");
    
    sb.append("  applications: ").append(applications).append("\n");
    sb.append("  userAgent: ").append(userAgent).append("\n");
    sb.append("  ip: ").append(ip).append("\n");
    sb.append("  loginTime: ").append(loginTime).append("\n");
    sb.append("  lastAccessTime: ").append(lastAccessTime).append("\n");
    sb.append("  sessionId: ").append(sessionId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
