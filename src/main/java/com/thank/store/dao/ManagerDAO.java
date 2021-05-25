package com.thank.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.thank.store.dto.CvsProductDTO;
import com.thank.store.dto.ManSearchDTO;
import com.thank.store.dto.ManagerDTO;
import com.thank.store.dto.MemberDTO;

public interface ManagerDAO {

	ManagerDTO getManagerInfo(long no) throws SQLException;

	List<CvsProductDTO> getAllProductList(ManSearchDTO searchDTO) throws SQLException;
	
	List<CvsProductDTO> getEnrolledProductList(ManSearchDTO searchDTO) throws SQLException;
	
	List<CvsProductDTO> getEnrolAvaiProductList(ManSearchDTO searchDTO) throws SQLException;

	long getManagerNoFromMember(MemberDTO memberDTO) throws SQLException;

	long getTotalRecord(ManSearchDTO searchDTO) throws SQLException;

	void updateStorecode(ManagerDTO managerDTO);
}
