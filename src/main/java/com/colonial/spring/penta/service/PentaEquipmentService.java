package com.colonial.spring.penta.service;

import java.util.List;

import com.colonial.spring.penta.domain.PentaEquipmentDetails;

public interface PentaEquipmentService {
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
