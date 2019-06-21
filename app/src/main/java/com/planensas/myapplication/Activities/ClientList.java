package com.planensas.myapplication.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.MainActivity;
import com.planensas.myapplication.R;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.services.ClienteRequests;
import com.planensas.myapplication.services.DataServiceGenerator;
import com.planensas.myapplication.utils.UtilData;

import java.util.List;

public class ClientList extends AppCompatActivity {
    ClienteViewModel clienteViewModel;
    private static final String TAG = "ClientList-Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        clienteViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);
        clienteViewModel.getAllClientes().observe(this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(@Nullable List<Cliente> clientes) {
                //update RecyclerView
                Toast.makeText(ClientList.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
        fetch();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ClienteRecyclerViewAdapter adapter = new ClienteRecyclerViewAdapter(clienteViewModel.getAllClientes().getValue());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ClienteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente cliente) {
                Intent intent = new Intent(ClientList.this, ClientEdit.class);
                intent.putExtra(UtilData.EXTRA_ID, cliente.getCustomerId());
                intent.putExtra(UtilData.EXTRA_NOMBRE, cliente.getNombre());
                intent.putExtra(UtilData.EXTRA_APELLIDO, cliente.getApellido());
                intent.putExtra(UtilData.EXTRA_DIRECCION, cliente.getDireccion());
                intent.putExtra(UtilData.EXTRA_TELEFONO, cliente.getTelefono());
                intent.putExtra(UtilData.EXTRA_ESTADO, cliente.getEstado());

                startActivityForResult(intent, UtilData.EDIT_NOTE_REQUEST);
            }
        });
    }


    private void fetch(){




        DataServiceGenerator DataServiceGenerator = new DataServiceGenerator();
        ClienteRequests service = DataServiceGenerator.createService(ClienteRequests.class);
        //get the value 3 of other request
        Call<List<Cliente>> call = service.getClientes(3);

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful())
                {
                    if (response != null){
                        List<Cliente> ClienteModelList = response.body();
                        //check if database has information
                        int Clientscount=clienteViewModel.getAllClientes().getValue().size();
                        if(ClienteModelList.size()==Clientscount)
                        {
                            Log.v(TAG, "Base de datos actualizada data ["+Clientscount+"] -["+ClienteModelList.size()+"]");
                        }
                        for (int i = 0; i < ClienteModelList.size(); i++)
                        {
                            int custumerID = ClienteModelList.get(i).getCustomerId();
                            String nombre= ClienteModelList.get(i).getNombre();
                            String apellido= ClienteModelList.get(i).getApellido();
                            String direccion= ClienteModelList.get(i).getDireccion();
                            String telefono= ClienteModelList.get(i).getTelefono();
                            String estado= ClienteModelList.get(i).getEstado();
                            Log.v(TAG, "recuperado: "+nombre+"-"+apellido+"-"+direccion+"-"+telefono+"**"+estado);
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
