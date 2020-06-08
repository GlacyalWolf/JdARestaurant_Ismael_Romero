package com.example.jdarestaurant_mvvm.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jdarestaurant_mvvm.Database.RoomConnection;
import com.example.jdarestaurant_mvvm.Model.Plato;
import com.example.jdarestaurant_mvvm.Model.Reserva;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Context context;
    private static Repository repository;
    private MutableLiveData<ArrayList<Plato>> livelistplato= new MutableLiveData<>();

    private MutableLiveData<ArrayList<Reserva>> livelistareserva= new MutableLiveData<>();






    private  Repository(Context context){
        this.context = context;
        livelistplato= new MutableLiveData<>();
        livelistareserva= new MutableLiveData<ArrayList<Reserva>>();


    }

    public static Repository get(Context context){
        if(repository == null){
            repository = new Repository(context);
        }
        return repository;
    }


    public static Repository getRepository(){return repository;}

    public void getPlatos() {
        myThread hilo= new myThread();
        hilo.execute("https://jdarestaurantapi.firebaseio.com/menu.json");

    }

    public LiveData<ArrayList<Plato>> getLiveListplatos(){
        return livelistplato;
    }




    public class myThread extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection;
            URL url;
            String result;
            result ="";

            try{

                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while(data != -1){
                    result += (char) data;
                    data = inputStream.read();
                }

            }catch (Exception e){

                e.printStackTrace();

            }

            Log.i("RESULT", result);



            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Plato> listcontact= new ArrayList<>();
            // tratamiento del json
            try {
                JSONArray jsonArray = new JSONArray("https://jdarestaurantapi.firebaseio.com/menu/entrantes.json");
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);
                    // passar a arraylist de Contactos

                    Plato plato = new Plato(jsonObject);
                    listcontact.add(plato);

                }

                jsonArray = new JSONArray("https://jdarestaurantapi.firebaseio.com/menu/postres.json");
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);
                    // passar a arraylist de Contactos

                    Plato plato = new Plato(jsonObject);
                    listcontact.add(plato);

                }

                jsonArray = new JSONArray("https://jdarestaurantapi.firebaseio.com/menu/postres/principales.json");
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);
                    // passar a arraylist de Contactos

                    Plato plato = new Plato(jsonObject);
                    listcontact.add(plato);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (Plato p: listcontact){
                System.out.println(p);
            }
            livelistplato.postValue(listcontact);


        }
    }



//________________________________________sqlite______________________________________

    public void insertReserva(Reserva reserva,Context c) {
        RoomConnection ro=RoomConnection.getRoomConnection(c);
        ro.ciudDao().insertAll(reserva);
        ro.destroyRoomConnection();
        ro.close();

    }

    public void getReservas(Context context) {
        RoomConnection ro=RoomConnection.getRoomConnection(context);
        ArrayList<Reserva> listares= new ArrayList<>();

        List<Reserva> l=ro.ciudDao().getAll();
        for(Reserva r:l){
            listares.add(r);

        }
        livelistareserva.postValue(listares);




    }

    public MutableLiveData<ArrayList<Reserva>>getlivereservas(){
        return livelistareserva;
    }




}
