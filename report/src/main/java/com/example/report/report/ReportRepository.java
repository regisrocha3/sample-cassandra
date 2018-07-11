package com.example.report.report;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CassandraRepository<Report, Report> {

}
