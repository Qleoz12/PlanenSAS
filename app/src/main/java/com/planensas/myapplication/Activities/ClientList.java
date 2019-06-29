package com.planensas.myapplication.Activities;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.Entities.Estado;
import com.planensas.myapplication.MainActivity;
import com.planensas.myapplication.R;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.ViewModels.EstadosViewModel;
import com.planensas.myapplication.services.ClienteRequests;
import com.planensas.myapplication.services.DataServiceGenerator;
import com.planensas.myapplication.utils.AppVault;
import com.planensas.myapplication.utils.UtilData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientList extends BaseActivity{
    ClienteViewModel clienteViewModel;
    EstadosViewModel estadosViewModel;
    private static final String TAG = "ClientList-Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initViews();

        fetch();

    }

    public int getLayoutId() {
        return R.layout.activity_client_list;
    }

    public void initViews()
    {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ClienteRecyclerViewAdapter adapter = new ClienteRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        clienteViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);
        estadosViewModel = ViewModelProviders.of(this).get(EstadosViewModel.class);
        clienteViewModel.getAllClientes().observe( this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(@Nullable List<Cliente> clientes) {
                    adapter.setMclientes(clientes);
            }
        });

        adapter.setOnItemClickListener(new ClienteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente cliente) {
                Intent intent = new Intent(ClientList.this, ClientEdit.class);
                intent.putExtra(UtilData.EXTRA_ID, cliente.getCustomerId());
                startActivity(intent);
            }
        });

        adapter.setOnDeleteItemClickListener(new ClienteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente cliente) {
                clienteViewModel.delete(cliente);
            }
        });

        adapter.setOnStatusItemClickListener(new ClienteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente cliente) {
                estadosViewModel.getEstado(cliente.getEstado()).observe( ClientList.this, new Observer<Estado>() {

                    @Override
                    public void onChanged(@Nullable Estado estado) {
                        Toast.makeText(ClientList.this,"estado: "+estado.getEstado(),Toast.LENGTH_SHORT).show();
                    }
                });
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
                        //check if database has information
                        int Clientscount= ( clienteViewModel.getAllClientes().getValue() == null) ? 0 : clienteViewModel.getAllClientes().getValue().size();
                        if(Clientscount>0)
                        {
                            Log.v(TAG, "Base de datos actualizada data ["+Clientscount+"] -["+ClienteModelList.size()+"]");
                            return;
                        }
                        for (int i = 0; i < ClienteModelList.size(); i++)
                        {
                            int custumerID = ClienteModelList.get(i).getCustomerId();
                            String nombre= ClienteModelList.get(i).getNombre();
                            String apellido= ClienteModelList.get(i).getApellido();
                            String direccion= ClienteModelList.get(i).getDireccion();
                            String telefono= ClienteModelList.get(i).getTelefono();
                            int estado= ClienteModelList.get(i).getEstado();
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
