package com.planensas.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.services.ClienteRequests;
import com.planensas.myapplication.services.DataServiceGenerator;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ClienteViewModel clienteViewModel;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetch();
        int datacount=clienteViewModel.getAllClientes().getValue().size();
        Log.v(TAG, "recuperado"+datacount);

    }


    private void fetch(){

        DataServiceGenerator DataServiceGenerator = new DataServiceGenerator();
        ClienteRequests service = DataServiceGenerator.createService(ClienteRequests.class);
        //get the value of other request
        Call<List<Cliente>> call = service.getClientes(3);

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful())
                {
                    if (response != null){
                        List<Cliente> ClienteModelList = response.body();
                        for (int i = 0; i < ClienteModelList.size(); i++)
                        {
                            int custumerID = ClienteModelList.get(i).getCustomerId();
                            String nombre= ClienteModelList.get(i).getNombre();
                            String apellido= ClienteModelList.get(i).getApellido();
                            String direccion= ClienteModelList.get(i).getDireccion();
                            String telefono= ClienteModelList.get(i).getTelefono();
                            String estado= ClienteModelList.get(i).getEstado();
                            Cliente cliente = new Cliente(custumerID,nombre,apellido,direccion,telefono,estado);

                            clienteViewModel.insert(cliente);
                        }
                    }//end for
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.v(TAG, t.getMessage());
            }


        });
    }




}
