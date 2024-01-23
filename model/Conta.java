package model;

public class Conta {

	private int numero;
	private double saldo;
	private double limite;
	
	public Conta () {
		
	}
	
	@Override
	public String toString() {
		return "Conta [numero = " + numero + ", saldo = " + saldo + ", limite = " + limite + "]";
	}

	public Conta(int numro, double saldo, double limite) {
		this.numero = numro;
		this.saldo = saldo;
		this.limite = limite;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numro) {
		this.numero = numro;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	
	
	
	
	
	
	
	
	
}
