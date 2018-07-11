package com.example.report.report;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "report", forceQuote = true)
public class Report {

	@PrimaryKeyColumn(name = "id", ordering = Ordering.DESCENDING, ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private UUID id;

	@Column(value = "name")
	private String name;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Report() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 */
	public Report(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

}
