
package com.axway.emailClient.config.schema;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "host",
    "port",
    "username",
    "password",
    "TLS",
    "protocol"
})
public class MailServerDetail {

    @JsonProperty("host")
    private String host;
    @JsonProperty("port")
    private Integer port;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("TLS")
    private String tLS;
    @JsonProperty("protocol")
    private MailServerDetail.Protocol protocol = MailServerDetail.Protocol.fromValue("smtp");
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty("port")
    public Integer getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(Integer port) {
        this.port = port;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("TLS")
    public String getTLS() {
        return tLS;
    }

    @JsonProperty("TLS")
    public void setTLS(String tLS) {
        this.tLS = tLS;
    }

    @JsonProperty("protocol")
    public MailServerDetail.Protocol getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(MailServerDetail.Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(host).append(port).append(username).append(password).append(tLS).append(protocol).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MailServerDetail) == false) {
            return false;
        }
        MailServerDetail rhs = ((MailServerDetail) other);
        return new EqualsBuilder().append(host, rhs.host).append(port, rhs.port).append(username, rhs.username).append(password, rhs.password).append(tLS, rhs.tLS).append(protocol, rhs.protocol).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    public enum Protocol {

        SMTP("smtp"),
        IMAP("imap");
        private final String value;
        private final static Map<String, MailServerDetail.Protocol> CONSTANTS = new HashMap<String, MailServerDetail.Protocol>();

        static {
            for (MailServerDetail.Protocol c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Protocol(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static MailServerDetail.Protocol fromValue(String value) {
            MailServerDetail.Protocol constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
