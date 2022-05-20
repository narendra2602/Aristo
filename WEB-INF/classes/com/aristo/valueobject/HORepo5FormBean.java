package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo5FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String head1;
	private double budget;
	private double gross;
	private double credit;
	private double net;
	private double ach;
	private double surp;
	private double pi;
	
	
	
	public double getPi() {
		return pi;
	}
	public void setPi(double pi) {
		this.pi = pi;
	}
	public double getAch() {
		return ach;
	}
	public void setAch(double ach) {
		this.ach = ach;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getGross() {
		return gross;
	}
	public void setGross(double gross) {
		this.gross = gross;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNet() {
		return net;
	}
	public void setNet(double net) {
		this.net = net;
	}
	public double getSurp() {
		return surp;
	}
	public void setSurp(double surp) {
		this.surp = surp;
	}
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	
}
