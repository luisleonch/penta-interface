package com.colonial.spring.penta.domain;

import java.sql.Date;

import oracle.xdb.XMLType;

public class PentaEquipmentDetails {
	private String activity_cd;
	private String barcode;
	private String cus_eq_capacity_cd;
	private String cus_eq_class_cd;
	private String cus_eq_id;
	private String cus_eq_manufacturer_id;
	private String cus_eq_stat_cd;
	private int cus_id;
	private String cus_loc_cd;
	private String customer_system_id;
	private Date d_stat_date;
	private String description;
	private int equipmentId;
	private String intr_rqst_id;
	private String location_in_building;
	private String model_id;
	private int seq_num;
	private String serial_id;
	private String user_id;
	private XMLType udf_xml_data;
	private String udf_column_name = "EQUIP_AREA_SERVED";
	private String alpha_value;
	private int wo_note_type_cd;
	private String wo_default_cd = "N";
	private String mc_default_cd = "N";
	private String allow_update_cd = "Y";
	private String area;
	private String filter;
	private String note;
	private String safety;
	private String text_3;

	public int getWo_note_type_cd() {
		return wo_note_type_cd;
	}

	public void setWo_note_type_cd(int i) {
		this.wo_note_type_cd = i;
	}

	public String getWo_default_cd() {
		return wo_default_cd;
	}

	public void setWo_default_cd(String wo_default_cd) {
		this.wo_default_cd = wo_default_cd;
	}

	public String getMc_default_cd() {
		return mc_default_cd;
	}

	public void setMc_default_cd(String mc_default_cd) {
		this.mc_default_cd = mc_default_cd;
	}

	public String getAllow_update_cd() {
		return allow_update_cd;
	}

	public void setAllow_update_cd(String allow_update_cd) {
		this.allow_update_cd = allow_update_cd;
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

	public XMLType getUdf_xml_data() {
		return udf_xml_data;
	}

	public void setUdf_xml_data(XMLType udf_xml_data) {
		this.udf_xml_data = udf_xml_data;
	}

	public String getActivity_cd() {
		return activity_cd;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getCus_eq_capacity_cd() {
		return cus_eq_capacity_cd;
	}

	public String getCus_eq_class_cd() {
		return cus_eq_class_cd;
	}

	public String getCus_eq_id() {
		return cus_eq_id;
	}

	public String getCus_eq_manufacturer_id() {
		return cus_eq_manufacturer_id;
	}

	public String getCus_eq_stat_cd() {
		return cus_eq_stat_cd;
	}

	public int getCus_id() {
		return cus_id;
	}

	public String getCus_loc_cd() {
		return cus_loc_cd;
	}

	public String getCustomer_system_id() {
		return customer_system_id;
	}

	public Date getD_stat_date() {
		return d_stat_date;
	}

	public String getDescription() {
		return description;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public String getIntr_rqst_id() {
		return intr_rqst_id;
	}

	public String getLocation_in_building() {
		return location_in_building;
	}

	public String getModel_id() {
		return model_id;
	}

	public int getSeq_num() {
		return seq_num;
	}

	public String getSerial_id() {
		return serial_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setActivity_cd(String activity_cd) {
		this.activity_cd = activity_cd;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setCus_eq_capacity_cd(String cus_eq_capacity_cd) {
		this.cus_eq_capacity_cd = cus_eq_capacity_cd;
	}

	public void setCus_eq_class_cd(String cus_eq_class_cd) {
		this.cus_eq_class_cd = cus_eq_class_cd;
	}

	public void setCus_eq_id(String cus_eq_id) {
		this.cus_eq_id = cus_eq_id;
	}

	public void setCus_eq_manufacturer_id(String cus_eq_manufacturer_id) {
		this.cus_eq_manufacturer_id = cus_eq_manufacturer_id;
	}

	public void setCus_eq_stat_cd(String cus_eq_stat_cd) {
		this.cus_eq_stat_cd = cus_eq_stat_cd;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public void setCus_loc_cd(String cus_loc_cd) {
		this.cus_loc_cd = cus_loc_cd;
	}

	public void setCustomer_system_id(String customer_system_id) {
		this.customer_system_id = customer_system_id;
	}
	public void setD_stat_date(Date d_stat_date) {
		this.d_stat_date = d_stat_date;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public void setIntr_rqst_id(String intr_rqst_id) {
		this.intr_rqst_id = intr_rqst_id;
	}
	public void setLocation_in_building(String location_in_building) {
		this.location_in_building = location_in_building;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num;
	}

	public void setSerial_id(String serial_id) {
		this.serial_id = serial_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	public String getText_3() {
		return text_3;
	}

	public void setText_3(String text_3) {
		this.text_3 = text_3;
	}

}
// # dumping database structure for pti_prod
// drop database if exists `pti_prod`;
// create database if not exists `pti_prod` /*!40100 default character set
// latin1 */;
// use `pti_prod`;
//
// # dumping structure for table pti_prod.intr_cuseq_eq
// drop table if exists `intr_cuseq_eq`;
// create table `intr_cuseq_eq`
//
// ( `intr_rqst_id` varchar(13) not null,
// `seq_num` int unsigned not null,
// `cus_id` int unsigned not null,
// `cus_eq_id` varchar(15) not null,
// `activity_cd` varchar(1) not null,
// `description` varchar(2000),
// `cus_loc_cd` varchar(8),
// `model_id` varchar(18),
// `serial_id` varchar(35),
// `cus_eq_stat_cd` varchar(1),
// `d_stat_date` date,
// `cus_eq_class_cd` varchar(15),
// `cus_eq_capacity_cd` varchar(15),
// `cus_eq_manufacturer_id` varchar(15),
// `barcode` varchar(128),
// `user_id` varchar(6) not null,
// `location_in_building` varchar(100),
// `customer_system_id` varchar(30),
// primary key (`intr_rqst_id`, `seq_num`)
// )