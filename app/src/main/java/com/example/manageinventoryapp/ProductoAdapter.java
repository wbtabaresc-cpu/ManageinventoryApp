package com.example.manageinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private Context context;
    private List<Producto> productos;

    public ProductoAdapter(Context context, List<Producto> productos) {
        super(context, R.layout.item_producto, productos);
        this.context = context;
        this.productos = productos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        }

        Producto producto = productos.get(position);

        TextView nombre = convertView.findViewById(R.id.txtNombreProducto);
        TextView idProd = convertView.findViewById(R.id.txtIdProducto);
        ImageButton btnEliminar = convertView.findViewById(R.id.btnEliminar);

        nombre.setText(producto.getNombre());
        idProd.setText("ID: " + producto.getId_producto());

        // Lógica para ELIMINAR
        btnEliminar.setOnClickListener(v -> {
            ApiService api = RetrofitClient.getApiService();
            api.eliminarProducto(producto.get_id()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        productos.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Eliminado de Atlas", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return convertView;
    }
}