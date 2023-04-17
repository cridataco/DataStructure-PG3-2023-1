package co.edu.uptc.pojos;

import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

public class MapElement {

    private ElementType typeElement;
    private int idElement;

    private GeoPosition geoPosition;

    private MapRoute mapRoute;


    public MapElement(GeoPosition position) {
        setGeoPosition(position);
        typeElement = ElementType.POINT;
    }

    public MapElement(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
        typeElement = ElementType.ROUTE;
    }

    public ElementType getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(ElementType typeElement) {
        this.typeElement = typeElement;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public MapRoute getMapRoute() {
        return mapRoute;
    }

    public void setMapRoute(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
    }
}
