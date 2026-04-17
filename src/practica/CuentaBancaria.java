package practica;

public class CuentaBancaria {
    private String titular;
    private double saldo;
    private boolean bloqueada;
    private static int totalCuentasCreadas = 0;

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial >= 0 ? saldoInicial : 0;
        this.bloqueada = false;
        totalCuentasCreadas++;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        if (titular != null && !titular.isBlank()) {
            this.titular = titular;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public static int getTotalCuentasCreadas() {
        return totalCuentasCreadas;
    }

    public static void reiniciarContador() {
        totalCuentasCreadas = 0;
    }

    public boolean ingresar(double cantidad) {
        if (bloqueada || cantidad <= 0) {
            return false;
        }
        saldo += cantidad;
        return true;
    }

    public boolean retirar(double cantidad) {
        if (bloqueada || cantidad <= 0 || cantidad > saldo) {
            return false;
        }
        saldo -= cantidad;
        return true;
    }

    public double aplicarComisionMensual() {
        if (bloqueada) {
            return saldo;
        }
        if (saldo < 1000) {
            saldo -= 5;
        } else if (saldo < 5000) {
            saldo -= 2;
        }
        if (saldo < 0) {
            saldo = 0;
        }
        return saldo;
    }


    public boolean bloquearSiSaldoCero() {
        if (saldo == 0) {
            bloqueada = true;
        }
        return bloqueada;
    }
}