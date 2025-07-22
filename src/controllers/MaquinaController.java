package controllers;

import models.Maquina;

import java.util.*;

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() > umbral) {
                resultado.push(m);
            }
        }
        return resultado;
    }

    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        Comparator<Maquina> ordenadorSubredNombre = new Comparator<Maquina>() {
            @Override
            public int compare(Maquina m1, Maquina m2) {
                int cmp = Integer.compare(m2.getSubred(), m1.getSubred());
                if (cmp != 0) {
                    return cmp;
                }
                return m1.getNombre().compareTo(m2.getNombre());
            }
        };
        TreeSet<Maquina> arbolOrdenado = new TreeSet<>(ordenadorSubredNombre);
        arbolOrdenado.addAll(pila);
        return arbolOrdenado;
    }

    public Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        Map<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            int riesgo = m.getRiesgo();
            mapa.putIfAbsent(riesgo, new LinkedList<>());
            mapa.get(riesgo).add(m);
        }
        return mapa;
    }
    
    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int maxCantidad = 0;
        int mayorRiesgoEnEmpate = -1;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int cantidad = entry.getValue().size();
            int riesgo = entry.getKey();

            if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > mayorRiesgoEnEmpate)) {
                maxCantidad = cantidad;
                mayorRiesgoEnEmpate = riesgo;
            }
        }

        if (mayorRiesgoEnEmpate == -1) {
            return new Stack<>();
        }

        Queue<Maquina> grupoSeleccionado = mapa.get(mayorRiesgoEnEmpate);
        Stack<Maquina> resultado = new Stack<>();
        List<Maquina> tempLista = new ArrayList<>(grupoSeleccionado);
        for (int i = tempLista.size() - 1; i >= 0; i--) {
            resultado.push(tempLista.get(i));
        }
        
        return resultado;
    }
    
}