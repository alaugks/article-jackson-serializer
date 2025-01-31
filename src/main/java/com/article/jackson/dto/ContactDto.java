package com.article.jackson.dto;

import java.time.LocalDate;

import com.article.jackson.annotation.MappingTable;
import com.article.jackson.serializer.MappingValue;
import com.article.jackson.serializer.MappingValueDeserializer;
import com.article.jackson.serializer.MappingValueSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE,
		isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class ContactDto {

	@JsonProperty("1")
	private String firstname;

	@JsonProperty("2")
	private String lastname;

	@JsonProperty("3")
	private String email;

	@JsonProperty("4")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birthday;

	@JsonProperty("46")
	@MappingTable(map = Maps.SALUTATION)
	@JsonSerialize(using = MappingValueSerializer.class)
	@JsonDeserialize(using = MappingValueDeserializer.class)
	private MappingValue<String> salutation;

	@JsonProperty("100674")
	@MappingTable(map = Maps.MARKETING_INFORMATION)
	@JsonSerialize(using = MappingValueSerializer.class)
	@JsonDeserialize(using = MappingValueDeserializer.class)
	private MappingValue<Boolean> marketingInformation;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getSalutation() {
		return salutation.getValue();
	}

	public void setSalutation(String salutation) {
		this.salutation = new MappingValue<>(salutation);
	}

	public Boolean isMarketingInformation() {
		return marketingInformation.getValue();
	}

	public void setMarketingInformation(Boolean marketingInformation) {
		this.marketingInformation = new MappingValue<>(marketingInformation);
	}
}
