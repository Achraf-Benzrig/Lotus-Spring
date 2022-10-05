package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Reporting;
import tn.esprit.spring.entity.ReportingReason;

public interface IReportingService {
	public void addReport(String reported,String reportedby,String reason);

	public void deleteReport(Long id);
	public List<Reporting> getReports();
	List<Reporting> findByUserFirstNameContains(String firstName);
	List<Reporting> findByReasonContains(String reason);
	List<Object[]> countTotalReportingByReason();
}
