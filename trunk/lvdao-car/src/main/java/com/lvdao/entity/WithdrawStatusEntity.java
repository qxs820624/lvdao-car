package com.lvdao.entity;

public class WithdrawStatusEntity {
	
	private String id;
	private String name;
	public WithdrawStatusEntity(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public synchronized String getId() {
		return id;
	}
	public synchronized void setId(String id) {
		this.id = id;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	
	
}
