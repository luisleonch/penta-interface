package com.colonial.spring.penta.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.colonial.spring.penta.domain.PentaEquipmentDetails;

@Repository(value = "pentaEquipmentDao")
public class PentaEquipmentDaoImpl implements PentaEquipmentDao {
	private static Logger logger = Logger.getLogger(PentaEquipmentDaoImpl.class);
	private SimpleJdbcInsert insertPentaEquipmentDetail;
	private SimpleJdbcInsert insertAreaDetail;
	private SimpleJdbcInsert insertNotesDetail;
  private SimpleJdbcCall procInterface;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.insertPentaEquipmentDetail = new SimpleJdbcInsert(dataSource)
		.withTableName("intr_cuseq_eq");
		this.insertAreaDetail = new SimpleJdbcInsert(dataSource)
		.withTableName("intr_cuseq_udf");
		this.insertNotesDetail = new SimpleJdbcInsert(dataSource)
		.withTableName("intr_cuseq_note");
		this.procInterface =
        new SimpleJdbcCall(dataSource)
                .withProcedureName("intertest");
//		.usingGeneratedKeyColumns("intr_rqst_id")
	}

	@Override
	public int createPentaEquipment(final PentaEquipmentDetails pentaEquipmentDetails) {
		Map<String, Object> parameters = new HashMap<String, Object>(17);
		parameters.put("intr_rqst_id", pentaEquipmentDetails.getIntr_rqst_id());
		parameters.put("seq_num", pentaEquipmentDetails.getSeq_num());
		parameters.put("cus_id", pentaEquipmentDetails.getCus_id());
		parameters.put("cus_loc_cd", pentaEquipmentDetails.getCus_loc_cd());
		parameters.put("activity_cd", pentaEquipmentDetails.getActivity_cd());
		parameters.put("cus_eq_stat_cd", pentaEquipmentDetails.getCus_eq_stat_cd());
		parameters.put("d_stat_date", pentaEquipmentDetails.getD_stat_date());
		parameters.put("customer_system_id", pentaEquipmentDetails.getCustomer_system_id());
		parameters.put("cus_eq_manufacturer_id", pentaEquipmentDetails.getCus_eq_manufacturer_id());
		parameters.put("model_id", pentaEquipmentDetails.getModel_id());
		parameters.put("serial_id", pentaEquipmentDetails.getSerial_id());
		parameters.put("location_in_building", pentaEquipmentDetails.getLocation_in_building());
		parameters.put("description", pentaEquipmentDetails.getDescription());
		parameters.put("cus_eq_class_cd", pentaEquipmentDetails.getCus_eq_class_cd());
		parameters.put("cus_eq_id", pentaEquipmentDetails.getCus_eq_id());
		parameters.put("barcode", pentaEquipmentDetails.getBarcode());
		parameters.put("user_id", pentaEquipmentDetails.getUser_id());		
//		parameters.put("udf_xml_data", (SQLXML) "<note></note>");	
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			SQLXML xmlVal = con.createSQLXML();
			xmlVal.setString("<a></a>");
			parameters.put("udf_xml_data", xmlVal);	
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int key = insertPentaEquipmentDetail.execute(parameters);
		return key;
	}

	public void subtractFromEquipment(int pentaEquipmentId, int amount) {
		jdbcTemplate
				.update("update penta_equipment_details set balance_amount = ? where equipment_id = ?",
						amount, pentaEquipmentId);
	}
	
	public void deleteFromEquipment(int pentaEquipmentId) {
		String sql = "delete from intr_cuseq_eq where seq_num > 0";
		logger.info(sql + " with seq_num:"
				+ pentaEquipmentId);
		jdbcTemplate
				.update(sql);
	}
	
	public List<PentaEquipmentDetails> readFromEquipment() {
	    return jdbcTemplate.query( "select * from intr_cuseq_eq order by seq_num", new EquipmentMapper());
//	    return jdbcTemplate.query( "select * from intr_cuseq_eq  where intr_rqst_id ='EIF20140305' order by seq_num", new EquipmentMapper());
	}

	private static final class EquipmentMapper implements RowMapper<PentaEquipmentDetails> {
		@Override
	    public PentaEquipmentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
	        PentaEquipmentDetails pentaEquipmentDetails = new PentaEquipmentDetails();
	        pentaEquipmentDetails.setCus_eq_id(rs.getString("cus_eq_id"));
	        pentaEquipmentDetails.setCus_id(rs.getInt("cus_id"));
	        pentaEquipmentDetails.setCus_loc_cd(rs.getString("cus_loc_cd"));
	        pentaEquipmentDetails.setSeq_num(rs.getInt("seq_num"));
	        pentaEquipmentDetails.setModel_id(rs.getString("model_id"));
	        pentaEquipmentDetails.setSerial_id(rs.getString("serial_id"));
	        pentaEquipmentDetails.setIntr_rqst_id(rs.getString("intr_rqst_id"));
	        return pentaEquipmentDetails;
	    }
	}

	@Override
	public void callInterface(String user, String inter) {
		procInterface.execute(user,inter);
	}

	@Override
	public int createArea(final PentaEquipmentDetails pentaEquipmentDetails) {
		Map<String, Object> parameters = new HashMap<String, Object>(17);
		parameters.put("intr_rqst_id", pentaEquipmentDetails.getIntr_rqst_id());
		parameters.put("seq_num", pentaEquipmentDetails.getSeq_num());
		parameters.put("cus_id", pentaEquipmentDetails.getCus_id());
		parameters.put("cus_eq_id", pentaEquipmentDetails.getCus_eq_id());
		parameters.put("udf_column_name", pentaEquipmentDetails.getUdf_column_name());
		parameters.put("alpha_value", pentaEquipmentDetails.getAlpha_value());	
		parameters.put("user_id", pentaEquipmentDetails.getUser_id());	
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			SQLXML xmlVal = con.createSQLXML();
			xmlVal.setString("<a></a>");
			parameters.put("udf_xml_data", xmlVal);	
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int key = insertAreaDetail.execute(parameters);
		return key;
	}

	@Override
	public void deleteArea(int pentaEquipmentId) {
		String sql = "delete from intr_cuseq_udf where seq_num > 0";
		logger.info(sql + " with seq_num:"
				+ pentaEquipmentId);
		jdbcTemplate
				.update(sql);
	}

	@Override
	public int createFilter(PentaEquipmentDetails pentaEquipmentDetails) {
		Map<String, Object> parameters = new HashMap<String, Object>(17);
		parameters.put("intr_rqst_id", pentaEquipmentDetails.getIntr_rqst_id());
		parameters.put("seq_num", pentaEquipmentDetails.getSeq_num());
		parameters.put("cus_id", pentaEquipmentDetails.getCus_id());
		parameters.put("cus_eq_id", pentaEquipmentDetails.getCus_eq_id());
		parameters.put("activity_cd", pentaEquipmentDetails.getActivity_cd());
		parameters.put("wo_note_type_cd", pentaEquipmentDetails.getWo_note_type_cd());
		parameters.put("wo_default_cd", pentaEquipmentDetails.getWo_default_cd());	
		parameters.put("mc_default_cd", pentaEquipmentDetails.getMc_default_cd());	
		parameters.put("allow_update_cd", pentaEquipmentDetails.getAllow_update_cd());	
		parameters.put("text_3", pentaEquipmentDetails.getText_3());	
		parameters.put("user_id", pentaEquipmentDetails.getUser_id());	
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			SQLXML xmlVal = con.createSQLXML();
			xmlVal.setString("<a></a>");
			parameters.put("udf_xml_data", xmlVal);	
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int key = insertNotesDetail.execute(parameters);
		return key;
	}

	@Override
	public int createNote(PentaEquipmentDetails pentaEquipmentDetails) {
		Map<String, Object> parameters = new HashMap<String, Object>(17);
		parameters.put("intr_rqst_id", pentaEquipmentDetails.getIntr_rqst_id());
		parameters.put("seq_num", pentaEquipmentDetails.getSeq_num());
		parameters.put("cus_id", pentaEquipmentDetails.getCus_id());
		parameters.put("cus_eq_id", pentaEquipmentDetails.getCus_eq_id());
		parameters.put("activity_cd", pentaEquipmentDetails.getActivity_cd());
		parameters.put("wo_note_type_cd", pentaEquipmentDetails.getWo_note_type_cd());
		parameters.put("wo_default_cd", pentaEquipmentDetails.getWo_default_cd());	
		parameters.put("mc_default_cd", pentaEquipmentDetails.getMc_default_cd());	
		parameters.put("allow_update_cd", pentaEquipmentDetails.getAllow_update_cd());	
		parameters.put("text_3", pentaEquipmentDetails.getText_3());	
		parameters.put("user_id", pentaEquipmentDetails.getUser_id());	
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			SQLXML xmlVal = con.createSQLXML();
			xmlVal.setString("<a></a>");
			parameters.put("udf_xml_data", xmlVal);	
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int key = insertNotesDetail.execute(parameters);
		return key;
	}

	@Override
	public int createSafety(PentaEquipmentDetails pentaEquipmentDetails) {
		Map<String, Object> parameters = new HashMap<String, Object>(17);
		parameters.put("intr_rqst_id", pentaEquipmentDetails.getIntr_rqst_id());
		parameters.put("seq_num", pentaEquipmentDetails.getSeq_num());
		parameters.put("cus_id", pentaEquipmentDetails.getCus_id());
		parameters.put("cus_eq_id", pentaEquipmentDetails.getCus_eq_id());
		parameters.put("activity_cd", pentaEquipmentDetails.getActivity_cd());
		parameters.put("wo_note_type_cd", pentaEquipmentDetails.getWo_note_type_cd());
		parameters.put("wo_default_cd", pentaEquipmentDetails.getWo_default_cd());	
		parameters.put("mc_default_cd", pentaEquipmentDetails.getMc_default_cd());	
		parameters.put("allow_update_cd", pentaEquipmentDetails.getAllow_update_cd());	
		parameters.put("text_3", pentaEquipmentDetails.getText_3());	
		parameters.put("user_id", pentaEquipmentDetails.getUser_id());	
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			SQLXML xmlVal = con.createSQLXML();
			xmlVal.setString("<a></a>");
			parameters.put("udf_xml_data", xmlVal);	
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int key = insertNotesDetail.execute(parameters);
		return key;
	}

	@Override
	public void deleteNotes(int pentaEquipmentId) {
		String sql = "delete from intr_cuseq_note where seq_num > 0";
		logger.info(sql + " with seq_num:"
				+ pentaEquipmentId);
		jdbcTemplate
				.update(sql);
	}
}