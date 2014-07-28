package com.colonial.spring.penta.dao;

import java.util.List;

import com.colonial.spring.penta.domain.PentaEquipmentDetails;

public interface PentaEquipmentDao {
	int createPentaEquipment(PentaEquipmentDetails pentaEquipmentDetails);
	void subtractFromEquipment(int pentaEquipmentId, int amount);
	void deleteFromEquipment(int pentaEquipmentId);
	List <PentaEquipmentDetails> readFromEquipment();
	void callInterface(String user, String inter);
	int createArea(PentaEquipmentDetails pentaEquipmentDetails);
	void deleteArea(int pentaEquipmentId);
	int createFilter(PentaEquipmentDetails pentaEquipmentDetails);
	int createNote(PentaEquipmentDetails pentaEquipmentDetails);
	int createSafety(PentaEquipmentDetails pentaEquipmentDetails);
	void deleteNotes(int pentaEquipmentId);
}
