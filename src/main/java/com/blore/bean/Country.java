package com.blore.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Country {
	@JsonIgnore
	@Column
	@Id
	@GeneratedValue
	private Long idCountry;
	
	@Column
	private String cname;
	@Column
	private String ccode;
	@Column
	private String cdialCode;

	public Country(String iso3Country, String displayCountry,String dilaCode) {
		this.cname=displayCountry;
		this.ccode=iso3Country;
		this.cdialCode=dilaCode;
	}
	public Country() {
		// TODO Auto-generated constructor stub
	}
	public Long getIdCountry() {
		return idCountry;
	}


	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}


	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCdialCode() {
		return cdialCode;
	}

	public void setCdialCode(String cdialCode) {
		this.cdialCode = cdialCode;
	}
	@Override
	public String toString() {
		return "Country [idCountry=" + idCountry + ", cname=" + cname
				+ ", ccode=" + ccode + ", cdialCode=" + cdialCode + "]";
	}
	
}
