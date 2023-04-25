package co.edu.uptc.models.graphs.Graphs202113214;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.MapElementGraph;
import co.edu.uptc.views.maps.types.ElementType;

public class ManagerModelGraphs202113214 implements ContractGraphs.Model {

    ContractGraphs.Presenter presenter ;    
    Graph graph = new Graph();

     @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        graph.addElement(mapElement);
        updateGraph();
        keepGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(graph.getElements().values());
    }

    @Override
    public MapElement getElement(int id) {
        return graph.getElement(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                MapElement point1 = mapElement.getMapRoute().getPoint1();
                MapElement point2 = mapElement.getMapRoute().getPoint2();
                if ((point1.getIdElement() == idElementPoint1 && point2.getIdElement() == idElementPoint2) ||
                        (point1.getIdElement() == idElementPoint2 && point2.getIdElement() == idElementPoint1)) {
                    return mapElement;
                }
            }
        }
        return null;
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "Santiago Andr\u00e9s Orjuela L\u00f3pez";
    }

    @Override
    public void loadGraphs() {
        try {
            graph.setElements(new Persistence202113214().getGraphs());
            graph.setExistingIDs(new ArrayList<>(graph.getElements().keySet()));
        } catch (RuntimeException e){
            presenter.notifyWarning(e.getMessage());
        } catch (Exception e){
            presenter.notifyWarning("Error al cargar");
        }
        updateGraph();
    }

    @Override
    public void deletePoint(int idElement) {
        //Pendiente - Eliminar el punto
        if (!pointHasRelation(idElement)) {
            graph.deleteElement(idElement);
            keepGraph();
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    private void keepGraph() {
        try {
            new Persistence202113214().keepGraph(graph.getElements());
        } catch (Exception e) {
            presenter.notifyWarning("Error al guardar el grafo");
            e.printStackTrace();
        }
    }

    private boolean pointHasRelation(int id) {
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id) {
                    return true;
                }
                if (mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.clearResultElements();
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
        presenter.updateResultGraph();
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.clearResultElements();
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.TIME);
        presenter.updateResultGraph();
    }

    @Override
    public Set<MapElement> getResultElements() {
        System.out.println("Tamaño lista de puntos finales: " + graph.getResultElements().values().size());
        return new HashSet<>(graph.getResultElements().values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        // TODO implementar
    }


    
}