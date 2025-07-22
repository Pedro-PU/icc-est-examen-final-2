package models;

import java.util.*;

public class Maquina{
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    private int calcularSubred() {
        String[] octetos = ip.split("\\.");
        return Integer.parseInt(octetos[2]);
    }
    
    private int calcularRiesgo() {
        int sumaDiv5 = 0;
        for (int codigo : codigos) {
            if (codigo % 5 == 0) {
                sumaDiv5 += codigo;
            }
        }

        String sinEspacios = nombre.replace(" ", "");
        Set<Character> unicos = new HashSet<>();
        for (char c : sinEspacios.toCharArray()) {
            unicos.add(c);
        }

        return sumaDiv5 * unicos.size();
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        return subred;
    }

    public int getRiesgo() {
        return riesgo;
    }

    @Override
    public String toString() {
        return nombre + " - " + ip + " - Subred: " + subred + " - Riesgo: " + riesgo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maquina)) return false;
        Maquina maquina = (Maquina) o;
        return subred == maquina.subred && Objects.equals(nombre, maquina.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subred, nombre);
    }
}