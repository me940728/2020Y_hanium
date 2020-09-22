package poly.service;

import java.util.List;

import poly.dto.EmpDTO;

public interface IEmpService {

	List<EmpDTO> getEmpList();

	List<EmpDTO> getManagerList();

}
