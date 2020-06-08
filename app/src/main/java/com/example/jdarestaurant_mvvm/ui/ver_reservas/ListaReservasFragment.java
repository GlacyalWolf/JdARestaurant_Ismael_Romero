package com.example.jdarestaurant_mvvm.ui.ver_reservas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdarestaurant_mvvm.Model.Reserva;
import com.example.jdarestaurant_mvvm.R;

import java.util.ArrayList;
import java.util.List;

public class ListaReservasFragment extends Fragment {

    private VerReservasViewModel verReservasViewModel;
    RecyclerView reservas_recycler;
    List<Reserva> reservas = new ArrayList<>();
    ReservaAdapter reservaAdapter;
    Button nuevareserva;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        verReservasViewModel =
                ViewModelProviders.of(requireActivity()).get(VerReservasViewModel.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vista_reserva, container, false);
        nuevareserva= view.findViewById(R.id.bNewReserva);

        reservas_recycler = view.findViewById(R.id.reservas_Recycler);
        reservas_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        reservaAdapter = new ReservaAdapter(reservas);
        reservas_recycler.setAdapter(reservaAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        reservas_recycler.addItemDecoration(dividerItemDecoration);


        verReservasViewModel.getReservas(getContext());

        verReservasViewModel.getlivereserva().observe(getViewLifecycleOwner(), new Observer<ArrayList<Reserva>>() {
            @Override
            public void onChanged(ArrayList<Reserva> reservas) {
               reservaAdapter.setReservaList(reservas);
               reservaAdapter.notifyDataSetChanged();
            }
        });






        nuevareserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nuevaReservaFragment);
            }
        });


        return view;
    }


    public class ReservaViewHolder extends RecyclerView.ViewHolder{

        TextView fecha, personas;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.fechaViewHolder);
            personas = itemView.findViewById(R.id.personasViewHolder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("POSICION",getAdapterPosition());
                    Navigation.findNavController(v).navigate(R.id.detalleReserva,bundle);

                }
            });
        }
    }


    public class ReservaAdapter extends RecyclerView.Adapter<ReservaViewHolder> {

        List<Reserva> reservaList;

        public ReservaAdapter(List<Reserva> reservaList) {
            this.reservaList = reservaList;
        }
        public void setReservaList(ArrayList<Reserva> listar){
            this.reservaList=listar;
        }

        @NonNull
        @Override
        public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View itemview = getLayoutInflater().inflate(R.layout.reserva_viewholder, viewGroup, false);
            return new ReservaViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservaViewHolder reservaViewHolder, int i) {
            String fecha=reservaList.get(i).getFecha().toString();
            String personas=String.valueOf(reservaList.get(i).getPersonas());

            reservaViewHolder.fecha.setText(fecha);
            reservaViewHolder.personas.setText(personas);

        }

        @Override
        public int getItemCount() {
            return reservaList.size();
        }
    }

}
