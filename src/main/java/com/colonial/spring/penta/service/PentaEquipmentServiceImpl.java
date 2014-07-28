package com.colonial.spring.penta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.colonial.spring.penta.dao.PentaEquipmentDao;
import com.colonial.spring.penta.domain.PentaEquipmentDetails;

@Service(value = "pentaEquipmentService")
public class PentaEquipmentServiceImpl implements PentaEquipmentService {

	@Autowired
	private PentaEquipmentDao pentaEquipmentDao;

	@Override
	public int createPentaEquipment(PentaEquipmentDetails pentaEquipmentDetails) {
		return pentaEquipmentDao.createPentaEquipment(pentaEquipmentDetails);
	}
	
	@Override
	public void subtractFromEquipment(int pentaEquipmentId, int amount) {
		pentaEquipmentDao.subtractFromEquipment(pentaEquipmentId, amount);
	}
	
	@Override
	public void deleteFromEquipment(int pentaEquipmentId) {
		pentaEquipmentDao.deleteFromEquipment(pentaEquipmentId);
	}
	
	@Override
	public List <PentaEquipmentDetails> readFromEquipment() {
		return pentaEquipmentDao.readFromEquipment();
	}
/*
 * int createPentaEquipment(PentaEquipmentDetails pentaEquipmentDetails);
	void subtractFromEquipment(int pentaEquipmentId, int amount);
	void deleteFromEquipment(int pentaEquipmentId);
	void readFromEquipment();
 */

	@Override
	public void callInterface(String user, String inter) {
		pentaEquipmentDao.callInterface(user, inter);
	}

	@Override
	public int createArea(PentaEquipmentDetails pentaEquipmentDetails) {
		return pentaEquipmentDao.createArea(pentaEquipmentDetails);
	}

	@Override
	public void deleteArea(int pentaEquipmentId) {
		pentaEquipmentDao.deleteArea(pentaEquipmentId);
	}

	@Override
	public int createFilter(PentaEquipmentDetails pentaEquipmentDetails) {
		return pentaEquipmentDao.createFilter(pentaEquipmentDetails);
	}

	@Override
	public int createNote(PentaEquipmentDetails pentaEquipmentDetails) {
		return pentaEquipmentDao.createNote(pentaEquipmentDetails);
	}

	@Override
	public int createSafety(PentaEquipmentDetails pentaEquipmentDetails) {
		return pentaEquipmentDao.createSafety(pentaEquipmentDetails);
	}

	@Override
	public void deleteNotes(int pentaEquipmentId) {
		pentaEquipmentDao.deleteNotes(pentaEquipmentId);
	}
}
