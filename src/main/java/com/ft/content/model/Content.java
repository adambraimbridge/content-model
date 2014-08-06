package com.ft.content.model;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class Content {

    private final String uuid;
    private final String title;
    private final String byline;
    private final String source;
    private final Date publicationDate;
    private final String xmlBody;

    public Content(@JsonProperty("uuid") UUID uuid,
                   @JsonProperty("title") String title,
                   @JsonProperty("byline") String byline,
                   @JsonProperty("source") String source,
                   @JsonProperty("publicationDate") Date publicationDate,
                   @JsonProperty("body") String xmlBody) {
        this.xmlBody = xmlBody;
        this.uuid = uuid == null ? null : uuid.toString();
        this.title = title;
        this.byline = byline;
        this.source = source;
        this.publicationDate = publicationDate;
    }

    @NotNull
    public String getUuid() {
        return uuid;
    }

    @NotEmpty
    public String getTitle() {
        return title;
    }
    
    public String getByline() {
    	return byline;
    }

    @NotEmpty
    public String getSource() {
        return source;
    }

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="UTC")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getBody() {
        return xmlBody;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass())
                .add("uuid", uuid)
                .add("title", title)
                .add("byline", byline)
                .add("source", source)
                .add("publicationDate", publicationDate)
                .add("body", xmlBody)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content that = (Content) o;

        return Objects.equal(this.uuid, that.uuid)
                && Objects.equal(this.title, that.title)
                && Objects.equal(this.byline, that.byline)
                && Objects.equal(this.source, that.source)
                && Objects.equal(this.xmlBody, that.xmlBody) // TODO maybe this could be better. The strings could be equivalent as xml even though they are different strings
                && Objects.equal(this.publicationDate, that.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, byline, source, uuid, publicationDate, xmlBody);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID uuid;
        private String title;
        private String byline;
        private String source;
        private Date publicationDate;
        private String xmlBody;

        public Builder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withByline(String byline) {
            this.byline = byline;
            return this;
        }

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public Builder withPublicationDate(Date lastPublicationDate) {
            this.publicationDate = lastPublicationDate;
            return this;
        }

        public Builder withXmlBody(String xmlBody) {
            this.xmlBody = xmlBody;
            return this;
        }

        public Builder withValuesFrom(Content content) {
            return withTitle(content.getTitle())
            		.withByline(content.getByline())
            		.withSource(content.getSource())
                    .withUuid(UUID.fromString(content.getUuid()))
                    .withPublicationDate(content.getPublicationDate())
                    .withXmlBody(content.getBody());
        }

        public Content build() {
            return new Content(uuid, title, byline, source, publicationDate, xmlBody);
        }
    }

}