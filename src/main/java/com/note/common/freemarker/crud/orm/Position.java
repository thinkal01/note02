package com.note.common.freemarker.crud.orm;


public class Position {
	
	private Integer id;
	private String level;
	
	public Position() {
	}
	public Position(Integer id, String level) {
		this.id = id;
		this.level = level;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Position [id=" + id + ", level=" + level + "]";
	}
}
