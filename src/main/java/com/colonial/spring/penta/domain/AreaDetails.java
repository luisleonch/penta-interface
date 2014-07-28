package com.colonial.spring.penta.domain;

import oracle.xdb.XMLType;

public class AreaDetails {
	private String intr_rqst_id;
	private int seq_num;
	private int cus_id;
	private String cus_eq_id;
	private String udf_column_name;
	private String alpha_value;
	private String user_id;
	private XMLType udf_xml_data;

	public XMLType getUdf_xml_data() {
		return udf_xml_data;
	}

	public void setUdf_xml_data(XMLType udf_xml_data) {
		this.udf_xml_data = udf_xml_data;
	}

	public String getIntr_rqst_id() {
		return intr_rqst_id;
	}

	public void setIntr_rqst_id(String intr_rqst_id) {
		this.intr_rqst_id = intr_rqst_id;
	}

	public int getSeq_num() {
		return seq_num;
	}

	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num;
	}

	public int getCus_id() {
		return cus_id;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public String getCus_eq_id() {
		return cus_eq_id;
	}

	public void setCus_eq_id(String cus_eq_id) {
		this.cus_eq_id = cus_eq_id;
	}

	public String getUdf_column_name() {
		return udf_column_name;
	}

	public void setUdf_column_name(String udf_column_name) {
		this.udf_column_name = udf_column_name;
	}

	public String getAlpha_value() {
		return alpha_value;
	}

	public void setAlpha_value(String alpha_value) {
		this.alpha_value = alpha_value;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}