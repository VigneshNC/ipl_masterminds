package com.masterminds.entity;

public class PointsTable implements Comparable<PointsTable> {

	private String participant;
	private Long points;
	
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	
	@Override
	public int compareTo(PointsTable o) {
		return o.getPoints().compareTo(this.getPoints());
	}
	
}
