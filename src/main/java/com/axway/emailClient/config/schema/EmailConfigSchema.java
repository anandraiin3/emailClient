
package com.axway.emailClient.config.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sendTo",
    "ccTo",
    "bccTo",
    "subject",
    "from",
    "mailServerDetails",
    "attachments",
    "template"
})
public class EmailConfigSchema {

    @JsonProperty("sendTo")
    private List<String> sendTo = new ArrayList<String>();
    @JsonProperty("ccTo")
    private List<String> ccTo = new ArrayList<String>();
    @JsonProperty("bccTo")
    private List<String> bccTo = new ArrayList<String>();
    @JsonProperty("subject")
    @NotNull
    private String subject;
    @JsonProperty("from")
    private String from;
    @JsonProperty("mailServerDetails")
    private List<MailServerDetail> mailServerDetails = new ArrayList<MailServerDetail>();
    @JsonProperty("attachments")
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @JsonProperty("template")
    private Template template;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sendTo")
    public List<String> getSendTo() {
        return sendTo;
    }

    @JsonProperty("sendTo")
    public void setSendTo(List<String> sendTo) {
        this.sendTo = sendTo;
    }

    @JsonProperty("ccTo")
    public List<String> getCcTo() {
        return ccTo;
    }

    @JsonProperty("ccTo")
    public void setCcTo(List<String> ccTo) {
        this.ccTo = ccTo;
    }

    @JsonProperty("bccTo")
    public List<String> getBccTo() {
        return bccTo;
    }

    @JsonProperty("bccTo")
    public void setBccTo(List<String> bccTo) {
        this.bccTo = bccTo;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    @JsonProperty("mailServerDetails")
    public List<MailServerDetail> getMailServerDetails() {
        return mailServerDetails;
    }

    @JsonProperty("mailServerDetails")
    public void setMailServerDetails(List<MailServerDetail> mailServerDetails) {
        this.mailServerDetails = mailServerDetails;
    }

    @JsonProperty("attachments")
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("template")
    public Template getTemplate() {
        return template;
    }

    @JsonProperty("template")
    public void setTemplate(Template template) {
        this.template = template;
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
        return new HashCodeBuilder().append(sendTo).append(ccTo).append(bccTo).append(subject).append(from).append(mailServerDetails).append(attachments).append(template).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EmailConfigSchema) == false) {
            return false;
        }
        EmailConfigSchema rhs = ((EmailConfigSchema) other);
        return new EqualsBuilder().append(sendTo, rhs.sendTo).append(ccTo, rhs.ccTo).append(bccTo, rhs.bccTo).append(subject, rhs.subject).append(from, rhs.from).append(mailServerDetails, rhs.mailServerDetails).append(attachments, rhs.attachments).append(template, rhs.template).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
