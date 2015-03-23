package com.boushtech.quickorder.libraries;

import com.boushtech.quickorder.entities.Producto;
import com.boushtech.quickorder.events.AgregarProducto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CarritoManager {

    private static List<AgregarProducto> productos = new ArrayList<AgregarProducto>();

    public static void addProducto(AgregarProducto p) {
        productos.add(p);
    }

    public static List<AgregarProducto> getProductos() {
        return productos;
    }

    public static void clear() {
        productos.clear();
    }



    public static JsonObject getRequest(){

        JsonObject datAObject = new JsonObject();

        JsonArray mesasArray = new JsonArray();
        JsonArray ordenDetalleArray = new JsonArray();
        JsonObject ordenObject = new JsonObject();

        JsonObject mesaObject = new JsonObject();
        mesaObject.addProperty("id", "4");


        mesasArray.add(mesaObject);


        for(int i = 0; i < productos.size(); i++){


            JsonObject od = new JsonObject();

            AgregarProducto ap = (AgregarProducto)productos.get(i);

            od.addProperty("precioUnitario", ap.getPrecio());
            od.addProperty("cantidad", ap.getCantidad());
            od.addProperty("productoId", ap.getId());


            ordenDetalleArray.add(od);
        }






        ordenObject.addProperty("tipoConsumo", "A");










        datAObject.add("mesas", mesasArray);
        datAObject.add("orden_detalle", ordenDetalleArray);
        datAObject.add("orden", ordenObject);


        return datAObject;


    }


    public static Double getTotalCarrito(){
        Double t = 0.0;

        for(int i =0; i < productos.size(); i++){

            AgregarProducto ap = productos.get(i);

            t = t + Double.parseDouble(ap.getSub_total());



        }


        return t;
    }

}