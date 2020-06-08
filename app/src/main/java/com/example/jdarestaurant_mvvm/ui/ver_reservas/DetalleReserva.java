package com.example.jdarestaurant_mvvm.ui.ver_reservas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdarestaurant_mvvm.Model.Reserva;
import com.example.jdarestaurant_mvvm.R;


public class DetalleReserva extends Fragment {
    private VerReservasViewModel verReservasViewModel;

    TextView fecha,comensales,nombre,telefono,comentario;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        verReservasViewModel =
                ViewModelProviders.of(requireActivity()).get(VerReservasViewModel.class);

        View root= inflater.inflate(R.layout.fragment_detalle_reserva, container, false);
        fecha=root.findViewById(R.id.fechaTextView);
        comensales=root.findViewById(R.id.comensalesTextView);
        nombre=root.findViewById(R.id.nombreTextView);
        telefono=root.findViewById(R.id.telefonoTextView);
        comentario=root.findViewById(R.id.comentariosTextView);

        cargarreserva(verReservasViewModel.getPosicio(getArguments().getInt("POSICION")));






        return root;
    }
    public void cargarreserva(Reserva reserva){
        fecha.setText(reserva.getFecha());
        comensales.setText(String.valueOf(reserva.getPersonas()));
        nombre.setText(reserva.getNombre());
        telefono.setText(reserva.getTelefono());
        comentario.setText(reserva.getComentarios());
    }
}